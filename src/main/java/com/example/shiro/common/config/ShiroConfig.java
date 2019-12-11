package com.example.shiro.common.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.shiro.common.cache.ShiroCacheManager;
import com.example.shiro.sys.shiro.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZhangTianhao
 * @date 2019/12/11 10:05
 */
@Configuration
public class ShiroConfig {

	@Autowired
	@Qualifier("shiroCacheManager")
	private ShiroCacheManager shiroCacheManager;

	/**
	 * 装配 自定义Realm
	 *
	 * @return
	 */
	@Bean
	public ShiroRealm shiroRealm() {
		return new ShiroRealm();
	}

	/**
	 * 配置安全管理器
	 *
	 * @return
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 注入自定义Realm
		securityManager.setRealm(shiroRealm());
		// 注入缓存管理器
		securityManager.setCacheManager(shiroCacheManager);
		return securityManager;
	}


	/**
	 * 配置权限权限过滤器
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
		// 注入安全管理器
		filter.setSecurityManager(securityManager());
		// 未认证的跳转地址
		filter.setLoginUrl("/login");
		//LinkedHashMap 有顺序，拦截规则也需要顺序，由小的权限往大的权限配
		Map<String, String> chain = new LinkedHashMap<>();
		// 登录链接不拦截 anon表示不拦截
		//映射资源的时候static不算在虚拟路径上
		chain.put("/login", "anon");
		chain.put("/css/**", "anon");
		chain.put("/img/**", "anon");
		chain.put("/js/**", "anon");
		chain.put("/lib/**", "anon");
		// user 权限表示拦截
		chain.put("/**", "user");
		filter.setFilterChainDefinitionMap(chain);
		return filter;
	}

	/**
	 * 启用Shiro内部Bean生命周期管理
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 开启aop对shiro的bean的动态代理,才能使注解完全生效
	 * @return
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		return creator;
	}

	/**
	 * 启用Shiro注解
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		// 注入安全管理器
		advisor.setSecurityManager(securityManager());
		return advisor;
	}


	/**
	 * 启用shiro thymeleaf标签支持
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
}
