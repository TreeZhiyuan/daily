package com.example.daily.common;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: zhiyuan
 * @date: 2017年12月5日
 * @project: spring-boot-demo
 * @description:
 */

public class ColorEnumTest {

	public static void main(String[] args) {
		System.out.println(ColorEnum.YELLO.getCode() + "|||" + ColorEnum.YELLO.getName());
		System.out.println(ColorEnum.YELLO == ColorEnum.RED);
		System.out.printf("%d-%d", Calendar.getInstance().getTimeInMillis(), new Date().getTime());
	}

}
