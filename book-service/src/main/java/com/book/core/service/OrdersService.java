package com.book.core.service;

import java.math.BigDecimal;
import java.util.List;

import com.book.core.model.Orders;
import com.book.core.model.OrdersExample;

/**
 * 预订订单管理Service
 * @author liweihan
 *
 */
public interface OrdersService {
	
	/**
	 * 插入数据
	 * @param order
	 * @return
	 */
	int add(Orders order);
	
	/**
	 * 获取满足条件的所有的订单
	 * @param example
	 * @param orders
	 * @return
	 */
	List<Orders> getAll(OrdersExample example);
	
	/**
	 * 获取满足条件的订单个数
	 * @param example
	 * @return
	 */
	long count(OrdersExample example);
	
	/**
	 * 统计实际支付的价格总和
	 * @param example
	 * @return
	 */
	BigDecimal sumPrice(OrdersExample example);
	
	/**
	 * 根据ID获取对象
	 * @param id
	 * @return
	 */
	Orders getOrdersById(Long id);
	
	/**
	 * 以ID为添加修改订单
	 * @param orders
	 * @return
	 */
	int updateById(Orders orders);
	
	/**
	 * 根据订单ID删除订单
	 * @param id
	 * @return
	 */
	int delById(Long id);

}
