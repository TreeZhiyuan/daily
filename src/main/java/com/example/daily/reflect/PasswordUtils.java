package com.example.daily.reflect;

import java.util.List;

/**
 * @author: zhiyuan
 * @date: 2017-12-28
 * @project: spring-boot-demo
 * @description:
 */
public class PasswordUtils {
	@UseCase(id = 47, description = "Password must contain at least one numeric")
	public boolean validatePassword(String password) {
		return (password.matches("\\w*\\d\\w*"));
	}

	@UseCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}

	@UseCase(id = 49, description = "New passwords can't equal previously used ones")
	public boolean checkForNewPassword(List<String> prevPasswords, String password) {
		return !prevPasswords.contains(password);
	}
}