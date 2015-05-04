package com.flol.semrankerengine.exampledataload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flol.semrankercommon.domain.AccountDomain;
import com.flol.semrankercommon.domain.AggregatedSearchengine;
import com.flol.semrankercommon.domain.Keyword;
import com.flol.semrankercommon.domain.KeywordSearchengine;
import com.flol.semrankercommon.domain.KeywordSearchengineAccountDomain;
import com.flol.semrankercommon.domain.Proxy;
import com.flol.semrankercommon.domain.ProxySearchengine;
import com.flol.semrankercommon.domain.Searchengine;
import com.flol.semrankercommon.repository.AggregatedSearchengineRepository;
import com.flol.semrankercommon.repository.KeywordRepository;
import com.flol.semrankercommon.repository.KeywordSearchengineAccountDomainRepository;
import com.flol.semrankercommon.repository.KeywordSearchengineRepository;
import com.flol.semrankercommon.repository.ProxyRepository;
import com.flol.semrankercommon.repository.ProxySearchengineRepository;
import com.flol.semrankercommon.repository.SearchengineRepository;

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

    
	@RequestMapping(value = "/populateKeywords", method = RequestMethod.GET, headers = "Accept=application/json")
    public Integer populateKeywords()
    {
		StopWatch sw = new StopWatch("Populate Keywords");
		sw.start();

		int kSaved = 0 , kSkipped = 0;
		AccountDomain ac = new AccountDomain();
		ac.setId(2);
		
		List<AggregatedSearchengine> secList = (List<AggregatedSearchengine>) aggregatedSearchengineRepository.findAll();
		
		List<String> keywordsList = readFile("keywords-positionly.txt");
		
		for(String kw : keywordsList)
		{
			List<Keyword> kwList = keywordRepository.findByText(kw);

			if(kwList==null || kwList.size()==0)
			{
				Keyword keyword = new Keyword();
				keyword.setText(kw);
				keyword.setWordcount(kw.split(" ").length);
				
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
				kSkipped++;
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
