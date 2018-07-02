package com.example.daily.redis;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author: zhiyuan
 * @date: 2018-04-03
 * @project: daily
 * @description:
 */

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public User(String email, String username, int age) {
		this.email = email;
		this.username = username;
		this.age = age;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", username=" + username + ", age=" + age + ", getAge()=" + getAge()
				+ ", getEmail()=" + getEmail() + ", getUsername()=" + getUsername() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	private String email;
	private String username;
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return Objects.equals(email, user.getEmail());
	}
}
