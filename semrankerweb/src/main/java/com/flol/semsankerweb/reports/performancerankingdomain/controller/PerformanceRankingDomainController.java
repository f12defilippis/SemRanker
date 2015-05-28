package com.flol.semsankerweb.reports.performancerankingdomain.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flol.semrankercommon.domain.AccountDomainHistoryData;
import com.flol.semrankercommon.domain.Country;
import com.flol.semrankercommon.domain.Searchengine;
import com.flol.semrankercommon.repository.AccountDomainHistoryDataRepository;
import com.flol.semrankercommon.repository.KeywordSearchengineAccountDomainRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semsankerweb.reports.performancerankingdomain.dto.PerformanceRankingDomainResultTO;
import com.flol.semsankerweb.reports.performancerankingdomain.form.PerformanceRankingDomainSearchForm;

@Controller
public class PerformanceRankingDomainController {

	@Autowired
	private AccountDomainHistoryDataRepository accountDomainHistoryDataRepository;

	@Autowired
	private KeywordSearchengineAccountDomainRepository keywordSearchengineAccountDomainRepository;

//	@RequestMapping("/performance_ranking_domain")
//	public String init(@ModelAttribute("performanceRankingDomainSearchForm") PerformanceRankingDomainSearchForm performanceRankingDomainSearchForm, Model model)
//	{
//		
//		List<AccountDomainHistoryData> list = accountDomainHistoryDataRepository.findByAccountDomainId(2);
//
//		List<PerformanceRankingDomainResultTO> returnList = new ArrayList<PerformanceRankingDomainResultTO>();
//		
//		for(AccountDomainHistoryData adhd : list)
//		{
//			PerformanceRankingDomainResultTO item = new PerformanceRankingDomainResultTO();
//			item.setEngine(adhd.getSearchengine()!=null ? adhd.getSearchengine().getName() : "***");
//			item.setCountry(adhd.getCountry()!=null ? adhd.getCountry().getName() : "***");
//			item.setDomain(adhd.getAccountDomain().getDomain().getName());
//			item.setDate(adhd.getDate());
//			item.setPosition(adhd.getAveragePosition().doubleValue());
//			item.setScore(adhd.getScore().doubleValue());
//			returnList.add(item);
//		}
//		
//		model.addAttribute("returnList",returnList);		
//		
//		return "reports/performancerankingdomain/performancerankingdomain";
//	}
	
	
	@RequestMapping("performance_ranking_domain")
	public String search(@ModelAttribute("performanceRankingDomainSearchForm") PerformanceRankingDomainSearchForm performanceRankingDomainSearchForm, Model model)
	{		
		List<PerformanceRankingDomainResultTO> returnList = getData(performanceRankingDomainSearchForm);
		
		model.addAttribute("returnList",returnList);
		
		return "reports/performancerankingdomain/performancerankingdomain";
	}
	
	@ResponseBody
	@RequestMapping(value = "performance_ranking_domain/json", method = RequestMethod.GET, produces="application/json")
	public List<PerformanceRankingDomainResultTO> loadGraph(Model model, @ModelAttribute("performanceRankingDomainSearchForm") PerformanceRankingDomainSearchForm performanceRankingDomainSearchForm)
	{
		return getData(performanceRankingDomainSearchForm);
	}


	private List<PerformanceRankingDomainResultTO> getData(PerformanceRankingDomainSearchForm performanceRankingDomainSearchForm)
	{
		Date dateTo = performanceRankingDomainSearchForm.getDateTo()!=null && !performanceRankingDomainSearchForm.getDateTo().equals("") ? DateUtil.getMidnight( DateUtil.fromStringToDate(performanceRankingDomainSearchForm.getDateTo()) ) : DateUtil.getTodaysMidnight();
		Date dateFrom = performanceRankingDomainSearchForm.getDateFrom()!=null && !performanceRankingDomainSearchForm.getDateFrom().equals("") ? DateUtil.getMidnight( DateUtil.fromStringToDate(performanceRankingDomainSearchForm.getDateFrom()) ) : DateUtil.getMidnightDaysAgo(30);
		Integer accountDomainId = performanceRankingDomainSearchForm.getAccountDomainId();
		
		
		Searchengine searchengine = performanceRankingDomainSearchForm.getSearchengineId()==null || performanceRankingDomainSearchForm.getSearchengineId().equals(-1) ? null : new Searchengine(performanceRankingDomainSearchForm.getSearchengineId());
		Country country = performanceRankingDomainSearchForm.getCountryId()==null || performanceRankingDomainSearchForm.getCountryId().equals(-1) ? null : new Country(performanceRankingDomainSearchForm.getCountryId());
		
		List<AccountDomainHistoryData> list = accountDomainHistoryDataRepository.findByFilters(accountDomainId, dateFrom, dateTo, searchengine, country);

		
		List<PerformanceRankingDomainResultTO> returnList = new ArrayList<PerformanceRankingDomainResultTO>();
		
		for(AccountDomainHistoryData adhd : list)
		{
			PerformanceRankingDomainResultTO item = new PerformanceRankingDomainResultTO();
			item.setEngine(adhd.getSearchengine()!=null ? adhd.getSearchengine().getName() : "***");
			item.setCountry(adhd.getCountry()!=null ? adhd.getCountry().getName() : "***");
			item.setDomain(adhd.getAccountDomain().getDomain().getName());
			item.setDate(adhd.getDate());
			item.setPosition(adhd.getAveragePosition().doubleValue());
			item.setScore(adhd.getScore().doubleValue());
			returnList.add(item);
		}
		
		return returnList;
	}
	
	
	
	@ModelAttribute
	public void common(Model model)
	{
		List<Searchengine> searchengineList = keywordSearchengineAccountDomainRepository.getSearchengineByAccountDomainId(2);
		List<Country> countryList = keywordSearchengineAccountDomainRepository.getCountryByAccountDomainId(2);
		
		
		model.addAttribute("searchengineList",searchengineList);
		model.addAttribute("countryList",countryList);
	}
	
	
}
