package com.flol.semrankercommon.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the searchengine_country database table.
 * 
 */
@Entity
@Table(name="searchengine_country")
@NamedQuery(name="SearchengineCountry.findAll", query="SELECT s FROM SearchengineCountry s")
public class SearchengineCountry extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	private String tld;
	
	@ManyToOne
    @JoinColumn(name = "searchengine", referencedColumnName = "id", nullable = false)
	private Searchengine searchengine;

	public SearchengineCountry() {
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTld() {
		return this.tld;
	}

	public void setTld(String tld) {
		this.tld = tld;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Searchengine getSearchengine() {
		return searchengine;
	}



	public void setSearchengine(Searchengine searchengine) {
		this.searchengine = searchengine;
	}

}