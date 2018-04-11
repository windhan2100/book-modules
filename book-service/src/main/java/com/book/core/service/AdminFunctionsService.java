package com.book.core.service;

import java.util.List;

import com.book.core.model.AdminFunctions;

/**
 * 用户具体权限名称和URL维护的service
 * @author liweihan
 *
 */
public interface AdminFunctionsService {
	
	/**
	 * 获取所有有效的链接对象
	 * @return
	 */
	List<AdminFunctions> getAll();
	
	List<AdminFunctions> getObjByIds(List<Integer> listIds);
	
	/**
	 * 添加菜单
	 * @param adminFunctions
	 * @return
	 */
	int addFunction(AdminFunctions adminFunctions);
	
	/**
	 * 根据ID删除菜单
	 * @param id
	 * 			菜单ID
	 * @return
	 */
	int delFunction(Integer id);

	/**
	 * 通过ID获取对象
	 * @param id
	 * 			通过ID获取对象
	 * @return
	 */
	AdminFunctions getObjById(Integer id);
	
	/**
	 * 修改菜单
	 */
	int updateObj(AdminFunctions adminFunctions);
}
