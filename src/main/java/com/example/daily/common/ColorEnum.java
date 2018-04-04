package com.example.daily.common;

/**
 * @author: zhiyuan
 * @date: 2017年12月5日
 * @project: spring-boot-demo
 * @description: 项目代码出现很多魔鬼数字和字符串，应该可以使用枚举缓解一点
 */

public enum ColorEnum {
	RED("红色", "0001"), GREEN("绿色", "0002"), BLANK("白色", "0003"), YELLO("黄色", "0004");

	// 成员变量
	private String name;
	private String code;

	// 构造方法
	private ColorEnum(String name, String code) {
		this.setName(name);
		this.setCode(code);
	}

	// 普通方法
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return this.code;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setCode(String code) {
		this.code = code;
	}
}
