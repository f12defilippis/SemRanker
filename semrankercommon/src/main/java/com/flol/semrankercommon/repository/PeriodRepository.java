package com.flol.semrankercommon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flol.semrankercommon.domain.Period;

public interface PeriodRepository extends CrudRepository<Period,Integer>{
	
	public List<Period> findByMonthAndYear(int month, int year);

}
