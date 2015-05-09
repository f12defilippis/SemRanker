package com.flol.semrankerengine.updatecompetitors.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankercommon.domain.AccountDomain;
import com.flol.semrankercommon.repository.KeywordScanSummaryRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semrankerengine.updatecompetitors.facade.UpdateCompetitorsFacade;

@RestController
public class UpdateCompetitorsController {
	
	@Autowired
	private UpdateCompetitorsFacade updateCompetitorsFacade;
	
	@Autowired
	private KeywordScanSummaryRepository keywordScanSummaryRepository;
	




    private final Logger log = LoggerFactory.getLogger(this.getClass());   

	
	public void updateCompetitors()
	{
		log.info("It's time to update the competitors");
		
		List<AccountDomain> accountDomainIdList = keywordScanSummaryRepository.findAccountDomainWorkedByDate(DateUtil.getTodaysMidnight());
		log.info("Found " + accountDomainIdList.size() + " accountDomain to update");
		
		for(AccountDomain accountDomain : accountDomainIdList)
		{
			log.info("Updating competitors for AccountDomain with domain name: " + accountDomain.getDomain().getName());
			updateCompetitorsFacade.updateCompetitor(accountDomain);
		}
		
		log.info("Competitors Updated");
		
	}
	
	
	
	
}
