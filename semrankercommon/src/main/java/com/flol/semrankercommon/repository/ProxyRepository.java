package com.flol.semrankercommon.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.Proxy;

@Transactional
public interface ProxyRepository extends CrudRepository<Proxy, Integer>{

	
	
}
