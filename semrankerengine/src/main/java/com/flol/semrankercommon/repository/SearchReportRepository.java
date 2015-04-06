package com.flol.semrankercommon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.KeywordSearchengine;
import com.flol.semrankercommon.domain.SearchReport;
import com.flol.semrankercommon.domain.Url;

public interface SearchReportRepository extends CrudRepository<SearchReport, Integer>{

	List<SearchReport> findByKeywordSearchengineAndUrlAndPositionAllByDateClosedNotNull(KeywordSearchengine keywordSearchengine, Url url, int position);

	List<SearchReport> findByKeywordSearchengineAndUrlAllByDateClosedNotNull(KeywordSearchengine keywordSearchengine, Url url);

}
