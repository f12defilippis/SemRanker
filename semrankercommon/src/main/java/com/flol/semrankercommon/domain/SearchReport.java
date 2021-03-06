package com.flol.semrankercommon.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the search_report database table.
 * 
 */
@Entity
@Table(name="search_report")
@NamedQuery(name="SearchReport.findAll", query="SELECT s FROM SearchReport s")
public class SearchReport extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_closed", nullable = true)
	private Date dateClosed;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_firstseen", nullable = true)
	private Date dateFirstSeen;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_lastseen", nullable = true)
	private Date dateLastSeen;

	@ManyToOne
    @JoinColumn(name = "keyword_searchengine", referencedColumnName = "id", nullable = false)
	private KeywordSearchengine keywordSearchengine;

	private int position;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create", nullable = true)
	private Date dateCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_update", nullable = true)
	private Date dateUpdate;

	@ManyToOne
    @JoinColumn(name = "url", referencedColumnName = "id", nullable = false)
	private Url url;
	
	@ManyToOne
    @JoinColumn(name = "keyword_scan_summary", referencedColumnName = "id", nullable = false)
	private KeywordScanSummary keywordScanSummary;


	public SearchReport() {
	}



	public Date getDateClosed() {
		return this.dateClosed;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public Date getDateFirstSeen() {
		return this.dateFirstSeen;
	}

	public void setDateFirstSeen(Date dateFirstSeen) {
		this.dateFirstSeen = dateFirstSeen;
	}

	public Date getDateLastSeen() {
		return this.dateLastSeen;
	}

	public void setDateLastSeen(Date dateLastSeen) {
		this.dateLastSeen = dateLastSeen;
	}



	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public KeywordSearchengine getKeywordSearchengine() {
		return keywordSearchengine;
	}



	public void setKeywordSearchengine(KeywordSearchengine keywordSearchengine) {
		this.keywordSearchengine = keywordSearchengine;
	}



	public Url getUrl() {
		return url;
	}



	public void setUrl(Url url) {
		this.url = url;
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



	public KeywordScanSummary getKeywordScanSummary() {
		return keywordScanSummary;
	}



	public void setKeywordScanSummary(KeywordScanSummary keywordScanSummary) {
		this.keywordScanSummary = keywordScanSummary;
	}

}