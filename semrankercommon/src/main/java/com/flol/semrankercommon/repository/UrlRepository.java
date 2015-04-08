package com.flol.semrankercommon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.Url;

public interface UrlRepository extends CrudRepository<Url, Integer>{

	List<Url> findByUrl(String url);
	
}
