package com.CSC450.ars.controller;

public class RatiosCal 
{
	double result;
	
	double activeRatioWeight;
	double focusRatioWeight;
	
	public void getWeightsConfig()
	{
		activeRatioWeight = 0.32;
		focusRatioWeight = 0.21;		
	}
	
	public double RatioFormula(double activeRatio, double foucsRatio) 
	{
		result = activeRatio * activeRatioWeight + foucsRatio * focusRatioWeight;
		return result;
	}
}



