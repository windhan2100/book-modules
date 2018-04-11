package com.book.core.service;

import java.util.List;

import com.book.core.model.Shop;

/**
 * 店铺管理Service
 * @author liweihan
 *
 */
public interface ShopService {
	
	/**
	 * 查找所有分店
	 * @return
	 */
	List<Shop> getAll();

}
