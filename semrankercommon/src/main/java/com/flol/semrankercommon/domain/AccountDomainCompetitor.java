package com.flol.semrankercommon.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the account_domain_competitor database table.
 * 
 */
@Entity
@Table(name="account_domain_competitor")
@NamedQuery(name="AccountDomainCompetitor.findAll", query="SELECT a FROM AccountDomainCompetitor a")
public class AccountDomainCompetitor extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="account_domain_competitor_status")
	private Integer accountDomainCompetitorStatus;

	@Column(name="account_domain")
	private AccountDomain accountDomain;

	@Column(name="domain_competitor")
	private Domain domain;

	public AccountDomainCompetitor() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public AccountDomain getAccountDomain() {
		return accountDomain;
	}

	public void setAccountDomain(AccountDomain accountDomain) {
		this.accountDomain = accountDomain;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Integer getAccountDomainCompetitorStatus() {
		return accountDomainCompetitorStatus;
	}

	public void setAccountDomainCompetitorStatus(
			Integer accountDomainCompetitorStatus) {
		this.accountDomainCompetitorStatus = accountDomainCompetitorStatus;
	}


}