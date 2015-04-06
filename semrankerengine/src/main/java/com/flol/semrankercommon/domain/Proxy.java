package com.flol.semrankercommon.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the proxy database table.
 * 
 */
@Entity
public class Proxy extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_lastfail")
	private Date dateLastfail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_lastsuccess")
	private Date dateLastsuccess;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_lastscan")
	private Date dateLastsscan;

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreate;

	private Integer errors;

	private String ip;

	private String port;

	private String type;

	private String username;

	private String password;

	public Proxy() {
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

	public Date getDateLastsscan() {
		return dateLastsscan;
	}

	public void setDateLastsscan(Date dateLastsscan) {
		this.dateLastsscan = dateLastsscan;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getErrors() {
		return errors;
	}



	public void setErrors(Integer errors) {
		this.errors = errors;
	}

}