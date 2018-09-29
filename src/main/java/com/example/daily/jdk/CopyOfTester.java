package com.example.daily.jdk;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Project: daily
 * @Auther: zhiyuan
 * @Date: 2018/9/29 17:14
 * @Description:
 */
public class CopyOfTester {
    @Test
    public void testObjectGroupBy() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] copied = new int[10];
        System.arraycopy(arr, 0, copied, 1, 5);//5 is the length to copy
        System.out.println(Arrays.toString(copied));
    }
}
