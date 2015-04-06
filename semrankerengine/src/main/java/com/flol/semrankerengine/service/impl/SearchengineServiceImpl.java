package com.flol.semrankerengine.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;
import com.flol.semrankerengine.service.SearchengineService;
import com.flol.semrankerengine.util.SearchengineMap;

@Service("SearchengineService")
public class SearchengineServiceImpl implements SearchengineService{

	@Autowired
	private SearchengineGoogleService googleService;
	
	@Override
	public SearchResultItemsTO searchKeyword(SearchKeywordParameterTO parameter)
	{
		SearchResultItemsTO ret = new SearchResultItemsTO();
		try {
			if(parameter.getSearchEngine().equals(SearchengineMap.GOOGLE))
			{
				ret = googleService.searchKeyword(parameter);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ret.setError(true);
			e.printStackTrace();
		}
	
		return ret;
	}

	
	
	
}
