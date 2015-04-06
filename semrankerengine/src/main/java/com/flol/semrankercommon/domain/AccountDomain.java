package com.flol.semrankercommon.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the account_domain database table.
 * 
 */
@Entity
@Table(name="account_domain")
@NamedQuery(name="AccountDomain.findAll", query="SELECT a FROM AccountDomain a")
public class AccountDomain extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "account", referencedColumnName = "id", nullable = false)
	private Account account;

	@Column(name="account_domain_type", nullable = true)
	private Integer accountDomainType;

	@ManyToOne
    @JoinColumn(name = "domain", referencedColumnName = "id", nullable = false)
	private Domain domain;

	public AccountDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getAccountDomainType() {
		return accountDomainType;
	}

	public void setAccountDomainType(Integer accountDomainType) {
		this.accountDomainType = accountDomainType;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}



}