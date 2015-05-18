package com.flol.semrankerengine.keywordsearch.service.searchengines;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.flol.semrankerengine.dto.SearchResultItemTO;

@Service("googleService")
public class SearchengineGoogleService extends SearchengineBaseService {

	@Override
	public List<SearchResultItemTO> parseSearchResult(Document doc, Integer pos)
			throws Exception {
		List<SearchResultItemTO> items = new ArrayList<SearchResultItemTO>();
		int position = pos;
		Elements hrclassr = doc.getElementsByClass("g");
		for (int i = 0; i < hrclassr.size(); i++) {
			Element link = hrclassr.get(i).select("a[href]").get(0);
			String temp = link.attr("href");
			String domainName = getDomainName(temp);
			if (!domainName.equals("webcache.googleusercontent.com")
					&& !(domainName.equals("www.youtube.com") && i > 0 && getDomainName(
							hrclassr.get(i - 1).select("a[href]").get(0)
									.attr("href")).equals("www.youtube.com"))
					&& !(domainName == null || domainName.equals(""))) {
				SearchResultItemTO item = new SearchResultItemTO();
				item.setDomain(domainName);
				item.setUrl(getUrl(temp));
				item.setPosition(position);
				if (item.getDomain() == null || item.getDomain().equals("")
						|| item.getDomain().trim().equals("")
						|| item.getUrl() == null || item.getUrl().equals("")
						|| item.getUrl().trim().equals("")) {
					logger.error("Url/Domain null. Link: " + link.toString());
					logger.error("Url/Domain null. DomainName: " + item.getDomain());
					logger.error("Url/Domain null. Url: " + item.getUrl());
				}else
				{
					items.add(item);
				}
				position++;
			}
		}
		return items;
	}

	@Override
	public Integer getFirstResult(int numResult) {
		return numResult;
	}

//	public static void main(String[] args) {
//
//		SearchengineGoogleService service = new SearchengineGoogleService();
//
//		try {
//			// FileInputStream fis = new FileInputStream(new
//			// File("/Users/francescodefilippis/Desktop/offline.html"));
//			// byte[] doc = Jsoup.parse(fis.toString());
//
//			Document document = Jsoup
//					.parse(new File(
//							"/Users/francescodefilippis/Desktop/offline.html"),
//							"UTF-8");
//
//			// System.out.println(document.toString());
//
//			List<SearchResultItemTO> list = service.parseSearchResult(document,
//					1);
//			System.out.println(list.size());
//			for (SearchResultItemTO se : list) {
//				System.out.println("Domain: " + se.getDomain() + " URL: "
//						+ se.getUrl());
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
}
