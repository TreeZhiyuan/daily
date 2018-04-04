package com.example.daily.reflect;

/**
 * @author: zhiyuan
 * @date: 2017-12-27
 * @project: spring-boot-demo
 * @description: one simple java reflect demo
 */
import java.lang.reflect.*;
import java.util.Stack;

public class DumpMethods {
	static void printClassName(Object obj, String... args) {
		System.out.printf("The class of is %s, ...args: %s\n\r", obj.getClass().getName(), String.join(",", args));
	}

	public static void main(String args[]) {
		printClassName(new Stack<Object>(), "a", "b", "c");
		try {
			// 加载字节码
			Class clazz = Class.forName("java.util.Stack");
			// 实例化
			Stack<String> stack = (Stack<String>) clazz.newInstance();
			stack.push("aaa");
			System.out.printf("data in stack: %s\n\r", stack.size() > 0 ? stack.peek() : "");

			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				System.out.printf("method: %s, declaringClass: %s, ", method.getName(), method.getDeclaringClass());
				Class[] parameterTypes = method.getParameterTypes();
				System.out.print("parameters:");
				for (Class class1 : parameterTypes) {
					System.out.println(class1.getName());
				}
				// 执行stack.pop()
				if (method.getName().contains("pop")) {
					method.invoke(stack, new Object[] {});
				}
				System.out.println();
			}
			//
			System.out.printf("data in stack: %s\n\r", stack.size() > 0 ? stack.peek() : "");
		} catch (Throwable e) {
			System.err.println(e);
		}
	}
}