package com.example.daily.dubbo;

/**
 * @author: zhiyuan
 * @date: 2018-05-28
 * @project: daily
 * @description:
 */

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }

}
