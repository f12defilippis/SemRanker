package com.flol.semrankercommon.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the keyword_searchengine_account_domain database table.
 * 
 */
@Entity
@Table(name="keyword_searchengine_account_domain")
public class KeywordSearchengineAccountDomain extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "account_domain", referencedColumnName = "id", nullable = false)
	private AccountDomain accountDomain;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_closed")
	private Date dateClosed;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	private Date dateCreated;

	@ManyToOne
    @JoinColumn(name = "keyword_searchengine", referencedColumnName = "id", nullable = false)
	private KeywordSearchengine keywordSearchengine;

	public KeywordSearchengineAccountDomain() {
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

	public AccountDomain getAccountDomain() {
		return accountDomain;
	}

	public void setAccountDomain(AccountDomain accountDomain) {
		this.accountDomain = accountDomain;
	}

	public KeywordSearchengine getKeywordSearchengine() {
		return keywordSearchengine;
	}

	public void setKeywordSearchengine(KeywordSearchengine keywordSearchengine) {
		this.keywordSearchengine = keywordSearchengine;
	}

}