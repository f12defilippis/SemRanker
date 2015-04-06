package com.flol.semrankercommon.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;

@Transactional
public interface KeywordSearchengineAccountDomainRepository extends CrudRepository<KeywordSearchengineAccountDomain, Integer>{

}
