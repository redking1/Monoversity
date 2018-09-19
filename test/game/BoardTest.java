package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests all the instance variables and associated methods with the
 * class Board
 * 
 * @authors Ryan, Helen
 *
 */

public class BoardTest {

	// test data
	Square[] validSquareLocations;
	Square validSquare1, validSquare2, validSquare3;

	int validCurrentPosition, validDieValue, expectedNewPosition;

	boolean expectedPassedGo;

	@Before
	public void setUp() throws Exception {

		// create some squares
		validSquare1 = new Square("validName");
		validSquare2 = new Square("validName2");
		validSquare3 = new Square("validName3");
		validSquareLocations = new Square[3]; // create array of length 3
		validSquareLocations[0] = validSquare1;
		validSquareLocations[1] = validSquare2;
		validSquareLocations[2] = validSquare3; // change first element to that of first square etc

		validCurrentPosition = 0;
		validDieValue = 4;
		expectedNewPosition = 1;
		expectedPassedGo = true;

	}

	/**
	 * Test of default constructor.
	 */
	@Test
	public void testBoard() {
		Board board = new Board();
		assertNotNull(board);
	}

	/**
	 * Test of constructor with arguments
	 */
	@Test
	public void testBoardSquareArray() {
		Board board = new Board();
		assertNotNull(board);
		board.setSquareLocations(validSquareLocations);
		assertArrayEquals(validSquareLocations, board.getSquareLocations());

	}

	/**
	 * Test of getter and setter for squareLocation with valid value
	 */
	@Test
	public void testGetSetSquareLocationsValidValue() {
		Board board = new Board();
		board.setSquareLocations(validSquareLocations);
		assertArrayEquals(validSquareLocations, board.getSquareLocations());
	}

	/**
	 * Sees if the new position is calculated correctly
	 */
	@Test
	public void testGetNewPosition() {
		Board board = new Board(validSquareLocations); // board needs to have squares for this to be tested
		int actualNewPosition;
		actualNewPosition = board.getNewPosition(validCurrentPosition, validDieValue);
		assertEquals(actualNewPosition, expectedNewPosition);
	}

	/**
	 * Test whether passedGo method results in correct value
	 */
	@Test
	public void testPassedGo() {
		Board board = new Board(validSquareLocations); // board needs to have squares for this to be tested
		boolean actualPassedGo;
		actualPassedGo = board.passedGo(validCurrentPosition, validDieValue);
		assertEquals(actualPassedGo, expectedPassedGo);
	}

}
