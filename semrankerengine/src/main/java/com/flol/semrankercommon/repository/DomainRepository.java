package com.flol.semrankercommon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.Domain;

public interface DomainRepository extends CrudRepository<Domain, Integer>{

	List<Domain> findByName(String name);
	
	
}
