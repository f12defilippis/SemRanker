package com.flol.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the search_report_account database table.
 * 
 */
@Entity
@Table(name="search_report_account")
@NamedQuery(name="SearchReportAccount.findAll", query="SELECT s FROM SearchReportAccount s")
public class SearchReportAccount extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateClosed;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFirstSeen;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastSeen;

	@Column(name="keyword_searchengine_account_domain")
	private int keywordSearchengineAccountDomain;

	private int position;

	private int url;

	public SearchReportAccount() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getKeywordSearchengineAccountDomain() {
		return this.keywordSearchengineAccountDomain;
	}

	public void setKeywordSearchengineAccountDomain(int keywordSearchengineAccountDomain) {
		this.keywordSearchengineAccountDomain = keywordSearchengineAccountDomain;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getUrl() {
		return this.url;
	}

	public void setUrl(int url) {
		this.url = url;
	}

}