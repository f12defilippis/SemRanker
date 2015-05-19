package com.flol.semrankercommon.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	public static final Integer AccountDomainCompetitorStatus_FOUND = 1;
	public static final Integer AccountDomainCompetitorStatus_SELECTED = 2;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name="account_domain_competitor_status")
	private Integer accountDomainCompetitorStatus;

	@ManyToOne
    @JoinColumn(name = "account_domain", referencedColumnName = "id", nullable = false)
	private AccountDomain accountDomain;

	@ManyToOne
    @JoinColumn(name = "domain_competitor", referencedColumnName = "id", nullable = false)
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