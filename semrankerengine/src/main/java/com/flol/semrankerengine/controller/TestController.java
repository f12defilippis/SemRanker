package com.flol.semrankerengine.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankercommon.domain.Keyword;
import com.flol.semrankercommon.dto.DomainCompetitorTO;
import com.flol.semrankercommon.repository.AccountDomainCompetitorHistoryDataRepository;
import com.flol.semrankercommon.repository.SearchReportAccountRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semrankerengine.updatecompetitors.service.SearchReportRepositoryService;

@RestController
public class TestController {

//    private final AtomicLong counter = new AtomicLong();
    private final Logger log = LoggerFactory.getLogger(this.getClass());    
    
    @Autowired
    private SearchReportAccountRepository searchReportAccountRepository;

    @Autowired
    private SearchReportRepositoryService searchReportRepositoryService;
    
    @Autowired
    private AccountDomainCompetitorHistoryDataRepository accountDomainCompetitorHistoryDataRepository;
    
    @RequestMapping(value = "/keyword", method = RequestMethod.GET)
    public Keyword keyword(@RequestParam(value="text", defaultValue="World") String text) {
    	log.info("Chiamata funzione keyword");
    	System.out.println(System.getProperty("LOG_FILE"));
    	
    	Keyword keyword = new Keyword();
//    	keyword.setId(counter.incrementAndGet());
//    	keyword.setText(text);
    	return keyword;
    }
    
//    @RequestMapping(value = "/score", method = RequestMethod.GET)
//    public Object[] getScore() {
//    	log.info("Chiamata funzione score");
//    	Object[] returnBig = searchReportAccountRepository.getDomainScore(2);
//    	return returnBig;
//    }
    
    @RequestMapping(value = "/scores", method = RequestMethod.GET)
    public List<DomainCompetitorTO> getScores() {
    	log.info("Chiamata funzione score");
    	Pageable pageableInt = new PageRequest(0, 5);
    	List<DomainCompetitorTO> scores =  searchReportRepositoryService.getDomainsScore(2,pageableInt);
    	return scores;
    }
    
    @RequestMapping(value = "/adc", method = RequestMethod.GET)
    public String getAdc() {
    	log.info("Chiamata funzione score");
    	accountDomainCompetitorHistoryDataRepository.deleteByAccountDomainIdAndDate(2, DateUtil.getTodaysMidnight());
    	return "OK";
    }
    
	
}
