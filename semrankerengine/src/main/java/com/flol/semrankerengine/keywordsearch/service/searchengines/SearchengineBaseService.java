package com.flol.semrankerengine.keywordsearch.service.searchengines;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import com.flol.semrankercommon.util.ProxyUtil;
import com.flol.semrankercommon.util.SemRankerUtil;
import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;

public abstract class SearchengineBaseService {

	protected static Pattern patternDomainName;
	protected static Pattern patternUrlName;

	protected Matcher matcher;
	protected static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	protected static final String URL_NAME_PATTERN = "https?://[^/&]+(/[^/&]+){0,4}";	
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());  	
	
	public SearchengineBaseService()
	{
		patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
		patternUrlName = Pattern.compile(URL_NAME_PATTERN, Pattern.CASE_INSENSITIVE);
	}
	
	public SearchResultItemsTO searchKeyword(SearchKeywordParameterTO parameter) throws Exception
	{
		SearchResultItemsTO returnValue = new SearchResultItemsTO();
		StopWatch stopWatch = new StopWatch(parameter.getSearchEngine().getName() + " keyword: " + parameter.getKeyword() + " Proxy: " + parameter.getProxyHost() + ":" + parameter.getProxyPort() + " UA: " + parameter.getUserAgent());
		try {

				stopWatch.start("Searchengine call");
				byte[] doc = executeCall(parameter.getSearchEngine().getRequest(),parameter.getKeyword(), parameter.getUserAgent(), parameter.getProxyHost(), parameter.getProxyPort(), parameter.getProxyUser(), parameter.getProxyPassword(), parameter.getTld(), parameter.getNumResultToSearch(), parameter.getUule());
				stopWatch.stop();
				
				if(parameter.getSearchEngine().isCachePage())
				{
					stopWatch.start("Document compress");
					returnValue.setCachePage(SemRankerUtil.compress(doc));
					stopWatch.stop();
				}
				
				stopWatch.start("Data parse");
				Document document = Jsoup.parse(new String(doc));
				List<SearchResultItemTO> items = parseSearchResult(document);
				returnValue.getItems().addAll(items);
				stopWatch.stop();
				
				logger.info(stopWatch.prettyPrint());
		} catch (Exception e) {
			if(stopWatch.isRunning())
			{
				stopWatch.stop();
			}
//			logger.error(stopWatch.prettyPrint());
			throw e;
		}
		return returnValue;
	}
		
	public List<SearchResultItemTO> parseSearchResult(Document document) {
		return null;
	}

	private byte[] executeCall(String requestString, String keyword, String userAgent, String proxyHost, String proxyPort, String proxyUser, String proxyPassword, String tld, String numResultToSearch, String uule) throws Exception
	{
		String request = requestString.replace("{TLD}", tld).replace("{KEYWORD}", keyword).replace("{NUM_RESULTS}", numResultToSearch).replace("{UULE}", uule!=null ? uule : "");
		ProxyUtil.setProxy(proxyHost, proxyPort, proxyUser, proxyPassword);
		byte[] doc = Jsoup
				.connect(request)
				.userAgent(userAgent)
				.timeout(60000).execute().bodyAsBytes();
		ProxyUtil.removeProxy(proxyHost);
		return doc;
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