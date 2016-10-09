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
	private double totalSpent;
	private long pageVisitId;
	private String pageLocation;
	
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
	public double getTotalSpent() {
		return totalSpent;
	}
	public void setTotalSpent(double totalTime) {
		this.totalSpent = totalSpent;
	}
	public long getPageVisitId() {
		return pageVisitId;
	}
	public void setPageVisitId(long pageVisitId) {
		this.pageVisitId = pageVisitId;
	}
	public String getPageLocation() {
		return pageLocation;
	}
	public void setPageLocation(String pageLocation) {
		this.pageLocation = pageLocation;
	}
}
