package com.flol.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the proxy database table.
 * 
 */
@Entity
@NamedQuery(name="Proxy.findAll", query="SELECT p FROM Proxy p")
public class Proxy extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_lastfail")
	private Date dateLastfail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_lastsuccess")
	private Date dateLastsuccess;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreate;

	private int errors;

	private String ip;

	private String port;

	private String type;

	public Proxy() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateLastfail() {
		return this.dateLastfail;
	}

	public void setDateLastfail(Date dateLastfail) {
		this.dateLastfail = dateLastfail;
	}

	public Date getDateLastsuccess() {
		return this.dateLastsuccess;
	}

	public void setDateLastsuccess(Date dateLastsuccess) {
		this.dateLastsuccess = dateLastsuccess;
	}

	public Date getDatecreate() {
		return this.datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}

	public int getErrors() {
		return this.errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}