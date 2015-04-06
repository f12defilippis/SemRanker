package com.flol.semrankerengine.facade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flol.semrankercommon.domain.Domain;
import com.flol.semrankercommon.domain.KeywordSearchengine;
import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.Proxy;
import com.flol.semrankercommon.domain.SearchReport;
import com.flol.semrankercommon.domain.SearchReportAccount;
import com.flol.semrankercommon.domain.Url;
import com.flol.semrankercommon.repository.DomainRepository;
import com.flol.semrankercommon.repository.KeywordSearchengineAccountDomainRepository;
import com.flol.semrankercommon.repository.ProxyRepository;
import com.flol.semrankercommon.repository.SearchReportAccountRepository;
import com.flol.semrankercommon.repository.SearchReportRepository;
import com.flol.semrankercommon.repository.SearchengineParameterRepository;
import com.flol.semrankercommon.repository.UrlRepository;
import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;
import com.flol.semrankerengine.service.SearchengineService;

@Component
public class KeywordSearchFacade {
	
//    private final Logger log = LoggerFactory.getLogger(this.getClass());   
	
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
    
    @Autowired
    private SearchReportRepository searchReportRepository;
    
    @Autowired
    private SearchReportAccountRepository searchReportAccountRepository;

	public void searchKeywordAndStore(KeywordSearchengineAccountDomain keywordSearchengineAccountDomain,
			Proxy proxy)
	{
		SearchResultItemsTO ret = searchKeyword(keywordSearchengineAccountDomain, proxy);
		Date now = new Date();
		if(!ret.isError())
		{
			proxy.setDateLastsuccess(now);
			// store data
			for(SearchResultItemTO item : ret.getItems())
			{
				storeData(item, keywordSearchengineAccountDomain);
			}
		}else
		{
			//store error
			proxy.setDateLastfail(now);
		}
		proxy.setDateLastsscan(now);
		proxy.setUsage(0);
		proxyRepository.save(proxy);
	}
	
	private void storeData(SearchResultItemTO item, KeywordSearchengineAccountDomain keywordSearchengineAccountDomain)
	{
		// check domain
		Domain domain = checkDomain(item.getDomain());
		// check url
		Url url = checkUrl(domain, item.getUrl());
		//store search report data
		storeSearchReport(url, keywordSearchengineAccountDomain.getKeywordSearchengine(), item);
		// if domain of account store data in searchreportaccount table
		if(domain.getId().equals(keywordSearchengineAccountDomain.getAccountDomain().getDomain().getId()))
		{
			storeSearchReportAccount(url, keywordSearchengineAccountDomain, item);
		}
	}
	
	private void storeSearchReportAccount(Url url, KeywordSearchengineAccountDomain keywordSearchengineAccountDomain, SearchResultItemTO item)
	{
		List<SearchReportAccount> searchReportList = searchReportAccountRepository.findByKeywordSearchengineAccountDomainAndUrlAllByDateClosedNotNull(keywordSearchengineAccountDomain, url);
		Date now = new Date();
		if(searchReportList!=null && searchReportList.size()>0 && searchReportList.get(0).getPosition() == item.getPosition())
		{
			SearchReportAccount searchReport = searchReportList.get(0);
			searchReport.setDateLastSeen(now);
			searchReportAccountRepository.save(searchReport);
		}else 
		{
			if(searchReportList!=null && searchReportList.size()>0 && searchReportList.get(0).getPosition() != item.getPosition())
			{
				SearchReportAccount searchReport = searchReportList.get(0);
				searchReport.setDateClosed(now);
				searchReportAccountRepository.save(searchReport);
			}

			SearchReportAccount newSearchReport = new SearchReportAccount();
			newSearchReport.setDateFirstSeen(now);
			newSearchReport.setDateLastSeen(now);
			newSearchReport.setKeywordSearchengineAccountDomain(keywordSearchengineAccountDomain);
			newSearchReport.setPosition(item.getPosition());
			newSearchReport.setUrl(url);
			searchReportAccountRepository.save(newSearchReport);
		}
	}

	
	private void storeSearchReport(Url url, KeywordSearchengine keywordSearchengine, SearchResultItemTO item)
	{
		List<SearchReport> searchReportList = searchReportRepository.findByKeywordSearchengineAndUrlAllByDateClosedNotNull(keywordSearchengine, url);
		Date now = new Date();
		if(searchReportList!=null && searchReportList.size()>0 && searchReportList.get(0).getPosition() == item.getPosition())
		{
			SearchReport searchReport = searchReportList.get(0);
			searchReport.setDateLastSeen(now);
			searchReportRepository.save(searchReport);
		}else 
		{
			if(searchReportList!=null && searchReportList.size()>0 && searchReportList.get(0).getPosition() != item.getPosition())
			{
				SearchReport searchReport = searchReportList.get(0);
				searchReport.setDateClosed(now);
				searchReportRepository.save(searchReport);
			}

			SearchReport newSearchReport = new SearchReport();
			newSearchReport.setDateFirstSeen(now);
			newSearchReport.setDateLastSeen(now);
			newSearchReport.setKeywordSearchengine(keywordSearchengine);
			newSearchReport.setPosition(item.getPosition());
			newSearchReport.setUrl(url);
			searchReportRepository.save(newSearchReport);
		}
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
		parameter.setMobile(keywordSearchengineAccountDomain.getKeywordSearchengine().getMobile());
		
		SearchResultItemsTO ret = searchEngineService.searchKeyword(parameter);
		
		return ret;
		
	}
	
}
