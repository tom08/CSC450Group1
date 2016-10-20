package com.CSC450.ars.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class RatiosCalTest {
	@Test
	public void testRatiosCal() {
		RatiosCal test = new RatiosCal();
		test.getWeightsConfig();
		double result = test.RatioFormula(0.2, 0.3);
		assertEquals(0.127, result, 0.0001);
	}
}
