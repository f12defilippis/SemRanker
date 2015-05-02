package com.flol.semrankercommon.domain;

import java.io.Serializable;
import java.util.Date;

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

	@Temporal(TemporalType.TIMESTAMP)
	private Date datecreate;

	@ManyToOne
    @JoinColumn(name = "domain", referencedColumnName = "id", nullable = false)
	private Domain domain;

	private String url;

	public Url() {
	}



	public Date getDatecreate() {
		return this.datecreate;
	}

	public void setDatecreate(Date datecreate) {
		this.datecreate = datecreate;
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

}