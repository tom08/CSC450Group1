package com.CSC450.ars.validation;

public class ARSValidator {
	
	private String errorMessage;
	public boolean validateSettings(String activeWeight, String focusWeight, String min, String max) {
		isDouble(min);
		isDouble(max);
		isPositive(activeWeight);
		isPositive(focusWeight);
		isPositive(min);
		isPositive(max);
		ratioTest(activeWeight, focusWeight);
		minLessThanMax(min, max);
		isEmptyOrNull(activeWeight);
		isEmptyOrNull(focusWeight);
		isEmptyOrNull(min);
		isEmptyOrNull(max);
		return errorMessage == null;
	}
	
	private void ratioTest(String ratio1, String ratio2) {
		Double verifiedRatio1 = isDouble(ratio1);
		Double verifiedRatio2 = isDouble(ratio2);
		if(verifiedRatio1 != null && verifiedRatio2 != null) {
			Double result = verifiedRatio1 + verifiedRatio2;
			if(result != 1.0) {
				setErrorMessage("The sum of the active and focus ratio weights must equal one. Your result: " + result);
			}
		}
	}
	
	private void minLessThanMax(String min, String max) {
		Double verifiedMin = isDouble(min);
		Double verifiedMax = isDouble(max);
		if(verifiedMin != null && verifiedMax != null) {
			if(verifiedMin >= verifiedMax) {
				setErrorMessage("The Min value must be less than the Max value. Your result: " + min + " < " + max);
			}
			
		}
	}
	
	private void isEmptyOrNull(String value) {
		if(value != null && value.replaceAll(" ", "").length() != 0) {
			return;
		}
		else {
			setErrorMessage("No value can be empty.");
		}
	}
	
	private void isPositive(String value) {
		Double verifiedValue = isDouble(value);
		if(verifiedValue != null) {
			if(verifiedValue < 0) {
				setErrorMessage("Can not be negative: " + value);
			}
		}
	}
	
	private Double isDouble(String value) {
		try {
			return Double.parseDouble(value);
		}
		catch(NumberFormatException e) {
			setErrorMessage(value + " is not a number.");
			return null;
		}
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}