package com.example.daily.jdk.lambda;

import com.example.daily.redis.User;
import org.junit.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zhiyuan
 * @date: 2018-04-11
 * @project: daily
 * @description:
 */

public class LambdaTester {

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

    private List<User> initUserData() {
        List<User> users = new ArrayList<User>() {
            private static final long serialVersionUID = 1L;

            {
                add(new User("123", "emails1", "aa", 22));
                add(new User("324", "emails2", "bb", 20));
                add(new User("456", "emails3", "cc", 19));
                add(new User("123", "emails3222", "dd", 23));
                add(new User("987", null, "dd", 35));
                add(new User("324", null, "ee", 30));
            }
        };
        return users;
    }

    private void printLine() {
        System.out.printf("%s\r\n", "-------------------华丽的分割线-------------------");
    }

    @Test
    public void sumAge() {
        List<User> users = initUserData();
        int ages = users.stream().filter(a -> a.getEmail() != null).mapToInt(User::getAge).sum();
        long count = users.stream().filter(a -> a.getEmail() != null).count();
        System.out.printf("age求和：%d,数量：%d\r\n", ages, count);
    }

    @Test
    public void testObjectGroupBy() {
        // turn User list to Map<String, User>
        List<User> users = initUserData();
        Map map = users.stream().collect(Collectors.groupingBy(User::getId, Collectors.toList()));
    }

    @Test
    public void testObjectSubList() {
        // turn User list to Map<String, User>
        List<User> users = initUserData();
        List<User> subUsers = users.subList(0, 99);
        System.out.printf("%s\r\n", "hahaha");
    }


    @Test
    public void testObject2Map() {
        // turn User list to Map<String, User>
        List<User> users = new ArrayList<>();
        Map<String, User> map = users.stream().collect(Collectors.toMap(User::getId, v -> v));
        System.out.println(map.keySet());
        System.out.println(map.values());
    }

    // https://zacard.net/2016/03/17/java8-list-to-map/

    /**
     * accounts.stream().collect(Collectors.toMap(Account::getUsername,
     * Function.identity(), (key1, key2) -> key2));
     */
    @Test
    public void List2MapExceptions() {
        List<User> users = initUserData();
        /**
         * 在Map中有相同的key存在
         */
        // Map<String, User> emailUserException = users.stream()
        // .collect(Collectors.toMap(User::getEmail, Function.identity()));
        Map<String, String> emailUser = users.stream().collect(
                Collectors.toMap(User::getEmail, User::getUsername, (key1, key2) -> key2));
        System.out.printf("----------------------", emailUser);
    }

    @Test
    public void arrayStr2ListLong() {
        String[] ids = new String[]{"123", "234", "456", "678"};
        Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList());
        List<Long> LongIds = new ArrayList<Long>() {{
            addAll(Arrays.stream(ids).map(Long::parseLong).collect(Collectors.toList()));
        }};
        LongIds.stream().forEach(System.out::println);
    }

    @Test
    public void test() {
        // return a immutable empty list
        List<User> strs = Collections.emptyList();
        for (User item : strs) {
            System.out.println(item);
        }
        System.out.println(strs.size());
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
    }

    @Test
    public void testStringUrls() {
        List<User> users = initUserData();
        List<String> emails = users.stream().map(User::getEmail).collect(Collectors.toList());
        System.out.println(emails);

        List<String> urls = new ArrayList<String>() {
            private static final long serialVersionUID = 1L;

            {
                add("");
                add(" ");
                add(" ");
                add("b");
                add("a");
                add("aaaa");
                add(null);
            }
        };
        List<String> filteredUrls = urls.stream().filter(a -> a != null && a.trim().length() != 0)
                .collect(Collectors.toList());
        System.out.println(String.join("-", filteredUrls));
    }

    @Test
    public void testStrings() {
        String[] stringArray = {"Barbara", "James", "Mary", "John", "Patricia", "Robert",
                "Michael", "Linda"};
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        System.out.println(String.join(">", stringArray));
    }

    /**
     * Integer值不不可变的
     */
    @Test
    public void testListInteger() {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
        List<Integer> mappedIntegers = listOfIntegers.stream().map(a -> a * 3)
                .collect(Collectors.toList());
        System.out.println(
                "\r\nSum of integers: " + listOfIntegers.stream().reduce(Integer::sum).get());
        System.out.println(
                "\r\nSum of integers: " + mappedIntegers.stream().reduce(Integer::sum).get());
    }

    @Test
    public void testListIntegerFlatMap() {
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));
        List<Integer> mappedIntegers = listOfIntegers.stream().map(a -> a * 3)
                .collect(Collectors.toList());
        System.out.println(
                "\r\nSum of integers: " + listOfIntegers.stream().reduce(Integer::sum).get());
        Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6));
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
        System.out.println("flatMapped:");
        outputStream.forEach(System.out::print);
    }

    @Test
    public void testList2Map() {
        List<User> users = initUserData();

        // users.stream().collect(Collectors.toMap(Account::getId, account -> account));
        Map<String, User> emailUser = users.stream()
                .collect(Collectors.toMap(User::getEmail, Function.identity()));

        List<String> listOfEmails = users.stream().map(User::getEmail).collect(Collectors.toList());
        listOfEmails.stream().forEach(System.out::println);
        System.out.println("--------------------------");
        Map<String, String> EmailNameMap = users.stream()
                .collect(Collectors.toMap(User::getEmail, User::getUsername));
        EmailNameMap.forEach((key, value) -> {
            System.out.printf("key is %s and value is %s\n\r", key, value);
        });
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        for (Map.Entry<String, String> item : EmailNameMap.entrySet()) {
            System.out.printf("key is %s and value is %s\n\r", item.getKey(), item.getValue());
        }
    }

    @Test
    public void list2MapStrings() {
        Map<String, Double> kvs = Stream.of("a:1.0", "b:2.0", "c:3.0")
                .map(elem -> elem.split(":"))
                .collect(Collectors.toMap(e -> e[0], e -> Double.parseDouble(e[1])));
        System.out.print(kvs);
    }

}
