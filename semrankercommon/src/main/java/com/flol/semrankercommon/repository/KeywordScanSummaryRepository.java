package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.KeywordScanSummary;

@Transactional
public interface KeywordScanSummaryRepository extends CrudRepository<KeywordScanSummary, Integer>{

	List<KeywordScanSummary> findByKeywordSearchengineAccountDomainKeywordSearchengineIdAndDate(Integer keywordSearchengineAccountDomainKeywordSearchengineId, Date date);
	
	
}
