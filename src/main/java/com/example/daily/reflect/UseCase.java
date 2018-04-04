package com.example.daily.reflect;

/**
 * @author: zhiyuan
 * @date: 2017-12-28
 * @project: spring-boot-demo
 * @description: 
 */
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
	public int id();

	public String description() default "no description";
}
