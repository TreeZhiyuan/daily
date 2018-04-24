package com.example.daily.common;

/**
 * @author: zhiyuan
 * @date: 2017年12月5日
 * @project: spring-boot-demo
 * @description:
 */

public enum Color {
	RED("红色", "0001"), GREEN("绿色", "0002"), BLANK("白色", "0003"), YELLO("黄色", "0004");

	// 成员变量
	private String name;
	private String code;

	// 构造方法
	private Color(String name, String code) {
		this.name = name;
		this.code = code;
	}

	// 普通方法
	public String getName() {
		// 完全没必要，因为每个枚举对象就是一个实例
		for (Color c : Color.values()) {
			if (c == this) {
				return c.name;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		for (Color c : Color.values()) {
			if (c == this) {
				return c.code;
			}
		}
		return null;
	}
}
