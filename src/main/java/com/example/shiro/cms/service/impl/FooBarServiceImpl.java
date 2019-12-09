package com.example.shiro.cms.service.impl;

import com.example.shiro.cms.entity.FooBar;
import com.example.shiro.cms.mapper.FooBarMapper;
import com.example.shiro.cms.service.IFooBarService;
import com.example.shiro.common.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * FooBar 服务实现类
 * </p>
 *
 * @author jack
 * @since 2019-12-09
 */
@Service
public class FooBarServiceImpl extends ServiceImpl<FooBarMapper, FooBar> implements IFooBarService {

}
