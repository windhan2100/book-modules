package com.book.core.service;

import com.book.core.model.PersistentLogins;

public interface PersistentLoginsService {

	/**
	 * 通过uuid获取persistentLogins对象
	 * 
	 * @param uuid
	 * 			UUID的值
	 * @return
	 */
	PersistentLogins getObjByUUID(String uuid);
	
	/**
	 * 更新对象
	 * 
	 * @param persistentLogins
	 * 				已经更新的对象
	 * @return
	 */
	int updateByObj(PersistentLogins persistentLogins);
	
	/**
	 * 根据ID删除数据库记录
	 * 
	 * @param id
	 * 			ID
	 * @return
	 */
	int delObjById(int id);
	
	/**
	 * 根据用户删除对象
	 * @param userName
	 * 			用户名
	 * @return
	 */
	int delObjByUserName(String userName);
}
