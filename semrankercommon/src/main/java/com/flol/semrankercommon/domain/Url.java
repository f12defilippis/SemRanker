package com.flol.semrankercommon.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the url database table.
 * 
 */
@Entity
public class Url extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "domain", referencedColumnName = "id", nullable = false)
	private Domain domain;

	
	
	private String url;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create", nullable = true)
	private Date dateCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_update", nullable = true)
	private Date dateUpdate;

	public Url() {
	}



	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Domain getDomain() {
		return domain;
	}



	public void setDomain(Domain domain) {
		this.domain = domain;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Url other = (Url) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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