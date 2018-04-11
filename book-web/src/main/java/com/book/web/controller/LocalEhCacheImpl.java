package com.book.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class LocalEhCacheImpl implements LocalEhCache{
	
	private Cache cache;
	private static Logger logger = LoggerFactory.getLogger(LocalEhCacheImpl.class);
	
	public LocalEhCacheImpl(EhCacheCacheManager ehCacheCacheManager,String name) {
    	logger.info(" ====== init ehcache!!");
		cache = ehCacheCacheManager.getCacheManager().getCache(name);
	}

	@Override
	public Cache getEhCache() {
		return this.cache;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Class<T> tClass) {
		try {
			if (this.containsKey(key)) {
				return (T)cache.get(key).getObjectValue();
			}
		} catch (IllegalStateException e) {
			logger.error(" ====== Get from Ehcache Error ,key:{}",key,e);
		} 
		return null;
	}

	@Override
	public void put(Object key, Object value) {
		try {
			Element element = new Element(key, value);
			cache.put(element);
		} catch (Exception e) {
			logger.error(" ====== put into Ehcache Error ,key:{},value:{}",key,value,e);
		}
	}

	@Override
	public void put(Object key, Object value, Integer idleTime, Integer liveTime) {
		try {
			Element element = new Element(key, value, false, idleTime, liveTime);
			cache.put(element);
		} catch (Exception e) {
			logger.error(" ====== put into Ehcache Error ,key:{},value:{},idle:{},liveTime:{}",
					key,value,idleTime,liveTime,e);
		}
	}

	@Override
	public boolean remove(Object key) {
		return cache.remove(key);
	}

	@Override
	public boolean containsKey(Object key) {
		boolean f = false;
		try {
			Element element = cache.getQuiet(key);//静态的获取Element,不会产生update
			if (cache.isKeyInCache(key) && element != null && !cache.isExpired(element)) {
				f = true;
			} else {
				f = false;
			}
		} catch (Exception e) {
			logger.error(" ====== ehcache check Key ERROR ! key:{}",key,e);
			f = false;
		} 
		return f;
	}

	@Override
	public int size() {
		return cache.getSize();
	}

	@Override
	public void evictExpiredElements() {
		cache.evictExpiredElements();
	}

	@Override
	public void removeAll() {
		cache.removeAll();
	}

}
