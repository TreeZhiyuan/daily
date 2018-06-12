package com.example.daily.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhiyuan
 * @date: 2018-06-07
 * @project: daily
 * @description:将ip地址转成long数字
 */

public class IPUtils {
    /**
     * ip地址转换成long数字
     * 
     * @param IP
     * @return
     */
    public static Long getIPNum(final String IP) {
        Long IPNum = 0l;
        final String IPStr = IP.trim();
        if (IP != null && IPStr.length() != 0) {
            final String[] subips = IPStr.split("\\.");
            for (final String str : subips) {
                // 向左移8位
                IPNum = IPNum << 8;
                IPNum += Integer.parseInt(str);
            }
        }
        return IPNum;
    }

    /**
     * long数字转换成ip地址
     * 
     * @param IPNum
     * @return
     */
    public static String getIPString(final Long IPNum) {
        final Long andNumbers[] = { 0xff000000L, 0x00ff0000L, 0x0000ff00L, 0x000000ffL };
        List<String> ipNums = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            ipNums.add(String.valueOf((IPNum & andNumbers[i]) >> 8 * (3 - i)));
        }
        return String.join(".", ipNums);
    }

    public static void main(final String[] args) {
        final String IPStr = "192.185.11.11";
        System.out.println(getIPNum(IPStr));

        final Long IPNum = 3233352459L;
        System.out.println(getIPString(IPNum));
    }

}
