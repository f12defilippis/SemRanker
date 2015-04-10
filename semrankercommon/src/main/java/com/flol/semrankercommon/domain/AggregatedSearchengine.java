package com.flol.semrankercommon.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="aggregated_searchengine")
public class AggregatedSearchengine extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -8486083010677154137L;

	@Id
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "geographical_targeting", referencedColumnName = "id", nullable = true)
	private GeographicalTargeting geographicalTargeting;

	@ManyToOne
    @JoinColumn(name = "searchengine", referencedColumnName = "id", nullable = false)
	private Searchengine searchengine;

	@ManyToOne
    @JoinColumn(name = "searchengine_country", referencedColumnName = "id", nullable = false)
	private SearchengineCountry searchengineCountry;
	
	private Integer mobile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GeographicalTargeting getGeographicalTargeting() {
		return geographicalTargeting;
	}

	public void setGeographicalTargeting(GeographicalTargeting geographicalTargeting) {
		this.geographicalTargeting = geographicalTargeting;
	}

	public Searchengine getSearchengine() {
		return searchengine;
	}

	public void setSearchengine(Searchengine searchengine) {
		this.searchengine = searchengine;
	}

	public SearchengineCountry getSearchengineCountry() {
		return searchengineCountry;
	}

	public void setSearchengineCountry(SearchengineCountry searchengineCountry) {
		this.searchengineCountry = searchengineCountry;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
	
	
	
	
	
}
