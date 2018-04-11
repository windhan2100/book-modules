package com.book.core.service;

import java.util.List;

import com.book.core.model.PayType;

/**
 * 支付类型管理
 * @author liweihan
 *
 */
public interface PayTypeService {
	
	/**
	 * 获取所有的支付方式
	 * @return
	 */
	List<PayType> getAll();

}
