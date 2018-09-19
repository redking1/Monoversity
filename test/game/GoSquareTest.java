/**
 * 
 */
package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests all the instance variables and associated methods with the
 * class GoSquare
 * 
 * @authors Ryan, Helen
 *
 */

public class GoSquareTest {

	// Test Data

	double validPrizeMoneyPositive, invalidPrizeMoneyNegative, invalidPrizeMoneyZero;
	String validSquareName;

	@Before
	public void setUp() throws Exception {

		validPrizeMoneyPositive = 10.00;
		invalidPrizeMoneyNegative = -2.00;
		invalidPrizeMoneyZero = 0.00;

		validSquareName = "valid name";

	}

	/**
	 * Test method for default constructor.
	 */
	@Test
	public void testGoSquare() {
		GoSquare go = new GoSquare();
		assertNotNull(go);
	}

	/**
	 * Test method for constructor with arguments.
	 * 
	 */
	@Test
	public void testGoSquareStringStringDouble() {
		GoSquare go = new GoSquare(validSquareName, validPrizeMoneyPositive);
		assertNotNull(go);
		assertEquals(validSquareName, go.getSquareName());
		assertEquals(validPrizeMoneyPositive, go.getPrizeMoney(), 0);

	}

	/**
	 * Test method for getter and setter for prize money.
	 */
	@Test
	public void testSetPrizeMoneyValidValues() {
		GoSquare go = new GoSquare();
		go.setPrizeMoney(validPrizeMoneyPositive);
		assertEquals(validPrizeMoneyPositive, go.getPrizeMoney(), 0);
	}

	/**
	 * Test method for getter and setter with negative prize money
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetPrizeMoneyInvalidValueNegative() {
		GoSquare go = new GoSquare();
		go.setPrizeMoney(invalidPrizeMoneyNegative);
	}

	/**
	 * Test method for getter and setter with zero prize money
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetPrizeMoneyInvalidValueZero() {
		GoSquare go = new GoSquare();
		go.setPrizeMoney(invalidPrizeMoneyZero);
	}

}
