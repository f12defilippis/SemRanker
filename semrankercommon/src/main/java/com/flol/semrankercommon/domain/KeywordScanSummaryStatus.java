package com.flol.semrankercommon.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="keyword_scan_summary_status")
public class KeywordScanSummaryStatus extends BaseDomain implements Serializable{

	private static final long serialVersionUID = 8230720298397371350L;
	
	public static final Integer RUNNING = 0;
	public static final Integer COMPLETED = 1;
	public static final Integer PROXY_FAILED = 2;
	public static final Integer STORE_FAILED = 3;
	public static final Integer RETRIEVED_FROM_DATA = 4;
	public static final Integer PARSE_FAILED = 5;

	public KeywordScanSummaryStatus(){}

	public KeywordScanSummaryStatus(Integer pid){
		id = pid;
	}

	@Id
	private Integer id;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		KeywordScanSummaryStatus other = (KeywordScanSummaryStatus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
