package com.flol.semrankercommon.domain;

import java.io.Serializable;
import javax.persistence.*;


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
	private double visitFactor;

	public KeywordPositionVisit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getVisitFactor() {
		return this.visitFactor;
	}

	public void setVisitFactor(double visitFactor) {
		this.visitFactor = visitFactor;
	}

}