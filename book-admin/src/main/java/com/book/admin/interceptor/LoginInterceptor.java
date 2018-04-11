package com.book.admin.interceptor;

import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import com.book.core.model.PersistentLogins;
import com.book.core.model.User;
import com.book.core.service.PersistentLoginsService;
import com.book.core.service.UserService;
import com.book.core.utils.Constants;
import com.book.core.utils.CookieUtil;
import com.book.core.utils.EncryptionUtil;


/**
 * 登录验证拦截器
 * @author liweihan
 *
 */
public class LoginInterceptor extends AbstractInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	@Autowired
	private PersistentLoginsService persistentLoginsService;
	@Autowired
	private UserService userService;

	@Override
	protected boolean innerPreHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
		if (user != null) {
			//已登录
			return true;
		} else {
			//从cookie中取值
			Cookie cookie = CookieUtil.getCookie(request, Constants.RememberMe_Admin);
			if (cookie != null) {
				String cookieValue = EncryptionUtil.base64Decode(cookie.getValue());
				String[] cValues = cookieValue.split(":");
				if (cValues.length == 2) {
					String userNameByCookie = cValues[0];//获取用户名
					String uuidByCookie = cValues[1];//获取UUID值
					
					//到数据库中查询自动登录记录
					PersistentLogins pLogins  = persistentLoginsService.getObjByUUID(uuidByCookie);
					if (pLogins != null) {
						String savedToken = pLogins.getToken();
						
						//获取有效时间
						Date savedValidTime = pLogins.getValidTime();
						Date currentTime = new Date();
						
						//如果还在有效期内,记录判断是否可以自动登录
						if (currentTime.before(savedValidTime)) {
							User u = userService.getUserByName(userNameByCookie);
							if (u != null) {
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(savedValidTime);
								
								// 精确到分的时间字符串
								String timeString = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH)
										+ "-" + calendar.get(Calendar.DAY_OF_MONTH) + "-"
										+ calendar.get(Calendar.HOUR_OF_DAY) + "-" + calendar.get(Calendar.MINUTE);
								// 为了校验而生成的密文
								String newToken = EncryptionUtil.sha256Hex(u.getName() + "_" + u.getPassword() + "_"
										+ timeString + "_" + Constants.salt);
								
								// 校验sha256加密的值，如果不一样则表示用户部分信息已被修改，需要重新登录
								if (savedToken.equals(newToken)) {
									//为了提高安全性，每次登录之后都更新自动登录的cookie值
									String uuidNewString = UUID.randomUUID().toString();
									String newCookieValue = EncryptionUtil.base64Encode(u.getName() + ":" + uuidNewString);
									CookieUtil.editCookie(request, response, Constants.RememberMe_Admin, newCookieValue, null);
									
									//同时更新数据
									pLogins.setSeries(uuidNewString);
									pLogins.setUpdateTime(new Date());
									persistentLoginsService.updateByObj(pLogins);
									
									//将用户加到session中，不退出浏览器时只需要判断session即可
									WebUtils.setSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY, u);
									
									//校验成功,此次拦截操作完成
									return true;
								} else {
									//用户信息部分被修改，删除cookie并清空数据库中的记录
									CookieUtil.delCookie(response, cookie);
									persistentLoginsService.delObjById(pLogins.getId());
								}
							}
						} else {
							// 超过保存的有效期，删除cookie并清空数据库中的记录
							CookieUtil.delCookie(response, cookie);
							persistentLoginsService.delObjById(pLogins.getId());
						}
					}
				}
			}
			
			try {
				response.sendRedirect("/login?src=" + URLEncoder.encode(request.getRequestURI(), "UTF-8"));
			} catch (Exception e) {
				logger.error(" ===== loginInterceptor error ,url:{}{}",request.getRequestURL(),request.getRequestURI(),e);
			} 
			return false;
		}
	}

}
