package com.flol.semsankerweb.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.flol.semrankercommon.domain.HistoricalCheckThreshold;
import com.flol.semrankercommon.domain.SearchReportAccount;
import com.flol.semrankercommon.domain.TopPositionThreshold;
import com.flol.semrankercommon.repository.AccountDomainRepository;
import com.flol.semrankercommon.repository.HistoricalCheckThresholdRepository;
import com.flol.semrankercommon.repository.SearchReportAccountRepository;
import com.flol.semrankercommon.repository.TopPositionThresholdRepository;
import com.flol.semrankercommon.util.DateUtil;
import com.flol.semsankerweb.dto.DomainDataOverviewTO;
import com.flol.semsankerweb.dto.KeywordDataOverviewTO;

public class DomainOverviewFacade {

	@Autowired
	private AccountDomainRepository accountDomainRepository;
	
	@Autowired
	private HistoricalCheckThresholdRepository historicalCheckThresholdRepository;
	
	@Autowired
	private SearchReportAccountRepository searchReportAccountRepository;
	
	@Autowired
	private TopPositionThresholdRepository topPositionThresholdRepository;
	
	public DomainDataOverviewTO retrieveDomainOverview(Integer accountDomainId){
		DomainDataOverviewTO ret = new DomainDataOverviewTO();
	
		// get actual data of domain
		List<SearchReportAccount> actualReportList = searchReportAccountRepository.findByKeywordScanSummaryKeywordSearchengineAccountDomainIdAndDateClosedNotNull(accountDomainId);

		// get list of historical threshold
		List<HistoricalCheckThreshold> historicalCheckThresholdList = (List<HistoricalCheckThreshold>) historicalCheckThresholdRepository.findAll();

		Map<HistoricalCheckThreshold,List<SearchReportAccount>> historicalReportList = new HashMap<HistoricalCheckThreshold, List<SearchReportAccount>>();
		
		// for each threshold get domain data
		for(HistoricalCheckThreshold hct : historicalCheckThresholdList)
		{
			Date dateToCheck = DateUtil.getMidnightDaysAgo(hct.getValue());
			List<SearchReportAccount> searchReportListInDate = searchReportAccountRepository.findByAccountDomainIdInDate(accountDomainId, dateToCheck);
			historicalReportList.put(hct, searchReportListInDate);
		}

		// aggregate data to present
		ret = aggregateData(actualReportList, historicalReportList);
		
		return ret;
	}
	
	public DomainDataOverviewTO aggregateData(List<SearchReportAccount> actualReportList, Map<HistoricalCheckThreshold,List<SearchReportAccount>> historicalReportList)
	{
		DomainDataOverviewTO ret = new DomainDataOverviewTO();
		
		List<TopPositionThreshold> topPositionThresholdList = (List<TopPositionThreshold>) topPositionThresholdRepository.findAll();
		
		for(SearchReportAccount sra : actualReportList)
		{
			Integer keywordId = sra.getKeywordScanSummary().getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getId();
			
			if(ret.getKeywordsData().get(keywordId)==null)
			{
				KeywordDataOverviewTO keywordOverview = new KeywordDataOverviewTO();
				keywordOverview.setText(sra.getKeywordScanSummary().getKeywordSearchengineAccountDomain().getKeywordSearchengine().getKeyword().getText());
				keywordOverview.setPosition(sra.getPosition());

				for(TopPositionThreshold tpt : topPositionThresholdList)
				{
					if(sra.getPosition() >= tpt.getLowThreshold() && sra.getPosition() <= tpt.getHighThreshold())
					{
						if(ret.getKeywordsInTop().get(tpt) == null) 
						{
							ret.getKeywordsInTop().put(tpt, 1); 
						}else
						{
							Integer kit = ret.getKeywordsInTop().get(tpt);
							kit = kit + 1;
							ret.getKeywordsInTop().put(tpt, kit);
						}
					}
				}
			
			}
			
		}
		
		
		
		return ret;
	}
	
	
}
