package com.flol.semrankerengine.dto;

import java.io.Serializable;

public class SearchResultItemTO implements Serializable{

	private static final long serialVersionUID = -3185893698211721271L;
	private String domain;
	private String url;
	private Integer position;
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	
}
