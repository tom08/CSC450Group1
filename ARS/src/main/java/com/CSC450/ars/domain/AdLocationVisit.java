package com.CSC450.ars.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

public class AdLocationVisit implements Serializable{

	private static final long serialVersionUID = -6748657696577761491L;

	private long id;
	private double focusRatio;
	private double activeRatio;
	private double totalSpent;
	private long pageId;
	private String pageLocation;
	private Timestamp createdAt;

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
	public void setTotalSpent(double totalSpent) {
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
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public double RatioFormula(double activeRatioWeight, double focusRatioWeight)
	{
		return activeRatio * activeRatioWeight + focusRatio * focusRatioWeight;
	}
}
