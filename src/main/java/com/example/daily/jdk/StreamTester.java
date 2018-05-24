package com.example.daily.jdk;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.daily.redis.User;

/**
 * @author: zhiyuan
 * @date: 2018-04-11
 * @project: daily
 * @description:
 */

public class StreamTester {

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

    /*
     * Determines if the input object matches some criteria.
     */
    @Test
    public void testPredicate() {
        List<User> users = new ArrayList<User>() {
            private static final long serialVersionUID = 1L;

            {
                add(new User("emails1", "aa", 22));
                add(new User("emails2", "bb", 20));
                add(new User("emails3", "cc", 19));
                add(new User(null, "dd", 35));
            }
        };
        // 折腾一下Predicate
        Predicate<User> predicate = u -> u.getEmail() != null;

        System.out.println("aaaaaaaaaaaaaa");
        users.stream().filter(predicate).forEach(item -> System.out.println(item.getUsername()));
        System.out.println("aaaaaaaaaaaaaa");
        users.stream().filter(predicate.negate())
                .forEach(item -> System.out.println(item.getUsername()));
        System.out.println("aaaaaaaaaaaaaa");
        users.stream().filter(predicate.and(a -> a.getAge() > 20))
                .forEach(item -> System.out.println(item.getUsername()));

        System.out.println("------------------------");
        List<String> emails = users.stream()
                .filter(u -> u.getEmail() != null && u.getEmail().length() > 0)
                .map(u -> u.getEmail()).collect(Collectors.toList());
        System.out.println(String.join("|", emails));
        assertEquals(users.size() - 1, emails.size());
    }

    @Test
    public void testStrings() {
        String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert",
                "Michael", "Linda" };
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        System.out.println(String.join(">", stringArray));
    }

    /*
     * 
     * Consumer接口的文档声明如下：
     * 
     * An operation which accepts a single input argument and returns no result.
     * Unlike most other functional interfaces, Consumer is expected to operate via
     * side-effects.
     * 
     * 即接口表示一个接受单个输入参数并且没有返回值的操作。不像其它函数式接口，Consumer接口期望执行带有副作用的操作(
     * Consumer的操作可能会更改输入参数的内部状态)。
     */
    @Test
    public void testConsumer() {
        List<User> users = new ArrayList<User>() {
            private static final long serialVersionUID = 1L;

            {
                add(new User("emails1", "aa", 22));
                add(new User("emails2", "bb", 20));
                add(new User("emails3", "cc", 19));
                add(new User(null, "dd", 35));
            }
        };

        Consumer<User> squarConsumer = a -> a.setAge(a.getAge() * 2);
        users.forEach(a -> squarConsumer.accept(a));
        users.forEach(System.out::print);

    }

    /*
     * A functional interface is any interface that contains only one abstract
     * method. (A functional interface may contain one or more default methods or
     * static methods.) Because a functional interface contains only one abstract
     * method, you can omit the name of that method when you implement it.
     * 
     * https://blog.csdn.net/pzxwhc/article/details/48314039
     */
    @Test
    public void testFunction() {
        // showing the difference between Function, Consumer and Predicate
        String name = "";
        String name1 = "1234";
        String name2 = "12345";
        System.out.println(validInput(name, inputStr -> inputStr.isEmpty() ? "名字不能为空" : inputStr));
        System.out
                .println(validInput(name1, inputStr -> inputStr.length() > 3 ? "名字过长" : inputStr));
        System.out.println("--------------------------------------");
        validInput2(name, inputStr -> System.out.println(inputStr.isEmpty() ? "名字不能为空!" : "名字正常"));
        validInput2(name1, inputStr -> System.out.println(inputStr.isEmpty() ? "名字不能为空" : "名字正常"));
        System.out.println("--------------------------------------");

        System.out.println(
                validInput3(name, inputStr -> !inputStr.isEmpty() && inputStr.length() <= 3));
        System.out.println(
                validInput3(name1, inputStr -> !inputStr.isEmpty() && inputStr.length() <= 3));
        System.out.println(
                validInput3(name2, inputStr -> !inputStr.isEmpty() && inputStr.length() <= 3));

    }

    public String validInput(String name, Function<String, String> function) {
        return function.apply(name);
    }

    public void validInput2(String name, Consumer<String> function) {
        function.accept(name);
    }

    public boolean validInput3(String name, Predicate<String> function) {
        return function.test(name);
    }

    /**
     * Integer值不不可变的
     */
    @Test
    public void testListInteger() {
        Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7, 8 };
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
        listOfIntegers.stream().map(a -> a * 3);
        System.out.println(
                "\r\nSum of integers: " + listOfIntegers.stream().reduce(Integer::sum).get());
    }

}
