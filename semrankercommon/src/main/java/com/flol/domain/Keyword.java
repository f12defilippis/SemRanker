package com.flol.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the keyword database table.
 * 
 */
@Entity
@NamedQuery(name="Keyword.findAll", query="SELECT k FROM Keyword k")
public class Keyword extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastscan;

	private String text;

	private int wordcount;

	public Keyword() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatecreate() {
		return this.datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
	}

	public Date getLastscan() {
		return this.lastscan;
	}

	public void setLastscan(Date lastscan) {
		this.lastscan = lastscan;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getWordcount() {
		return this.wordcount;
	}

	public void setWordcount(int wordcount) {
		this.wordcount = wordcount;
	}

}