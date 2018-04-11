package com.book.web.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.core.model.User;

/**
 * ehcache测试类
 * @author liweihan
 *
 */
//@Controller
//@RequestMapping("/cache")
public class TestEhCacheController {
	
	@Autowired
	private CacheManager cacheManager;
	
	private static Logger logger = LoggerFactory.getLogger(TestEhCacheController.class);
	
	/**
	 * 测试：放入缓存和取数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/testa")
	public String testA() {
		//获取testa缓存容器
		Cache cache = cacheManager.getCache("cachea");
		User user = new User();
		user.setId(1);
		user.setName("hanchao");
		
		//将数据放入缓存
		cache.put(1,user);
		
		//获取数据
		//方法1
		User user2 = (User) cache.get(1).get();
		logger.info("result1:" +  user2.getName());
		
		//方法2
		User user3 = cache.get(1, User.class);
		logger.info("result2:" + user3.getName());
		
		return "a";
	}

	/**
	 * 测试取数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/testb")
	public String testB() {
		//获取testa缓存容器
		Cache cache = cacheManager.getCache("cachea");
		
		//获取数据
		//方法1
//		User user2 = (User) cache.get(1).get();
//		logger.info("result1:" + user2.getName());
		
		//方法2
		/*User user3 = cache.get(1, User.class);
		logger.info("result2:" +  user3.getName());*/
		
		//☆☆注意事项：key的类型必须也一样！☆☆
/*		User user = (User) cache.get("1").get();
		logger.info("类型不一样不行:" + user.getName());*/
		
		//方法3：避免获取失效缓存时出现异常的情况
		ValueWrapper valueWrapper = cache.get(1);
		if (valueWrapper != null) {
			User user = (User)valueWrapper.get();
			logger.info("result3:" +  user.getName());
		}
		
		return "b";
	}
	
	/**
	 * 测试删除数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/testc")
	public String testC() {
		//获取缓存容器
		Cache cache = cacheManager.getCache("cachea");
		cache.evict(1);
		return "c";
	}
}
