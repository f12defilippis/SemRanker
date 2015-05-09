package com.flol.semrankercommon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.KeywordHistoryData;

public interface KeywordHistoryDataRepository extends CrudRepository<KeywordHistoryData, Integer>{

	public List<KeywordHistoryData> findByKeywordAndPeriod(Integer keyword, Integer period);
	
}
