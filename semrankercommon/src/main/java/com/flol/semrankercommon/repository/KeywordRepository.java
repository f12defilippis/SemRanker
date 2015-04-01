package com.flol.semrankercommon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.Keyword;

public interface KeywordRepository extends CrudRepository<Keyword, Long>{

	public List<Keyword> findByText(String text);
	
}
