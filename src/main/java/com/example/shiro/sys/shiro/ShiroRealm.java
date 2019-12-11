package com.example.shiro.sys.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author ZhangTianhao
 * @date 2019/12/11 9:59
 */
public class ShiroRealm extends AuthorizingRealm {

	/**
	 * 认证
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 1.获取用户输入的用户名
		String username = token.getUsername();
		// 2.获取用户输入的密码
		String password = new String(token.getPassword());
		if (!"admin".equals(username) || !"123456".equals(password)) {
			throw new RuntimeException("用户名或密码错误");
		}
		// 创建简单认证信息对象
		SimpleAuthenticationInfo info =
				new SimpleAuthenticationInfo(username, password, getName());
		return info;
	}

	/**
	 * 授权检查
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 简单授权信息对象，对象中包含用户的角色和权限信息
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole("admin");
		info.addStringPermission("cms:fooBar:view");
		info.addStringPermission("cms:fooBar:list");
		System.out.println("授权完成....");
		return info;
	}

}
