

import static org.junit.Assert.*;

import org.junit.Test;
import com.CSC450.ars.domain.AdLocationVisit;

public class RatiosCalTest {
	@Test
	public void testRatiosCal() {
		AdLocationVisit test = new AdLocationVisit();
		test.setFocusRatio(0.25);
		test.setActiveRatio(0.75);
		double result = test.RatioFormula(0.5, 0.5);
		assertEquals(0.5, result, 0.0001);
	}
}
