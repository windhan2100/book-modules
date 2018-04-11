package com.book.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.book.core.model.User;
import com.book.core.serializable.SerializationUtil;

/**
 * Ehcache实际使用
 * @author liweihan
 *
 */
//@Controller
//@RequestMapping("/c")
public class TestEhcache2Controller {
	
	private static Logger logger = LoggerFactory.getLogger(TestEhcache2Controller.class);
	@Autowired
	private LocalEhCache localEhcache;
	
	private static final String TEST_SUFF = "T_%s";
	
	@ResponseBody
	@RequestMapping("/get/{id}")
	public String get1(@PathVariable(value = "id") int id) {
		logger.info(" ----- 用户Id:{},{}",
				id,System.getProperty("java.io.tmpdir"));
		
		//字符串
		String key =  String.format(TEST_SUFF,1);
		localEhcache.put(key, "Test");
		logger.info(" ----- 获取缓存数据:key:{},value:{}",
				key,localEhcache.get(key, String.class));
		
		//整型
		String key2 = String.format(TEST_SUFF, 2);
		localEhcache.put(key2, 2);
		logger.info(" ----- 获取缓存数据:key:{},value:{}",
				key2,localEhcache.get(key2, Integer.class));
		
		//对象
		User user = new User();
		user.setId(1);
		user.setName("韩超");
		user.setEmail("hanchaohan@126.com");
		String key3 = String.format(TEST_SUFF, 3);
		long begin3 = System.currentTimeMillis();
		localEhcache.put(key3, user);
		logger.info(" ----- 获取缓存数据:key:{},value:{},time:{}",
				key3,localEhcache.get(key3, User.class).getName()
				,(System.currentTimeMillis()-begin3));
		
		//JSON对象
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("user", "hanchao");
		jsonObject.put("blog", "https://my.oschina.net/hanchao/blog");
		String key4 = String.format(TEST_SUFF, 4);
		localEhcache.put(key4,jsonObject);
		logger.info(" ----- 获取缓存数据:key:{},value:{}",
				key4,localEhcache.get(key4,JSONObject.class).get("blog"));
		
		//序列化对象-注意：不是所有的数据都需要序列化的！比如JSON对象，字符串,等没有必要序列化
		//关于序列化参考一下：http://blog.51cto.com/hanchaohan/1962798
		//因为Ehcache一般作为第一层的本地化缓存,究竟要不要序列化,具体项目具体考虑效率，内存等！
		String key5 = String.format(TEST_SUFF, 5);
		try {
			long begin5 = System.currentTimeMillis();
			localEhcache.put(key5, SerializationUtil.object2Bytes(user));
			byte[] obj = localEhcache.get(key5, byte[].class);
			User result = (User)SerializationUtil.bytes2Object(obj);
			logger.info(" ----- 获取缓存数据:key:{},value:{},time:{}",
					key5,result.getName(),(System.currentTimeMillis() - begin5));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "OK";
	}
	
	/**
	 * 根据获取
	 * @param key
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/get2/{key}")
	public String get2(@PathVariable(value = "key") String key) {
		String key1 =  String.format(TEST_SUFF,1);
		logger.info(" ----- 获取缓存数据:key:{},value:{}",
				key,localEhcache.get(key1, String.class));
		
		String key4 = String.format(TEST_SUFF, 4);
		logger.info(" ----- 获取缓存数据:key:{},value:{}",
				key4,localEhcache.get(key4,JSONObject.class));
		
		return "OK";
	}
	
	/**
	 * 删除某个元素
	 * @param key
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/r/{key}")
	public String remove(@PathVariable(value = "key") String key) {
		boolean result = localEhcache.remove(key);
		localEhcache.evictExpiredElements();
		logger.info(" ====== result : {} ,key :{}",result,key);
		return "OK";
	}

	
	@ResponseBody
	@RequestMapping("/i")
	public String info() {
		logger.info(" ====== size:{}",localEhcache.size());
		
		return "OK";
	}
	
	/**
	 * 删除所有元素
	 * @param key
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/removeall")
	public String removeAll() {
		localEhcache.removeAll();
		return "OK";
	}
}
