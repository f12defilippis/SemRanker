package com.flol.semrankercommon.dto;

import java.math.BigDecimal;

import com.flol.semrankercommon.domain.Domain;

public class DomainCompetitorTO {

	public Domain domain;
	private Integer commonKeywords;
	private BigDecimal avgPosition;
	private BigDecimal score;
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
	
	
	
}
