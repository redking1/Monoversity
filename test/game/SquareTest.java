package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests all the instance variables and associated methods with the
 * class Square
 * 
 * @authors Ryan, Helen
 *
 */
public class SquareTest {

	// test data
	String validSquareName, validSquareNameMin, validSquareNameMax, invalidSquareNameNull, invalidSquareNameEmpty,
			invalidSquareNameMax;

	@Before
	public void setUp() throws Exception {

		validSquareName = "validName";
		validSquareNameMin = "a"; // 1 character
		validSquareNameMax = "aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeee"; // 50 characters
		invalidSquareNameNull = null;
		invalidSquareNameEmpty = " "; // white space
		invalidSquareNameMax = "aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeef"; // 51 characters long

	}

	/**
	 * Test of default constructor
	 */
	@Test
	public void testSquare() {
		Square cube = new Square();
		assertNotNull(cube);
	}

	/**
	 * Test of constructor with arguments
	 */
	@Test
	public void testSquareStringString() {
		Square cube = new Square(validSquareName);
		assertNotNull(cube);
		assertNotNull(cube.getSquareName());
		assertEquals(validSquareName, cube.getSquareName());

	}

	/**
	 * Test of getter and setter for squareName with valid values
	 */
	@Test
	public void testGetSetSquareNameValidValues() {
		Square cube = new Square();
		cube.setSquareName(validSquareName); // test valid square name
		assertEquals(validSquareName, cube.getSquareName());
		cube.setSquareName(validSquareNameMax); // test maximum length of square name
		assertEquals(validSquareNameMax, cube.getSquareName());
		cube.setSquareName(validSquareNameMin); // test minimum length of square name
		assertEquals(validSquareNameMin, cube.getSquareName());
	}

	/**
	 * Test of getter and setter using a square name that is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetSquareNameInvalidValueNull() {
		Square cube = new Square();
		cube.setSquareName(invalidSquareNameNull);

	}

	/**
	 * Test of getter and setter using a square name that is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetSquareNameInvalidValueEmpty() {
		Square cube = new Square();
		cube.setSquareName(invalidSquareNameEmpty);
		assertEquals(invalidSquareNameEmpty, cube.getSquareName());

		cube.setSquareName(invalidSquareNameEmpty);
	}

	/**
	 * Test of getter and setter using a square name that is too long
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetSquareNameInvalidValueMax() {
		Square cube = new Square();
		cube.setSquareName(invalidSquareNameMax);

	}

}
