package com.book.web.controller;

import net.sf.ehcache.Cache;

/**
 * 本地缓存
 * @author liweihan
 *
 */
public interface LocalEhCache {
	
	/**
	 * 获取Cache对象
	 * @return
	 */
    public Cache getEhCache();
    /**
     * 根据key获取缓存对象
     * @param key	缓存的key
     * @param tClass	类似的class
     * @return
     */
    public <T> T get(Object key, Class<T> tClass);

    /**
     * 缓存对象
     * @param key
     * @param value
     */
    public void put(Object key, Object value);
    
    /**
     * 可以控制时间的缓存对象
     * @param key
     * @param obj
     * @param idleTime	无访问缓存时间(单位:秒)
     * @param liveTime	生存时间(单位:秒)
     * 		
     */
    public void put(Object key, Object obj, Integer idleTime, Integer liveTime);
    
    /**
     * 根据key清除缓存对象
     * @param key
     */
    public boolean remove(Object key);
    
    /**
     * 判断缓存中是否包括key的缓存
     * @param key
     * @return
     */
    public boolean containsKey(Object key);
    
    /**
     * 缓存的数量
     * @return
     */
    public int size();
    
    /**
     * 清除过期的缓存
     * 注意的是触发ehcache去检查这个元素是否过期expiry，
     * 是由用户访问了元素，即调用cache.get(key)按需触发，
     * 这时ehcache才会去检查这个元素是否过期，
     * 如果过期就把该元素清除，并返回null。
     * 
     * 所以如果存在这样的场景：
     * 有些元素我们一直都不去访问，
     * 且内存中的元素数量又没超出maxElementsInMemory的值，
     * 那么这些过期元素将一直驻留在内存中。
     * 
     * 为了解决这个问题，我们应该创建一个后台线程，
     * 这个线程可以过一段时间去触发一下cache.evictExpiredElements()，
     * 这样即可把内存中驻留的过期元素清除。
     */
    public void evictExpiredElements();
    
    /**
     * 清除所有ehcache缓存
     */
    public void removeAll();
    
}
