package com.example.shiro.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhangTianhao
 * @date 2019/12/10 10:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void test(){
		redisTemplate.multi();
		redisTemplate.opsForValue().set("k2","hello redis ...");
//		int i = 100/0;
		redisTemplate.opsForValue().set("k3","hello redis ...");
		redisTemplate.exec();
	}

}
