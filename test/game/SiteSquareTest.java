/**
 * 
 */
package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests all the instance variables and associated methods with the
 * class SiteSquare
 * 
 * @author Helen
 *
 */
public class SiteSquareTest {

	// test data
	String validSiteName;
	double validSitePrice, invalidSitePriceMin, invalidSitePriceMax;
	Group validGroup;
	boolean validOwned;

	@Before
	public void setUp() throws Exception {
		validSiteName = "name";
		validSitePrice = 50.00;
		invalidSitePriceMin = 0.00;
		invalidSitePriceMax = 100.00;
		validGroup = Group.RED;
		validOwned = false;
	}

	/**
	 * Test default constructor
	 */
	@Test
	public void testSiteSquare() {
		SiteSquare siteSquare = new SiteSquare();
		assertNotNull(siteSquare);
	}

	/**
	 * Test for constructor with args
	 */
	@Test
	public void testSiteSquareStringDoubleGroupBoolean() {
		SiteSquare siteSquare = new SiteSquare(validSiteName, validSitePrice, validGroup, validOwned);
		assertNotNull(siteSquare);
		// assert equals
		assertEquals(siteSquare.getSquareName(), validSiteName);
		assertEquals(siteSquare.getSitePrice(), validSitePrice, 0);
		assertEquals(siteSquare.getGroup(), validGroup);
		assertEquals(siteSquare.isOwned(), validOwned);

	}

	/**
	 * Test method for getter and setter of a validPrice
	 */
	@Test
	public void testGetSetSitePriceValid() {
		SiteSquare siteSquare = new SiteSquare();
		siteSquare.setSitePrice(validSitePrice);
		assertEquals(validSitePrice, siteSquare.getSitePrice(), 0);
	}

	/**
	 * Test method for getter and setter of the lower boundary of invalid site
	 * prices
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetSitePriceInvalidMin() {
		SiteSquare siteSquare = new SiteSquare();
		siteSquare.setSitePrice(invalidSitePriceMin);
	}

	/**
	 * Test method for getter and setter of the upper boundary of invalid site
	 * prices
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetSitePriceInvalidMax() {
		SiteSquare siteSquare = new SiteSquare();
		siteSquare.setSitePrice(invalidSitePriceMax);

	}

	/**
	 * Test method for getter and setter for group with valid data
	 */
	@Test
	public void testGetSetGroup() {
		SiteSquare siteSquare = new SiteSquare();
		siteSquare.setGroup(validGroup);
		assertEquals(validGroup, siteSquare.getGroup());
	}

	/**
	 * Test method for getter and setter for owned with valid data
	 */
	@Test
	public void testIsSetOwned() {
		SiteSquare siteSquare = new SiteSquare();
		siteSquare.setOwned(validOwned);
		assertEquals(validOwned, siteSquare.isOwned());
	}

}
