package com.CSC450.ars.domain;

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

@Entity
@Table(name = "page")
public class Page implements Serializable{

	private static final long serialVersionUID = 2012468640809192033L;
	
	@Id
	@GeneratedValue
	private long id;
	private String url;
	
	@ManyToMany
	@JoinTable(name="keyword_page",
				joinColumns = @JoinColumn(name="page_id", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name="keyword_id", referencedColumnName = "id"))
	private List<Keyword> keywords;
	
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
	public List<Keyword> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}
	
	
}
