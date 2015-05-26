package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.AccountDomainHistoryData;
import com.flol.semrankercommon.domain.Country;
import com.flol.semrankercommon.domain.Searchengine;

public interface AccountDomainHistoryDataRepository extends CrudRepository<AccountDomainHistoryData, Integer>{

	@Modifying
	@Query("delete from AccountDomainHistoryData where accountDomain.id = :accountDomainId and date = :date")
	public void deleteByAccountDomainIdAndDate(@Param("accountDomainId") Integer accountDomainId, @Param("date") Date date);
	
	public List<AccountDomainHistoryData> findByAccountDomainId(Integer accountDomainId);

	@Query("from AccountDomainHistoryData where accountDomain.id = :accountDomainId and date >= :dateFrom and date <= :dateTo and "
			+ "((:searchengine is null and searchengine is null) or searchengine = :searchengine) and "
			+ "((:country is null and country is null) or country = :country)")
	public List<AccountDomainHistoryData> findByFilters(@Param("accountDomainId") Integer accountDomainId, @Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo,
			@Param("searchengine") Searchengine searchengine, @Param("country") Country country);

}
