package com.flol.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the account_domain_competitor database table.
 * 
 */
@Embeddable
public class AccountDomainCompetitorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int id;

	@Column(name="account_domain_competitor_status")
	private int accountDomainCompetitorStatus;

	public AccountDomainCompetitorPK() {
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountDomainCompetitorStatus() {
		return this.accountDomainCompetitorStatus;
	}
	public void setAccountDomainCompetitorStatus(int accountDomainCompetitorStatus) {
		this.accountDomainCompetitorStatus = accountDomainCompetitorStatus;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AccountDomainCompetitorPK)) {
			return false;
		}
		AccountDomainCompetitorPK castOther = (AccountDomainCompetitorPK)other;
		return 
			(this.id == castOther.id)
			&& (this.accountDomainCompetitorStatus == castOther.accountDomainCompetitorStatus);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id;
		hash = hash * prime + this.accountDomainCompetitorStatus;
		
		return hash;
	}
}