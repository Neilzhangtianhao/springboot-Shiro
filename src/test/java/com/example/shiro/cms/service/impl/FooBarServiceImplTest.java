package com.example.shiro.cms.service.impl;


import com.example.shiro.cms.entity.FooBar;
import com.example.shiro.cms.service.IFooBarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhangTianhao
 * @date 2019/12/10 9:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FooBarServiceImplTest {
	@Autowired
	private IFooBarService iFooBarService;

	@Test
	public void test(){
		FooBar fooBar = iFooBarService.getById(1);
//		fooBar.setBar("hhhh");
//		iFooBarService.save(fooBar);
		System.out.println(fooBar);
		System.out.println(iFooBarService.getById(1));
	}

	@Test
	public void test2() {
//		FooBar fooBar = new FooBar();
//		fooBar.setName("woshiname");
//		fooBar.setBar("hhhh");
//		iFooBarService.save(fooBar);
		iFooBarService.removeById(3);
	}
}