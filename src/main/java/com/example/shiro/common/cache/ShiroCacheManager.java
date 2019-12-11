package com.example.shiro.common.cache;

/**
 * @author ZhangTianhao
 * @date 2019/12/11 16:53
 */
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class ShiroCacheManager<K, V> implements CacheManager {

	// Spring的缓存管理器
	private org.springframework.cache.CacheManager springCacheManager;

	public ShiroCacheManager(org.springframework.cache.CacheManager springCacheManager) {
		this.springCacheManager = springCacheManager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
		return new ShiroCache(springCacheManager, cacheName);
	}
}

