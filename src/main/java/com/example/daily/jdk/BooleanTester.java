package com.example.daily.jdk;

import org.junit.Test;

/**
 * author: Zhiyuan Cui
 * project: daily
 * date: 2019/4/17 11:11
 * description:
 */
public class BooleanTester {
    @Test
    public void 测试Java中哪些为False() {
        boolean isFalse = false;
        isFalse = Boolean.valueOf("");
        isFalse = Boolean.valueOf(null);
        isFalse = Boolean.valueOf("null");
        isFalse = Boolean.valueOf("False");
        isFalse = Boolean.valueOf("tRue");
        isFalse = Boolean.valueOf("1");
        isFalse = Boolean.valueOf("0");
        System.out.println("isFalse: " + isFalse);
    }
}
