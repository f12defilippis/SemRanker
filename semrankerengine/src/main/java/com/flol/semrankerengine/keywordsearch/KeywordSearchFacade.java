package com.flol.semrankerengine.keywordsearch;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flol.semrankercommon.domain.KeywordScanSummary;
import com.flol.semrankercommon.domain.KeywordScanSummaryStatus;
import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.Proxy;
import com.flol.semrankercommon.repository.KeywordScanSummaryRepository;
import com.flol.semrankercommon.repository.KeywordSearchengineAccountDomainRepository;
import com.flol.semrankercommon.repository.ProxyRepository;
import com.flol.semrankercommon.repository.SearchengineParameterRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;

@Component
public class KeywordSearchFacade {
	
    private final Logger log = LoggerFactory.getLogger(this.getClass());   
	
    @Autowired
    private SearchengineService searchEngineService;

    @Autowired
    private KeywordStoreDataService keywordStoreDataService;
    
    @Autowired
    private KeywordSearchengineAccountDomainRepository keywordSearchengineAccountDomainRepository;
    
	@Autowired
	private SearchengineParameterRepository searchengineParameterRepository;
	
    @Autowired
    private ProxyRepository proxyRepository;
    
    @Autowired
    private KeywordScanSummaryRepository keywordScanSummaryRepository;
    

    
	public void searchKeywordAndStore(KeywordSearchengineAccountDomain keywordSearchengineAccountDomain, Proxy proxy)
	{
		KeywordScanSummary keywordScanSummary = storeNewKeywordScanSummary(keywordSearchengineAccountDomain, proxy);
		SearchResultItemsTO ret = searchKeyword(keywordScanSummary);
		Date now = new Date();
		if(!ret.isError())
		{
			proxy.setDateLastsuccess(now);
			// store data
			try {
				keywordStoreDataService.storeKeywordsData(ret.getItems(), keywordScanSummary);
			} catch (KeywordStoreDataException e) {
				updateKeywordScanSummary(keywordScanSummary, KeywordScanSummaryStatus.STORE_FAILED);
				log.warn("KEYWORD FAILED ON STORE: kw=" + keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText() + " "
						+ "proxy=" + proxy.getIp());
				e.printStackTrace();
			}
			updateKeywordScanSummary(keywordScanSummary, KeywordScanSummaryStatus.COMPLETED);
			log.info("KEYWORD STORED: kw=" + keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText() + " "
					+ "proxy=" + proxy.getIp());
		}else
		{
			//store error
			proxy.setDateLastfail(now);
			updateKeywordScanSummary(keywordScanSummary, KeywordScanSummaryStatus.PROXY_FAILED);
			log.warn("KEYWORD FAILED ON PROXY: kw=" + keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText() + " "
					+ "proxy=" + proxy.getIp());
		}
		proxy.setDateLastsscan(now);
		proxy.setUsage(0);
		proxyRepository.save(proxy);
	}
	
	private KeywordScanSummary storeNewKeywordScanSummary(KeywordSearchengineAccountDomain keywordSearchengineAccountDomain,
			Proxy proxy)
	{
		KeywordScanSummary kss = new KeywordScanSummary();
		kss.setKeywordSearchengineAccountDomain(keywordSearchengineAccountDomain);
		kss.setProxy(proxy);
		kss.setDate(DateUtil.getTodaysMidnight());
		kss.setKeywordScanSummaryStatus(new KeywordScanSummaryStatus(KeywordScanSummaryStatus.RUNNING));
		keywordScanSummaryRepository.save(kss);
		return kss;
	}

	private void updateKeywordScanSummary(KeywordScanSummary keywordScanSummary, Integer status)
	{
		keywordScanSummary.setKeywordScanSummaryStatus(new KeywordScanSummaryStatus(status));
		keywordScanSummaryRepository.save(keywordScanSummary);
	}
	
	

	


	
	private SearchResultItemsTO searchKeyword(KeywordScanSummary keywordScanSummary)
	{
		String numResultsToSearch = searchengineParameterRepository.findOne("NUM_RESULTS_TO_SEARCH").getValue();
		
		SearchKeywordParameterTO parameter = new SearchKeywordParameterTO();
		parameter.setKeyword(keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText());
		parameter.setNumResultToSearch(numResultsToSearch);
		parameter.setProxyHost(keywordScanSummary.getProxy().getIp());
		parameter.setProxyPort(keywordScanSummary.getProxy().getPort());
		parameter.setSearchEngine(keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getAggregatedSearchengine().getSearchengine().getId());
		parameter.setTld(keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getAggregatedSearchengine().getSearchengineCountry().getTld());
		parameter.setMobile(keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getAggregatedSearchengine().getMobile());
		
		SearchResultItemsTO ret = searchEngineService.searchKeyword(parameter);
		
		return ret;
		
	}
	
}
