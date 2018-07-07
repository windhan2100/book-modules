package com.book.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 处理首页
 * @author liweihan
 *
 */
@Controller
public class IndexController extends BaseController {

	/**
	 * 本来是在springmvc-servlet.xml中配置的。
	 * 但是要区分一下手机版和PC版,所以单独出来一个servlet
	 * <mvc:view-controller path="/index" view-name="main"/>
	   <mvc:view-controller path="/" view-name="main"/>
	 */
	
	@RequestMapping(value = "/index",method = {RequestMethod.GET,RequestMethod.POST})
	public String index(HttpServletRequest request,HttpServletResponse response) {
		return getReturnStr(request, "main");
	}

	@RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
	public String index2(HttpServletRequest request,HttpServletResponse response) {
		return getReturnStr(request, "main");
	}
}
