package game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * This class tests all the instance variables and associated methods with the
 * class Player
 * 
 * @authors Ryan, Helen
 */

public class PlayerTest {

	// test data
	String validName, invalidNameMax, invalidNameNull, invalidNameEmpty;
	Piece validPiece;

	double validOpeningBalance;
	Account validAccount;

	ArrayList<TitleDeed> emptyDeedPortfolio;
	Portfolio emptyPortfolio;

	@Before
	public void setUp() throws Exception {

		validName = "name1";
		invalidNameMax = "aaaaaaaaaabbbbbbbbbbc"; // 21 characters long
		invalidNameNull = null;
		invalidNameEmpty = "  "; // only whitespace

		validPiece = new Piece("dog", 1);

		validOpeningBalance = 100.00;
		validAccount = new Account(validOpeningBalance);

		emptyDeedPortfolio = new ArrayList<>(0); // empty deedPortfolio
		emptyPortfolio = new Portfolio(emptyDeedPortfolio);

	}

	/**
	 * Test case for default constructor.
	 */
	@Test
	public void testPlayer() {
		Player player = new Player();
		assertNotNull(player);
	}

	/**
	 * Test for constructor with arguments
	 */
	@Test
	public void testPlayerStringPieceDouble() {
		Player player = new Player(validName, validPiece, validOpeningBalance);
		assertNotNull(player);
		assertEquals(validName, player.getName());
		assertEquals(validPiece, player.getPlayerPiece());
		assertEquals(validOpeningBalance, player.getPlayerAccount().getBalance(), 0);// an account that has been created
																						// using validOpeningBalance
		assertEquals(emptyDeedPortfolio, player.getPlayerPortfolio().getDeedPortfolio()); // an empty portfolio has been
																							// created

	}

	/**
	 * Test player get and set with a valid name
	 */
	@Test
	public void testGetSetNameValidValue() {
		Player player = new Player();
		player.setName(validName);
		assertEquals(validName, player.getName());
	}

	/**
	 * Test get and set for player with name that is too long
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetNameInvalidMax() {
		Player player = new Player();
		player.setName(invalidNameMax);
	}

	/**
	 * Test get and set for player with name that is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetNameInvalidNull() {
		Player player = new Player();
		player.setName(invalidNameNull);
	}

	/**
	 * Test get and set for player with name that is empty
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetNameInvalidEmpty() {
		Player player = new Player();
		player.setName(invalidNameEmpty);
	}

	/**
	 * Testing getter and setter for account with a valid value
	 */
	@Test
	public void testSetPlayerAccount() {
		Player player = new Player();
		player.setPlayerAccount(validAccount);
		assertEquals(validAccount, player.getPlayerAccount());
	}

	/**
	 * Testing getter and setter for portfolio with a valid value
	 */
	@Test
	public void testSetPlayerPortfolio() {
		Player player = new Player();
		player.setPlayerPortfolio(emptyPortfolio);
		assertEquals(emptyPortfolio, player.getPlayerPortfolio());
	}

	/**
	 * Testing getter and setter for piece with a valid value
	 */
	@Test
	public void testSetPlayerPiece() {
		Player player = new Player();
		player.setPlayerPiece(validPiece);
		assertEquals(validPiece, player.getPlayerPiece());
	}

	/**
	 * Testing that an account that the player creates has the desired balance
	 */
	@Test
	public void testCreateAccount() {
		Player player = new Player();
		player.createAccount(validOpeningBalance);
		double createdByPlayer = player.getPlayerAccount().getBalance();
		assertEquals(validOpeningBalance, createdByPlayer, 0);
	}

	/**
	 * Testing that a portfolio that the player creates is empty
	 */
	@Test
	public void testCreatePortfolio() {
		Player player = new Player();
		player.createPortfolio(); // should create an empty portfolio
		assertEquals(emptyDeedPortfolio, player.getPlayerPortfolio().getDeedPortfolio());
	}

}
