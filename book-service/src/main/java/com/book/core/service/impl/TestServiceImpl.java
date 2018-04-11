package com.book.core.service.impl;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.book.core.mapper.TestOrderMapper;
import com.book.core.model.TestOrderExample;
import com.book.core.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Resource
	TestOrderMapper testOrderMapper;

	@Override
	public void testService() {
		
		TestOrderExample example = new TestOrderExample();
		Long count = testOrderMapper.countByExample(example);
		logger.debug(" =======  test-order have {} line  " , count);
		logger.debug(" ====== book.service test logger ... ");
	}

}
