package com.flol.semrankerengine.keywordsearch.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.KeywordsWebRequest;
import com.flol.semrankercommon.domain.ProxySearchengine;
import com.flol.semrankercommon.domain.SearchengineParameter;
import com.flol.semrankercommon.repository.KeywordSearchengineAccountDomainRepository;
import com.flol.semrankercommon.repository.KeywordsWebRequestRepository;
import com.flol.semrankercommon.repository.ProxyRepository;
import com.flol.semrankercommon.repository.ProxySearchengineRepository;
import com.flol.semrankercommon.repository.SearchengineParameterRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semrankercommon.util.SemRankerUtil;
import com.flol.semrankerengine.keywordsearch.facade.KeywordSearchFacade;
import com.flol.semrankerengine.keywordsearch.service.SearchengineService;

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
	
	@Autowired
	private KeywordsWebRequestRepository keywordsWebRequestRepository;



	private static int MAX_WAIT = 50;
	private static int LOOP_FACTOR = 60;	


	@Async("jobExecutor")
	public void searchKeywordsJob(Integer searchengine)
	{
		searchKeywords(searchengine, null);
	}
	
	
	@RequestMapping(value = "/searchKeywords", method = RequestMethod.GET, headers = "Accept=application/json")
	public boolean searchKeywords(Integer searchengine, Integer webrequest)
	{
		log.info("START searching keywords of SearchEngine: " + searchengine);
		boolean allSaved = false;
		SearchengineParameter hourlyCall = searchengineParameterRepository.findOne("HOURLY_PROXY_MAX_CALLS");
		Integer intHourlyCall = Integer.valueOf(hourlyCall.getValue());
		Integer proxyTimeoutMillis = (60 * 60 * 1000) / intHourlyCall;

		SearchengineParameter webRequestWaitParameter = searchengineParameterRepository.findOne("WEB_REQUEST_WAIT_SECOND");
		Integer webRequestWait = Integer.valueOf(webRequestWaitParameter.getValue());
		
		SearchengineParameter maxParseFailsParameter = searchengineParameterRepository.findOne("MAX_PARSE_FAILS");
		Integer maxParseFails = Integer.valueOf(maxParseFailsParameter.getValue());
		
		
		List<KeywordSearchengineAccountDomain> keywordSearchengineAccountDomainList = (List<KeywordSearchengineAccountDomain>) keywordSearchengineAccountDomainRepository.findDataToSearch(DateUtil.getTodaysMidnight(), searchengine, maxParseFails);
		log.info("Found: " + keywordSearchengineAccountDomainList.size() + " record to search");
		// get the same number of proxy and ksad 
		Pageable pageable = new PageRequest(0, keywordSearchengineAccountDomainList!=null && keywordSearchengineAccountDomainList.size()>0 ? keywordSearchengineAccountDomainList.size() : 1);
		Date proxyCheckDate = DateUtil.getNowMinusMillis(proxyTimeoutMillis);
		List<ProxySearchengine> proxyList = proxySearchengineRepository.findProxy(proxyCheckDate, searchengine,pageable);
		log.info("Found: " + proxyList.size() + " proxy available");
		if(proxyList!=null && proxyList.size()>0 && keywordSearchengineAccountDomainList.size() > 0)
		{
			List<Integer> ids = new ArrayList<Integer>();
			for(ProxySearchengine psg : proxyList)
			{
				ids.add(psg.getProxy().getId());
			}
			proxySearchengineRepository.setProxyUsageByProxyList(proxyCheckDate, searchengine,ids);
		}
		
		int maxIteration = keywordSearchengineAccountDomainList != null && proxyList != null && keywordSearchengineAccountDomainList.size() > proxyList.size() ? keywordSearchengineAccountDomainList.size() / proxyList.size() * LOOP_FACTOR : LOOP_FACTOR;

		int iteration = 0;
		while(keywordSearchengineAccountDomainList != null && keywordSearchengineAccountDomainList.size() > 0 && iteration <= maxIteration)
		{
			boolean waitForWeb = false;
			if(webrequest==null)
			{
				List<KeywordsWebRequest> kwrList = keywordsWebRequestRepository.findBySearchengineAndEndDateNull(searchengine);
				waitForWeb = kwrList != null && kwrList.size() > 0;
			}
			if(!waitForWeb)
			{
				if(iteration > 0)
				{
					log.info("Iteration num. " + iteration + " max: " + maxIteration);
					keywordSearchengineAccountDomainList = (List<KeywordSearchengineAccountDomain>) keywordSearchengineAccountDomainRepository.findDataToSearch(DateUtil.getTodaysMidnight(), searchengine, maxParseFails);
					log.info("Found: " + keywordSearchengineAccountDomainList.size() + " record to search");
					if(keywordSearchengineAccountDomainList!=null && keywordSearchengineAccountDomainList.size()>0)
					{
						Pageable pageableInt = new PageRequest(0, keywordSearchengineAccountDomainList!=null && keywordSearchengineAccountDomainList.size()>0 ? keywordSearchengineAccountDomainList.size() : 1);
						proxyCheckDate = DateUtil.getNowMinusMillis(proxyTimeoutMillis);
						
						proxyList = proxySearchengineRepository.findProxy(proxyCheckDate, searchengine, pageableInt);
						log.info("Found: " + proxyList.size() + " proxy available");
						if(proxyList!=null && proxyList.size()>0)
						{
							List<Integer> ids = new ArrayList<Integer>();
							for(ProxySearchengine psg : proxyList)
							{
								ids.add(psg.getProxy().getId());
							}
							proxySearchengineRepository.setProxyUsageByProxyList(proxyCheckDate, searchengine,ids);
						}
					}
				}
				allSaved = searchKeywords(keywordSearchengineAccountDomainList, proxyList);
				iteration++;
				log.info("Waiting " + webRequestWait/3 + " seconds between iteration...");
				SemRankerUtil.waitMillis(webRequestWait*1000/3);
			}else
			{
				SemRankerUtil.waitMillis(webRequestWait*1000);
			}
		}
		if(webrequest!=null)
		{
			KeywordsWebRequest kwr = keywordsWebRequestRepository.findOne(webrequest);
			Integer keywordsNotStored = keywordSearchengineAccountDomainList == null || keywordSearchengineAccountDomainList.size() == 0 ? 0 : keywordSearchengineAccountDomainList.size();
			kwr.setEndDate(new Date());
			kwr.setKeywordsNotStored(keywordsNotStored);
			
			keywordsWebRequestRepository.save(kwr);
		}
		log.info("FINISH searching keywords of SearchEngine: " + searchengine);
		return allSaved;
	}

	private boolean searchKeywords(List<KeywordSearchengineAccountDomain> keywordSearchengineAccountDomainList, List<ProxySearchengine> proxyList)
	{
		SearchengineParameter maxThreadParameter = searchengineParameterRepository.findOne("NUM_MAX_THREADS");
		Integer maxThread = Integer.valueOf(maxThreadParameter.getValue());
		boolean availableResource = true;
		int waited = 0;
		for(int ksadIndex = 0 ; ksadIndex < keywordSearchengineAccountDomainList.size() && availableResource; )
		{
			KeywordSearchengineAccountDomain keywordSearchengineAccountDomain = keywordSearchengineAccountDomainList.get(ksadIndex);
			if(proxyList==null || ksadIndex >= proxyList.size())
			{
				availableResource = false;
			}else if(threadPoolTaskExecutor.getActiveCount() >= maxThread-1)
			{
				if(waited>MAX_WAIT)
				{
					availableResource = false;
				}
				log.info(threadPoolTaskExecutor.getActiveCount() + " active threads. Waiting...");
				SemRankerUtil.waitForFreeThreads();
				waited++;
			}else
			{
				waited = 0;
				keywordSearchFacade.searchKeywordAndStore(keywordSearchengineAccountDomain, proxyList.get(ksadIndex));
				ksadIndex++;
				SemRankerUtil.waitBetweenThreads();
			}
		}
		waited = 0;
		while(threadPoolTaskExecutor.getActiveCount()>0)
		{
			if(waited % 5 == 0)
			{
				log.info(threadPoolTaskExecutor.getActiveCount() + " active threads. Waiting...");
				waited = 0;
			}
			SemRankerUtil.waitForFreeThreads();
			waited++;
		}
		return availableResource;
	}
	
	
}
