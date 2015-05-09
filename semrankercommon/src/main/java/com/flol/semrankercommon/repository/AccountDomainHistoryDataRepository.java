package com.flol.semrankercommon.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.AccountDomainHistoryData;

public interface AccountDomainHistoryDataRepository extends CrudRepository<AccountDomainHistoryData, Integer>{

	@Modifying
	@Query("delete from AccountDomainHistoryData where accountDomain.id = :accountDomainId and date = :date")
	public void deleteByAccountDomainIdAndDate(@Param("accountDomainId") Integer accountDomainId, @Param("date") Date date);
	
	
}
