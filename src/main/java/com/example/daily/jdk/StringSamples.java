package com.example.daily.jdk;

import org.junit.Test;

import java.util.Date;

/**
 * @auther: Zhiyuan Cui
 * @project: daily
 * @date: 2019/2/15 14:25
 * <p>
 * https://www.cnblogs.com/fsjohnhuang/p/4094777.html
 * <p>
 * https://docs.oracle.com/javase/9/docs/api/java/util/Formatter.html
 */
public class StringSamples {
    @Test
    public void 测试转成String(){
        Long accountId = null;
//        System.out.println(accountId + "");
//        System.out.println((accountId + "") instanceof String);
        String accountIdStr = String.valueOf(accountId);
        System.out.println(accountIdStr);
    }
    /**
     * 1000 -> 1,000
     */
    @Test
    public void formatInt() {
        int pay = 10990;
        String result = String.format("%,d", pay);
        System.out.println(result);
    }


    /**
     * #，对8进制和16进制，8进制前添加一个0,16进制前添加0x
     */
    @Test
    public void format8Or16() {
        int pay = 9;

        // 8进制
        String result = String.format("%#o", pay);
        System.out.println(result);

        // 16进制
        result = String.format("%#x", pay);
        System.out.println(result);

    }

    /**
     * 补充前置0
     */
    @Test
    public void formatPreZero() {
        String result = null;
        int pay = 1;
        result = String.format("%02d", pay);
        System.out.println(pay + "->" + result);

        pay = 0;
        result = String.format("%02d", pay);
        System.out.println(pay + "->" + result);

        pay = 10;
        result = String.format("%02d", pay);
        System.out.println(pay + "->" + result);
    }

    @Test
    public void testDate() {
        Date now = new Date();
        String str = String.join(" ", String.format("%tF", now), String.format("%tT", now));
        System.out.println(str);
    }

    @Test
    public void testReplaceStr() {
        String formatted = String.format("%s今年%d岁。", "小李", 30);
        System.out.println(formatted);
    }

    @Test
    public void testStringInternSingle() {
        String h = new String("cc");
        String intern = h.intern();
        System.out.println(intern == h);
    }

    @Test
    public void testStringIntern() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
