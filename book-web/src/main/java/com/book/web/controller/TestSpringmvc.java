package com.book.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * springmvc和ehcache结合
 * @author liweihan
 *
 */
//@Controller
//@RequestMapping("/cachet")
public class TestSpringmvc {
	
	@Autowired
	TestUserService testUserService;
	
	private static Logger logger = LoggerFactory.getLogger(TestSpringmvc.class);
	
	/**
	 * 获取用户信息:直接调用缓存方法
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get/{id}")
	public String getUser(@PathVariable(value = "id") int id) {
		System.getProperty("java.io.tmpdir") ;
		logger.info(" ----- 用户Id:{},{}",id,System.getProperty("java.io.tmpdir"));
		return testUserService.get(id);
	}
	
	/**
	 * 获取用户信息2：间接获取缓存方法
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get2/{id}")
	public String getUser2(@PathVariable(value = "id") int id) {
		logger.info(" get2 ----- 用户Id:{}",id);
		return testUserService.get2(id);
	}

	/**
	 * 更新用户信息:同时更新缓存!
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update/{id}")
	public String updateUser(@PathVariable(value = "id") int id) {
		logger.info(" update ----- 用户Id:{}",id);
		testUserService.update(id,"更新的内容"+id);
		return "OK";
	}
	
	/**
	 * 根据Id清除缓存
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/clear/{id}")
	public String clearUserCacheById(@PathVariable(value = "id") int id) {
		testUserService.clearById(id);
		return "OK";
	}
	
	/**
	 * 清除所有缓存
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/clearall")
	public String clearAll() {
		testUserService.clearAll();
		return "clearAll";
	}
}
