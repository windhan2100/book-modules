package com.book.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author liweihan
 *
 */
public class CookieUtil {
	
	/**
	 * 检查cookie是否启用
	 * @param request
	 * @return
	 */
	public static boolean checkCookieEnable(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据KEY获得cookie里的值
	 * @param Key
	 * @param request
	 * @return
	 */
	public static String getCookieValue(String Key, HttpServletRequest request) {
		// 获得cookie集合
		Cookie[] cookies = request.getCookies();

		String value = "";

		// 获得指定的cookie值
		if (cookies != null) {
			// Cookie种子
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equalsIgnoreCase(Key)) {
					value = c.getValue();
					break;
				} else {
					value = "";
				}
			}
		} else {
			return "cookie Null";
		}
		return value;
	}
	
	/**
	 * 设置cookei值
	 * @param Key
	 * @param value
	 * @param dmain
	 * @param response
	 */
	public static void setCookieValue(String key, String value, String dmain, int maxAge, HttpServletResponse response){
		//设置用户角色cookie
		Cookie cookie = new Cookie(key,value);
		cookie.setDomain(dmain);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}
	
	/**
	 * 添加一个新cookie
	 * @param response
	 * 			HttpServletResponse
	 * @param cookieName
	 * 			cookie的名称
	 * @param cookieValue
	 * 			cookie的值
	 * @param domain
	 * 			cookie所属的子域
	 * @param httpOnly
	 * 			是否将cookie设置成httpOnly
	 * @param maxAge
	 * 			设置cookie的最大生存期
	 * @param path
	 * 			设置cookie的路径
	 * @param secure
	 * 			是否只允许https访问
	 */
	public static void addCookie(
				HttpServletResponse response, String cookieName, 
				String cookieValue, String domain,
	            boolean httpOnly, int maxAge, String path, boolean secure) {
	        if (cookieName != null && !cookieName.equals("")) {
	            if (cookieValue == null) {
	            	cookieValue = "";
	            }
	 
	            Cookie newCookie = new Cookie(cookieName, cookieValue);
	            if (domain != null) {
	            	newCookie.setDomain(domain);
	            }
	 
	            newCookie.setHttpOnly(httpOnly);
	 
	            if (maxAge > 0) {
	            	newCookie.setMaxAge(maxAge);
	            }
	 
	            if (path == null) {
	            	newCookie.setPath("/");
	            }  else {
	            	newCookie.setPath(path);
	            }
	 
	            newCookie.setSecure(secure);
	            addCookie(response, newCookie);
	        }
	    }
	
	  /**
	   * 添加一个新cookie
	   * 
	   * @param response
	   * 			 HttpServletResponse
	   * @param cookie
	   * 			新cookie
	   */        
	  public static void addCookie(HttpServletResponse response, Cookie cookie) {
	        if (cookie != null) {
	        	response.addCookie(cookie);
	        }
	  }

	 /**
	  * 添加一个新cookie
	  * 
	  * @param response
	  *       	 HttpServletResponse
	  * @param cookieName
	  *       	 cookie名称
	  * @param cookieValue
	  *       	 cookie值
	  * @param domain
	  * 		   cookie所属的子域
	  */
	 public static void addCookie(
			 HttpServletResponse response, String cookieName, 
			 String cookieValue, String domain) {
	        addCookie(response, cookieName, cookieValue, domain, true, Constants.COOKIE_MAX_AGE, "/", false);
	 }
	 
	/**
	 * 根据cookie的名称获取对应的Cookie
	 *  
	 * @param request
	 * 			 HttpServletRequest	
	 * @param cookieName
	 * 			 cookie名称
	 * @return  对应cookie，如果不存在则返回null
	 */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
 
        if (cookies == null || cookieName == null || cookieName.equals(""))
            return null;
 
        for (Cookie c : cookies) {
            if (c.getName().equals(cookieName)) {
            	return (Cookie) c;
            }
        }
        return null;
    }
    
    /**
     * 删除指定的Cookie
     * 
     * @param response
     * 			HttpServletResponse
     * @param cookie
     * 			  待删除cookie
     */
    public static void delCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath("/"); 
            cookie.setMaxAge(0);
            cookie.setValue(null);
 
            response.addCookie(cookie);
        }
    }
    
    /**
     * 根据cookie的名称删除指定的cookie
     * 
     * @param request
     * 			 HttpServletRequest
     * @param response
     * 			 HttpServletResponse
     * @param cookieName
     * 			 待删除cookie名
     */
    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie c = getCookie(request, cookieName);
        if (c != null && c.getName().equals(cookieName)) {
            delCookie(response, c);
        }
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param domain
     */
    public static void editCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
            String cookieValue,String domain) {
        Cookie c = getCookie(request, cookieName);
        if (c != null && cookieName != null && !cookieName.equals("") && c.getName().equals(cookieName)) {
            addCookie(response, cookieName, cookieValue, domain);
        }
    }
}
