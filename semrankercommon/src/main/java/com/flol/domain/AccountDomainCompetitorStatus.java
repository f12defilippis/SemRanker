package com.flol.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the account_domain_competitor_status database table.
 * 
 */
@Entity
@Table(name="account_domain_competitor_status")
@NamedQuery(name="AccountDomainCompetitorStatus.findAll", query="SELECT a FROM AccountDomainCompetitorStatus a")
public class AccountDomainCompetitorStatus extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	public AccountDomainCompetitorStatus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}