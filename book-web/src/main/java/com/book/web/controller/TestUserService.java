package com.book.web.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 测试一下springmvc和ehcache结合
 * @author liweihan
 *
 */
//@Service
//@CacheConfig(cacheNames="cachea")
public class TestUserService {
	
	private Map<Integer,String> usersData = new ConcurrentHashMap<Integer, String>();
	private static Logger logger = LoggerFactory.getLogger(TestUserService.class);
	
	public TestUserService() {
		logger.info(" ====== 用户初始化数据开始....");
		usersData.put(1, "韩超");
		usersData.put(2, "我的博客：http://blog.51cto.com/hanchaohan");
		usersData.put(3, "我的新博客：https://my.oschina.net/hanchao/blog");
		logger.info(" ====== 用户初始化数据结束...");
	}
	
	/**
	 * 注意：我们只缓存result不为NULL的结果
	 * @param userId
	 * @return
	 */
	@Cacheable(key="'user_' + #userId",unless="#result == null")
	public String get(int userId) {
		logger.info(" ====== 用户信息:" + usersData.get(userId));
		return usersData.get(userId);
	}
	
	/**
	 * 注意事项：
	 * 在此类的get2()方法内部,我们调用get()方法的试试,@Cacheable是不起作用的;
	 * 原因：是@Cacheable是基于SpringAOP代理类,get()方法是内部方法，
	 * 在get2()中直接调用get()方法时,是不走代理的!
	 * 
	 * @param userId
	 * @return
	 */
	public String get2(int userId) {
		logger.info(" get2 ====== 用户信息:" + usersData.get(userId));
		return get(userId);
	}

	
	/**
	 * 数据修改了,我们要更新缓存
	 * 注意事项：
	 * @CachePut：这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中。
	 * 
	 * @param userId
	 * @return
	 */
	@CachePut(key="'user_' + #userId")
	public String update(int userId,String value) {
		logger.info(" ====== update 用户信息:" + value);
		usersData.put(userId, value);
		return value;
	}
	
	/**
	 * 根据Id清除缓存
	 * @param userId
	 */
	@CacheEvict(key="'user_' + #userId")
	public void clearById(int userId) {
		logger.info(" ====== clear cache 用户Id:" + userId);
	}
	
	/**
	 * cachea中的所有缓存都被清除!
	 */
	@CacheEvict(allEntries=true)
	public void clearAll() {
		logger.info(" ====== clear all cache !");
	}
}
