package com.example.daily.jdk;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author: zhiyuan
 * @date: 2018/6/5
 * @project: daily
 * @description: https://www.journaldev.com/13121/java-9-features-with-examples#private-methods
 */

public class Java9Tester {

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
    public void testImmutableList() {
        // List immutableList = List.of("one","two","three");
        // immutableList.stream().forEach(a -> System.out.printf("%s", a));
    }
}
