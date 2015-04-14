package com.flol.semrankerengine.keywordsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.Proxy;
import com.flol.semrankercommon.repository.KeywordSearchengineAccountDomainRepository;
import com.flol.semrankercommon.repository.ProxyRepository;
import com.flol.semrankercommon.repository.SearchengineParameterRepository;
import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;

@RestController
public class KeywordSearchController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());   
	
    @Autowired
    private SearchengineService searchEngineService;

    @Autowired
    private KeywordSearchengineAccountDomainRepository keywordSearchengineAccountDomainRepository;
    
	@Autowired
	private SearchengineParameterRepository searchengineParameterRepository;    
    
    @Autowired
    private ProxyRepository proxyRepository;
    
    @Autowired
    private KeywordSearchFacade keywordSearchFacade;
    
	@RequestMapping(value = "/searchkeyword", method = RequestMethod.POST, headers = "Accept=application/json")
    public SearchResultItemsTO searchkeyword(@RequestParam(value="text", defaultValue="World") String text) {
    	log.info("Chiamata funzione keyword");
    	SearchResultItemsTO ret = null;
		SearchKeywordParameterTO parameter = new SearchKeywordParameterTO();
		parameter.setKeyword(text);
		parameter.setNumResultToSearch("100");
		parameter.setProxyHost("");
		parameter.setSearchEngine(1);
		parameter.setTld("it");
//		parameter.setUserAgent(UserAgentMap.getRandomAgent());
		parameter.setUserAgent("Opera/9.80 (J2ME/MIDP; Opera Mini/9.80 (S60; SymbOS; Opera Mobi/23.348; U; en) Presto/2.5.25 Version/10.54");
		ret = searchEngineService.searchKeyword(parameter);
    	return ret;
	}
	
	
	@RequestMapping(value = "/searchkeywordAndStore", method = RequestMethod.GET, headers = "Accept=application/json")
	public boolean searchKeywordAndStore(@RequestParam(value="keywordSearchEngineAccountDomainId") String keywordSearchengineAccountDomainId,
			@RequestParam(value="proxyId") String proxyId)
	{
		KeywordSearchengineAccountDomain keywordSearchengineAccountDomain = keywordSearchengineAccountDomainRepository.findOne(Integer.valueOf(keywordSearchengineAccountDomainId));
		Proxy proxy = proxyRepository.findOne(Integer.valueOf(proxyId));
				
		keywordSearchFacade.searchKeywordAndStore(keywordSearchengineAccountDomain, proxy);
		
		return true;
	}
	
	
}
