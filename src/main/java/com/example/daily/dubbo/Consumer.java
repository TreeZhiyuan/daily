package com.example.daily.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: zhiyuan
 * @date: 2018-05-28
 * @project: daily
 * @description:
 */

public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "consumer.xml" });
        HelloService helloService = (HelloService) context.getBean("helloService");
        // get service invocation proxy
        String hello = helloService.sayHello("world");
        // do invoke!
        System.out.println("-------------------------------------------" + hello);
        // cool, how are you~
    }

}
