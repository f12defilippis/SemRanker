package com.flol.semrankercommon.domain;

import java.io.Serializable;
import javax.persistence.*;


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
	private int id;

	private String name;

	private String tld;

	public SearchengineCountry() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

}