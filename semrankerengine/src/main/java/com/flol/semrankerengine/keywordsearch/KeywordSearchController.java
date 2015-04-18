package com.flol.semrankerengine.keywordsearch;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.ProxySearchengine;
import com.flol.semrankercommon.domain.SearchengineParameter;
import com.flol.semrankercommon.repository.KeywordSearchengineAccountDomainRepository;
import com.flol.semrankercommon.repository.ProxyRepository;
import com.flol.semrankercommon.repository.ProxySearchengineRepository;
import com.flol.semrankercommon.repository.SearchengineParameterRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semrankercommon.util.SemRankerUtil;
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
    
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Autowired
	private ProxySearchengineRepository proxySearchengineRepository;
	
	private static int MAX_WAIT = 20;
	private static int LOOP_FACTOR = 4;
    
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
		ProxySearchengine proxy = proxySearchengineRepository.findOne(Integer.valueOf(proxyId));
				
		keywordSearchFacade.searchKeywordAndStore(keywordSearchengineAccountDomain, proxy);
		
		return true;
	}

	@RequestMapping(value = "/searchKeywords", method = RequestMethod.GET, headers = "Accept=application/json")
	public boolean searchKeywords(Integer searchengine)
	{
		boolean allSaved = false;
		SearchengineParameter hourlyCall = searchengineParameterRepository.findOne("HOURLY_PROXY_MAX_CALLS");
		Integer intHourlyCall = Integer.valueOf(hourlyCall.getValue());
		Integer proxyTimeoutMillis = (60 * 60 * 1000) / intHourlyCall;

		Date proxyCheckDate = DateUtil.getNowMinusMillis(proxyTimeoutMillis);
		List<ProxySearchengine> proxyList = proxySearchengineRepository.findProxy(proxyCheckDate, searchengine);

		proxySearchengineRepository.setProxyUsage(proxyCheckDate, searchengine);
		List<KeywordSearchengineAccountDomain> keywordSearchengineAccountDomainList = (List<KeywordSearchengineAccountDomain>) keywordSearchengineAccountDomainRepository.findDataToSearch(DateUtil.getTodaysMidnight(), searchengine);
		
		int maxIteration = keywordSearchengineAccountDomainList != null && proxyList != null && keywordSearchengineAccountDomainList.size() > proxyList.size() ? keywordSearchengineAccountDomainList.size() / proxyList.size() * LOOP_FACTOR : LOOP_FACTOR;

		int iteration = 0;
		while(keywordSearchengineAccountDomainList != null && keywordSearchengineAccountDomainList.size() > 0 && iteration <= maxIteration)
		{
			if(iteration > 0)
			{
				proxyCheckDate = DateUtil.getNowMinusMillis(proxyTimeoutMillis);
				
				proxyList = proxySearchengineRepository.findProxy(proxyCheckDate, searchengine);
				proxySearchengineRepository.setProxyUsage(proxyCheckDate, searchengine);
				
				keywordSearchengineAccountDomainList = (List<KeywordSearchengineAccountDomain>) keywordSearchengineAccountDomainRepository.findDataToSearch(DateUtil.getTodaysMidnight(), searchengine);
			}
			allSaved = searchKeywords(keywordSearchengineAccountDomainList, proxyList);
			iteration++;
		}
		return allSaved;
	}

	private boolean searchKeywords(List<KeywordSearchengineAccountDomain> keywordSearchengineAccountDomainList, List<ProxySearchengine> proxyList)
	{
		SearchengineParameter maxThreadParameter = searchengineParameterRepository.findOne("NUM_MAX_THREADS");
		Integer maxThread = Integer.valueOf(maxThreadParameter.getValue());
		boolean availableResource = true;
		int waited = 0;
		for(int ksadIndex = 0 ; ksadIndex < keywordSearchengineAccountDomainList.size() && availableResource; ksadIndex++)
		{
			KeywordSearchengineAccountDomain keywordSearchengineAccountDomain = keywordSearchengineAccountDomainList.get(ksadIndex);
			if(proxyList==null || ksadIndex >= proxyList.size())
			{
				availableResource = false;
			}else if(threadPoolTaskExecutor.getActiveCount() >= maxThread)
			{
				if(waited>MAX_WAIT)
				{
					availableResource = false;
				}
				SemRankerUtil.waitForFreeThreads();
				waited++;
			}else
			{
				waited = 0;
				keywordSearchFacade.searchKeywordAndStore(keywordSearchengineAccountDomain, proxyList.get(ksadIndex));
				ksadIndex++;
			}
		}
		while(threadPoolTaskExecutor.getActiveCount()>0)
		{
			SemRankerUtil.waitForFreeThreads();
		}
		return availableResource;
	}
	
}
