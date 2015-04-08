package com.flol.semrankerengine.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flol.semrankercommon.util.ProxyUtil;
import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;

@Service("googleService")
public class SearchengineGoogleService{

	private static Pattern patternDomainName;
	private static Pattern patternUrlName;

	private Matcher matcher;
	private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	private static final String URL_NAME_PATTERN = "https?://[^/&]+(/[^/&]+){0,4}";	

	
	private static final String GOOGLE_REQUEST = "https://www.google.{TLD}/search?q={KEYWORD}&num={NUM_RESULTS}&uule={UULE}";
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	public SearchengineGoogleService()
	{
		patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
		patternUrlName = Pattern.compile(URL_NAME_PATTERN, Pattern.CASE_INSENSITIVE);
	}

	public SearchResultItemsTO searchKeyword(SearchKeywordParameterTO parameter) throws IOException
	{
		SearchResultItemsTO returnValue = new SearchResultItemsTO();
		try {
				Document doc = executeGoogleCall(parameter.getKeyword(), parameter.getUserAgent(), parameter.getProxyHost(), parameter.getProxyPort(), parameter.getProxyUser(), parameter.getProxyPassword(), parameter.getTld(), parameter.getNumResultToSearch(), parameter.getUule());
				List<SearchResultItemTO> items = parseSearchResult(doc);
				returnValue.getItems().addAll(items);
				logger.debug("KEYWORD: " + parameter.getKeyword() + " UserAgent: " + parameter.getUserAgent() + " Proxy: " + parameter.getProxyHost() + ":" + parameter.getProxyPort() + " Successful processed");
		} catch (IOException e) {
			logger.error("KEYWORD=" + parameter.getKeyword() + " UserAgent: " + parameter.getUserAgent() + " Proxy: " + parameter.getProxyHost() + ":" + parameter.getProxyPort());
			throw e;
		}
		return returnValue;
	}
	
	
	private Document executeGoogleCall(String keyword, String userAgent, String proxyHost, String proxyPort, String proxyUser, String proxyPassword, String tld, String numResultToSearch, String uule) throws IOException
	{
		String request = GOOGLE_REQUEST.replace("{TLD}", tld).replace("{KEYWORD}", keyword).replace("{NUM_RESULTS}", numResultToSearch).replace("{UULE}", uule!=null ? uule : "");
		ProxyUtil.setProxy(proxyHost, proxyPort, proxyUser, proxyPassword);
		Document doc = Jsoup
				.connect(request)
				.userAgent(userAgent)
				.timeout(30000).get();
		ProxyUtil.removeProxy(proxyHost);
		return doc;
	}

	private List<SearchResultItemTO> parseSearchResult(Document doc)
	{
		List<SearchResultItemTO> items = new ArrayList<SearchResultItemTO>();
		int position = 1;

		Elements hrclassr = doc.getElementsByClass("r");
		for (int i = 0 ; i < hrclassr.size() ; i++) {
			Element link = hrclassr.get(i).select("a[href]").get(0);
			String temp = link.attr("href");
			if (temp.startsWith("/url?q=")) {
				String domainName = getDomainName(temp);
				if (!domainName.equals("webcache.googleusercontent.com")
						&& !(domainName.equals("www.youtube.com") && i>0 && getDomainName(hrclassr.get(i - 1).select("a[href]").get(0).attr("href")).equals("www.youtube.com"))
						&& !(domainName == null || domainName.equals(""))) {
					SearchResultItemTO item = new SearchResultItemTO();
					item.setDomain(domainName);
					item.setUrl(getUrl(temp));
					item.setPosition(position);
					items.add(item);
					position++;
				}
			}
		}
		return items;
	}
		
	private String getUrl(String urlToParse) {
		String url = "";
		matcher = patternUrlName.matcher(urlToParse);
		if (matcher.find()) {
			url = matcher.group(0).toLowerCase().trim();
		}
		return url;
	}

	private String getDomainName(String url) {

		String domainName = "";
		matcher = patternDomainName.matcher(url);
		if (matcher.find()) {
			domainName = matcher.group(0).toLowerCase().trim();
		}
		return domainName;

	}	
	
}
