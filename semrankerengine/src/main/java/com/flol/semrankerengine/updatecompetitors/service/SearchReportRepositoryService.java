package com.flol.semrankerengine.updatecompetitors.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.flol.semrankercommon.domain.Country;
import com.flol.semrankercommon.domain.Domain;
import com.flol.semrankercommon.domain.Searchengine;
import com.flol.semrankercommon.dto.DomainCompetitorTO;
import com.flol.semrankercommon.repository.SearchReportRepository;

@Component
public class SearchReportRepositoryService {

	@Autowired
	private SearchReportRepository searchReportRepository;
	
	public List<DomainCompetitorTO> getDomainsScore(Integer accountDomainId, Searchengine searchengine, Country country, Pageable pageable)
	{
		List<DomainCompetitorTO> ret = new ArrayList<DomainCompetitorTO>();
		List<Object[]> scoresObject = searchReportRepository.getDomainsScore(accountDomainId, searchengine, country, pageable);
		for(Object [] obj : scoresObject)
		{
			DomainCompetitorTO dcTo = new DomainCompetitorTO();
			dcTo.setDomain((Domain)obj[0]);
			dcTo.setCommonKeywords(((Long)obj[1]).intValue());
			dcTo.setAvgPosition(new BigDecimal((Double)obj[2]));
			dcTo.setScore((BigDecimal)obj[3]);
			
			dcTo.setSearchengine(searchengine);
			dcTo.setCountry(country);
			
			ret.add(dcTo);
		}
		
		return ret;
	}
	
}
