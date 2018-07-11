package com.example.daily.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.daily.redis.User;

/**
 * @author: zhiyuan
 * @date: 2018-07-02
 * @project: daily
 * @description:
 */

public class StreamDistinct {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDistinctIds() {
        List<Long> ids = new ArrayList<Long>() {
            private static final long serialVersionUID = 1L;
            {
                add(123l);
                add(123l);
                add(234l);
                add(232l);
            }
        };
        List<Long> distinctIds = ids.stream().distinct().collect(Collectors.toList());
        distinctIds.stream().forEach(System.out::println);
    }

    @Test
    public void testDistinctObjects() {
        List<User> ids = new ArrayList<User>() {
            private static final long serialVersionUID = 1L;
            {
                add(new User());
            }
        };
        // TODO
    }

}
