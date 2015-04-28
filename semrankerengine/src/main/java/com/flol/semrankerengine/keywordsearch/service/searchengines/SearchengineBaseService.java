package com.flol.semrankerengine.keywordsearch.service.searchengines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchengineBaseService {

	protected static Pattern patternDomainName;
	protected static Pattern patternUrlName;

	protected Matcher matcher;
	protected static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	protected static final String URL_NAME_PATTERN = "https?://[^/&]+(/[^/&]+){0,4}";	

	public SearchengineBaseService()
	{
		patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
		patternUrlName = Pattern.compile(URL_NAME_PATTERN, Pattern.CASE_INSENSITIVE);
	}
	

		
	protected String getUrl(String urlToParse) {
		String url = "";
		matcher = patternUrlName.matcher(urlToParse);
		if (matcher.find()) {
			url = matcher.group(0).toLowerCase().trim();
		}
		return url;
	}

	protected String getDomainName(String url) {

		String domainName = "";
		matcher = patternDomainName.matcher(url);
		if (matcher.find()) {
			domainName = matcher.group(0).toLowerCase().trim();
		}
		return domainName;

	}	
	
}
