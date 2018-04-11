package com.book.core.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.book.core.mapper.TestOrderMapper;
import com.book.core.model.TestOrderExample;

public class TestA {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:*.xml");
		context.start();
		
		
		TestOrderMapper testOrderMapper = context.getBean(TestOrderMapper.class);
		
		TestOrderExample example = new TestOrderExample();
		Long count = testOrderMapper.countByExample(example);
		System.out.println(" ====== : test:数据库所有的数量:{}"+count);
	}

}
