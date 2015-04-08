package com.flol.semsankerweb.dto;

import java.util.Map;

public class KeywordDataOverviewTO {

	private String text;
	
	private Integer globalSearchesNumber;
	
	private Integer localSearchesNumber;
	
	private Integer position;
	
	private Integer positionVariation;
	
	//TODO create object to evaluate periods to track
	// replace Integer with object created
	private Map<Integer,Integer> historicalPosition;
	
	//TODO create object to evaluate the interval of the top position
	// replace Integer with object created
	private Map<Integer,Boolean> topPositionEnter;
	
	//TODO create object to evaluate the interval of the top position
	// replace Integer with object created
	private Map<Integer,Boolean> topPositionExit;

	// replace Integer with object created
	private Map<Integer,Map<Integer,Boolean>> historicalTopPositionEnter;

	// replace Integer with object created
	private Map<Integer,Map<Integer,Boolean>> historicalTopPositionExit;

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

	public Map<Integer, Integer> getHistoricalPosition() {
		return historicalPosition;
	}

	public void setHistoricalPosition(Map<Integer, Integer> historicalPosition) {
		this.historicalPosition = historicalPosition;
	}

	public Map<Integer, Boolean> getTopPositionEnter() {
		return topPositionEnter;
	}

	public void setTopPositionEnter(Map<Integer, Boolean> topPositionEnter) {
		this.topPositionEnter = topPositionEnter;
	}

	public Map<Integer, Boolean> getTopPositionExit() {
		return topPositionExit;
	}

	public void setTopPositionExit(Map<Integer, Boolean> topPositionExit) {
		this.topPositionExit = topPositionExit;
	}

	public Map<Integer, Map<Integer, Boolean>> getHistoricalTopPositionEnter() {
		return historicalTopPositionEnter;
	}

	public void setHistoricalTopPositionEnter(
			Map<Integer, Map<Integer, Boolean>> historicalTopPositionEnter) {
		this.historicalTopPositionEnter = historicalTopPositionEnter;
	}

	public Map<Integer, Map<Integer, Boolean>> getHistoricalTopPositionExit() {
		return historicalTopPositionExit;
	}

	public void setHistoricalTopPositionExit(
			Map<Integer, Map<Integer, Boolean>> historicalTopPositionExit) {
		this.historicalTopPositionExit = historicalTopPositionExit;
	}
	
	

}
