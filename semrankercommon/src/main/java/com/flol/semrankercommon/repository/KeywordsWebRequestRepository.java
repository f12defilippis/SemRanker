package com.flol.semrankercommon.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.KeywordsWebRequest;

@Transactional
public interface KeywordsWebRequestRepository extends CrudRepository<KeywordsWebRequest, Integer>{

	List<KeywordsWebRequest> findBySearchengineAndEndDateNull(Integer searchengine);
	
	
	
}
