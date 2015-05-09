package com.flol.semrankercommon.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.flol.semrankercommon.domain.AccountDomain;
import com.flol.semrankercommon.domain.KeywordScanSummary;

@Transactional
public interface KeywordScanSummaryRepository extends CrudRepository<KeywordScanSummary, Integer>{
	
	@Query("from KeywordScanSummary where keywordSearchengineAccountDomain.keywordSearchengine.id = :keywordSearchengineId and "
			+ "date = :date and keywordScanSummaryStatus.id <> 1 and keywordScanSummaryStatus.id <> 4"
			)	
	List<KeywordScanSummary> findBykeywordSearchengineIdAndDateNotCompleted(@Param("keywordSearchengineId") Integer keywordSearchengineId, @Param("date") Date date);

	@Query("from KeywordScanSummary where keywordSearchengineAccountDomain.keywordSearchengine.id = :keywordSearchengineId and "
			+ "date = :date and (keywordScanSummaryStatus.id = 1 or keywordScanSummaryStatus.id = 4)"
			)	
	List<KeywordScanSummary> findBykeywordSearchengineIdAndDateCompleted(@Param("keywordSearchengineId") Integer keywordSearchengineId, @Param("date") Date date);

	@Query("select distinct(kss.keywordSearchengineAccountDomain.accountDomain) from KeywordScanSummary kss where kss.keywordScanSummaryStatus.id in (1,4) and kss.date = :date")
	List<AccountDomain> findAccountDomainWorkedByDate(@Param("date") Date date);
	
}
