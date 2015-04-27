package com.flol.semrankercommon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.AccountDomainCompetitor;


public interface AccountDomainCompetitorRepository extends CrudRepository<AccountDomainCompetitor, Integer>{

	List<AccountDomainCompetitor> findByAccountDomainIdAndAccountDomainCompetitorStatus(Integer accountDomainId, Integer accountDomainCompetitorStatus);
	
}
