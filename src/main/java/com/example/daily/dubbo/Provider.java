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
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "provider.xml" });
        // 启动成功，监听端口为2 0 8 8 0
        System.in.read(); // 按任意键退出
    }

}
