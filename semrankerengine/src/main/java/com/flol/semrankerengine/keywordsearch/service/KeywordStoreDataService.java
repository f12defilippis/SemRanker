package com.flol.semrankerengine.keywordsearch.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flol.semrankercommon.domain.AccountDomainCompetitor;
import com.flol.semrankercommon.domain.AccountDomainCompetitorStatus;
import com.flol.semrankercommon.domain.Domain;
import com.flol.semrankercommon.domain.KeywordScanSummary;
import com.flol.semrankercommon.domain.KeywordSearchengine;
import com.flol.semrankercommon.domain.SearchReport;
import com.flol.semrankercommon.domain.SearchReportAccount;
import com.flol.semrankercommon.domain.Url;
import com.flol.semrankercommon.repository.AccountDomainCompetitorRepository;
import com.flol.semrankercommon.repository.DomainRepository;
import com.flol.semrankercommon.repository.SearchReportAccountRepository;
import com.flol.semrankercommon.repository.SearchReportRepository;
import com.flol.semrankercommon.repository.UrlRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semrankerengine.dto.SearchResultItemTO;
import com.flol.semrankerengine.dto.SearchResultItemsTO;
import com.flol.semrankerengine.keywordsearch.exception.KeywordStoreDataException;

@Service
public class KeywordStoreDataService {
	
    @Autowired
	private DomainRepository domainRepository;
    
    @Autowired
    private UrlRepository urlRepository;
    
    @Autowired
    private SearchReportRepository searchReportRepository;
    
    @Autowired
    private SearchReportAccountRepository searchReportAccountRepository;
    
    @Autowired
    private AccountDomainCompetitorRepository accountDomainCompetitorRepository;

    @Transactional
	public void storeKeywordsData(List<SearchResultItemTO> items, KeywordScanSummary kss) throws KeywordStoreDataException
	{
    	try {
    		List<AccountDomainCompetitor> accountDomainCompetitorList = accountDomainCompetitorRepository.findByAccountDomainIdAndAccountDomainCompetitorStatus(kss.getKeywordSearchengineAccountDomain().getAccountDomain().getId(), AccountDomainCompetitorStatus.ACTIVE);
    		Map<Integer,Integer> domainCompetitorMap = new HashMap<Integer,Integer>();
    		for(AccountDomainCompetitor adc : accountDomainCompetitorList)
    		{
    			domainCompetitorMap.put(adc.getDomain().getId(), adc.getDomain().getId());
    		}
    		for(SearchResultItemTO item : items)
    		{
    			storeData(item, kss, domainCompetitorMap);
    		}
		} catch (Exception e) {
			throw new KeywordStoreDataException(e);
		}
	}
    
    public SearchResultItemsTO getKeywordsData(Integer keywordSearchengineId, String keyword)
    {
    	SearchResultItemsTO ret = new SearchResultItemsTO();
    	ret.setKeyword(keyword);
    	List<SearchReport> searchReportList = searchReportRepository.findByKeywordSearchengineIdAndDateClosedNotNull(keywordSearchengineId);
    	for(SearchReport sr : searchReportList)
    	{
    		SearchResultItemTO item = new SearchResultItemTO();
    		item.setDomain(sr.getUrl().getDomain().getName());
    		item.setUrl(sr.getUrl().getUrl());
    		item.setPosition(sr.getPosition());
    		
    		ret.getItems().add(item);
    	}
    	
    	return ret;
    }
	
	private void storeData(SearchResultItemTO item, KeywordScanSummary keywordScanSummary, Map<Integer,Integer> domainCompetitorMap)
	{
		// check domain
		Domain domain = checkDomain(item.getDomain().replaceAll("www.", ""));
		// check url
		Url url = checkUrl(domain, item.getUrl());
		//store search report data
		storeSearchReport(url, keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine(), item);
		// if domain of account or competitor store data in searchreportaccount table
		if(domain.getId().equals(keywordScanSummary.getKeywordSearchengineAccountDomain().getAccountDomain().getDomain().getId()) || domainCompetitorMap.get(domain.getId())!=null)
		{
			storeSearchReportAccount(url, keywordScanSummary, item);
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
			urlRepository.save(urlRet);
		}else
		{
			urlRet = urlList.get(0);
		}
		return urlRet;
	}
	
	private void storeSearchReportAccount(Url url, KeywordScanSummary keywordScanSummary, SearchResultItemTO item)
	{
		List<SearchReportAccount> searchReportList = searchReportAccountRepository.findByKeywordScanSummaryKeywordSearchengineAccountDomainIdAndKeywordScanSummaryKeywordSearchengineAccountDomainKeywordSearchengineAggregatedSearchengineIdAndUrlAndDateClosedNotNull(keywordScanSummary.getKeywordSearchengineAccountDomain().getId(), keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine().getAggregatedSearchengine().getId(),  url);
		Date now = DateUtil.getTodaysMidnight();
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
			newSearchReport.setKeywordScanSummary(keywordScanSummary);
			newSearchReport.setPosition(item.getPosition());
			newSearchReport.setUrl(url);
			searchReportAccountRepository.save(newSearchReport);
		}
	}

	
	private void storeSearchReport(Url url, KeywordSearchengine keywordSearchengine, SearchResultItemTO item)
	{
		List<SearchReport> searchReportList = searchReportRepository.findByKeywordSearchengineAndUrlAndDateClosedNotNull(keywordSearchengine, url);
		Date now = DateUtil.getTodaysMidnight();
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

	
	
}
