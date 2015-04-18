package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.ProxySearchengine;

@Transactional
public interface ProxySearchengineRepository extends CrudRepository<ProxySearchengine, Integer>{

	@Query("from ProxySearchengine where searchengine = :searchengine and dateLastscan < :date and usage = 0")
	List<ProxySearchengine> findProxy(@Param("date") Date date, @Param("searchengine") Integer searchengine);	

	@Modifying
	@Transactional
	@Query("update ProxySearchengine set usage = 1 where searchengine = :searchengine and dateLastscan < :date")
	Integer setProxyUsage(@Param("date") Date date, @Param("searchengine") Integer searchengine);	
	
	
}
