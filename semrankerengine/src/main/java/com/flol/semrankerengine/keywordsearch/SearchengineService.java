package com.flol.semrankerengine.keywordsearch;

import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;

public interface SearchengineService {

	public SearchResultItemsTO searchKeyword(SearchKeywordParameterTO parameter);
	
}
