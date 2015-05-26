package com.flol.semsankerweb.reports.performancerankingdomain.form;

import javax.validation.constraints.Null;

public class PerformanceRankingDomainSearchForm {

	private Integer accountDomainId = 2;

	@Null
	private Integer searchengineId;
	@Null
	private Integer countryId;
	@Null
	private Integer domain;
	@Null
	private String dateFrom;
	@Null
	private String dateTo;
	

	public Integer getDomain() {
		return domain;
	}
	public void setDomain(Integer domain) {
		this.domain = domain;
	}
	public Integer getAccountDomainId() {
		return accountDomainId;
	}
	public void setAccountDomainId(Integer accountDomainId) {
		this.accountDomainId = accountDomainId;
	}
	public Integer getSearchengineId() {
		return searchengineId;
	}
	public void setSearchengineId(Integer searchengineId) {
		this.searchengineId = searchengineId;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	
	
	
}
