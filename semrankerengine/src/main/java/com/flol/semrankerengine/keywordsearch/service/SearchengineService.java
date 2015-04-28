package com.flol.semrankerengine.keywordsearch.service;

import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;

public interface SearchengineService {

	public SearchResultItemsTO searchKeyword(SearchKeywordParameterTO parameter);
	
}
