package com.flol.domain;

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
	private int id;

	private String name;

	public Searchengine() {
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

}