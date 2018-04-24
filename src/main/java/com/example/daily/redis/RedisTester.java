package com.example.daily.redis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * @author: zhiyuan
 * @date: 2018-04-10
 * @project: daily
 * @description:
 */

public class RedisTester {
	private Jedis jedis;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// 连接redis服务器(在这里是连接本地的)
		jedis = new Jedis("127.0.0.1", 6379);
		// 权限认证 本地redis会被作为windows服务自启动
//		jedis.auth("123456");
		System.out.println("连接服务成功");
	}

	@After
	public void tearDown() throws Exception {
		jedis.close();
		System.out.println("关闭服务成功");
	}

	/**
	 * Redis操作字符串
	 */
	@Test
	public void testString() {
		final String key = "test_string_key";
		final String value = "cuizhiyuan";
		jedis.del(key);
		// 添加数据
		jedis.set(key, value);
		assertEquals(value, jedis.get(key));

		String appended = " is my name;";
		jedis.append(key, appended);
		assertEquals(value + appended, jedis.get(key));
		assertEquals(Long.valueOf(1), jedis.del(key));
	}

	@Test
	public void testMap() {
		final String key = "test_map_key";
		jedis.del(key);
		Map<String, String> map = new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;

			{
				put("name", "chx");
				put("age", "100");
				put("email", "***@outlook.com");
			}
		};
		jedis.hmset(key, map);
		Map result = jedis.hgetAll(key);
		assertEquals(Long.valueOf(1), jedis.del(key));
	}

	/**
	 * jedis操作List
	 */
	@Test
	public void testList() {
		final String key = "test_list_key";
		jedis.del(key);
		List tlist = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			{
				add("spring");
				add("springMVC");
				add("mybatis");
			}
		};
		String result = jedis.setex(key.getBytes(), 24 * 60 * 60, ObjectTranscoder.serializeList(tlist));
		System.out.println("长度:" + ObjectTranscoder.unserializeList(jedis.get(key.getBytes())).size());

		jedis.del(key);
		System.out.println("删除后长度:" + jedis.llen("javaFramework"));
		System.out.println(jedis.lrange("javaFramework", 0, -1));
	}

	/**
	 * 把List对象转换成JsonString存储 比较推荐使用这种方式
	 */
	@Test
	public void testListWithJson() {
		final String key = "test_list_key";
		jedis.del(key);
		List tlist = new ArrayList<String>() {
			private static final long serialVersionUID = 1L;
			{
				add("spring");
				add("springMVC");
				add("mybatis");
			}
		};
		String arrayString = JSONArray.fromObject(tlist).toString();
		String result = jedis.set(key, arrayString);
		String cachedString = jedis.get(key);

		System.out.println("获取:" + cachedString);
		Collection c = JSONArray.toCollection(JSONArray.fromObject(cachedString));
		List gotlist = new ArrayList<String>(c);
		assertEquals(true, gotlist.containsAll(tlist));
		jedis.del(key);
		System.out.println("删除后长度:" + jedis.llen(key));
		System.out.println(jedis.lrange(key, 0, -1));
	}

	@Test
	public void testObject() {
		final String key = "test_object_key";
		jedis.del(key);
		
		User preUser = new User("email", "username", 22);
		jedis.set(key.getBytes(), ObjectTranscoder.serialize(preUser));
		User cachedUser = (User) ObjectTranscoder.deserialize(jedis.get(key.getBytes()));

		System.out.println(cachedUser.getEmail());
		assertEquals(preUser.getEmail(), cachedUser.getEmail());
		assertEquals(preUser.getUsername(), cachedUser.getUsername());
		jedis.del(key);
	}
	
	@Test
	public void testListObject() {
		final String key = "test_list_object_key";
		jedis.del(key);
		List preList = new ArrayList() {{
			add(new User("email", "username", 23));
		}};

		jedis.del(key);
	}

}
