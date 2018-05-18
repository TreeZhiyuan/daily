package com.example.daily.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author: zhiyuan
 * @date: 2018-02-24
 * @project: spring-boot-demo
 * @description:
 */

public class MybatisTester {
	static MybatisDao dao = null;
	static SqlSession session = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String resource = "mybatis-conf.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		// 创建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// true表示自动提交。否则需要使用commit方法才会提交。默认是false
		session = sqlSessionFactory.openSession();

		// 拿到接口的代理对象
		dao = session.getMapper(MybatisDao.class);
		// 拿到了dao这个对象接下来就可以创建sql语句了;（直接调用接口方法）
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		session.close();
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 单个的参数Mybatis不会做特殊处理 #{whatever}都能把传递的值取到
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException {
		List x = dao.getMenus(2); // 这个方法有个int返回值，会告诉你已影响了多少条数据
		System.out.println(x);
		// 如果上面不设置自动提交表单，那么就需要commit方法
		session.commit();
	}

	/**
	 * 多个参数。Mybatis会做特殊处理。会把传入的参数自动封装成Map类型 Map 的key值就是从param1...paramN ..
	 * map.put("param1",name) map.put("param2,id") @param("name") 可以使用这个注解
	 * 来自定义Map封装数据的key值。
	 * 
	 * @throws IOException
	 */
	@Test
	public void test4ManyParams() throws IOException {
		List<Map> x = dao.getMenus4ManyParams(1, "2");
		System.out.println(x.get(0).keySet());
		System.out.println(x.get(0).values());
		session.commit();
	}

	/**
	 * 直接传入Map
	 * 
	 * @throws IOException
	 */
	@Test
	public void test4MapParams() throws IOException {
		List<Map> x = dao.getMenus4MapParams(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("required", "1");
				put("parentMenu", "1");
			}
		});
		System.out.println(x.get(0).keySet());
		System.out.println(x.get(0).values());
		session.commit();
	}

	/**
	 * 传入对象POJO(普通的java类).. #{对象的属性名称}
	 * 
	 * @throws IOException
	 */
	@Test
	public void test4POJO() throws IOException {
		Menu1st menu = new Menu1st();
		menu.setRequired("1");
		menu.setParentMenu("1");
		List<Map> x = dao.getMenus4POJO(menu);
		System.out.println(x.get(0).keySet());
		System.out.println(x.get(0).values());
		session.commit();
	}

	/**
	 * Collection(集合)类型(List,Set) ,数组。 Mybatis也会做特殊处理。。 如果是List或者Set 封装到map中 如果是数组
	 * map.put("array",你传入的数组)
	 * 
	 * @throws IOException
	 */
	@Test
	public void test4Collection() throws IOException {
		// TODO
		Menu1st menu = new Menu1st();
		menu.setRequired("1");
		menu.setParentMenu("1");
		List<Map> x = dao.getMenus4POJO(menu);
		System.out.println(x.get(0).keySet());
		System.out.println(x.get(0).values());
		session.commit();
	}

	@Test
	public void test4ResultMapCollections() {
		final String roleOid = "";
		List<Menu> mappedMenus = dao.getMapMenu(roleOid);
		System.out.println(JSONArray.fromObject(mappedMenus).toString());
		session.commit();
	}

	@Test
	public void test5ResultMapCollections() {
		final String roleOid = "";
		List<Menu> mappedMenus = dao.getMapMenuSeprate(roleOid);
		List<Integer> codes = getMenuCodes(mappedMenus);
		codes.forEach(code -> System.out.println(code));
		session.commit();
	}

	/**
	 * 返回所有mapped menu code
	 * 
	 * @param menus
	 * @return list of menu code
	 */
	private List<Integer> getMenuCodes(List<Menu> menus) {
		List<Integer> codes = new ArrayList<Integer>();
		codes.addAll(menus.stream().map(menu -> {
			if (menu.getChilds().size() > 0) {
				codes.addAll(getMenuCodes(menu.getChilds()));
			}
			return menu.getMenuCode();
		}).collect(Collectors.toList()));
		return codes;
	}
}
