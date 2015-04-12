package com.flol.semsankerweb.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.flol.semrankercommon.domain.HistoricalCheckThreshold;
import com.flol.semrankercommon.domain.TopPositionThreshold;

public class DomainDataOverviewTO {

	private Integer keywordsNumber;

	private Integer keywordsUp;
	
	private Integer keywordsDown;
	
	private BigDecimal domainScore;
	
	private Map<TopPositionThreshold,Integer> keywordsInTop;
	
	private Map<HistoricalCheckThreshold,Map<TopPositionThreshold,Integer>> keywordsInTopVariation;
	
	private Map<Integer,KeywordDataOverviewTO> keywordsData;

	public Integer getKeywordsNumber() {
		return keywordsNumber;
	}

	public void setKeywordsNumber(Integer keywordsNumber) {
		this.keywordsNumber = keywordsNumber;
	}

	public Integer getKeywordsUp() {
		return keywordsUp;
	}

	public void setKeywordsUp(Integer keywordsUp) {
		this.keywordsUp = keywordsUp;
	}

	public Integer getKeywordsDown() {
		return keywordsDown;
	}

	public void setKeywordsDown(Integer keywordsDown) {
		this.keywordsDown = keywordsDown;
	}

	public Map<TopPositionThreshold, Integer> getKeywordsInTop() {
		return keywordsInTop;
	}

	public void setKeywordsInTop(Map<TopPositionThreshold, Integer> keywordsInTop) {
		this.keywordsInTop = keywordsInTop;
	}

	public Map<HistoricalCheckThreshold, Map<TopPositionThreshold, Integer>> getKeywordsInTopVariation() {
		return keywordsInTopVariation;
	}

	public void setKeywordsInTopVariation(
			Map<HistoricalCheckThreshold, Map<TopPositionThreshold, Integer>> keywordsInTopVariation) {
		this.keywordsInTopVariation = keywordsInTopVariation;
	}

	public Map<Integer, KeywordDataOverviewTO> getKeywordsData() {
		return keywordsData;
	}

	public void setKeywordsData(Map<Integer, KeywordDataOverviewTO> keywordsData) {
		this.keywordsData = keywordsData;
	}

	public BigDecimal getDomainScore() {
		return domainScore;
	}

	public void setDomainScore(BigDecimal domainScore) {
		this.domainScore = domainScore;
	}
	
	

	
}
