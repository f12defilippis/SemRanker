package com.flol.semrankerengine.keywordsearch.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flol.semrankercommon.util.SearchengineMap;
import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;
import com.flol.semrankerengine.keywordsearch.exception.KeywordCallException;
import com.flol.semrankerengine.keywordsearch.exception.KeywordParseException;
import com.flol.semrankerengine.keywordsearch.service.searchengines.SearchengineBingService;
import com.flol.semrankerengine.keywordsearch.service.searchengines.SearchengineGoogleService;
import com.flol.semrankerengine.keywordsearch.service.searchengines.SearchengineYahooService;
import com.flol.semrankerengine.util.UserAgentMap;

@Service("SearchengineService")
public class SearchengineServiceImpl implements SearchengineService{

	@Autowired
	private SearchengineGoogleService googleService;

	@Autowired
	private SearchengineYahooService yahooService;

	@Autowired
	private SearchengineBingService bingService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());  	

	@Override
	public SearchResultItemsTO searchKeyword(SearchKeywordParameterTO parameter)
	{
		SearchResultItemsTO ret = new SearchResultItemsTO();
		if(parameter.getMobile()!=null && parameter.getMobile().equals(1))
		{
			parameter.setUserAgent(UserAgentMap.getRandomMobileAgent());
		}else
		{
			parameter.setUserAgent(UserAgentMap.getRandomAgent());
		}
		try {
			if(parameter.getSearchEngineCountry().getSearchengine().getId().equals(SearchengineMap.GOOGLE))
			{
				ret = googleService.searchKeyword(parameter);
			}else if(parameter.getSearchEngineCountry().getSearchengine().getId().equals(SearchengineMap.YAHOO))
			{
				ret = yahooService.searchKeyword(parameter);
			}else if(parameter.getSearchEngineCountry().getSearchengine().getId().equals(SearchengineMap.BING))
			{
				ret = bingService.searchKeyword(parameter);
			}
		} catch (Exception e) {
			ret.setError(true);
			e.printStackTrace();
			logger.error(e.getMessage());
			if(e instanceof KeywordParseException)
			{
				ret.setParseError(true);
			}else if(e instanceof KeywordCallException)
			{
				ret.setCallError(true);
			}
		}
	
		return ret;
	}

	
	
	
}
