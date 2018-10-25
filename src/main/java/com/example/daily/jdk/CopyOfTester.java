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
    int[] arr = {1, 2, 3, 4, 5};

    @Test
    public void testObjectGroupBy() {
        int[] copied = new int[10];
        System.arraycopy(arr, 1, copied, 1, 4);//5 is the length to copy
        System.out.println(Arrays.toString(copied));
    }

    @Test
    public void testWithArraysCopy() {
        int[] copied = Arrays.copyOf(arr, 10); //10 the the length of the new array
        System.out.println(Arrays.toString(copied));

        copied = Arrays.copyOf(arr, 3);
        System.out.println(Arrays.toString(copied));
    }
}
