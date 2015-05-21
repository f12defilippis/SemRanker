package com.flol.semrankercommon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.KeywordSearchengine;

public interface KeywordSearchengineRepository extends CrudRepository<KeywordSearchengine, Integer>{

	public List<KeywordSearchengine> findByAggregatedSearchengineIdAndKeywordId(Integer aggregatedSearchengineId, Integer keywordId);
	
}
