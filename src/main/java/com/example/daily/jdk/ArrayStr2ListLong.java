package com.example.daily.jdk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Zhiyuan Cui
 * @Project: daily
 * @Date: 2018/12/4 10:14
 * @Description:
 */
public class ArrayStr2ListLong {
    @Test
    public void arrayStr2ListLong() {
        String[] ids = new String[]{"123", "234", "456", "678"};
        Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList());
        List<Long> LongIds = new ArrayList<Long>() {{
            addAll(Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList()));
        }};
        LongIds.stream().forEach(System.out::println);
    }
}
