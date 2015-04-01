package com.flol.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the period database table.
 * 
 */
@Entity
@NamedQuery(name="Period.findAll", query="SELECT p FROM Period p")
public class Period extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int month;

	private int year;

	public Period() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}