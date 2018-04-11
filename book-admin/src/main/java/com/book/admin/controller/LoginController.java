package com.book.admin.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.book.core.model.User;
import com.book.core.service.UserService;
import com.book.core.utils.Constants;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserService userService;
	
	/**
	 * 默认去登陆页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "",method = {RequestMethod.GET,RequestMethod.POST})
	public String index(HttpServletRequest request,HttpServletResponse response) {
		
		String src = request.getParameter("src");
		try {
			User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
			if (user != null) {
				request.setAttribute("userName", user.getName());
				request.setAttribute("password", user.getPassword());
			}
			
			if (StringUtils.isNotBlank(src)) {
				String oriUrl = URLDecoder.decode(src,"UTF-8");
				request.setAttribute("src", oriUrl);
			}
		} catch (Exception e) {
			logger.error(" ====== error login/ ",e);
			e.printStackTrace();
		}
		return "login";
	}
	
	/**
	 * 登录验证
	 * 
	 * @param username
	 * 			用户名
	 * @param password
	 * 			密码
	 * @param remember
	 * 			是否记住我
	 * @param request
	 * 			HttpServletRequest
	 * @param response
	 * 			HttpServletResponse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/validate.json",method = RequestMethod.POST)
	public Map<String, Object> validate(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(value = "remember",required = false,defaultValue="false") Boolean remember,
			HttpServletRequest request,HttpServletResponse response) {
		
		logger.info(" ====== username:{},password:{},remember:{}",username,password,remember);
		Map<String, Object> map = new HashMap<String,Object>();
		
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		
		User resultUser = userService.adminlogin(user, remember, response);
		if (resultUser != null) {
			//登录成功后把用户信息放入session
			WebUtils.setSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY, resultUser);
			
			map.put("code", 1);
			map.put("msg", "ok");
		} else {
			map.put("code", 0);
			map.put("msg", "用户名或密码错误!");
		}
		return map;
	}
	
	/**
	 * 退出登录
	 * 
	 * @param request
	 * 			HttpServletRequest
	 * @param response
	 * 			HttpServletResponse
	 */
	@RequestMapping(value = "/out",method = {RequestMethod.GET,RequestMethod.POST})
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		userService.adminLogout(request, response);
		try {
			response.sendRedirect("/login");
		} catch (Exception e) {
			logger.error(" ====== adminLogout error !!",e);
		}
	}

}
