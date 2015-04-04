package com.flol.semrankerengine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;
import com.flol.semrankerengine.service.SearchengineService;
import com.flol.semrankerengine.util.UserAgentMap;

@RestController
public class KeywordSearchController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());   
	
    @Autowired
    private SearchengineService searchEngineService;
    
    
	@RequestMapping(value = "/searchkeyword", method = RequestMethod.POST, headers = "Accept=application/json")
    public SearchResultItemsTO keyword(@RequestParam(value="text", defaultValue="World") String text) {
    	log.info("Chiamata funzione keyword");
    	SearchResultItemsTO ret = null;
		SearchKeywordParameterTO parameter = new SearchKeywordParameterTO();
		parameter.setKeyword(text);
		parameter.setNumResultToSearch("100");
		parameter.setProxyHost("");
		parameter.setSearchEngine(1);
		parameter.setTld("it");
		parameter.setUserAgent(UserAgentMap.getRandomAgent());
		ret = searchEngineService.searchKeyword(parameter);
    	return ret;
	}	
	
	
}
