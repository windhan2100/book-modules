package com.book.core.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.core.model.User;

public interface UserService {
	
	/**
	 * 登录
	 * 
	 * @param user
	 * 			登录用户信息
	 * @param remember
	 * 			是否记住用户
	 * @param response
	 * 			HttpServletResponse
	 * @return	根据用户传递的信息在数据库中查找到用户的详情
	 */
	User adminlogin(User user,boolean remember,HttpServletResponse response);
	
	/**
	 * 退出登录
	 * 
	 * @param request
	 * 			HttpServletRequest
	 * @param response
	 * 			HttpServletResponse
	 */
	public void adminLogout(HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 根据用户名获取用户对象
	 * 
	 * @param name
	 * 			用户名
	 * @return
	 */
	User getUserByName(String name);
	
	/**
	 * 增加新对象
	 * 
	 * @param user
	 * 			新用户
	 * @return
	 */
	int add(User user);
	
	/**
	 * 根据用户名删除对象
	 * @param name
	 * 			用户名
	 * @return
	 */
	int delByName(String name);

	/**
	 * 根据用户ID修改用户信息
	 * @param user
	 * 			用户对象
	 * @param oldName
	 * 			用户查找adminRight对象,修改adminRight对象的name
	 * @return
	 */
	int updateUserById(User user,String oldName);
	
	/**
	 * 更加ID获取对象
	 * @param id
	 * 			user表的ID
	 * @return
	 */
	User getUserById(Integer id);
}
