package com.CSC450.ars.validation;

import java.math.BigDecimal;

public class ARSValidator {
	
	private String errorMessage;
	public boolean validateSettings(String activeWeight, String focusWeight, String min, String max) {
		isDouble(min);
		isDouble(max);
		isGreaterEqualTo("Active Ratio Weight", activeWeight, "", "0.0");
		isGreaterEqualTo("Focus Ratio Weight", focusWeight, "", "0.0");
		minConstraint(min);
		ratioEqualToOne(activeWeight, focusWeight);
		isGreaterThan("Max", max, "Min", min);
		isEmptyOrNull(activeWeight);
		isEmptyOrNull(focusWeight);
		isEmptyOrNull(min);
		isEmptyOrNull(max);
		return errorMessage == null;
	}
	
	private void minConstraint(String value) {
		Double verifiedValue = isDouble(value);
		if(verifiedValue != null) {
			if(verifiedValue != 0 && verifiedValue < 0.01) {
				setErrorMessage("Min value can be 0 or greater than 0.01.");
			}
		}
	}
	
	private void ratioEqualToOne(String ratio1, String ratio2) {
		Double verifiedRatio1 = isDouble(ratio1);
		Double verifiedRatio2 = isDouble(ratio2);
		if(verifiedRatio1 != null && verifiedRatio2 != null) {
			Double result = verifiedRatio1 + verifiedRatio2;
			if(result != 1.0) {
				setErrorMessage("The sum of the active and focus ratio weights must equal one. Your result: " + result);
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
	
	private void isGreaterThan(String nameOfValue, String value, String nameOfTest, String test) {
		Double verifiedValue = isDouble(value);
		Double verifiedTest = isDouble(test);
		if(verifiedValue != null && verifiedTest != null) {
			if(verifiedValue <= verifiedTest) {
				setErrorMessage(nameOfValue + " must be greater than " + nameOfTest + ": " + test + ". You entered " + value);
			}
		}
	}
	
	private void isGreaterEqualTo(String nameOfValue, String value, String nameOfTest, String test) {
		Double verifiedValue = isDouble(value);
		Double verifiedTest = isDouble(test);
		if(verifiedValue != null && verifiedTest != null) {
			if(verifiedValue < verifiedTest) {
				setErrorMessage(nameOfValue + " must be greater than " + nameOfTest + ": " + test + ". You entered " + value);
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