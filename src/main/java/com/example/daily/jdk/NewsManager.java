package com.example.daily.jdk;

/**
 * @Auther: Zhiyuan Cui
 * @Project: daily
 * @Date: 2018/12/3 14:34
 * @Description:
 */

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
class News {
    private int hits;
    private int id;
    private String title;

    private Date publishTime;
}

public class NewsManager {

    public static void main(String[] args) {

        List<News> newss = getNewsList();

        for (News news : newss) {
            System.out.println("id:" + news.getId());
            System.out.println("title:" + news.getTitle());
            System.out.println("hits:" + news.getHits());
            System.out.println("------------------------");
        }

    }


    private static List<News> getNewsList() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        News news1 = new News();
        news1.setHits(1);
        news1.setId(1);
        news1.setTitle("test1");
        news1.setPublishTime(calendar.getTime());

        News news2 = new News();
        news2.setHits(7);
        news2.setId(2);
        news2.setTitle("test2");
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        news2.setPublishTime(calendar.getTime());

        News news3 = new News();
        news3.setHits(3);
        news3.setId(3);
        news3.setTitle("test3");
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        news3.setPublishTime(calendar.getTime());

        News news4 = new News();
        news4.setHits(5);
        news4.setId(4);
        news4.setTitle("test4");
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        news4.setPublishTime(calendar.getTime());

        List<News> list = new ArrayList<News>() {{
            add(news1);
            add(news2);
            add(news3);
            add(news4);
        }};

        // 按点击数倒序
        list = list.stream().sorted((n1, n2) -> {
                    int hits0 = n1.getHits();
                    int hits1 = n2.getHits();
                    return Integer.compare(hits1, hits0);
                }
        ).collect(Collectors.toList());

        Collections.sort(list, (n1, n2) -> {
            int hits0 = n1.getHits();
            int hits1 = n2.getHits();
            return Integer.compare(hits1, hits0);
        });

        list.sort((n1, n2) -> {
            int hits0 = n1.getHits();
            int hits1 = n2.getHits();
            return Integer.compare(hits1, hits0);
        });

        Collections.sort(list, (n1, n2) -> {
            return n2.getTitle().compareTo(n1.getTitle());
        });



        // 升序
        Collections.sort(list, (o1, o2) ->
                Long.compare(o1.getPublishTime().getTime(), o2.getPublishTime().getTime()));
        return list;
    }

}