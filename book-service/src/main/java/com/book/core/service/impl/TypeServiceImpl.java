package com.book.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.core.mapper.TypeMapper;
import com.book.core.model.Type;
import com.book.core.model.TypeExample;
import com.book.core.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{
	
	@Autowired
	private TypeMapper typeMapper;

	@Override
	public List<Type> getAll() {
		TypeExample example = new TypeExample();
		return typeMapper.selectByExample(example);
	}

}
