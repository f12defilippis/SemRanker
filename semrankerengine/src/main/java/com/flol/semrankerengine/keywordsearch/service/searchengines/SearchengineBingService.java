package com.flol.semrankerengine.keywordsearch.service.searchengines;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flol.semrankercommon.util.ProxyUtil;
import com.flol.semrankercommon.util.SemRankerUtil;
import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;

@Service("bingService")
public class SearchengineBingService extends SearchengineBaseService{

	private static final String BING_REQUEST = "http://www.bing.com/search?q={KEYWORD}&count={NUM_RESULTS}&cc={TLD}";
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());  

	public SearchResultItemsTO searchKeyword(SearchKeywordParameterTO parameter) throws IOException
	{
		SearchResultItemsTO returnValue = new SearchResultItemsTO();
		try {
				byte[] doc = executeBingCall(parameter.getKeyword(), parameter.getUserAgent(), parameter.getProxyHost(), parameter.getProxyPort(), parameter.getProxyUser(), parameter.getProxyPassword(), parameter.getTld(), parameter.getNumResultToSearch(), parameter.getUule());

				returnValue.setCachePage(SemRankerUtil.compress(doc));
				
				Document document = Jsoup.parse(new String(doc));
				List<SearchResultItemTO> items = parseSearchResult(document);
				returnValue.getItems().addAll(items);
				logger.debug("KEYWORD: " + parameter.getKeyword() + " UserAgent: " + parameter.getUserAgent() + " Proxy: " + parameter.getProxyHost() + ":" + parameter.getProxyPort() + " Successful processed");
		} catch (IOException e) {
			logger.error("KEYWORD=" + parameter.getKeyword() + " UserAgent: " + parameter.getUserAgent() + " Proxy: " + parameter.getProxyHost() + ":" + parameter.getProxyPort());
			throw e;
		}
		return returnValue;
	}
	
	
	private byte[] executeBingCall(String keyword, String userAgent, String proxyHost, String proxyPort, String proxyUser, String proxyPassword, String tld, String numResultToSearch, String uule) throws IOException
	{
		String request = BING_REQUEST.replace("{TLD}", tld).replace("{KEYWORD}", keyword).replace("{NUM_RESULTS}", numResultToSearch);
		ProxyUtil.setProxy(proxyHost, proxyPort, proxyUser, proxyPassword);
		byte[] doc = Jsoup
				.connect(request)
				.userAgent(userAgent)
				.timeout(60000).execute().bodyAsBytes();
		ProxyUtil.removeProxy(proxyHost);
		return doc;
	}

	protected List<SearchResultItemTO> parseSearchResult(Document doc)
	{
		List<SearchResultItemTO> items = new ArrayList<SearchResultItemTO>();
		int position = 1;

		Elements hrclassr = doc.getElementsByClass("b_algo");
		for (int i = 0 ; i < hrclassr.size() ; i++) {
			Element link = hrclassr.get(i).select("a[href]").get(0);
			String temp = link.attr("href");
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
		return items;
	}	
	
	
}
