package com.book.core.utils;

/**
 * 公用的常量
 * @author liweihan
 *
 */
public class Constants {
	
	// cookie的有效期默认为30天
    public final static int COOKIE_MAX_AGE = 60 * 60 * 24 * 30; 
    //cookie加密时的额外的salt
    public final static String salt = "www.hanchao.cn";
    //自动登录的Cookie名
    public final static String RememberMe_Admin = "remember-me-admin";
    //admin存放session的用户key
    public static String ADMIN_SESSION_USER_KEY = "admin_user";

}
