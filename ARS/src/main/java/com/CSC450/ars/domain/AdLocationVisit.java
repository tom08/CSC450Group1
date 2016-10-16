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
	private long pageId;
	private String pageLocation;

	public AdLocationVisit() {}

	public AdLocationVisit(
            long adId,
            double adFocus,
            double adActive,
            double adSpent,
            long adPageId,
            String loc){
        setId(adId);
        setFocusRatio(adFocus);
        setActiveRatio(adActive);
        setTotalSpent(adSpent);
        setPageId(adPageId);
        setPageLocation(loc);
    }
	
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
	public long getPageId() {
		return pageId;
	}
	public void setPageId(long pageId) {
		this.pageId = pageId;
	}
	public String getPageLocation() {
		return pageLocation;
	}
	public void setPageLocation(String pageLocation) {
		this.pageLocation = pageLocation;
	}
}
