package com.book.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.core.mapper.PayTypeMapper;
import com.book.core.model.PayType;
import com.book.core.model.PayTypeExample;
import com.book.core.service.PayTypeService;

@Service
public class PayTypeServiceImpl implements PayTypeService{

	@Autowired
	private PayTypeMapper payTypeMapper;
	
	@Override
	public List<PayType> getAll() {
		PayTypeExample example = new PayTypeExample();
		return payTypeMapper.selectByExample(example);
	}

}
