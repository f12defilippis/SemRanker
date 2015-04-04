package com.flol.semrankercommon.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.SearchengineParameter;

@Transactional
public interface SearchengineParameterRepository extends CrudRepository<SearchengineParameter, String>{

	SearchengineParameter findById(String id);
	
}
