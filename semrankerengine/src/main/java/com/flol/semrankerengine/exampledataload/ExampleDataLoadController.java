package com.flol.semrankerengine.exampledataload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.flol.semrankercommon.domain.AccountDomain;
import com.flol.semrankercommon.domain.AggregatedSearchengine;
import com.flol.semrankercommon.domain.Keyword;
import com.flol.semrankercommon.domain.KeywordHistoryData;
import com.flol.semrankercommon.domain.KeywordPositionVisit;
import com.flol.semrankercommon.domain.KeywordSearchengine;
import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.Period;
import com.flol.semrankercommon.domain.Proxy;
import com.flol.semrankercommon.domain.ProxySearchengine;
import com.flol.semrankercommon.domain.Searchengine;
import com.flol.semrankercommon.repository.AggregatedSearchengineRepository;
import com.flol.semrankercommon.repository.KeywordHistoryDataRepository;
import com.flol.semrankercommon.repository.KeywordPositionVisitRepository;
import com.flol.semrankercommon.repository.KeywordRepository;
import com.flol.semrankercommon.repository.KeywordSearchengineAccountDomainRepository;
import com.flol.semrankercommon.repository.KeywordSearchengineRepository;
import com.flol.semrankercommon.repository.PeriodRepository;
import com.flol.semrankercommon.repository.ProxyRepository;
import com.flol.semrankercommon.repository.ProxySearchengineRepository;
import com.flol.semrankercommon.repository.SearchengineRepository;
import com.flol.semrankerengine.dto.PopulateKeywordsDataBeanTO;
import com.flol.semrankerengine.dto.PopulateKeywordsReadyBeanTO;

@RestController
public class ExampleDataLoadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());   
    
    @Autowired
    private ProxyRepository proxyRepository;

    @Autowired
    private ProxySearchengineRepository proxySearchengineRepository;
    
    @Autowired
    private SearchengineRepository searchengineRepository;

    @Autowired
    private AggregatedSearchengineRepository aggregatedSearchengineRepository;
    
    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private KeywordSearchengineRepository keywordSearchengineRepository;

    @Autowired
    private KeywordSearchengineAccountDomainRepository keywordSearchengineAccountDomainRepository;
    
    @Autowired
    private PeriodRepository periodRepository;
    
    @Autowired
    private KeywordHistoryDataRepository keywordHistoryDataRepository;

    @Autowired
    private KeywordPositionVisitRepository keywordPositionVisitRepository;

    
	@RequestMapping(value = "/position", method = RequestMethod.GET, headers = "Accept=application/json")
	public Integer position()
    {
    	BigDecimal bd = new BigDecimal(0.004);
    	BigDecimal tick = bd.divide(new BigDecimal(80));
    	for(int i = 21 ; i<=100 ; i++)
    	{
    		bd = bd.subtract(tick);
    		KeywordPositionVisit kpv = new KeywordPositionVisit();
    		kpv.setId(i);
    		kpv.setVisitFactor(bd);
    		
    		keywordPositionVisitRepository.save(kpv);
    		
    	}
    	
    	
    	
    	return 1;
    }
    
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/populateKeywords", method = RequestMethod.GET, headers = "Accept=application/json")
    public Integer populateKeywords()
    {
		StopWatch sw = new StopWatch("Populate Keywords");
		sw.start();

		InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream("kw_positionly_1.csv");
		CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(in)),'\t');
		ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
		strat.setType(PopulateKeywordsDataBeanTO.class);
		String[] columns = new String[] {"adgroup", "keyword", "currency", "avgMonthlySearches", "competition", "suggestedBid", "imprshare", "inaccount", "inplan", "extractedfrom"}; // the fields to bind do in your JavaBean
		strat.setColumnMapping(columns);

		CsvToBean csv = new CsvToBean();
		List<PopulateKeywordsDataBeanTO> list = csv.parse(strat, reader);

		List<PopulateKeywordsReadyBeanTO> readyList = new ArrayList<PopulateKeywordsReadyBeanTO>();
		for(PopulateKeywordsDataBeanTO bean : list)
		{
			try {
				if(bean.getKeyword()!=null && !bean.getKeyword().equals(""))
				{
					PopulateKeywordsReadyBeanTO readyBean = new PopulateKeywordsReadyBeanTO();
					readyBean.setKeyword(bean.getKeyword());
					readyBean.setAvgMonthlySearches(bean.getAvgMonthlySearches()!=null && !bean.getAvgMonthlySearches().equals("") ? Integer.parseInt(bean.getAvgMonthlySearches().replace(" ", "")) : 0);
					readyBean.setCompetition(bean.getCompetition()!=null && !bean.getCompetition().replace("\"", "").equals("") ? Double.parseDouble(bean.getCompetition().replace("\"", "").replace(",", ".")) : null);
					readyBean.setSuggestedBid(bean.getSuggestedBid()!=null && !bean.getSuggestedBid().replace("\"", "").equals("") ? Double.parseDouble(bean.getSuggestedBid().replace("\"", "").replace(",", ".")) : null);
					readyList.add(readyBean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		int kSaved = 0 , kSkipped = 0;
		AccountDomain ac = new AccountDomain();
		ac.setId(2);
		
		List<AggregatedSearchengine> secList = (List<AggregatedSearchengine>) aggregatedSearchengineRepository.findAll();
				
		for(PopulateKeywordsReadyBeanTO readyBean : readyList)
		{
			List<Keyword> kwList = keywordRepository.findByText(readyBean.getKeyword());
			Keyword keyword = null;

			if(kwList==null || kwList.size()==0)
			{
				keyword = new Keyword();
				keyword.setText(readyBean.getKeyword());
				keyword.setWordcount(readyBean.getKeyword().split(" ").length);
				
				keywordRepository.save(keyword);
				
				for(AggregatedSearchengine sec : secList)
				{
					KeywordSearchengine ksec = new KeywordSearchengine();
					ksec.setAggregatedSearchengine(sec);
					ksec.setKeyword(keyword);
					
					keywordSearchengineRepository.save(ksec);
					
					KeywordSearchengineAccountDomain ksad = new KeywordSearchengineAccountDomain();
					ksad.setKeywordSearchengine(ksec);
					ksad.setAccountDomain(ac);
					
					keywordSearchengineAccountDomainRepository.save(ksad);
				}
				
				kSaved++;
				
			}else
			{
				keyword = kwList.get(0);
				kSkipped++;
			}
			
			List<Period> periodList = periodRepository.findByMonthAndYear(Calendar.getInstance().get(Calendar.MONTH)+1, Calendar.getInstance().get(Calendar.YEAR));
			Period period = periodList.get(0);
			
			List<KeywordHistoryData> oldkhd = keywordHistoryDataRepository.findByKeywordAndPeriod(keyword.getId(), period.getId());

			if(oldkhd==null || oldkhd.size()==0)
			{
				KeywordHistoryData khd = new KeywordHistoryData();
				khd.setKeyword(keyword.getId());
				khd.setAvgmonthlysearches(readyBean.getAvgMonthlySearches());
				khd.setCompetition(readyBean.getCompetition());
				khd.setPeriod(period.getId());
				khd.setScandate(new Date());
				khd.setSearchengineCountry(1);
				khd.setSuggestedbid(readyBean.getSuggestedBid());
				
				keywordHistoryDataRepository.save(khd);
			}
			
		}
		
		sw.stop();
		logger.info(sw.prettyPrint());
		logger.info("Saved: " + kSaved + " Skipped: " + kSkipped);
		return kSaved;
    }
    
    
	@RequestMapping(value = "/populateProxies", method = RequestMethod.GET, headers = "Accept=application/json")
	public Integer populateProxies()
	{
		StopWatch sw = new StopWatch("Populate Proxy");
		sw.start();
		List<Searchengine> searchengines = (List<Searchengine>) searchengineRepository.findAll();
		
		int savedProxies = 0, skippedProxies = 0;
		List<String> listaProxies = readFile("proxies.txt");
		for(String key : listaProxies)
		{
			String host = key.split(":")[0];
			String port = key.split(":")[1];
			List<Proxy> proxyList = proxyRepository.findByIpAndPort(host, port);
			if(proxyList==null || proxyList.size()==0)
			{
				logger.debug("SAVING PROXY: " + key);
				Proxy proxy = new Proxy();
				proxy.setIp(host);
				proxy.setPort(port);
				proxy.setDatecreate(new Date());
				proxyRepository.save(proxy);
				
				for(Searchengine se : searchengines)
				{
					ProxySearchengine proxyse = new ProxySearchengine();
					proxyse.setProxy(proxy);
					proxyse.setSearchengine(se);
					proxyse.setDateLastscan(new Date());
					proxyse.setUsage(0);
					proxyse.setNumFails(0);
					proxyse.setNumSuccess(0);
					proxyse.setStreak(0);
					
					proxySearchengineRepository.save(proxyse);
				}
				
				savedProxies++;
			}else
			{
				skippedProxies++;
			}
		}
		logger.info("Proxies SAVED: " + savedProxies + " || Proxies SKIPPED: " + skippedProxies);
		sw.stop();
		logger.info(sw.prettyPrint());
		
		
		return savedProxies;
	}

	public List<String> readFile(String filename) {
		List<String> lista = new ArrayList<String>();
		InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream(filename);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null)
            	lista.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }	
    
}
