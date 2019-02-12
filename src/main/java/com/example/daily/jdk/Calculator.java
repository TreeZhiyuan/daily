package com.example.daily.jdk;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: zhiyuan
 * @date: 2018-04-11
 * @project: daily
 * @description:将接口的实现作为参数传到方法中,有点类似与JavaScript的函数传递
 */

public class Calculator {

    interface IntegerMath {
        int operation(int a, int b);
    }

    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }

    public static void main(String... args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        System.out.println(sdf.format(new Date()) + ">" + ((int) (Math.random() * 900) + 100) + "");
        Calculator myApp = new Calculator();
        System.out.println("40 + 2 = " + myApp.operateBinary(40, 2, (a, b) -> a + b));
        System.out.println("20 - 10 = " + myApp.operateBinary(20, 10, (a, b) -> a - b));
        System.out.printf("%s\r\n", String.format("以有城市经理分配该上门区域:%s-%s-%s-%d", "aaaa", "bbb", "cc",23));
    }

}
