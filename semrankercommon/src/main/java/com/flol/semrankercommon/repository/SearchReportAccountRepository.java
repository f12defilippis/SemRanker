package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.SearchReportAccount;
import com.flol.semrankercommon.domain.Url;

public interface SearchReportAccountRepository extends CrudRepository<SearchReportAccount, Integer>{

	List<SearchReportAccount> findByKeywordScanSummaryKeywordSearchengineAccountDomainAndUrlAndDateClosedNotNull(KeywordSearchengineAccountDomain keywordScanSummaryKeywordSearchengineAccountDomain, Url url);

	List<SearchReportAccount> findByKeywordScanSummaryKeywordSearchengineAccountDomainIdAndDateClosedNotNull(Integer KeywordScanSummaryKeywordSearchengineAccountDomainId);

	@Query("select s from SearchReportAccount where keywordScanSummary.keywordSearchengineAccountDomain.accountDomain.id = :accountDomainId and "
			+ "( (dateFirstSeen <= :date and dateClosed > :date) or (dateFirstSeen <= :date and dateClosed is null))")
	List<SearchReportAccount> findByAccountDomainIdInDate(@Param("accountDomainId") Integer accountDomainId, @Param("date") Date date);
	
	
}
