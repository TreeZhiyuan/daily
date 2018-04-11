package com.example.daily.feature.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.daily.redis.User;

/**
 * @author: zhiyuan
 * @date: 2018-04-09
 * @project: daily
 * @description:
 */

public class StreamsUtils {
	public static void main(String[] args) {
		List<User> users = new ArrayList<User>() {
			private static final long serialVersionUID = 1L;

			{
				add(new User("emails1", "aa", 22));
				add(new User("emails2", "bb", 23));
				add(new User("emails3", "cc", 26));
			}
		};
		List emails = users.stream().map(User::getEmail).collect(Collectors.toList());
		System.out.println(emails);
	}
}
