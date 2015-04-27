package com.flol.semrankerengine.keywordsearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.flol.semrankercommon.domain.Searchengine;
import com.flol.semrankercommon.repository.SearchengineRepository;

@Component
public class KeywordsSearchJob {

	
	@Autowired
	private SearchengineRepository searchengineRepository;

	@Autowired
	private KeywordSearchController keywordSearchController;
	

	
	@Scheduled(cron="${kjst}")
	public void go()
	{
		List<Searchengine> searchengines = (List<Searchengine>) searchengineRepository.findAll();

		for(Searchengine se : searchengines)
		{
			keywordSearchController.searchKeywords(se.getId(), null);
		}
	}	
	
	
}
