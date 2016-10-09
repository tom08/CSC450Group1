package com.CSC450.ars.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keyword")
public class Keyword {
	
	@Id
	@GeneratedValue
	private long id;
	private String keywordName;
	
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
	
}
