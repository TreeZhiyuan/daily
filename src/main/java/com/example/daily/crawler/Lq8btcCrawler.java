package com.example.daily.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

public class Lq8btcCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern
            .compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp4|zip|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href.startsWith("http://www.ics.uci.edu/");
    }

    @Override
    public void visit(Page page) {
        final String anchorPoint = "#zan-main > div.container div.bodyer > div.article-list > article.article.clearfix";
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            Document doc = Jsoup.parse(html);
            Elements articlePreviews = doc.select(anchorPoint);
            articlePreviews.stream().forEach(preview -> {
                Element coverImg = preview.selectFirst(
                        "div.article-thumbnail.pull-left img.img-responsive.wp-post-image");
                String coverImgUrl = coverImg.attr("src");
                Element articlePreviw = preview
                        .selectFirst("div.article-content > div.article-title.visible-lg > a");
                String articleTitle = articlePreviw.attr("title");
                String articleUrl = articlePreviw.attr("href");
                Document articleDetailPage = null;
                try {
                    articleDetailPage = Jsoup.connect(articleUrl).get();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (articleDetailPage != null) {
                    Element root = articleDetailPage.selectFirst(
                            "#zan-bodyer > div > div > div.col-md-8.col-sm-8 > article.single-article.clearfix");
                    Element articleContentNode = root.selectFirst("div.article-content");
                    articleContentNode.select("div").forEach(div -> div.remove());
                    String articleContent = articleContentNode.html();
                    Element detailTitleNode = root.selectFirst("div.article-title > h1");
                    articleTitle = detailTitleNode.text();
                    Elements tags = root.select(
                            "div.share.clearfix > div.share.clearfix > div.tags.pull-left.hidden-xs > a");
                    Set tagsSet = new HashSet<String>() {
                        {
                            tags.forEach(tag -> add(tag.text()));
                        }
                    };

                    Element crumbsNode = root.selectFirst("div.single-crumbs.clearfix");
                    String author = crumbsNode.selectFirst("span > a[title]").text();
                    String readers = root.selectFirst("span.pull-right.fa-eye-span").text();
                    System.out.printf(
                            "aaaaaaa \r\n %s aaaaaaaaaaaa %s\n\rRRRRRRRRRR \n\r%s****************%s\r\n**JJJJJJJJJJJ**%s\r\n",
                            articleContent, articleTitle, tagsSet, author, readers);
                }
            });
        }
    }

}
