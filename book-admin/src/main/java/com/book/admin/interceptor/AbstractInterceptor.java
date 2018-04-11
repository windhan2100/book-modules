package com.book.admin.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author liweihan
 *
 */
public abstract class AbstractInterceptor implements HandlerInterceptor{
	
	private static Logger logger = LoggerFactory.getLogger(AbstractInterceptor.class);
	
	//不需要拦截的链接
	protected static List<String> excludeActionList = new ArrayList<String>();
	
	static {
        excludeActionList.add("^/(login|static)(/)?(.+)?$");
        excludeActionList.add("^/(flush|test|site_map)(/)?(.+)?$");     //redis data flush
        excludeActionList.add("^/app/(flush|info.json|apkinfo.json)(/)?(.+)?$");    //前端接口http://m.tv.sohu.com/app
        excludeActionList.add("^/(hikeapp)(/)?(.+)?$");        //需要拉起客户端的专辑数据访问接口
        excludeActionList.add("^/(cooperation|activity|api|open|mobile|mb)(/)?(.+)?$");
        excludeActionList.add("^/(activity|api|open|mobile|mb)(/)?(.+)?$");
        excludeActionList.add("^/(test)(/)?(.+)?$");
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		logger.debug(" ====== prehandle !");
//		logger.info(" ======= URI:{}",request.getRequestURI());
		request.setAttribute("uri", request.getRequestURI());//为了突出显示选中的链接
		for(String excludeUrl : excludeActionList) {
			if(Pattern.matches(excludeUrl, request.getRequestURI())) {
				return true;
			}
		}
		return innerPreHandle(request, response, handler);
	}
	
	protected abstract boolean innerPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		logger.debug(" ====== postHandle !");
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		logger.debug(" ====== afterCompletion !");
	}
}
