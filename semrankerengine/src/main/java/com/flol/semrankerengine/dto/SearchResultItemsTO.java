package com.flol.semrankerengine.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchResultItemsTO implements Serializable{

	private static final long serialVersionUID = 6166255254888909328L;

	private String keyword;
	private boolean error;
	private boolean parseError;
	private boolean callError;
	
	private Date lastScan;
	
	private byte[] cachePage;	
	
	private List<SearchResultItemTO> items;
	
	
	public SearchResultItemsTO(){
		items = new ArrayList<SearchResultItemTO>();
	}

	public List<SearchResultItemTO> getItems() {
		return items;
	}

	public void setItems(List<SearchResultItemTO> items) {
		this.items = items;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public byte[] getCachePage() {
		return cachePage;
	}

	public void setCachePage(byte[] cachePage) {
		this.cachePage = cachePage;
	}

	public Date getLastScan() {
		return lastScan;
	}

	public void setLastScan(Date lastScan) {
		this.lastScan = lastScan;
	}

	public boolean isParseError() {
		return parseError;
	}

	public void setParseError(boolean parseError) {
		this.parseError = parseError;
	}

	public boolean isCallError() {
		return callError;
	}

	public void setCallError(boolean callError) {
		this.callError = callError;
	}
	
	
}
