package com.flol.semrankercommon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.AccountDomainCompetitor;


public interface AccountDomainCompetitorRepository extends CrudRepository<AccountDomainCompetitor, Integer>{

	List<AccountDomainCompetitor> findByAccountDomainIdAndAccountDomainCompetitorStatus(Integer accountDomainId, Integer accountDomainCompetitorStatus);
	
	List<AccountDomainCompetitor> findByAccountDomainId(Integer accountDomainId);

//	@Query("select distinct(adc.domain.id) from AccountDomainCompetitor where accountDomain.id = :accountDomainId")
//	List<Integer> findDomainIdByAccountDomainId(@Param("accountDomainId") Integer accountDomainId);
	
	
}
