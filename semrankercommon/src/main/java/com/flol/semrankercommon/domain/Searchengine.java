package com.flol.semrankercommon.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the searchengine database table.
 * 
 */
@Entity
@NamedQuery(name="Searchengine.findAll", query="SELECT s FROM Searchengine s")
public class Searchengine extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	private String request;

	@Column(name="cache_page")
	private boolean cachePage;

	@Column(name="cache_snippet")
	private boolean cacheSnippet;
	
	@Column(name="max_results_per_page")
	private Integer maxResultsPerPage;

	public Searchengine() {
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getRequest() {
		return request;
	}



	public void setRequest(String request) {
		this.request = request;
	}



	public boolean isCachePage() {
		return cachePage;
	}



	public void setCachePage(boolean cachePage) {
		this.cachePage = cachePage;
	}



	public boolean isCacheSnippet() {
		return cacheSnippet;
	}



	public void setCacheSnippet(boolean cacheSnippet) {
		this.cacheSnippet = cacheSnippet;
	}



	public Integer getMaxResultsPerPage() {
		return maxResultsPerPage;
	}



	public void setMaxResultsPerPage(Integer maxResultsPerPage) {
		this.maxResultsPerPage = maxResultsPerPage;
	}

}