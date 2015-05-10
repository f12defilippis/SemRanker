package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.SearchReportAccount;
import com.flol.semrankercommon.domain.Url;

public interface SearchReportAccountRepository extends CrudRepository<SearchReportAccount, Integer>{

	
	@Query("from SearchReportAccount where keywordScanSummary.keywordSearchengineAccountDomain.id = :keywordSearchengineAccountDomainId and "
			+ "url = :url and dateFirstSeen <> :date and dateLastSeen <> :date and dateClosed is null")
	List<SearchReportAccount> findOpenByKeywordSearchengineAccountDomainIdAndUrl(@Param("keywordSearchengineAccountDomainId") Integer keywordScanSummaryKeywordSearchengineAccountDomainId, @Param("url") Url url, @Param("date") Date date);

	List<SearchReportAccount> findByKeywordScanSummaryKeywordSearchengineAccountDomainIdAndKeywordScanSummaryKeywordSearchengineAccountDomainKeywordSearchengineAggregatedSearchengineIdAndDateClosedNotNull(Integer KeywordScanSummaryKeywordSearchengineAccountDomainId, Integer keywordScanSummaryKeywordSearchengineAggregatedSearchengineId);

	@Query("from SearchReportAccount where keywordScanSummary.keywordSearchengineAccountDomain.accountDomain.id = :accountDomainId and "
			+ "keywordScanSummary.keywordSearchengineAccountDomain.keywordSearchengine.aggregatedSearchengine.id = :ageId "
			+ "and ( (dateFirstSeen <= :date and dateClosed > :date) or (dateFirstSeen <= :date and dateClosed is null))")
	List<SearchReportAccount> findByAccountDomainIdAndAggregatedSearchEngineIdInDate(@Param("accountDomainId") Integer accountDomainId, @Param("ageId") Integer ageId, @Param("date") Date date);
	
	@Query(value = ""
			+ "select sum(khd.avgmonthlysearches*kpv.visitFactor), avg(sra.position) from "
			+ "SearchReportAccount sra, KeywordPositionVisit kpv, KeywordHistoryData khd where "
			+ "sra.keywordScanSummary.keywordSearchengineAccountDomain.keywordSearchengine.keyword.id = khd.keyword "
			+ "and sra.position = kpv.id "
			+ "and sra.keywordScanSummary.keywordSearchengineAccountDomain.accountDomain.id = :accountDomainId "
			+ "and sra.dateClosed is null"
			+ "")
	List<Object[]> getDomainScore(@Param("accountDomainId") Integer accountDomainId);
	
	
}
