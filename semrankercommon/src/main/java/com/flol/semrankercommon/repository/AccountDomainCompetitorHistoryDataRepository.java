package com.flol.semrankercommon.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.AccountDomainCompetitorHistoryData;

public interface AccountDomainCompetitorHistoryDataRepository extends CrudRepository<AccountDomainCompetitorHistoryData, Integer>{
	
	@Modifying
	@Query("delete from AccountDomainCompetitorHistoryData where date = :date and accountDomainCompetitor in "
			+ "(select adc from AccountDomainCompetitor adc where accountDomain.id = :accountDomainId)")
	public void deleteByAccountDomainIdAndDate(@Param("accountDomainId") Integer accountDomainId, @Param("date") Date date);

}
