package com.flol.semrankerengine.updatecompetitors.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.flol.semrankercommon.domain.AccountDomain;
import com.flol.semrankercommon.domain.AccountDomainCompetitor;
import com.flol.semrankercommon.domain.AccountDomainCompetitorHistoryData;
import com.flol.semrankercommon.domain.AccountDomainHistoryData;
import com.flol.semrankercommon.dto.DomainCompetitorTO;
import com.flol.semrankercommon.repository.AccountDomainCompetitorHistoryDataRepository;
import com.flol.semrankercommon.repository.AccountDomainCompetitorRepository;
import com.flol.semrankercommon.repository.AccountDomainHistoryDataRepository;
import com.flol.semrankercommon.repository.SearchReportAccountRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semrankerengine.updatecompetitors.service.SearchReportRepositoryService;

@Component
public class UpdateCompetitorsFacade {

	@Autowired
	private SearchReportRepositoryService searchReportRepositoryService;

	@Autowired
	private SearchReportAccountRepository searchReportAccountRepository;

	@Autowired
	private AccountDomainCompetitorRepository accountDomainCompetitorRepository;
	
	@Autowired
	private AccountDomainCompetitorHistoryDataRepository accountDomainCompetitorHistoryDataRepository;
	
	@Autowired
	private AccountDomainHistoryDataRepository accountDomainHistoryDataRepository;	
	
	private static int MAX_COMPETITORS = 30;
	
	@Transactional
	public void updateCompetitor(AccountDomain accountDomain)
	{
		Pageable pageableInt = new PageRequest(0, MAX_COMPETITORS);
//		List<Integer> oldAccountDomainCompetitorList = accountDomainCompetitorRepository.findDomainIdByAccountDomainId(accountDomainId);
		List<AccountDomainCompetitor> accountDomainCompetitorList = accountDomainCompetitorRepository.findByAccountDomainId(accountDomain.getId());

		Map<Integer,AccountDomainCompetitor> accountDomainCompetitorMap = new HashMap<Integer, AccountDomainCompetitor>();
		for(AccountDomainCompetitor accountDomainCompetitor : accountDomainCompetitorList)
		{
			accountDomainCompetitorMap.put(accountDomainCompetitor.getDomain().getId(), accountDomainCompetitor);
		}
		
		List<AccountDomainCompetitorHistoryData> accountDomainCompetitorHistoryDataList = new ArrayList<AccountDomainCompetitorHistoryData>();
		
		List<DomainCompetitorTO> domainCompetitorList = searchReportRepositoryService.getDomainsScore(accountDomain.getId(), pageableInt);
		
		accountDomainCompetitorHistoryDataRepository.deleteByAccountDomainIdAndDate(accountDomain.getId(), DateUtil.getTodaysMidnight());
		accountDomainHistoryDataRepository.deleteByAccountDomainIdAndDate(accountDomain.getId(), DateUtil.getTodaysMidnight());
		
		List<Object[]> accountDomainHistoryObjectList = searchReportAccountRepository.getDomainScore(accountDomain.getId());

		Object[] accountDomainHistoryObject = accountDomainHistoryObjectList!=null ? accountDomainHistoryObjectList.get(0) : null;

		BigDecimal avgPosition = accountDomainHistoryObject!=null && accountDomainHistoryObject[1]!=null ? new BigDecimal((Double)accountDomainHistoryObject[1]) : new BigDecimal(0);
		BigDecimal score = accountDomainHistoryObject!=null && accountDomainHistoryObject[1]!=null ? (BigDecimal)accountDomainHistoryObject[0] : new BigDecimal(0);
		
		AccountDomainHistoryData accountDomainHistoryData = new AccountDomainHistoryData();
		accountDomainHistoryData.setAccountDomain(accountDomain);
		accountDomainHistoryData.setAveragePosition(avgPosition);
		accountDomainHistoryData.setDate(DateUtil.getTodaysMidnight());
		accountDomainHistoryData.setDateCreate(new Date());
		accountDomainHistoryData.setScore(score);
		
		accountDomainHistoryDataRepository.save(accountDomainHistoryData);		
		
				
		for(DomainCompetitorTO domainCompetitor : domainCompetitorList)
		{
			if(domainCompetitor.getDomain().getId().equals(accountDomain.getDomain().getId()))
			{				
				//DO NOTHING
			}else
			{
				AccountDomainCompetitor adc = null;
				if(accountDomainCompetitorMap.get(domainCompetitor.getDomain().getId())==null)
				{
					adc = new AccountDomainCompetitor();
					adc.setAccountDomain(accountDomain);
					adc.setAccountDomainCompetitorStatus(AccountDomainCompetitor.AccountDomainCompetitorStatus_FOUND);
					adc.setDomain(domainCompetitor.getDomain());
					
					accountDomainCompetitorRepository.save(adc);
				}else
				{
					adc = accountDomainCompetitorMap.get(domainCompetitor.getDomain().getId());
				}

				AccountDomainCompetitorHistoryData accountDomainCompetitorHistoryData = new AccountDomainCompetitorHistoryData();
				accountDomainCompetitorHistoryData.setAccountDomainCompetitor(adc);
				accountDomainCompetitorHistoryData.setDate(DateUtil.getTodaysMidnight());
				accountDomainCompetitorHistoryData.setAveragePosition(domainCompetitor.getAvgPosition());
				accountDomainCompetitorHistoryData.setKeywordsPositioned(domainCompetitor.getCommonKeywords());
				accountDomainCompetitorHistoryData.setScore(domainCompetitor.getScore());
				
				accountDomainCompetitorHistoryDataList.add(accountDomainCompetitorHistoryData);
			}
			
		}
		
		accountDomainCompetitorHistoryDataRepository.save(accountDomainCompetitorHistoryDataList);
		
		
	}
	
	
}
