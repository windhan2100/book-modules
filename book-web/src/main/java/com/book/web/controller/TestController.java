package com.book.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.core.mapper.TestOrderMapper;
import com.book.core.model.TestOrderExample;
import com.book.core.model.User;
import com.book.core.service.TestService;
import com.book.web.test.hystrix.CommandHelloWorld4;

@Controller
public class TestController {
	
	@Autowired
	TestService testService;
	@Autowired
	TestOrderMapper testOrderMapper;
	
	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/test")
	public String test() {
		
		logger.info(" ====== log4j2 test ... ");
		
		testService.testService();
//		System.out.println(" test ---- :" + Test.JUKKA_DRAMA_BASE_INFO_KEY);

		/**
		 * 查询所有的数量
		 */
		TestOrderExample example = new TestOrderExample();
		Long count = testOrderMapper.countByExample(example);
		System.out.println(" num : " + count);
		
		
		return "test";
	}
	
	public static void main(String[] args) {
		
		System.out.println(" ======= test begin");
		
		SimpleDateFormat simpleDateFormate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		SimpleDateFormat simpleDateFormate = new SimpleDateFormat("HH:mm"); 
		
		int add_minute = 30;
		int begin_hour = 8;
		int begin_minute = 0;
		int begin_second = 0;
		
		int end_hour = 18;
		int end_minute = 45;
		int end_second = 0;
		
		//创建一个日历对象
		Calendar calendar = Calendar.getInstance();
		//初始化日历对象
		calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR); 
        int month = calendar.get(Calendar.MONTH) + 1; 
        int date = calendar.get(Calendar.DAY_OF_MONTH); 
        
        calendar.set(year, month, date, begin_hour, begin_minute,begin_second);
		System.out.println(" 开始 ==== : " + simpleDateFormate.format(calendar.getTime()));
		
		//结束时间
		Calendar endCal = Calendar.getInstance();
		endCal.set(year, month, date, end_hour, end_minute,end_second);
		System.out.println(" 结束 -----  : " + simpleDateFormate.format(endCal.getTime()));
		
		while (true) {
			if (calendar.getTimeInMillis()>endCal.getTimeInMillis()) {
				System.out.println("over");
				break;
			} else {
				System.out.println(" ==== : " + simpleDateFormate.format(calendar.getTime()));
				calendar.add(Calendar.MINUTE, add_minute);
			}
		}
		
		
		//对天数的控制
		Calendar calDay = Calendar.getInstance();
		//初始化日历对象
		calDay.setTime(new Date());
		calDay.add(Calendar.MONTH, 2);
		
		System.out.println(" ===== " + simpleDateFormate2.format(calDay.getTime()));
		
	}
	
}
