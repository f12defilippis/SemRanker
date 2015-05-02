package com.flol.semrankercommon.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the keyword_searchengine database table.
 * 
 */
@Entity
@Table(name="keyword_searchengine")
public class KeywordSearchengine extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "keyword", referencedColumnName = "id", nullable = false)
	private Keyword keyword;

	@ManyToOne
    @JoinColumn(name = "aggregated_searchengine", referencedColumnName = "id", nullable = true)
	private AggregatedSearchengine aggregatedSearchengine;
	
	public KeywordSearchengine() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public AggregatedSearchengine getAggregatedSearchengine() {
		return aggregatedSearchengine;
	}

	public void setAggregatedSearchengine(
			AggregatedSearchengine aggregatedSearchengine) {
		this.aggregatedSearchengine = aggregatedSearchengine;
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
		KeywordSearchengine other = (KeywordSearchengine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}









}