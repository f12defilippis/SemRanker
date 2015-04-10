package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.SearchReportAccount;
import com.flol.semrankercommon.domain.Url;

public interface SearchReportAccountRepository extends CrudRepository<SearchReportAccount, Integer>{

	List<SearchReportAccount> findByKeywordScanSummaryKeywordSearchengineAccountDomainIdAndKeywordScanSummaryKeywordSearchengineAggregatedSearchengineIdAndUrlAndDateClosedNotNull(Integer keywordScanSummaryKeywordSearchengineAccountDomainId, Integer KeywordScanSummaryKeywordSearchengineAggregatedSearchengineId, Url url);

	List<SearchReportAccount> findByKeywordScanSummaryKeywordSearchengineAccountDomainIdAndKeywordScanSummaryKeywordSearchengineAggregatedSearchengineIdAndDateClosedNotNull(Integer KeywordScanSummaryKeywordSearchengineAccountDomainId, Integer keywordScanSummaryKeywordSearchengineAggregatedSearchengineId);

	@Query("select s from SearchReportAccount where keywordScanSummary.keywordSearchengineAccountDomain.accountDomain.id = :accountDomainId and "
			+ "keywordScanSummary.keywordSearchengineAccountDomain.aggregatedSearchEngine.id = :ageId "
			+ "( (dateFirstSeen <= :date and dateClosed > :date) or (dateFirstSeen <= :date and dateClosed is null))")
	List<SearchReportAccount> findByAccountDomainIdAndAggregatedSearchEngineIdInDate(@Param("accountDomainId") Integer accountDomainId, @Param("ageId") Integer ageId, @Param("date") Date date);
	
	
}
