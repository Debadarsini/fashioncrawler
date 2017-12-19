package com.startup.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class TestCrawler {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		 String crawlStorageFolder = "/Users/aksmahaj/crawler";
	        int numberOfCrawlers = 7;

	        CrawlConfig config = new CrawlConfig();
	        config.setCrawlStorageFolder(crawlStorageFolder);
	        config.setUserAgentString("My-user-agent");
	       // config.

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
	        //controller.addSeed("https://www.amazon.in/");
			controller.addSeed("https://www.amazon.in/Katso-Mens-Cotton-T-Shirt-KATSO-HOOD-FULL-BLACK-L_Large_Black/dp/B01HQ4O12A/ref=sr_1_2?s=apparel&ie=UTF8&qid=1513415857&sr=1-2&nodeID=1968024031&psd=1&keywords=shirts");
	       // controller.addSeed("http://www.ics.uci.edu/~welling/");
	    	//controller.addSeed("http://www.ics.uci.edu/");

	        /*
	         * Start the crawl. This is a blocking operation, meaning that your code
	         * will reach the line after this only when crawling is finished.
	         */
	        controller.start(MyCrawler.class, numberOfCrawlers);
	}

}
