package com.CSC450.ars.domain;

/**
 * This class will be used for displaying the results of the ARS calculations for
 * 		Pages, Keywords, and Ad Locations.
 * Once the calculation is made for one of the previously mentioned objects, put 
 * the results into one of these bad boys. Stick these in a list (List<StatDisplay>)
 * and pass them to the jsp.
 * @author natha
 *
 */
public class StatDisplay<T> {
	
	private int rank;
	private String title;
	private double focusRatio;
	private double activeRatio;
	private double timeSpent;
	private double dollarValue;
	private String adLocation;
	private T type;
	
	public StatDisplay() {}
	
	public StatDisplay(int rank, String title, double focusRatio, double activeRatio, double timeSpent, double dollarValue, String adLocation) {
		this.rank = rank;
		this.title = title;
		this.focusRatio = focusRatio;
		this.activeRatio = activeRatio;
		this.timeSpent = timeSpent;
		this.dollarValue = dollarValue;
		this.adLocation = adLocation;
	}
	public StatDisplay(int rank, String title, double focusRatio, double activeRatio, double timeSpent, double dollarValue) {
		this.rank = rank;
		this.title = title;
		this.focusRatio = focusRatio;
		this.activeRatio = activeRatio;
		this.timeSpent = timeSpent;
		this.dollarValue = dollarValue;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public double getTimeSpent() {
		return timeSpent;
	}
	public void setTimeSpent(double timeSpent) {
		this.timeSpent = timeSpent;
	}
	public double getDollarValue() {
		return dollarValue;
	}
	public void setDollarValue(double dollarValue) {
		this.dollarValue = dollarValue;
	}
	public String getAdLocation() {
		return adLocation;
	}
	public void setAdLocation(String adLocation) {
		this.adLocation = adLocation;
	}

	public T getType() {
		return type;
	}

	public void setType(T type) {
		this.type = type;
	}

}
