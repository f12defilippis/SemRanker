package com.flol.semrankercommon.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the keyword_position_visit database table.
 * 
 */
@Entity
@Table(name="keyword_position_visit")
@NamedQuery(name="KeywordPositionVisit.findAll", query="SELECT k FROM KeywordPositionVisit k")
public class KeywordPositionVisit extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="visit_factor")
	private BigDecimal visitFactor;

	public KeywordPositionVisit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getVisitFactor() {
		return visitFactor;
	}

	public void setVisitFactor(BigDecimal visitFactor) {
		this.visitFactor = visitFactor;
	}
}