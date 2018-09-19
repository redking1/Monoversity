package game;

import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests all the instance variables and associated methods with the
 * class Portfolio
 * 
 * @author Helen
 *
 */
public class PortfolioTest {

	// Test data
	Player portfolioHolder;
	SiteSquare validSite1, validSite2, validSite3, validSite4;
	Square[] boardSquares;
	Board board;
	TitleDeed titleDeed1, titleDeed2, titleDeed3, titleDeed4;
	ArrayList<TitleDeed> validDeedPortfolio, expectedBuildableSites;

	@Before
	public void setUp() throws Exception {
		validSite1 = new SiteSquare("validName1", 50.00, Group.RED, true);
		validSite2 = new SiteSquare("validName2", 50.00, Group.RED, true);
		validSite3 = new SiteSquare("validName3", 50.00, Group.BLUE, true);
		validSite4 = new SiteSquare("validName4", 50.00, Group.BLUE, false); // to be added

		portfolioHolder = new Player();

		titleDeed1 = new TitleDeed(validSite1, portfolioHolder, 3, 1); // cannot be built on
		titleDeed2 = new TitleDeed(validSite2, portfolioHolder, 2, 0); // can be built on and we have monopoly
		titleDeed3 = new TitleDeed(validSite3, portfolioHolder, 0, 0); // can be built on but we don't have monopoly
		titleDeed4 = new TitleDeed(validSite4, portfolioHolder, 0, 0); // to be added

		validDeedPortfolio = new ArrayList<>(3);
		validDeedPortfolio.add(0, titleDeed1);
		validDeedPortfolio.add(0, titleDeed2);
		validDeedPortfolio.add(0, titleDeed3);

		// creating board
		// this has all the squares of above
		// so ideally we have a red monopoly but not a blue one
		boardSquares = new Square[4];
		boardSquares[0] = validSite1;
		boardSquares[1] = validSite2;
		boardSquares[2] = validSite3;
		boardSquares[3] = validSite4;
		board = new Board(boardSquares);

		expectedBuildableSites = new ArrayList<>(1);
		expectedBuildableSites.add(0, titleDeed2);

	}

	/**
	 * Tests default constructor
	 */
	@Test
	public void testPortfolio() {
		Portfolio pf = new Portfolio();
		assertNotNull(pf);
	}

	/**
	 * Tests constructor with args
	 */
	@Test
	public void testPortfolioArrayListOfTitleDeed() {
		Portfolio pf = new Portfolio(validDeedPortfolio);
		assertNotNull(pf); // test not null
		assertEquals(validDeedPortfolio, pf.getDeedPortfolio()); // test assigned object correctly
	}

	/**
	 * Tests getter and setter for deedPortfolio with valid input
	 */
	@Test
	public void testGetSetDeedPortfolio() {
		Portfolio pf = new Portfolio();
		pf.setDeedPortfolio(validDeedPortfolio);
		assertEquals(validDeedPortfolio, pf.getDeedPortfolio());
	}

	/**
	 * Tests whether a title deed can be added to a portfolio successfully
	 */
	@Test
	public void testAddTitleDeedToPortfolio() {
		Portfolio pf = new Portfolio(validDeedPortfolio);
		// doing methodically
		ArrayList<TitleDeed> expectedResult = validDeedPortfolio;
		expectedResult.add(titleDeed4);
		// via designed method
		pf.addTitleDeedToPortfolio(titleDeed4);
		assertEquals(expectedResult, pf.getDeedPortfolio());
	}

	/**
	 * Tests whether or not a specific titleDeed is in a portfolio
	 */
	@Test
	public void testCheckInPortfolio() {
		Portfolio pf = new Portfolio(validDeedPortfolio);
		// checking for a site we know is in the portfolio
		Boolean actualResult = pf.checkInPortfolio(validSite1);
		assertEquals(true, actualResult);

		// checking for a site we know is not in the portfolio
		Boolean actualResult2 = pf.checkInPortfolio(validSite4);
		assertEquals(false, actualResult2);

	}

	/**
	 * Tests that information in a portfolio is displayed
	 */
	@Test
	public void testDisplayPortfolio() {
		Portfolio pf = new Portfolio(validDeedPortfolio);
		pf.displayPortfolio();
	}

	/**
	 * Tests that monopoly is checked for correctly
	 */
	@Test
	public void testCheckMonopoly() {
		Portfolio pf = new Portfolio(validDeedPortfolio);
		// expected to find a monopoly
		boolean actualResult = pf.checkMonopoly(board, Group.RED);
		assertEquals(true, actualResult);
		// expect to not find a monopoly
		boolean actualResult2 = pf.checkMonopoly(board, Group.BLUE);
		assertEquals(false, actualResult2);

	}

	/**
	 * Tests that buildable sites displayed correctly
	 */
	@Test
	public void testCalculateBuildableSites() {
		Portfolio pf = new Portfolio(validDeedPortfolio);
		// Actual Result
		ArrayList<TitleDeed> actualResult = pf.calculateBuildableSites(board);

		assertEquals(actualResult, expectedBuildableSites);

	}

}
