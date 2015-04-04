package com.flol.semrankerengine.service;

import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;

public interface SearchengineService {

	public SearchResultItemsTO searchKeyword(SearchKeywordParameterTO parameter);
	
}
