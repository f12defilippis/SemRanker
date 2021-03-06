package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.Country;
import com.flol.semrankercommon.domain.KeywordSearchengine;
import com.flol.semrankercommon.domain.SearchReport;
import com.flol.semrankercommon.domain.Searchengine;
import com.flol.semrankercommon.domain.Url;

public interface SearchReportRepository extends CrudRepository<SearchReport, Integer>{

	List<SearchReport> findByKeywordSearchengineAndUrlAndPositionAndDateClosedNotNull(KeywordSearchengine keywordSearchengine, Url url, int position);

	@Query("from SearchReport where keywordSearchengine = :keywordSearchengine and url = :url and dateFirstSeen <> :date and dateLastSeen <> :date and dateClosed is null")
	List<SearchReport> findByKeywordSearchengineAndUrlAndDateClosedNull(@Param("keywordSearchengine") KeywordSearchengine keywordSearchengine, @Param("url") Url url, @Param("date") Date date);

	@Query("from SearchReport where keywordSearchengine = :keywordSearchengine and position = :position and dateFirstSeen <> :date and dateLastSeen <> :date and dateClosed is null")
	List<SearchReport> findByKeywordSearchengineAndPositionAndDateClosedNull(@Param("keywordSearchengine") KeywordSearchengine keywordSearchengine, @Param("position") Integer position, @Param("date") Date date);
	
	
	
	List<SearchReport> findByKeywordSearchengineIdAndDateClosedNull(Integer keywordSearchengineId);

	@Query(value = ""
			+ "select sr.url.domain as domain, count(sr.url.domain.name) as commonKeywords, avg(sr.position) as avgPosition, sum(khd.avgmonthlysearches*kpv.visitFactor) as score from "
			+ "SearchReport sr, KeywordPositionVisit kpv, KeywordHistoryData khd, KeywordSearchengine ks, KeywordSearchengineAccountDomain ksad, AggregatedSearchengine ase, SearchengineCountry sec where "
			+ "sr.keywordSearchengine = ks.id "
			+ "and ksad.keywordSearchengine = ks.id "
			+ "and sr.keywordSearchengine.keyword.id = khd.keyword "
			+ "and sr.position = kpv.id "
			+ "and ks.aggregatedSearchengine = ase.id "
			+ "and ase.searchengineCountry = sec.id "
			+ "and ksad.accountDomain.id = :accountDomainId "
			+ "and (:country is null or sec.country = :country) "
			+ "and (:searchengine is null or sec.searchengine = :searchengine) "
			+ "and sr.dateClosed is null group by sr.url.domain.name order by score desc"
			+ "")
	List<Object[]> getDomainsScore(@Param("accountDomainId") Integer accountDomainId, @Param("searchengine") Searchengine searchengine, @Param("country") Country country, Pageable pageable);
	
	
	
}
