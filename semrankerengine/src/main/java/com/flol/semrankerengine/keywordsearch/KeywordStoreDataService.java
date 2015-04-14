package com.flol.semrankerengine.keywordsearch;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flol.semrankercommon.domain.Domain;
import com.flol.semrankercommon.domain.KeywordScanSummary;
import com.flol.semrankercommon.domain.KeywordSearchengine;
import com.flol.semrankercommon.domain.SearchReport;
import com.flol.semrankercommon.domain.SearchReportAccount;
import com.flol.semrankercommon.domain.Url;
import com.flol.semrankercommon.repository.DomainRepository;
import com.flol.semrankercommon.repository.SearchReportAccountRepository;
import com.flol.semrankercommon.repository.SearchReportRepository;
import com.flol.semrankercommon.repository.UrlRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semrankerengine.dto.SearchResultItemTO;

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

    @Transactional
	public void storeKeywordsData(List<SearchResultItemTO> items, KeywordScanSummary kss) throws KeywordStoreDataException
	{
    	try {
        	for(SearchResultItemTO item : items)
    		{
    			storeData(item, kss);
    		}
		} catch (Exception e) {
			throw new KeywordStoreDataException(e);
		}
	}
	
	private void storeData(SearchResultItemTO item, KeywordScanSummary keywordScanSummary)
	{
		// check domain
		Domain domain = checkDomain(item.getDomain().replaceAll("www.", ""));
		// check url
		Url url = checkUrl(domain, item.getUrl());
		//store search report data
		storeSearchReport(url, keywordScanSummary.getKeywordSearchengineAccountDomain().getKeywordSearchengine(), item);
		// if domain of account store data in searchreportaccount table
		if(domain.getId().equals(keywordScanSummary.getKeywordSearchengineAccountDomain().getAccountDomain().getDomain().getId()))
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
