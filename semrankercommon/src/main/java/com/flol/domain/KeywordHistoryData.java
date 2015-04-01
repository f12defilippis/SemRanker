package com.flol.domain;

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
	private int id;

	private int avgmonthlysearches;

	private double competition;

	private int keyword;

	private int period;

	@Temporal(TemporalType.TIMESTAMP)
	private Date scandate;

	@Column(name="searchengine_country")
	private int searchengineCountry;

	private double suggestedbid;

	public KeywordHistoryData() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvgmonthlysearches() {
		return this.avgmonthlysearches;
	}

	public void setAvgmonthlysearches(int avgmonthlysearches) {
		this.avgmonthlysearches = avgmonthlysearches;
	}

	public double getCompetition() {
		return this.competition;
	}

	public void setCompetition(double competition) {
		this.competition = competition;
	}

	public int getKeyword() {
		return this.keyword;
	}

	public void setKeyword(int keyword) {
		this.keyword = keyword;
	}

	public int getPeriod() {
		return this.period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Date getScandate() {
		return this.scandate;
	}

	public void setScandate(Date scandate) {
		this.scandate = scandate;
	}

	public int getSearchengineCountry() {
		return this.searchengineCountry;
	}

	public void setSearchengineCountry(int searchengineCountry) {
		this.searchengineCountry = searchengineCountry;
	}

	public double getSuggestedbid() {
		return this.suggestedbid;
	}

	public void setSuggestedbid(double suggestedbid) {
		this.suggestedbid = suggestedbid;
	}

}