package com.example.shiro.cms.service.impl;

import com.example.shiro.cms.entity.FooBar;
import com.example.shiro.cms.mapper.FooBarMapper;
import com.example.shiro.cms.service.IFooBarService;
import com.example.shiro.common.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 * FooBar 服务实现类
 * </p>
 *
 * @author jack
 * @since 2019-12-09
 */
@Service
@CacheConfig(cacheNames = "com.shiro.cms.foobar")
public class FooBarServiceImpl extends ServiceImpl<FooBarMapper, FooBar> implements IFooBarService {


	@Override
	@Cacheable(key = "#id")
	public FooBar getById(Serializable id) {
		return super.getById(id);
	}

	/**
	 * 测试事务
	 * @param fooBar 实体对象
	 * @return
	 */
	@Override
	@Transactional
	@CachePut(key = "#fooBar.id")
	public FooBar save(FooBar fooBar) {
		super.save(fooBar);
//		int i = 100/0;
		return fooBar;
	}

	@Override
	@CachePut(key = "#fooBar.id")
	public FooBar updateById(FooBar fooBar) {
		return super.updateById(fooBar);
	}

	@Override
	@CacheEvict(key = "#id")
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}
}
