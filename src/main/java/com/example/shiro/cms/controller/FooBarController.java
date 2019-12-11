package com.example.shiro.cms.controller;


import com.example.shiro.cms.service.IFooBarService;
import com.example.shiro.common.model.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * FooBar 前端控制器
 * </p>
 *
 * @author jack
 * @since 2019-12-09
 */
@Controller
@RequestMapping("/cms/foo-bar")
public class FooBarController {

	@Autowired
	private IFooBarService iFooBarService;

	/**
	 * @PathVariable 表示是类路径上的资源
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@ResponseBody
	@RequiresPermissions("cms:fooBar:view")
	public R get(@PathVariable Integer id){
		return R.ok("请求成功",iFooBarService.getById(id));
	}
}
