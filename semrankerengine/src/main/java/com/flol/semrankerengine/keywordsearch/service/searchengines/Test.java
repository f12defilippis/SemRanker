package com.flol.semrankerengine.keywordsearch.service.searchengines;

public class Test extends SearchengineBaseService{

	
	public static void main(String[] args) {
		String domain = "http://www.webmasterradio.fm/seo-rockstars";
		Test test = new Test();
		
		System.out.println(test.getDomainName(domain));
		
	}
}
