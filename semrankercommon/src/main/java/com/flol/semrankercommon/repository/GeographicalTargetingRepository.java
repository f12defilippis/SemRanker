package com.flol.semrankercommon.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.GeographicalTargeting;

@Transactional
public interface GeographicalTargetingRepository extends CrudRepository<GeographicalTargeting, Integer>{

}
