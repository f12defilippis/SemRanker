package com.flol.semrankercommon.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country extends BaseDomain{

	private static final long serialVersionUID = 2925169084003768267L;

	@Id
	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
}
