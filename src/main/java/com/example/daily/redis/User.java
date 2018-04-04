package com.example.daily.redis;

import java.io.Serializable;

/**
 * @author: zhiyuan
 * @date: 2018-04-03
 * @project: daily
 * @description:
 */

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public User(String email, String username) {
		this.email = email;
		this.username = username;
	}

	private String email;
	private String username;

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
}
