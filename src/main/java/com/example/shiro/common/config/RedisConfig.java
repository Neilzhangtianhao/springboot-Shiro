package com.example.shiro.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ZhangTianhao
 * @date 2019/12/10 11:01
 */
@Configuration
public class RedisConfig {


	@Autowired
	private RedisConnectionFactory redisConnectionFactory;


	/**
	 * string序列化
	 * @return
	 */
	@Bean
	public StringRedisSerializer stringRedisSerializer() {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		return stringRedisSerializer;
	}

	/**
	 * jackson序列化
	 * @return
	 */
	@Bean
	public Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer
				= new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL,
				JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
		return jackson2JsonRedisSerializer;
	}


	/**
	 * 觉得StringRedisTemplate不好用，想改造它
	 * @return
	 */
	@Bean
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate stringRedisTemplate =
				new StringRedisTemplate();
		//如果自己new的话，就得手动设置数据库连接工厂
		stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
		// 开启事务支持
		stringRedisTemplate.setEnableTransactionSupport(true);
		return stringRedisTemplate;
	}

	@Bean
	public RedisTemplate redisTemplate() {
		RedisTemplate<String, Object> redisTemplate
				= new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		//序列化，默认时使用jdk的序列化，会造成乱码
		redisTemplate.setKeySerializer(stringRedisSerializer());
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
		redisTemplate.setHashKeySerializer(stringRedisSerializer());
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
		// 开启事务支持
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}
}
