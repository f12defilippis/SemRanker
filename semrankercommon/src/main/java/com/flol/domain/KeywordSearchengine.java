package com.flol.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the keyword_searchengine database table.
 * 
 */
@Entity
@Table(name="keyword_searchengine")
@NamedQuery(name="KeywordSearchengine.findAll", query="SELECT k FROM KeywordSearchengine k")
public class KeywordSearchengine extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="geographical_targeting")
	private int geographicalTargeting;

	private int keyword;

	private int searchengine;

	@Column(name="searchengine_country")
	private int searchengineCountry;

	public KeywordSearchengine() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGeographicalTargeting() {
		return this.geographicalTargeting;
	}

	public void setGeographicalTargeting(int geographicalTargeting) {
		this.geographicalTargeting = geographicalTargeting;
	}

	public int getKeyword() {
		return this.keyword;
	}

	public void setKeyword(int keyword) {
		this.keyword = keyword;
	}

	public int getSearchengine() {
		return this.searchengine;
	}

	public void setSearchengine(int searchengine) {
		this.searchengine = searchengine;
	}

	public int getSearchengineCountry() {
		return this.searchengineCountry;
	}

	public void setSearchengineCountry(int searchengineCountry) {
		this.searchengineCountry = searchengineCountry;
	}

}