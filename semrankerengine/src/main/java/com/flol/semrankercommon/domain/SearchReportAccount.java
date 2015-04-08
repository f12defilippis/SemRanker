package com.flol.semrankercommon.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the search_report_account database table.
 * 
 */
@Entity
@Table(name="search_report_account")
public class SearchReportAccount extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateClosed;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFirstSeen;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastSeen;

	@ManyToOne
    @JoinColumn(name = "keyword_scan_summary", referencedColumnName = "id", nullable = false)
	private KeywordScanSummary keywordScanSummary;

	private int position;

	@ManyToOne
    @JoinColumn(name = "url", referencedColumnName = "id", nullable = false)
	private Url url;

	public SearchReportAccount() {
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

	public Url getUrl() {
		return url;
	}

	public void setUrl(Url url) {
		this.url = url;
	}

	public KeywordScanSummary getKeywordScanSummary() {
		return keywordScanSummary;
	}

	public void setKeywordScanSummary(KeywordScanSummary keywordScanSummary) {
		this.keywordScanSummary = keywordScanSummary;
	}

}