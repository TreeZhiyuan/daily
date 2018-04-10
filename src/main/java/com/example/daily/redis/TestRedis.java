package com.example.daily.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: zhiyuan
 * @date: 2018-04-03
 * @project: daily
 * @description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test() throws Exception {
		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
		stringRedisTemplate.delete("aaa");
		stringRedisTemplate.opsForList().rightPushAll("ListKey", new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			{
				add("a");
				add("b");
				add("c");
			}
		});
		List<String> result = stringRedisTemplate.opsForList().range("ListKey", 0, -1);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals(true, result.containsAll(new ArrayList<String>() {
			private static final long serialVersionUID = 1L;

			{
				add("a");
				add("b");
				add("c");
			}
		}));
		stringRedisTemplate.delete("ListKey");
	}

	@Test
	public void test2() throws Exception {
		// 保存字符串
		User u = new User("email1", "usr1");
		redisTemplate.opsForHash().put("user:", "1", u);
		/* 查看redisTemplate 的Serializer */
		System.out.println(redisTemplate.getKeySerializer());
		System.out.println(redisTemplate.getValueSerializer());

		/* 查看StringRedisTemplate 的Serializer */
		System.out.println(stringRedisTemplate.getValueSerializer());
		System.out.println(stringRedisTemplate.getValueSerializer());

		/* 将stringRedisTemplate序列化类设置成RedisTemplate的序列化类 */
		stringRedisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
		stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

		/*
		 * 即使在更换stringRedisTemplate的的Serializer和redisTemplate一致的
		 * JdkSerializationRedisSerializer 最后还是无法从redis中获取序列化的数据
		 */
		System.out.println(stringRedisTemplate.getValueSerializer());
		System.out.println(stringRedisTemplate.getValueSerializer());

		User user = (User) redisTemplate.opsForHash().get("user:", "1");
		User user2 = (User) stringRedisTemplate.opsForHash().get("user:", "1");
		System.out.println("dsd");
	}

}