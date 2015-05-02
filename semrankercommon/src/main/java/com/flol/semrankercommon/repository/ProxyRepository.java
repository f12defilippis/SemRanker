package com.flol.semrankercommon.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.Proxy;

@Transactional
public interface ProxyRepository extends CrudRepository<Proxy, Integer>{

	public List<Proxy> findByIpAndPort(String ip, String port);
	
}
