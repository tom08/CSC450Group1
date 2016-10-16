package com.CSC450.ars.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ad_space")
public class AdSpace {

	@Id
	@GeneratedValue
	private long id;
	
	public AdSpace() {}
	
	public AdSpace(long id, long containingPage, long timeSpent, long focusRatio, long activeRatio) {
		setId(id);
		setContainingPage(containingPage);
		setFocusRatio(focusRatio);
		setActiveRatio(activeRatio);
		setTimeSpent(timeSpent);
	}
	
	private long containingPage;
	private long timeSpent;
	private long focusRatio;
	private long activeRatio;

    public AdSpace() {}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getContainingPage() {
		return containingPage;
	}
	public void setContainingPage(long containingPage) {
		this.containingPage = containingPage;
	}
	public long getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(long timeSpent) {
		this.timeSpent = timeSpent;
	}
	public long getFocusRatio() {
		return focusRatio;
	}
	public void setFocusRatio(long focusRatio) {
		this.focusRatio = focusRatio;
	}
	public long getActiveRatio() {
		return activeRatio;
	}
	public void setActiveRatio(long activeRatio) {
		this.activeRatio = activeRatio;
	}
}
