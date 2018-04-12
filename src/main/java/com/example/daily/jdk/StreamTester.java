package com.example.daily.jdk;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.daily.redis.User;

/**
 * @author: zhiyuan
 * @date: 2018-04-11
 * @project: daily
 * @description:A functional interface is any interface that contains only one
 *                abstract method. (A functional interface may contain one or
 *                more default methods or static methods.) Because a functional
 *                interface contains only one abstract method, you can omit the
 *                name of that method when you implement it. To do this, instead
 *                of using an anonymous class expression, you use a lambda
 *                expression, which is highlighted in the following method
 *                invocation
 */

public class StreamTester {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		List<User> users = new ArrayList<User>() {
			private static final long serialVersionUID = 1L;

			{
				add(new User("emails1", "aa", 22));
				add(new User("emails2", "bb", 20));
				add(new User("emails3", "cc", 19));
				add(new User(null, "dd", 35));
			}
		};
		// 顺便折腾一下Predicate
		Predicate<User> predicate = u -> u.getEmail() != null;

		users.stream().filter(predicate).forEach(item -> System.out.println(item.getUsername()));;
		System.out.println("aaaaaaaaaaaaaa");
		users.stream().filter(predicate.negate()).forEach(item -> System.out.println(item.getUsername()));
		
		// List emails = users.stream().filter(u -> u.getEmail() != null &&
		// u.getEmail().length() > 0).map(User::getEmail).collect(Collectors.toList());
		List emails = users.stream().filter(u -> u.getEmail() != null && u.getEmail().length() > 0)
				.map(u -> u.getEmail()).collect(Collectors.toList());
		System.out.println(String.join("|", emails));
		assertEquals(users.size() - 1, emails.size());
	}

	@Test
	public void testStrings() {
		String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
		Arrays.sort(stringArray, String::compareToIgnoreCase);
		System.out.println(String.join(">", stringArray));
	}

	@Test
	public void testInteger() {
		Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8 };
		List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
		System.out.println("Sum of integers: " + listOfIntegers.stream().reduce(Integer::sum).get());
	}

	@Test
	public void testPredicate() {
		Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8 };
		List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
		System.out.println("Sum of integers: " + listOfIntegers.stream().reduce(Integer::sum).get());
	}

}
