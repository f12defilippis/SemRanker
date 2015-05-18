package com.flol.semrankerengine.keywordsearch.service.searchengines;

public class Test extends SearchengineBaseService{

	public static void main(String[] args) {
		String a = "http://www2007.wwwconference.org/workshops/paper_87.pdf";
		System.out.println(new Test().getDomainName(a));
	}
	
	
}
