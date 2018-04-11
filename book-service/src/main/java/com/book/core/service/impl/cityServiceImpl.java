package com.book.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.core.mapper.CityMapper;
import com.book.core.model.City;
import com.book.core.model.CityExample;
import com.book.core.service.CityService;

@Service
public class cityServiceImpl implements CityService{
	
	@Autowired
	private CityMapper cityMapper;

	@Override
	public List<City> getAll() {
		CityExample example = new CityExample();
		return cityMapper.selectByExample(example);
	}

}
