package com.flol.semrankerengine.keywordsearch.facade;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.flol.semrankercommon.domain.KeywordScanSummary;
import com.flol.semrankercommon.domain.KeywordScanSummaryStatus;
import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.Proxy;
import com.flol.semrankercommon.domain.ProxySearchengine;
import com.flol.semrankercommon.repository.KeywordScanSummaryRepository;
import com.flol.semrankercommon.repository.KeywordSearchengineAccountDomainRepository;
import com.flol.semrankercommon.repository.ProxySearchengineRepository;
import com.flol.semrankercommon.repository.SearchengineParameterRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semrankerengine.dto.SearchKeywordParameterTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;
import com.flol.semrankerengine.keywordsearch.exception.KeywordStoreDataException;
import com.flol.semrankerengine.keywordsearch.service.KeywordStoreDataService;
import com.flol.semrankerengine.keywordsearch.service.SearchengineService;

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
    private ProxySearchengineRepository proxySearchengineRepository;
    
    @Autowired
    private KeywordScanSummaryRepository keywordScanSummaryRepository;
    
    @Async("threadPoolTaskExecutor")
	public void searchKeywordAndStore(KeywordSearchengineAccountDomain keywordSearchengineAccountDomain, ProxySearchengine proxy)
	{
		Date now = new Date();
		SearchResultItemsTO ret = new SearchResultItemsTO();
		KeywordScanSummary keywordScanSummary  = null;
		
		List<KeywordScanSummary> oldScanSummaryList = keywordScanSummaryRepository.findByKeywordSearchengineAccountDomainKeywordSearchengineIdAndDate(keywordSearchengineAccountDomain.getKeywordSearchengine().getId(), DateUtil.getTodaysMidnight());
		
		if(oldScanSummaryList!=null && oldScanSummaryList.size()>0)
		{
			keywordScanSummary = storeNewKeywordScanSummary(keywordSearchengineAccountDomain, null);
			ret = searchKeywordFromData(keywordScanSummary);
			
			try {
				keywordStoreDataService.storeKeywordsData(ret.getItems(), keywordScanSummary);
				keywordScanSummary.setCachePage(ret.getCachePage());
				updateKeywordScanSummary(keywordScanSummary, KeywordScanSummaryStatus.RETRIEVED_FROM_DATA);
				log.info("KEYWORD STORED: kw=" + keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText());
			} catch (KeywordStoreDataException e) {
				updateKeywordScanSummary(keywordScanSummary, KeywordScanSummaryStatus.STORE_FAILED);
				log.warn("KEYWORD FAILED ON STORE BY DATA: kw=" + keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText());
				e.printStackTrace();
			}
			
		}else
		{
			keywordScanSummary = storeNewKeywordScanSummary(keywordSearchengineAccountDomain, proxy.getProxy());
			proxy.setDateLastscan(now);
			proxySearchengineRepository.save(proxy);
			ret = searchKeyword(keywordScanSummary);
			if(!ret.isError())
			{
				proxy.setDateLastsuccess(now);
				// store data
				try {
					keywordStoreDataService.storeKeywordsData(ret.getItems(), keywordScanSummary);
					updateKeywordScanSummary(keywordScanSummary, KeywordScanSummaryStatus.COMPLETED);
					log.info("KEYWORD STORED: kw=" + keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText() + " "
							+ "proxy=" + proxy.getProxy().getIp());
				} catch (KeywordStoreDataException e) {
					updateKeywordScanSummary(keywordScanSummary, KeywordScanSummaryStatus.STORE_FAILED);
					log.warn("KEYWORD FAILED ON STORE: kw=" + keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText() + " "
							+ "proxy=" + proxy.getProxy().getIp());
					e.printStackTrace();
				}
			}else
			{
				//store error
				proxy.setDateLastfail(now);
				updateKeywordScanSummary(keywordScanSummary, KeywordScanSummaryStatus.PROXY_FAILED);
				log.warn("KEYWORD FAILED ON PROXY: kw=" + keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText() + " "
						+ "proxy=" + proxy.getProxy().getIp());
			}
			proxy.setUsage(0);
			proxySearchengineRepository.save(proxy);
		}
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
	
	
	private SearchResultItemsTO searchKeywordFromData(KeywordScanSummary keywordScanSummary)
	{
		SearchResultItemsTO ret = keywordStoreDataService.getKeywordsData(keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getId(), keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText());
		return ret;
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
