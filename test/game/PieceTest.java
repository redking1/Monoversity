package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests all the instance variables and associated methods with the
 * class Piece
 * 
 * @author Ryans, Helen
 * 
 */

public class PieceTest {
	// Test data
	String validType, invalidTypeEmpty, invalidTypeNull, invalidTypeMax;

	int validPosition, validPositionZero, invalidPosition;

	@Before
	public void setUp() throws Exception {

		validType = "Type Valid";
		invalidTypeEmpty = "  ";
		invalidTypeNull = null;
		invalidTypeMax = "aaaaaaaaaabbbbbbbbbbc"; // 21 characters

		validPosition = 2; // more than 0
		validPositionZero = 0; // equals 0
		invalidPosition = -1; // less than 0

	}

	/**
	 * Default constructor
	 */
	@Test
	public void testPiece() {
		Piece piece = new Piece();
		assertNotNull(piece);
	}

	/**
	 * Test constructor with arguments
	 */
	@Test
	public void testPieceStringInt() {
		Piece piece = new Piece(validType, validPosition);
		assertNotNull(piece);
		assertEquals(validType, piece.getType());
		assertEquals(validPosition, piece.getCurrentPosition());

	}

	/**
	 * Test getter and setter for type with valid value
	 */
	@Test
	public void testGetSetTypeValidValue() {
		Piece piece = new Piece(validType, validPosition);
		piece.setType(validType);
		assertEquals(validType, piece.getType());

	}

	/**
	 * Test getter and setter for type with invalid value of null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetTypeInvalidValueNull() {
		Piece piece = new Piece();
		piece.setType(invalidTypeNull);
	}

	/**
	 * Test getter and setter for type with invalid value of empty (i.e. whitespace)
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetTypeInvalidValueEmpty() {
		Piece piece = new Piece();
		piece.setType(invalidTypeEmpty);
	}

	/**
	 * Test getter and setter for type with invalid value of too many characters
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetTypeInvalidValue() {
		Piece piece = new Piece();
		piece.setType(invalidTypeMax);
	}

	/**
	 * Getter and setter for current position are tested together. This is the test
	 * case for a valid current position set to '2'. and a validCurrentPosition set
	 * to 0
	 */
	@Test
	public void testSetCurrentPositionValidValue() {
		Piece piece = new Piece(validType, validPosition);
		piece.setCurrentPosition(validPosition); // more than 0
		assertEquals(validPosition, piece.getCurrentPosition());
		piece.setCurrentPosition(validPositionZero); // equals 0
		assertEquals(validPositionZero, piece.getCurrentPosition());
	}

	/**
	 * This is the test case for an invalid current position set to '-1'.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetCurrentPositionInvalidValue() {
		Piece piece = new Piece();
		piece.setCurrentPosition(invalidPosition);
	}

}
