package com.flol.semrankercommon.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the account_domain_competitor database table.
 * 
 */
@Entity
@Table(name="account_domain_competitor")
@NamedQuery(name="AccountDomainCompetitor.findAll", query="SELECT a FROM AccountDomainCompetitor a")
public class AccountDomainCompetitor extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccountDomainCompetitorPK id;

	@Column(name="account_domain")
	private int accountDomain;

	@Column(name="domain_competitor")
	private int domainCompetitor;

	public AccountDomainCompetitor() {
	}

	public AccountDomainCompetitorPK getId() {
		return this.id;
	}

	public void setId(AccountDomainCompetitorPK id) {
		this.id = id;
	}

	public int getAccountDomain() {
		return this.accountDomain;
	}

	public void setAccountDomain(int accountDomain) {
		this.accountDomain = accountDomain;
	}

	public int getDomainCompetitor() {
		return this.domainCompetitor;
	}

	public void setDomainCompetitor(int domainCompetitor) {
		this.domainCompetitor = domainCompetitor;
	}

}