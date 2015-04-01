package com.flol.semrankercommon.domain;

import java.io.Serializable;
import javax.persistence.*;


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
	private int id;

	private int account;

	@Column(name="account_domain_type")
	private int accountDomainType;

	private int domain;

	public AccountDomain() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccount() {
		return this.account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getAccountDomainType() {
		return this.accountDomainType;
	}

	public void setAccountDomainType(int accountDomainType) {
		this.accountDomainType = accountDomainType;
	}

	public int getDomain() {
		return this.domain;
	}

	public void setDomain(int domain) {
		this.domain = domain;
	}

}