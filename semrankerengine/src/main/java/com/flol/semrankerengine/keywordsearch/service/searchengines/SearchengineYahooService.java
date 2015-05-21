package com.flol.semrankerengine.keywordsearch.service.searchengines;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.flol.semrankerengine.dto.SearchResultItemTO;
import com.flol.semrankerengine.keywordsearch.exception.KeywordParseException;

@Service("yahooService")
public class SearchengineYahooService extends SearchengineBaseService{
	
	@Override
	public List<SearchResultItemTO> parseSearchResult(Document doc, Integer pos) throws Exception
	{
		List<SearchResultItemTO> items = new ArrayList<SearchResultItemTO>();
		
		int position = pos;
		Elements hrclassr = doc.getElementsByClass("algo");
		for (int i = 0 ; i < hrclassr.size() ; i++) {
			Element link = hrclassr.get(i).select("a[href]").get(0);
			String temp = link.attr("href");
			try
			{
				if(temp.indexOf("RU=")<0 || temp.indexOf("RK")<0)
				{
					logger.debug("Yahoo: NO RU HREF: " + temp);
				}else
				{
					temp = temp.substring(temp.indexOf("RU=")+3, temp.indexOf("RK")-1);
					temp = java.net.URLDecoder.decode(temp, "UTF-8");
				}
				String domainName = getDomainName(temp);
				if (!domainName.equals("webcache.googleusercontent.com")
						&& !(domainName.equals("www.youtube.com") && i>0 && getDomainName(hrclassr.get(i - 1).select("a[href]").get(0).attr("href")).equals("www.youtube.com"))
						&& !(domainName == null || domainName.equals(""))) {
					SearchResultItemTO item = new SearchResultItemTO();
					item.setDomain(domainName);
					item.setUrl(getUrl(temp));
					item.setPosition(position);
					items.add(item);
					if(item.getDomain()==null || item.getDomain().equals("") || item.getUrl()==null || item.getUrl().equals(""))
					{
						throw new Exception("Url/Domain null");
					}				
					position++;
				}
				
			}catch(Exception e)
			{
				throw new KeywordParseException(e, temp);
			}
		}
		return items;
	}
	
//	@Override
//	public Integer getFirstResult(int numResult, int page, int maxResultsPerPage)
//	{
//		return page == 1 ? 1 : (page-1)*maxResultsPerPage+1;
//	}

	
//	public static void main(String[] args) {
//		
//		SearchengineYahooService service = new SearchengineYahooService();
//		
//		try {
////			FileInputStream fis = new FileInputStream(new File("/Users/francescodefilippis/Desktop/offline.html"));
////			byte[] doc = Jsoup.parse(fis.toString());
//			
//			Document document = Jsoup.parse(new File("/Users/francescodefilippis/Desktop/offline.html"),"UTF-8");
//			
////			System.out.println(document.toString());
//			
//			List<SearchResultItemTO> list = service.parseSearchResult(document, 1);
//			System.out.println(list.size());
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
