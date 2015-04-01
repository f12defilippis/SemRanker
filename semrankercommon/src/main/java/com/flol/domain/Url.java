package com.flol.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the url database table.
 * 
 */
@Entity
@NamedQuery(name="Url.findAll", query="SELECT u FROM Url u")
public class Url extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreate;

	private int domain;

	private String url;

	public Url() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatecreate() {
		return this.datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}

	public int getDomain() {
		return this.domain;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}