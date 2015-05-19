package com.flol.semrankercommon.dto;

import java.math.BigDecimal;

import com.flol.semrankercommon.domain.Country;
import com.flol.semrankercommon.domain.Domain;
import com.flol.semrankercommon.domain.Searchengine;

public class DomainCompetitorTO {

	public Domain domain;
	private Integer commonKeywords;
	private BigDecimal avgPosition;
	private BigDecimal score;
	
	private Searchengine searchengine;
	private Country country;
	
	public Domain getDomain() {
		return domain;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	public Integer getCommonKeywords() {
		return commonKeywords;
	}
	public void setCommonKeywords(Integer commonKeywords) {
		this.commonKeywords = commonKeywords;
	}
	public BigDecimal getAvgPosition() {
		return avgPosition;
	}
	public void setAvgPosition(BigDecimal avgPosition) {
		this.avgPosition = avgPosition;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public Searchengine getSearchengine() {
		return searchengine;
	}
	public void setSearchengine(Searchengine searchengine) {
		this.searchengine = searchengine;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
}
