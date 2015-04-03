package com.flol.semrankerengine.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchResultItemsTO {

	private String keyword;
	private boolean error;
	
	
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
	
	
}
