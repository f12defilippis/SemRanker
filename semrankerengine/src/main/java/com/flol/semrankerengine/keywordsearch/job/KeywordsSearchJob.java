package com.flol.semrankerengine.keywordsearch.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.flol.semrankercommon.domain.Searchengine;
import com.flol.semrankercommon.repository.SearchengineRepository;
import com.flol.semrankercommon.util.SemRankerUtil;
import com.flol.semrankerengine.keywordsearch.controller.KeywordSearchController;
import com.flol.semrankerengine.updatecompetitors.controller.UpdateCompetitorsController;

@Component
public class KeywordsSearchJob {

	 private final Logger log = LoggerFactory.getLogger(this.getClass());   
	
	@Autowired
	private SearchengineRepository searchengineRepository;

	@Autowired
	private KeywordSearchController keywordSearchController;
	
	@Autowired
	private UpdateCompetitorsController updateCompetitorsController;
	
	@Autowired
	private ThreadPoolTaskExecutor jobExecutor;
	
	@Scheduled(cron="${scheduled.daily}")
	public void go()
	{
		List<Searchengine> searchengines = (List<Searchengine>) searchengineRepository.findAll();

		for(Searchengine se : searchengines)
		{
			keywordSearchController.searchKeywordsJob(se.getId());
		}

		SemRankerUtil.waitBetweenThreads();
		while(jobExecutor.getActiveCount()>0)
		{
			log.info("It's not yet time to update the competitors there are still " + jobExecutor.getActiveCount() + " job in progress. Wait for 4 minutes before retry");
			SemRankerUtil.waitMillis(240000);
		}

		updateCompetitorsController.updateCompetitors();
		
	}	
	
	
}
