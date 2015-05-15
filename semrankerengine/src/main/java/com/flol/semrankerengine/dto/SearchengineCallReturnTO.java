package com.flol.semrankerengine.dto;

import java.util.Map;

public class SearchengineCallReturnTO {

	private byte[] doc;
	private Map<String,String> cookies;
	public Map<String, String> getCookies() {
		return cookies;
	}
	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}
	public byte[] getDoc() {
		return doc;
	}
	public void setDoc(byte[] doc) {
		this.doc = doc;
	}
	
	
	
}
