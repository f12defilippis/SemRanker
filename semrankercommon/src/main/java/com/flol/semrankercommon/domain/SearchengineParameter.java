package com.flol.semrankercommon.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the searchengine_parameter database table.
 * 
 */
@Entity
@Table(name="searchengine_parameter")
@NamedQuery(name="SearchengineParameter.findAll", query="SELECT s FROM SearchengineParameter s")
public class SearchengineParameter extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String value;

	public SearchengineParameter() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}