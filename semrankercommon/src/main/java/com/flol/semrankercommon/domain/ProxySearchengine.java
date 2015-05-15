package com.flol.semrankercommon.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="proxy_searchengine")
public class ProxySearchengine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "searchengine", referencedColumnName = "id", nullable = false)
	private Searchengine searchengine;

	@ManyToOne
    @JoinColumn(name = "proxy", referencedColumnName = "id", nullable = false)
	private Proxy proxy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_lastfail")
	private Date dateLastfail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_lastsuccess")
	private Date dateLastsuccess;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_lastscan")
	private Date dateLastscan;
	
	@Column(name="proxy_usage")
	private Integer usage;	

	@Column(name="num_success")
	private Integer numSuccess;	

	@Column(name="num_fails")
	private Integer numFails;	

	@Column(name="streak")
	private Integer streak;	
	
//	@Lob
//	@Column(name = "serializedCookies")
////	@Basic(fetch=FetchType.LAZY)
//	private Object serializedCookies;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Searchengine getSearchengine() {
		return searchengine;
	}

	public void setSearchengine(Searchengine searchengine) {
		this.searchengine = searchengine;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	public Date getDateLastfail() {
		return dateLastfail;
	}

	public void setDateLastfail(Date dateLastfail) {
		this.dateLastfail = dateLastfail;
	}

	public Date getDateLastsuccess() {
		return dateLastsuccess;
	}

	public void setDateLastsuccess(Date dateLastsuccess) {
		this.dateLastsuccess = dateLastsuccess;
	}



	public Integer getUsage() {
		return usage;
	}

	public void setUsage(Integer usage) {
		this.usage = usage;
	}

	public Date getDateLastscan() {
		return dateLastscan;
	}

	public void setDateLastscan(Date dateLastscan) {
		this.dateLastscan = dateLastscan;
	}

	public Integer getNumSuccess() {
		return numSuccess;
	}

	public void setNumSuccess(Integer numSuccess) {
		this.numSuccess = numSuccess;
	}

	public Integer getNumFails() {
		return numFails;
	}

	public void setNumFails(Integer numFails) {
		this.numFails = numFails;
	}

	public Integer getStreak() {
		return streak;
	}

	public void setStreak(Integer streak) {
		this.streak = streak;
	}

//	public Object getSerializedCookies() {
//		return serializedCookies;
//	}
//
//	public void setSerializedCookies(Object serializedCookies) {
//		this.serializedCookies = serializedCookies;
//	}


	
	
}
