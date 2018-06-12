package com.example.daily.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author: zhiyuan
 * @date: 2018-06-07
 * @project: daily
 * @description:
 */

public class LqJinSeCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern
            .compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp4|zip|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href.startsWith("http://www.ics.uci.edu/");
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            Document doc = Jsoup.parse(html);
            Elements articleMains = doc.select(
                    "#app > div.js-main > div > div.js-left > div.ja-article-list > .article-main ol");
            articleMains.stream().forEach(a -> {
                // 获取新闻作者信息以及阅读量
                Element authorInfo = a.select("ul > li.article-info").last();
                // 作者头像地址
                String authorImgUrl = authorInfo.select("a > img.author").attr("src");
                // 作者名称
                String authorName = authorInfo.select("a > span").text();
                // 阅读量
                String readers = authorInfo.select("span").last().text();

                System.out.printf("authorImgUrl: %s, authorName : %s, readers: %s\n\r",
                        authorImgUrl, authorName, readers);
                Element link = a.selectFirst("> a.article-img");
                // 新闻地址
                Document newDetailDoc = null;
                try {
                    newDetailDoc = Jsoup.connect(link.attr("href")).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Element article = newDetailDoc
                        .select("#app > div:nth-child(2) > div > div.js-left > div.js-article")
                        .first();
                List<String> tags = new ArrayList<String>();
                article.select("div.tags > a").forEach(tag -> tags.add(tag.text()));
                article.select("div").forEach(div -> div.remove());

                // article tags
                System.out.println(String.join("|", tags));
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                // 文章正文
                System.out.println(article.html());

                // 标题
                System.out.println(link.attr("title"));
                // 预览图片地址
                System.out.println(link.select("img").attr("src"));
            });
        }
    }

}
