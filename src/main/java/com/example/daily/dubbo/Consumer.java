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
        // get service invocation proxy
        HelloService helloService = (HelloService) context.getBean("helloService");
        // do invoke!
        String hello = helloService.sayHello("world");
        // cool, how are you~
        System.out.println("---------###########----------------------------------" + hello);
    }

}
