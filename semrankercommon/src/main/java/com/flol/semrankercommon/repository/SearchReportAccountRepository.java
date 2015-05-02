package com.flol.semrankercommon.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.SearchReportAccount;
import com.flol.semrankercommon.domain.Url;

public interface SearchReportAccountRepository extends CrudRepository<SearchReportAccount, Integer>{

	List<SearchReportAccount> findByKeywordScanSummaryKeywordSearchengineAccountDomainIdAndKeywordScanSummaryKeywordSearchengineAccountDomainKeywordSearchengineAggregatedSearchengineIdAndUrlAndDateClosedNull(Integer keywordScanSummaryKeywordSearchengineAccountDomainId, Integer KeywordScanSummaryKeywordSearchengineAggregatedSearchengineId, Url url);

	List<SearchReportAccount> findByKeywordScanSummaryKeywordSearchengineAccountDomainIdAndKeywordScanSummaryKeywordSearchengineAccountDomainKeywordSearchengineAggregatedSearchengineIdAndDateClosedNotNull(Integer KeywordScanSummaryKeywordSearchengineAccountDomainId, Integer keywordScanSummaryKeywordSearchengineAggregatedSearchengineId);

	@Query("from SearchReportAccount where keywordScanSummary.keywordSearchengineAccountDomain.accountDomain.id = :accountDomainId and "
			+ "keywordScanSummary.keywordSearchengineAccountDomain.keywordSearchengine.aggregatedSearchengine.id = :ageId "
			+ "and ( (dateFirstSeen <= :date and dateClosed > :date) or (dateFirstSeen <= :date and dateClosed is null))")
	List<SearchReportAccount> findByAccountDomainIdAndAggregatedSearchEngineIdInDate(@Param("accountDomainId") Integer accountDomainId, @Param("ageId") Integer ageId, @Param("date") Date date);
	
	@Query(value = "select sum(score) from ("
			+ "select khd.avgmonthlysearches*kpv.visitFactor score from"
			+ "SearchReportAccount sra, KeywordPositionVisit kpv, KeywordHistoryData khd where "
			+ "sra.keywordScanSummary.keywordSearchEngineAccountDomain.keywordSearchEngine.keyword.id = khd.keyword "
			+ "and sra.position = kpv.id "
			+ "and sra.keywordScanSummary.keywordSearchEngineAccountDomain.accountDomain.id = :accountDomainId"
			+ "and sra.dateClosed is null"
			+ ")" , nativeQuery = true)
	BigDecimal getDomainScore(@Param("accountDomainId") Integer accountDomainId);
	
	
}
