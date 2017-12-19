package com.startup.crawler;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp4|zip|gz))$");

	/**
	 * This method receives two parameters. The first parameter is the page in
	 * which we have discovered this new url and the second parameter is the new
	 * url. You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic). In this example,
	 * we are instructing the crawler to ignore urls that have css, js, git, ...
	 * extensions and to only accept urls that start with
	 * "http://www.ics.uci.edu/". In this case, we didn't need the referringPage
	 * parameter to make the decision.
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		/*return 
				 * !FILTERS.matcher(href).matches() &&
				 href.startsWith("https://www.amazon.in/"); */
		//return href.contains("fashion");
		return href.equals("https://www.amazon.in/Katso-Mens-Cotton-T-Shirt-KATSO-HOOD-FULL-BLACK-L_Large_Black/dp/B01HQ4O12A/ref=sr_1_2?s=apparel&ie=UTF8&qid=1513415857&sr=1-2&nodeID=1968024031&psd=1&keywords=shirts");
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			Set<WebURL> links = htmlParseData.getOutgoingUrls();

			Document doc = Jsoup.parse(html);
			String title = doc.title();
			//String body = doc.body().text();

			System.out.printf("Title: %s%n", title);
			String description = doc.select("meta[name=description]").first().attr("content");
			System.out.println("Description : " + description);

			String keywords = doc.select("meta[name=keywords]").first().attr("content");
			System.out.println("Keywords : " + keywords);

			Element body = doc.body();
			Elements media = doc.select("[src]");
			Elements price = doc.select("div[id^=price]");

			for(Element e : price) {
				System.out.println(e.text());
			}

			// Elements imports = doc.select("link[href]");
	        //External Links and media on the page
	        /*
	        print("\nMedia: (%d)", media.size());
	        for (Element src : media) {
	            if (src.tagName().equals("img"))
	                print(" * %s: <%s> %sx%s (%s)",
	                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
	                        trim(src.attr("alt"), 20));
	            else
	                print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
	        }*/

            /*
            Elements price = body.getElementsMatchingText("Price");
            Iterator<Element> itr = price.iterator();

            while(itr.hasNext()) {
                Element elem = itr.next();
                print(elem.html());
            }*/

            //System.out.println(price.text());
			/*System.out.printf("Body: %s", body);
			System.out.println("html : " + text);
			System.out.println("Text length: " + text.length());
			System.out.println("Html length: " + html.length());
			System.out.println("Number of outgoing links: " + links.size()); */
		}
	}


	private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

}
