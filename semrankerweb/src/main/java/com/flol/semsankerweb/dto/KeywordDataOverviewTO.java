package com.flol.semsankerweb.dto;

import java.util.Map;

import com.flol.semrankercommon.domain.HistoricalCheckThreshold;
import com.flol.semrankercommon.domain.TopPositionThreshold;

public class KeywordDataOverviewTO {

	private String text;
	
	private Integer globalSearchesNumber;
	
	private Integer localSearchesNumber;
	
	private Integer position;
	
	private Integer positionVariation;
	
	private Integer bestPosition;
	
	private Map<HistoricalCheckThreshold,Integer> historicalPosition;
	
	private Map<TopPositionThreshold,Boolean> topPositionEnter;
	
	private Map<TopPositionThreshold,Boolean> topPositionExit;

	private Map<HistoricalCheckThreshold,Map<TopPositionThreshold,Boolean>> historicalTopPositionEnter;

	private Map<HistoricalCheckThreshold,Map<TopPositionThreshold,Boolean>> historicalTopPositionExit;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getGlobalSearchesNumber() {
		return globalSearchesNumber;
	}

	public void setGlobalSearchesNumber(Integer globalSearchesNumber) {
		this.globalSearchesNumber = globalSearchesNumber;
	}

	public Integer getLocalSearchesNumber() {
		return localSearchesNumber;
	}

	public void setLocalSearchesNumber(Integer localSearchesNumber) {
		this.localSearchesNumber = localSearchesNumber;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getPositionVariation() {
		return positionVariation;
	}

	public void setPositionVariation(Integer positionVariation) {
		this.positionVariation = positionVariation;
	}

	public Integer getBestPosition() {
		return bestPosition;
	}

	public void setBestPosition(Integer bestPosition) {
		this.bestPosition = bestPosition;
	}

	public Map<HistoricalCheckThreshold, Integer> getHistoricalPosition() {
		return historicalPosition;
	}

	public void setHistoricalPosition(
			Map<HistoricalCheckThreshold, Integer> historicalPosition) {
		this.historicalPosition = historicalPosition;
	}

	public Map<TopPositionThreshold, Boolean> getTopPositionEnter() {
		return topPositionEnter;
	}

	public void setTopPositionEnter(
			Map<TopPositionThreshold, Boolean> topPositionEnter) {
		this.topPositionEnter = topPositionEnter;
	}

	public Map<TopPositionThreshold, Boolean> getTopPositionExit() {
		return topPositionExit;
	}

	public void setTopPositionExit(
			Map<TopPositionThreshold, Boolean> topPositionExit) {
		this.topPositionExit = topPositionExit;
	}

	public Map<HistoricalCheckThreshold, Map<TopPositionThreshold, Boolean>> getHistoricalTopPositionEnter() {
		return historicalTopPositionEnter;
	}

	public void setHistoricalTopPositionEnter(
			Map<HistoricalCheckThreshold, Map<TopPositionThreshold, Boolean>> historicalTopPositionEnter) {
		this.historicalTopPositionEnter = historicalTopPositionEnter;
	}

	public Map<HistoricalCheckThreshold, Map<TopPositionThreshold, Boolean>> getHistoricalTopPositionExit() {
		return historicalTopPositionExit;
	}

	public void setHistoricalTopPositionExit(
			Map<HistoricalCheckThreshold, Map<TopPositionThreshold, Boolean>> historicalTopPositionExit) {
		this.historicalTopPositionExit = historicalTopPositionExit;
	}
	
	

}
