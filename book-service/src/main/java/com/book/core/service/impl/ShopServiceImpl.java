package com.book.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.core.mapper.ShopMapper;
import com.book.core.model.Shop;
import com.book.core.model.ShopExample;
import com.book.core.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopMapper shopMapper;

	@Override
	public List<Shop> getAll() {
		ShopExample example = new ShopExample();
		return shopMapper.selectByExample(example);
	}

}
