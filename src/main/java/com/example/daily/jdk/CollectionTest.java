package com.example.daily.jdk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author: Zhiyuan Cui
 * project: daily
 * date: 2019/4/18 11:42
 * description:
 */
public class CollectionTest {
    @Test
    public void Set集合转成数组无序() {
        Set<String> emails = new HashSet<String>() {{
            add("aaaaa1");
            add("aaaaa2");
            add("aaaaa3");
            add("aaaaa4");
        }};

        // 无序
        String[] strEmails = emails.toArray(new String[emails.size()]);
        for (String item : strEmails) {
            System.out.println(item);
        }

        System.out.println(String.valueOf(null));
    }

    @Test
    public void List集合转成数组有序() {
        List<String> emails = new ArrayList<String>() {{
            add("aaaaa1");
            add("aaaaa2");
            add("aaaaa3");
            add("aaaaa4");
        }};

        // 有序
        String[] strEmails = emails.toArray(new String[emails.size()]);
        for (String item : strEmails) {
            System.out.println(item);
        }
    }
}
