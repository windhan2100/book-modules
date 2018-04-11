package com.book.core.service;

import java.util.List;

import com.book.core.model.AdminRight;

/**
 * 用户权限service
 * @author liweihan
 *
 */
public interface AdminRightService {

	/**
	 * 通过用户名获取用户权限对象
	 * @param userName
	 * 			用户名
	 * @return
	 * 			AdminRight
	 */
	AdminRight getObjByUserName(String userName);
	
	/**
	 * 获取所有分配权利的用户
	 * @return
	 */
	List<AdminRight> getAll();
	
	/**
	 * 添加新对象【】
	 * @param adminRight
	 * 			新对象
	 * @return
	 */
	int add(AdminRight adminRight) throws Exception;
	
	/**
	 * 通过AdminRight的ID获取对象
	 * @param id
	 * 			AdminRight的ID
	 * @return
	 */
	AdminRight getObiById(Integer id);
	
	/**
	 * 根据AdminRight的I的删除对应的对象
	 * @param id
	 * 			AdminRight的ID
	 * @return
	 */
	int delById(Integer id);
	
	/**
	 * 根据adminRigh的IDt更新对象的相关信息
	 * @param adminRight
	 * 			参数对象
	 * @return
	 */
	int updateObjById(AdminRight adminRight);
	
}
