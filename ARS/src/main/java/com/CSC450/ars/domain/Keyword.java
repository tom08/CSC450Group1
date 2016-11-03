package com.CSC450.ars.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

public class Keyword {
	
	private long id;
	private String keywordName;
	
	private long[] pageIds;
	
	private List<Page> pages;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKeywordName() {
		return keywordName;
	}
	public void setKeywordName(String name) {
		this.keywordName = name;
	}
	public long[] getPageIds() {
		return pageIds;
	}
	public void setPageIds(long[] pageIds) {
		this.pageIds = pageIds;
	}
	public List<Page> getPages() {
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	
}
