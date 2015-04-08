package com.flol.semrankercommon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.SearchReportAccount;
import com.flol.semrankercommon.domain.Url;

public interface SearchReportAccountRepository extends CrudRepository<SearchReportAccount, Integer>{

	List<SearchReportAccount> findByKeywordScanSummaryKeywordSearchengineAccountDomainAndUrlAndDateClosedNotNull(KeywordSearchengineAccountDomain keywordSearchengineAccountDomain, Url url);
	
	
}
