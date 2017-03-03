package com.crawl;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class ControllerMain {

	public static int count = 0;

	public synchronized static int getCont() {
		return ++count;
	}
	
	public static void main(String[] args) throws Exception {
		String crawlStorageFolder = "E:/mo_c";
		int numberOfCrawlers = 5;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		//无限深度
		config.setMaxDepthOfCrawling(-1);
		config.setPolitenessDelay(200);
		config.setResumableCrawling(true);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
		controller.addSeed("https://avmo.pw/cn/genre");
		controller.addSeed("https://avmo.pw/cn/actresses");
		controller.addSeed("https://avmo.pw/cn/released");
		controller.addSeed("https://avmo.pw/cn/popular");
		controller.addSeed("https://avmo.pw/cn/");

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(Crawler4Mo.class, numberOfCrawlers);
	}
}
