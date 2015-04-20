package com.flol.semrankerengine.keywordsearch;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flol.semrankercommon.util.SearchengineMap;
import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;
import com.flol.semrankerengine.util.UserAgentMap;

@Service("SearchengineService")
public class SearchengineServiceImpl implements SearchengineService{

	@Autowired
	private SearchengineGoogleService googleService;
	
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
			if(parameter.getSearchEngine().equals(SearchengineMap.GOOGLE))
			{
				ret = googleService.searchKeyword(parameter);
			}
		} catch (IOException e) {
			ret.setError(true);
			e.printStackTrace();
		}
	
		return ret;
	}

	
	
	
}
