package com.example.daily.crawler;

import java.util.Map;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author: zhiyuan
 * @date: 2018-06-07
 * @project: daily
 * @description:
 */

public class JSCrawlerUtils {
    class LqJinSeCrawler extends WebCrawler {
        private final Pattern FILTERS = Pattern
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
                    Element link = a.selectFirst("> a.article-img");

                    // 新闻地址
                    System.out.println(link.attr("href"));
                    // 标题或者预览
                    System.out.println(link.attr("title"));
                    // 预览图片地址
                    System.out.println(link.select("img").attr("src"));
                    System.out.println("--------------------------");
                });
            }
        }

    }

    public static Map startCrawler(final String url) throws Exception {
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 1;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first URLs that
         * are fetched and then the crawler starts following links which are found in
         * these pages
         */
        // controller.addSeed("https://www.jinse.com/depth");
        controller.addSeed(url);

        /*
         * Start the crawl. This is a blocking operation, meaning that your code will
         * reach the line after this only when crawling is finished.
         */
        controller.start(LqJinSeCrawler.class, numberOfCrawlers);
        return null;

    }
}
