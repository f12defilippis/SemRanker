package com.flol.semrankercommon.domain;

import java.math.BigDecimal;
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

@Entity
@Table(name="account_domain_history_data")
public class AccountDomainHistoryData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "account_domain", referencedColumnName = "id", nullable = false)
	private AccountDomain accountDomain;

	private Date date;
	
	@Column(name="average_position")
	private BigDecimal averagePosition;

	private BigDecimal score;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create", nullable = true)
	private Date dateCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_update", nullable = true)
	private Date dateUpdate;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getAveragePosition() {
		return averagePosition;
	}

	public void setAveragePosition(BigDecimal averagePosition) {
		this.averagePosition = averagePosition;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}	
	
}
