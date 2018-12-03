package com.example.daily.jdk;

/**
 * @Auther: Zhiyuan Cui
 * @Project: daily
 * @Date: 2018/12/3 14:34
 * @Description:
 */

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class News{
    private int hits;
    private int id;
    private String title;

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
public class NewsManager {

    /**
     * @param args
     */
    public static void main(String[] args) {

        List newss=getNewsList();

        for(int i=0;i<newss.size();i++)
        {
            News news=(News)newss.get(i);

            System.out.println("id:"+news.getId());
            System.out.println("title:"+news.getTitle());
            System.out.println("hits:"+news.getHits());
            System.out.println("------------------------");
        }

    }


    public static List getNewsList()
    {


        News news1=new News();
        news1.setHits(1);
        news1.setId(1);
        news1.setTitle("test1");

        News news2=new News();
        news2.setHits(7);
        news2.setId(2);
        news2.setTitle("test2");

        News news3=new News();
        news3.setHits(3);
        news3.setId(3);
        news3.setTitle("test3");

        News news4=new News();
        news4.setHits(5);
        news4.setId(4);
        news4.setTitle("test4");

        List list=new ArrayList(){{
            add(news1);
            add(news2);
            add(news3);
            add(news4);
        }};

        // 按点击数倒序
        Collections.sort(list, new Comparator<News>() {
            public int compare(News news1, News news2) {
                int hits0 = news1.getHits();
                int hits1 = news2.getHits();
                if (hits1 > hits0) {
                    return 1;
                } else if (hits1 == hits0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        return list;
    }

}