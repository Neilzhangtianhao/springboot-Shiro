package com.example.shiro.sys.controller;

import com.example.shiro.common.model.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ZhangTianhao
 * @date 2019/12/11 11:11
 */
@Controller
@RequestMapping
public class LoginController {

	/**
	 * 跳转到系统登录页面
	 * @return
	 */
	@GetMapping("/login")
	public String login(){
		return "login";
	}


	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/login")
	@ResponseBody
	public R login(String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return R.ok();
	}

	/**
	 * 跳转到系统初始化页面
	 * @return
	 */
	@GetMapping("/index")
	public String index(){
		return "index";
	}


	@GetMapping("/logout")
	public String logout(){
		// 销毁会话
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login";
	}
}
