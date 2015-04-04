package com.flol.semrankercommon.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the keyword_searchengine_account_domain database table.
 * 
 */
@Entity
@Table(name="keyword_searchengine_account_domain")
@NamedQuery(name="KeywordSearchengineAccountDomain.findAll", query="SELECT k FROM KeywordSearchengineAccountDomain k")
public class KeywordSearchengineAccountDomain extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="account_domain")
	private int accountDomain;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_closed")
	private Date dateClosed;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	private Date dateCreated;

	@Column(name="keyword_searchengine")
	private int keywordSearchengine;

	public KeywordSearchengineAccountDomain() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountDomain() {
		return this.accountDomain;
	}

	public void setAccountDomain(int accountDomain) {
		this.accountDomain = accountDomain;
	}

	public Date getDateClosed() {
		return this.dateClosed;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getKeywordSearchengine() {
		return this.keywordSearchengine;
	}

	public void setKeywordSearchengine(int keywordSearchengine) {
		this.keywordSearchengine = keywordSearchengine;
	}

}