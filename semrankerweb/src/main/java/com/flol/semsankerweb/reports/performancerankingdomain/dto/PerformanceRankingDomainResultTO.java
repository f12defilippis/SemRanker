package com.flol.semsankerweb.reports.performancerankingdomain.dto;

import java.util.Date;

public class PerformanceRankingDomainResultTO {

	private String engine;
	private String country;
	private String domain;
	private Date date;
	private Double position;
	private Double score;
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getPosition() {
		return position;
	}
	public void setPosition(Double position) {
		this.position = position;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	
	
	
}
