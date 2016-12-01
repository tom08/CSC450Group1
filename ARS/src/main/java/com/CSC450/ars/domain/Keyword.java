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
	private double value;
	private double dollarValue;

	public Keyword() {}

	public Keyword(long k_id, String name){
        setId(k_id);
        setKeywordName(name);
    }

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
	public List<Page> getPages() {
		return pages;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getValue() {
		return value;
	}

	public double getDollarValue() {
		return dollarValue;
	}

	public void setDollarValue(double dollarValue) {
		this.dollarValue = dollarValue;
	}

}
