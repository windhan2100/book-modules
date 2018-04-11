package com.book.admin.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import com.book.core.model.AdminFunctions;
import com.book.core.model.AdminRight;
import com.book.core.model.User;
import com.book.core.service.AdminFunctionsService;
import com.book.core.service.AdminRightService;
import com.book.core.utils.Constants;

/**
 * 用户权限过滤
 * @author liweihan
 *
 */
public class FunctionsInterceptor extends AbstractInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(FunctionsInterceptor.class);
	
	@Autowired
	private AdminRightService adminRightService;
	@Autowired
	private AdminFunctionsService adminFunctionsService;
	
	//登录后不需要拦截的链接
	protected static List<String> excludeActionList = new ArrayList<String>();
	
	static {
        excludeActionList.add("^/(index|admin/user|admin/myinfo)(/)?(.+)?$");
        excludeActionList.add("^/(book/del|book/detail.json|book/addorupdate)(/)?(.+)?$");
	}

	@Override
	protected boolean innerPreHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try {
			User user = (User) WebUtils.getSessionAttribute(request, Constants.ADMIN_SESSION_USER_KEY);
			if (user == null) {
				response.sendRedirect("/login");
				return false;
			}
			
			//查找该用户的权限
			AdminRight adminRight = adminRightService.getObjByUserName(user.getName());
			if (adminRight == null) {
				return true;
			}
			
			List<AdminFunctions> menus = null;
			if (adminRight.getIsAdmin() == 1) {
				menus = adminFunctionsService.getAll(); 
				
				request.setAttribute("menus", menus);
				request.setAttribute("isAdmin", adminRight.getIsAdmin());
				return true;
			} else {
				String right = adminRight.getRights();
				if (StringUtils.isNotBlank(right)) {
					String[] rs = right.split(",");
					List<Integer> listId = null;
					
					if (rs != null && rs.length > 0) {
						listId = new ArrayList<Integer>();
						for (int i = 0; i < rs.length; i++) {
							if (StringUtils.isNotBlank(rs[i])) {
								listId.add(Integer.valueOf(rs[i]));
							}
						}
					}
					
					//查询
					menus = adminFunctionsService.getObjByIds(listId);
				}
				for(String excludeUrl : excludeActionList) {
					if(Pattern.matches(excludeUrl, request.getRequestURI())) {
						request.setAttribute("menus", menus);
						request.setAttribute("isAdmin", adminRight.getIsAdmin());
						return true;
					}
				}
				//对权限进行过滤，不能输入URL就可以访问
				if (menus != null && menus.size() > 0) {
					for(AdminFunctions adminFunctions : menus) {
						if (request.getRequestURI().startsWith(adminFunctions.getUrl())) {
							logger.info(" ====== request.getRequestURI():{},table-url:{}",request.getRequestURI(),adminFunctions.getUrl());
							request.setAttribute("menus", menus);
							request.setAttribute("isAdmin", adminRight.getIsAdmin());
							return true;
						}
					}
				}
			}
			response.sendRedirect("/login");
			return false;
		} catch (Exception e) {
			logger.error(" ====== get AdminRight error!",e);
			e.printStackTrace();
		}
		
		return false;
	}

}
