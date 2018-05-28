package com.example.daily.dubbo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: zhiyuan
 * @date: 2018-05-28
 * @project: daily
 * @description:
 */

public class Provider {

    public static void main(String[] args) throws IOException {
        // 启动成功，监听端口为20880
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "provider.xml" });
        // 按任意键退出
        System.in.read();
    }

}
