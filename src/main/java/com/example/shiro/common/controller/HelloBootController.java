package com.example.shiro.common.controller;

import com.example.shiro.common.model.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author ZhangTianhao
 * @date 2019/12/9 13:17
 */
@Controller
public class HelloBootController {
	@GetMapping("/hello")
	@ResponseBody
	public Map<String, Object> hello() {
		return R.ok("Hello Boot");
	}

	/**
	 * 使用restcontroller返回String无效
	 * @param model
	 * @return
	 */
	@GetMapping("/thymeleaf")
	public String thymeleaf(Model model){
		model.addAttribute("hello","Hello Thymeleaf");
		return "index";
	}
}
