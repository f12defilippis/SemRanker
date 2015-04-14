package com.flol.semrankercommon.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.KeywordScanSummary;

@Transactional
public interface KeywordScanSummaryRepository extends CrudRepository<KeywordScanSummary, Integer>{

}
