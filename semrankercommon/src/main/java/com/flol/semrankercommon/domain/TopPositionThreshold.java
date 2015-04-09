package com.flol.semrankercommon.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="top_position_threshold")
public class TopPositionThreshold extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1157166736441540864L;

	@Id
	private Integer id;
	
	private String description;
	
	private Integer lowThreshold;

	private Integer highThreshold;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLowThreshold() {
		return lowThreshold;
	}

	public void setLowThreshold(Integer lowThreshold) {
		this.lowThreshold = lowThreshold;
	}

	public Integer getHighThreshold() {
		return highThreshold;
	}

	public void setHighThreshold(Integer highThreshold) {
		this.highThreshold = highThreshold;
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
		TopPositionThreshold other = (TopPositionThreshold) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
