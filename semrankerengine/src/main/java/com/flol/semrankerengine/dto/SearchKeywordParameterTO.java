package com.flol.semrankerengine.dto;

import com.flol.semrankercommon.domain.Searchengine;

public class SearchKeywordParameterTO {

	private Searchengine searchEngine;
	
	private String keyword;
	private String proxyHost;
	private String proxyPort;
	private String proxyUser;
	private String proxyPassword;
	
	private String userAgent;
	private String tld;
	private String numResultToSearch;
	private String uule;
	
	private Integer mobile;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getProxyHost() {
		return proxyHost;
	}
	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}
	public String getProxyPort() {
		return proxyPort;
	}
	public void setProxyPort(String proxyPort) {
		this.proxyPort = proxyPort;
	}
	public String getTld() {
		return tld;
	}
	public void setTld(String tld) {
		this.tld = tld;
	}
	public String getNumResultToSearch() {
		return numResultToSearch;
	}
	public void setNumResultToSearch(String numResultToSearch) {
		this.numResultToSearch = numResultToSearch;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getProxyUser() {
		return proxyUser;
	}
	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
	}
	public String getProxyPassword() {
		return proxyPassword;
	}
	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}
	public String getUule() {
		return uule;
	}
	public void setUule(String uule) {
		this.uule = uule;
	}
	public Integer getMobile() {
		return mobile;
	}
	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
	public Searchengine getSearchEngine() {
		return searchEngine;
	}
	public void setSearchEngine(Searchengine searchEngine) {
		this.searchEngine = searchEngine;
	}

	
	
	
}
