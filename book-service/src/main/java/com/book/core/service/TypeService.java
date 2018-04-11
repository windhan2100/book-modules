package com.book.core.service;

import java.util.List;

import com.book.core.model.Type;

/**
 * 套餐类型管理的service
 * @author liweihan
 *
 */
public interface TypeService {
	
	/**
	 * 查找所有的套餐
	 * @return
	 */
	List<Type> getAll();

}
