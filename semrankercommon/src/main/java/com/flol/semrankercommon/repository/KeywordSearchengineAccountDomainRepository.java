package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;

@Transactional
public interface KeywordSearchengineAccountDomainRepository extends CrudRepository<KeywordSearchengineAccountDomain, Integer>{

	@Query("from KeywordSearchengineAccountDomain where keywordSearchengine.aggregatedSearchengine.searchengineCountry.searchengine.id = :searchengine and "
			+ "id not in "
			+ "(select distinct kss.keywordSearchengineAccountDomain.id from KeywordScanSummary kss where "
			+ "kss.date = :date and (kss.keywordScanSummaryStatus.id = 1 or kss.keywordScanSummaryStatus.id = 4 or (kss.keywordScanSummaryStatus.id = 5 and kss.numParseFails >= :maxParseFails))"
			+ ")")
	List<KeywordSearchengineAccountDomain> findDataToSearch(@Param("date") Date date, @Param("searchengine") Integer searchengine, @Param("maxParseFails") Integer maxParseFails);
	
	
}
