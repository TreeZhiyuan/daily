package com.example.daily.jdk;

import org.junit.Test;

/**
 * @Project: daily
 * @Auther: Administrator
 * @Date: 2018/8/1 16:42
 * @Description:
 */
public class MathTester {
    @Test
    public void test() {
        System.out.printf("%s\r\n", Math.ceil(10));
        System.out.printf("%s\r\n", Math.ceil(10.20));
        System.out.printf("%s\r\n", Math.ceil(10.3));
    }
}
