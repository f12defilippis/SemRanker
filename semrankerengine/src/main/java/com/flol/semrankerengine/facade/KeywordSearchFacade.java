package com.flol.semrankerengine.facade;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flol.semrankercommon.domain.Domain;
import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.Proxy;
import com.flol.semrankercommon.domain.Url;
import com.flol.semrankercommon.repository.DomainRepository;
import com.flol.semrankercommon.repository.KeywordSearchengineAccountDomainRepository;
import com.flol.semrankercommon.repository.ProxyRepository;
import com.flol.semrankercommon.repository.SearchengineParameterRepository;
import com.flol.semrankercommon.repository.UrlRepository;
import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;
import com.flol.semrankerengine.service.SearchengineService;
import com.flol.semrankerengine.util.UserAgentMap;

@Component
public class KeywordSearchFacade {
	
    private final Logger log = LoggerFactory.getLogger(this.getClass());   
	
    @Autowired
    private SearchengineService searchEngineService;

    @Autowired
    private KeywordSearchengineAccountDomainRepository keywordSearchengineAccountDomainRepository;
    
	@Autowired
	private SearchengineParameterRepository searchengineParameterRepository;
	
    @Autowired
	private DomainRepository domainRepository;
    
    @Autowired
    private UrlRepository urlRepository;
    
    @Autowired
    private ProxyRepository proxyRepository;

	public void searchKeywordAndStore(KeywordSearchengineAccountDomain keywordSearchengineAccountDomain,
			Proxy proxy)
	{
		SearchResultItemsTO ret = searchKeyword(keywordSearchengineAccountDomain, proxy);
		if(!ret.isError())
		{
			// store data
			for(SearchResultItemTO item : ret.getItems())
			{
				storeData(item);
			}
		}else
		{
			//store error
		}
		
		
	}
	
	private void storeData(SearchResultItemTO item)
	{
		// check domain
		Domain domain = checkDomain(item.getDomain());
		// check url
		Url url = checkUrl(domain, item.getUrl());
	}
	
	private Domain checkDomain(String domainName)
	{
		Domain domainRet = null;
		List<Domain> domainList = domainRepository.findByName(domainName);
		if(domainList == null || domainList.size()==0)
		{
			domainRet = new Domain();
			domainRet.setName(domainName);
			domainRet.setDatecreate(new Date());
			domainRepository.save(domainRet);
		}else
		{
			domainRet = domainList.get(0);
		}
		return domainRet;
	}
	
	private Url checkUrl(Domain domain, String url)
	{
		Url urlRet = null;
		List<Url> urlList = urlRepository.findByUrl(url);
		if(urlList==null || urlList.size()==0)
		{
			urlRet = new Url();
			urlRet.setDomain(domain);
			urlRet.setUrl(url);
			urlRet.setDatecreate(new Date());
		}else
		{
			urlRet = urlList.get(0);
		}
		return urlRet;
	}
	
	private SearchResultItemsTO searchKeyword(KeywordSearchengineAccountDomain keywordSearchengineAccountDomain,
								Proxy proxy)
	{
		String numResultsToSearch = searchengineParameterRepository.findOne("NUM_RESULTS_TO_SEARCH").getValue();
		
		SearchKeywordParameterTO parameter = new SearchKeywordParameterTO();
		parameter.setKeyword(keywordSearchengineAccountDomain.getKeywordSearchengine().getKeyword().getText());
		parameter.setNumResultToSearch(numResultsToSearch);
		parameter.setProxyHost(proxy.getIp());
		parameter.setProxyPort(proxy.getPort());
		parameter.setSearchEngine(keywordSearchengineAccountDomain.getKeywordSearchengine().getSearchengine().getId());
		parameter.setTld(keywordSearchengineAccountDomain.getKeywordSearchengine().getSearchengineCountry().getTld());
		parameter.setUserAgent(UserAgentMap.getRandomAgent());
		
		SearchResultItemsTO ret = searchEngineService.searchKeyword(parameter);
		
		return ret;
		
	}
	
}
