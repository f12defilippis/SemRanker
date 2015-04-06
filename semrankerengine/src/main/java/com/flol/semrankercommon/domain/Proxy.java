package com.flol.semrankercommon.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	private Integer usage;

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

	public Integer getUsage() {
		return usage;
	}



	public void setUsage(Integer usage) {
		this.usage = usage;
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
		Proxy other = (Proxy) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}