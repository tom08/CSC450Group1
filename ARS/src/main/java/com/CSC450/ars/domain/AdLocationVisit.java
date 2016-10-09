package com.CSC450.ars.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ad_location_visit")
public class AdLocationVisit {
	
	@Id
	@GeneratedValue	
	private long id;
	private double focusRatio;
	private double activeRatio;
	private long totalTime;
	private long pageVisitId;
	private String locationOnPage;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getFocusRatio() {
		return focusRatio;
	}
	public void setFocusRatio(double focusRatio) {
		this.focusRatio = focusRatio;
	}
	public double getActiveRatio() {
		return activeRatio;
	}
	public void setActiveRatio(double activeRatio) {
		this.activeRatio = activeRatio;
	}
	public long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}
	public long getPageVisitId() {
		return pageVisitId;
	}
	public void setPageVisitId(long pageVisitId) {
		this.pageVisitId = pageVisitId;
	}
	public String getLocationOnPage() {
		return locationOnPage;
	}
	public void setLocationOnPage(String locationOnPage) {
		this.locationOnPage = locationOnPage;
	}
}
