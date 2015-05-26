package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.Country;
import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.Searchengine;

@Transactional
public interface KeywordSearchengineAccountDomainRepository extends CrudRepository<KeywordSearchengineAccountDomain, Integer>{

	@Query("from KeywordSearchengineAccountDomain where keywordSearchengine.aggregatedSearchengine.searchengineCountry.searchengine.id = :searchengine and "
			+ "id not in "
			+ "(select distinct kss.keywordSearchengineAccountDomain.id from KeywordScanSummary kss where "
			+ "kss.date = :date and (kss.keywordScanSummaryStatus.id = 1 or kss.keywordScanSummaryStatus.id = 4 or kss.numParseFails >= :maxParseFails)"
			+ ")")
	List<KeywordSearchengineAccountDomain> findDataToSearch(@Param("date") Date date, @Param("searchengine") Integer searchengine, @Param("maxParseFails") Integer maxParseFails);
	

	
	@Query("select distinct ksad.keywordSearchengine.aggregatedSearchengine.searchengineCountry.searchengine from KeywordSearchengineAccountDomain ksad "
			+ "where ksad.accountDomain.id = :accountDomainId")
	List<Searchengine> getSearchengineByAccountDomainId(@Param("accountDomainId") Integer accountDomainId);

	@Query("select distinct ksad.keywordSearchengine.aggregatedSearchengine.searchengineCountry.country from KeywordSearchengineAccountDomain ksad "
			+ "where ksad.accountDomain.id = :accountDomainId")
	List<Country> getCountryByAccountDomainId(@Param("accountDomainId") Integer accountDomainId);
	
}
