package com.example.daily.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author: zhiyuan
 * @date: 2018-06-07
 * @project: daily
 * @description:
 */

public class CrawlerController {

    public static void main(String[] args) throws Exception {
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
//        controller.addSeed("http://www.8btc.com/");
//        controller.addSeed("https://www.jinse.com/xinwen");
        controller.addSeed("https://www.jinse.com/depth");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code will
         * reach the line after this only when crawling is finished.
         */
        controller.start(LqJinSeCrawler.class, numberOfCrawlers);
    }

}