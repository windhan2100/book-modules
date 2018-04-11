package com.book.core.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.core.mapper.OrdersMapper;
import com.book.core.model.Orders;
import com.book.core.model.OrdersExample;
import com.book.core.service.OrdersService;
@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public int add(Orders order) {
		return ordersMapper.insertSelective(order);
	}

	@Override
	public List<Orders> getAll(OrdersExample example) {
		return ordersMapper.selectByExample(example);
	}

	@Override
	public Orders getOrdersById(Long id) {
		return ordersMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(Orders orders) {
		return ordersMapper.updateByPrimaryKeySelective(orders);
	}

	@Override
	public int delById(Long id) {
		return ordersMapper.deleteByPrimaryKey(id);
	}

	@Override
	public long count(OrdersExample example) {
		return ordersMapper.countByExample(example);
	}

	@Override
	public BigDecimal sumPrice(OrdersExample example) {
		return ordersMapper.sumPayByExample(example);
	}

}
