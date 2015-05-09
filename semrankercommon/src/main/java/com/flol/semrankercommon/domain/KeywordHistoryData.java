package com.flol.semrankercommon.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the keyword_history_data database table.
 * 
 */
@Entity
@Table(name="keyword_history_data")
@NamedQuery(name="KeywordHistoryData.findAll", query="SELECT k FROM KeywordHistoryData k")
public class KeywordHistoryData extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer avgmonthlysearches;

	private Double competition;

	private Integer keyword;

	private Integer period;

	@Temporal(TemporalType.TIMESTAMP)
	private Date scandate;

	@Column(name="searchengine_country")
	private int searchengineCountry;

	private Double suggestedbid;

	public KeywordHistoryData() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAvgmonthlysearches() {
		return avgmonthlysearches;
	}

	public void setAvgmonthlysearches(Integer avgmonthlysearches) {
		this.avgmonthlysearches = avgmonthlysearches;
	}

	public Double getCompetition() {
		return competition;
	}

	public void setCompetition(Double competition) {
		this.competition = competition;
	}

	public Integer getKeyword() {
		return keyword;
	}

	public void setKeyword(Integer keyword) {
		this.keyword = keyword;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Date getScandate() {
		return scandate;
	}

	public void setScandate(Date scandate) {
		this.scandate = scandate;
	}

	public int getSearchengineCountry() {
		return searchengineCountry;
	}

	public void setSearchengineCountry(int searchengineCountry) {
		this.searchengineCountry = searchengineCountry;
	}

	public Double getSuggestedbid() {
		return suggestedbid;
	}

	public void setSuggestedbid(Double suggestedbid) {
		this.suggestedbid = suggestedbid;
	}


}