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

	public Searchengine(Integer pid) {
		id = pid;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Searchengine other = (Searchengine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}