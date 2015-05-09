package com.flol.semrankerengine.dto;

public class PopulateKeywordsReadyBeanTO {

	private String keyword;
	private Integer avgMonthlySearches;
	private Double competition;
	private Double suggestedBid;
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getAvgMonthlySearches() {
		return avgMonthlySearches;
	}
	public void setAvgMonthlySearches(Integer avgMonthlySearches) {
		this.avgMonthlySearches = avgMonthlySearches;
	}
	public Double getCompetition() {
		return competition;
	}
	public void setCompetition(Double competition) {
		this.competition = competition;
	}
	public Double getSuggestedBid() {
		return suggestedBid;
	}
	public void setSuggestedBid(Double suggestedBid) {
		this.suggestedBid = suggestedBid;
	}
	
	
	
}
