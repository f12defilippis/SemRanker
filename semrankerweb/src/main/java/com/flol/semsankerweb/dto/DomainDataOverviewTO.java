package com.flol.semsankerweb.dto;

import java.util.List;
import java.util.Map;

public class DomainDataOverviewTO {

	private Integer keywordsNumber;

	private Integer keywordsUp;
	
	private Integer keywordsDown;
	
	//TODO create object to evaluate the interval of the top position
	private Map<Integer,Integer> keywordsInTop;
	
	//TODO create object to evaluate periods to track
	private Map<Integer,Map<Integer,Integer>> keywordsInTopVariation;
	
	private List<KeywordDataOverviewTO> keywordsData;

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

	public Map<Integer, Integer> getKeywordsInTop() {
		return keywordsInTop;
	}

	public void setKeywordsInTop(Map<Integer, Integer> keywordsInTop) {
		this.keywordsInTop = keywordsInTop;
	}

	public Map<Integer, Map<Integer, Integer>> getKeywordsInTopVariation() {
		return keywordsInTopVariation;
	}

	public void setKeywordsInTopVariation(
			Map<Integer, Map<Integer, Integer>> keywordsInTopVariation) {
		this.keywordsInTopVariation = keywordsInTopVariation;
	}

	public List<KeywordDataOverviewTO> getKeywordsData() {
		return keywordsData;
	}

	public void setKeywordsData(List<KeywordDataOverviewTO> keywordsData) {
		this.keywordsData = keywordsData;
	}
	
	

	
}
