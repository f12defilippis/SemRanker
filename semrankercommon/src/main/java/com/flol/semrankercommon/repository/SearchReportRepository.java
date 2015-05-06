package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.KeywordSearchengine;
import com.flol.semrankercommon.domain.SearchReport;
import com.flol.semrankercommon.domain.Url;

public interface SearchReportRepository extends CrudRepository<SearchReport, Integer>{

	List<SearchReport> findByKeywordSearchengineAndUrlAndPositionAndDateClosedNotNull(KeywordSearchengine keywordSearchengine, Url url, int position);

	@Query("from SearchReport where keywordSearchengine = :keywordSearchengine and url = :url and dateFirstSeen <> :date and dateLastSeen <> :date and dateClosed is not null")
	List<SearchReport> findByKeywordSearchengineAndUrlAndDateClosedNull(@Param("keywordSearchengine") KeywordSearchengine keywordSearchengine, @Param("url") Url url, @Param("date") Date date);

	List<SearchReport> findByKeywordSearchengineIdAndDateClosedNotNull(Integer keywordSearchengineId);

}
