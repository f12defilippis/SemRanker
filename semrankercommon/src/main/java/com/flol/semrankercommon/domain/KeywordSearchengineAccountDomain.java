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
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	public KeywordSearchengineAccountDomain(Integer pid) {
		id = pid;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		KeywordSearchengineAccountDomain other = (KeywordSearchengineAccountDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}