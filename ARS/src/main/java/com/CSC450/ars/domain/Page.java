package com.CSC450.ars.domain;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

public class Page implements Serializable{

	private static final long serialVersionUID = 2012468640809192033L;
	
	private long id;
	private String url;

    @ManyToMany
    private List<Keyword> keywords;

	public Page(){}

	public Page(long pg_id, String pg_url){
        setId(pg_id);
        setUrl(pg_url);
    }
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
    /*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "page_keywords", joinColumns = @JoinColumn(name = "page_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "keyword_id", referencedColumnName = "id"))
    public List<Keyword> getKeywords() {
        return keywords;
    }
    */
	public List<Keyword> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}
	
	public void addKeyword(Keyword keyword){
	    if(this.keywords == null)
	        this.keywords = new ArrayList();
        this.keywords.add(keyword);
    }
}
