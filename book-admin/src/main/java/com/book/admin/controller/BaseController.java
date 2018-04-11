package com.book.admin.controller;

import javax.servlet.http.HttpServletRequest;

import com.book.core.utils.MobileCheck;

public class BaseController {
	
	/**
	 * 根据用户使用环境返回手机版页面还是PC版页面
	 * eg : PC时返回main ,手机版返回：main_mobile
	 * @param request
	 * @param response
	 * @return
	 */
	public String getReturnStr(HttpServletRequest request,String pcUrl) {
		String user_agent = request.getHeader("user-agent");
		if (user_agent == null) {
			user_agent = "";
		} else {
			user_agent = user_agent.toLowerCase();
		}
		
		if (MobileCheck.check(user_agent)) {
			//移动端
			return pcUrl + "_mobile";
		} else {
			//PC
			return pcUrl;
		}
	}
	
	/**
	 * 判断是否是移动端
	 * @param request
	 * @return
	 */
	public boolean isMobile(HttpServletRequest request) {
		String user_agent = request.getHeader("user-agent");
		if (user_agent == null) {
			user_agent = "";
		} else {
			user_agent = user_agent.toLowerCase();
		}
		
		if (MobileCheck.check(user_agent)) {
			//移动端
			return true;
		} else {
			//PC
			return false;
		}
	}

}
