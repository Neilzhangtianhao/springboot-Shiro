package com.example.shiro.common.cache;

import org.apache.shiro.cache.CacheException;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 自定义Shiro缓存，实现 shiro Cache 接口
 *
 * @param <K>
 * @param <V>
 */
public class ShiroCache<K, V> implements org.apache.shiro.cache.Cache<K, V> {

	// Spring的缓存管理器
	private CacheManager springCacheManager;
	// Spring的缓存对象
	private Cache springCache;
	// 缓存名称
	private String cacheName;

	public ShiroCache(org.springframework.cache.CacheManager springCacheManager, String cacheName) {
		this.springCacheManager = springCacheManager;
		this.springCache = springCacheManager.getCache(cacheName);
		this.cacheName = cacheName;
	}

	@Override
	public V get(K k) throws CacheException {
		Cache.ValueWrapper valueWrapper = springCache.get(k);
		if (valueWrapper == null) {
			return null;
		}
		return (V) valueWrapper.get();
	}

	@Override
	public V put(K k, V v) throws CacheException {
		springCache.put(k, v);
		return v;
	}

	@Override
	public V remove(K k) throws CacheException {
		V v = this.get(k);
		springCache.evict(k);
		return v;
	}

	@Override
	public void clear() throws CacheException {
		springCache.clear();
	}

	/**
	 * 获取所有缓存key的集合
	 *
	 * @return
	 */
	@Override
	public Set<K> keys() {
		return (Set<K>) springCacheManager.getCacheNames();
	}

	/**
	 * 获取所有缓存value值的集合
	 *
	 * @return
	 */
	@Override
	public Collection<V> values() {
		List<V> list = new ArrayList<>();
		Set<K> keys = keys();
		for (K k : keys) {
			list.add(this.get(k));
		}
		return list;
	}

	/**
	 * 获取缓存对象的数量
	 *
	 * @return
	 */
	@Override
	public int size() {
		int size = keys().size();
		return size;
	}
}
