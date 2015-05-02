package com.flol.semrankercommon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="keywords_web_request")
public class KeywordsWebRequest extends BaseDomain{

	private static final long serialVersionUID = 6047566588366406109L;

	@Id
	private Integer id;

	@Column(name="num_keywords")
	private Integer numKeywords;

	@Column(name="keywords_not_stored")
	private Integer keywordsNotStored;

	private Integer searchengine;

	@Column(name="start_date")
	private Date startDate;

	@Column(name="end_date")
	private Date endDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumKeywords() {
		return numKeywords;
	}

	public void setNumKeywords(Integer numKeywords) {
		this.numKeywords = numKeywords;
	}

	public Integer getSearchengine() {
		return searchengine;
	}

	public void setSearchengine(Integer searchengine) {
		this.searchengine = searchengine;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getKeywordsNotStored() {
		return keywordsNotStored;
	}

	public void setKeywordsNotStored(Integer keywordsNotStored) {
		this.keywordsNotStored = keywordsNotStored;
	}



}
