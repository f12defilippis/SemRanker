package com.flol.semrankerengine.keywordsearch.service.searchengines;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.flol.semrankerengine.dto.SearchResultItemTO;

@Service("yahooService")
public class SearchengineYahooService extends SearchengineBaseService{
	
	@Override
	public List<SearchResultItemTO> parseSearchResult(Document doc)
	{
		List<SearchResultItemTO> items = new ArrayList<SearchResultItemTO>();
		int position = 1;

		Elements hrclassr = doc.getElementsByClass("res");
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
