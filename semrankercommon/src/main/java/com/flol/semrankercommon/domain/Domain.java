package com.flol.semrankercommon.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the domain database table.
 * 
 */
@Entity
@NamedQuery(name="Domain.findAll", query="SELECT d FROM Domain d")
public class Domain extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create", nullable = true)
	private Date dateCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_update", nullable = true)
	private Date dateUpdate;

	public Domain() {
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


	public Date getDateCreate() {
		return dateCreate;
	}


	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}


	public Date getDateUpdate() {
		return dateUpdate;
	}


	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

}