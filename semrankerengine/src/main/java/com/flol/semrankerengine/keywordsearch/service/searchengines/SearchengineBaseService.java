package com.flol.semrankerengine.keywordsearch.service.searchengines;

import java.util.Date;
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
	protected static Pattern patternUrlFtpName;

//	protected static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	protected static final String DOMAIN_NAME_PATTERN = "([A-Za-z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[A-Za-z]{2,6}";
	
	protected static final String URL_NAME_PATTERN = "https?://[^/&]+(/[^/&]+){0,15}";	
	protected static final String URL_NAME_PATTERN_FTP = "ftp://[^/&]+(/[^/&]+){0,15}";	

	protected static final Integer MAX_LOOP = 20;	

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());  	
	
	public SearchengineBaseService()
	{
		patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
		patternUrlName = Pattern.compile(URL_NAME_PATTERN, Pattern.CASE_INSENSITIVE);
		patternUrlFtpName = Pattern.compile(URL_NAME_PATTERN_FTP, Pattern.CASE_INSENSITIVE);
	}
	
	public SearchResultItemsTO searchKeyword(SearchKeywordParameterTO parameter) throws Exception
	{
		SearchResultItemsTO returnValue = new SearchResultItemsTO();
		StopWatch stopWatch = new StopWatch(parameter.getSearchEngineCountry().getName() + " keyword: " + parameter.getKeyword() + " Proxy: " + parameter.getProxyHost() + ":" + parameter.getProxyPort() + " UA: " + parameter.getUserAgent());
		try {
				int loops = 0;
				Date lastScan = new Date();
				boolean error = false;
				while(returnValue.getItems().size()< Integer.valueOf(parameter.getNumResultToSearch()) && loops < MAX_LOOP && !error)
				{
					if(returnValue.getItems().size()>0)
					{
						SemRankerUtil.waitBetweenCalls();
					}
					
					stopWatch.start("Searchengine call");
					lastScan = new Date();
					byte[] doc = executeCall(parameter.getSearchEngineCountry().getSearchengine().getRequest(),parameter.getKeyword(), parameter.getUserAgent(), parameter.getProxyHost(), parameter.getProxyPort(), parameter.getProxyUser(), parameter.getProxyPassword(), parameter.getTld(), 
							String.valueOf(parameter.getSearchEngineCountry().getSearchengine().getMaxResultsPerPage()), parameter.getUule(), getFirstResult(returnValue.getItems().size()), parameter.getAcceptLanguage(), parameter.getHost());
					stopWatch.stop();
					
					if(parameter.getSearchEngineCountry().getSearchengine().isCachePage())
					{
						stopWatch.start("Document compress");
						returnValue.setCachePage(SemRankerUtil.compress(doc));
						stopWatch.stop();
					}
					
					stopWatch.start("Data parse");
					Document document = Jsoup.parse(new String(doc));
					Integer pos = returnValue.getItems().size()==0 ? 1 : returnValue.getItems().get(returnValue.getItems().size()-1).getPosition() + 1;
					List<SearchResultItemTO> items = parseSearchResult(document,pos);
					if(items==null || items.size()==0)
					{
						throw new Exception("Errors in data retrieve");
					}
					returnValue.getItems().addAll(items);
					stopWatch.stop();
					loops++;
				}
				returnValue.setLastScan(lastScan);
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
		
	public List<SearchResultItemTO> parseSearchResult(Document document, Integer position) throws Exception{
		return null;
	}
	
	public Integer getFirstResult(int numResult)
	{
		return numResult+1;
	}

	private byte[] executeCall(String requestString, String keyword, String userAgent, String proxyHost, String proxyPort, String proxyUser, String proxyPassword, String tld, String numResultToSearch, String uule, Integer firstResult, String acceptLanguage, String host) throws Exception
	{
		String request = requestString.replace("{TLD}", tld).replace("{KEYWORD}", keyword).replace("{NUM_RESULTS}", numResultToSearch).replace("{UULE}", uule!=null ? uule : "").replace("{FIRST_RESULT}", String.valueOf(firstResult));

//		URL website = new URL(request);
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, Integer.valueOf(proxyPort)));
//		HttpURLConnection httpUrlConnetion = (HttpURLConnection) website.openConnection(proxy);
//		httpUrlConnetion.setRequestProperty("User-Agent", userAgent);
//		httpUrlConnetion.connect();
//		
//		String line = null;
//		StringBuilder tmp = new StringBuilder();
//		BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConnetion.getInputStream()));
//		while ((line = in.readLine()) != null) {
//		    tmp.append(line);
//		}

//		Document doc = Jsoup.parse(tmp.toString());		
//		byte[] doc = String.valueOf(tmp).getBytes();
		
		
		ProxyUtil.setProxy(proxyHost, proxyPort, proxyUser, proxyPassword);
		logger.info("CALL: " + request + " SYSPROXY: " + System.getProperty("https.proxyHost") + " APPPROXY: " + proxyHost);
		byte[] doc = Jsoup
				.connect(request)
				.userAgent(userAgent)
				.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
				.header("Accept-Encoding", "gzip, deflate")
				.header("Connection", "keep-alive")
				.header("Accept-Language", acceptLanguage)
				.header("Host", host)
				.header("cache-control", "no-cache")
				.header("pragma", "no-cache")
				.timeout(60000).execute().bodyAsBytes();
		
		ProxyUtil.removeProxy(proxyHost);
		return doc;
	}
	
	
	
	protected String getUrl(String urlToParse) {
		String url = "";
		Matcher matcher = patternUrlName.matcher(urlToParse);
		Matcher matcherFtp = patternUrlFtpName.matcher(urlToParse);
		if (matcher.find()) {
			url = matcher.group(0).toLowerCase().trim();
			url = url.replaceAll(" ", "").replaceAll("/url?q=", "").trim();
		}else if(matcherFtp.find())
		{
			url = matcherFtp.group(0).toLowerCase().trim();
			url = url.replaceAll(" ", "").replaceAll("/url?q=", "").trim();
		}
		return url;
	}

	protected String getDomainName(String url) {

		String domainName = "";
		Matcher matcher = patternDomainName.matcher(url);
		if (matcher.find()) {
			domainName = matcher.group(0).toLowerCase().trim();
			domainName = domainName.replaceAll("www2?.", "").replaceAll(" ", "").replaceAll("http://", "").replaceAll("/url?q=", "").trim();
		}
		return domainName;

	}
	
	
}
