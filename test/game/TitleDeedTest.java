package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests all the instance variables and associated methods with the
 * class Square
 * 
 * @author Helen
 *
 */
public class TitleDeedTest {

	// Test data
	SiteSquare validLocation;
	Player validOwner;
	Piece playersPiece;
	int validBaseCount, validBaseCountMax, invalidBaseCountMin, invalidBaseCountMax, validSpecialCount,
			invalidSpecialCountMin, invalidSpecialCountMax, expectedSpaceForBase, expectedSpaceForSpecial;
	double expectedRentNoItems, expectedRentBaseItems, expectedRentBothItems, expectedBaseItemCost,
			expectedSpecialItemCost;

	@Before
	public void setUp() throws Exception {
		validLocation = new SiteSquare("siteName", 40.00, Group.RED, false);
		playersPiece = new Piece("dog", 0);
		validOwner = new Player("Helen", playersPiece, 20.00);

		validBaseCount = 2;
		validBaseCountMax = 3; // used during expectedRentBothItems
		invalidBaseCountMin = -1;
		invalidBaseCountMax = 4;

		validSpecialCount = 1;
		invalidSpecialCountMin = -1;
		invalidSpecialCountMax = 2;

		expectedRentNoItems = 10.00; // (0.25*40.00)
		expectedRentBaseItems = 90.00; // (0.25*40.00) + validBaseCount*(40.00)
		expectedRentBothItems = 170.00; // (0.25*40.00) + validBaseCountMax*(40.00) + validSpecialCount*(40.00)

		expectedBaseItemCost = 40.00; // just uses siteCost * 1
		expectedSpecialItemCost = 40.00; // just uses siteCost * 1

		expectedSpaceForBase = 1; // when using validBaseCount of 2
		expectedSpaceForSpecial = 0; // when using validSpecialCount of 1
	}

	/**
	 * Tests default constructor
	 */
	public void testTitleDeed() {
		TitleDeed td = new TitleDeed();
		assertNotNull(td);
	}

	/**
	 * Tests constructor with argument
	 */
	public void testTitleDeedSiteSquarePlayerIntInt() {
		TitleDeed td = new TitleDeed(validLocation, validOwner, validBaseCount, validSpecialCount);
		assertNotNull(td);
		// assert equals
		assertEquals(validLocation, td.getLocation());
		assertEquals(validOwner, td.getOwner());
		assertEquals(validBaseCount, td.getBaseItemCount());
		assertEquals(validSpecialCount, td.getSpecialItemCount());

	}

	/**
	 * Tests the getter and setter for owner with valid data
	 */
	@Test
	public void testGetSetOwner() {
		TitleDeed td = new TitleDeed();
		td.setOwner(validOwner);
		assertEquals(validOwner, td.getOwner());
	}

	/**
	 * Tests the getter and setter for baseItemCount with valid data
	 */
	@Test
	public void testGetSetBaseItemCount() {
		TitleDeed td = new TitleDeed();
		td.setBaseItemCount(validBaseCount);
		assertEquals(validBaseCount, td.getBaseItemCount());
	}

	/**
	 * Tests the getter and setter for baseItemCount with the lower boundary of
	 * invalid data
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetBaseItemCountInvalidMin() {
		TitleDeed td = new TitleDeed();
		td.setBaseItemCount(invalidBaseCountMin);
	}

	/**
	 * Tests the getter and setter for baseItemCount with the upper boundary of
	 * invalid data
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetBaseItemCountInvalidMax() {
		TitleDeed td = new TitleDeed();
		td.setBaseItemCount(invalidBaseCountMax);
	}

	/**
	 * Tests the getter and setter for specialItemCount with valid data
	 */
	@Test
	public void testGetSetSpecialItemCountValid() {
		TitleDeed td = new TitleDeed();
		td.setSpecialItemCount(validSpecialCount);
		assertEquals(validSpecialCount, td.getSpecialItemCount());
	}

	/**
	 * Tests the getter and setter for specialItemCount with the lower boundary of
	 * invalid data
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetSpecialItemCountInvalidMin() {
		TitleDeed td = new TitleDeed();
		td.setSpecialItemCount(invalidSpecialCountMin);
	}

	/**
	 * Tests the getter and setter for specialItemCount with the upper boundary of
	 * invalid data
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSetSpecialItemCountInvalidMax() {
		TitleDeed td = new TitleDeed();
		td.setSpecialItemCount(invalidSpecialCountMax);
	}

	/**
	 * Tests the getter and setter for location with valid data
	 */
	@Test
	public void testGetSetLocation() {
		TitleDeed td = new TitleDeed();
		td.setLocation(validLocation);
		assertEquals(validLocation, td.getLocation());
	}

	/**
	 * Tests that method calculates rent successfully when there are 0 base items
	 * and 0 special items
	 */
	@Test
	public void testCalculateRentNoItems() {
		TitleDeed td = new TitleDeed(validLocation, validOwner, 0, 0);
		double actualRent = td.calculateRent();
		assertEquals(actualRent, expectedRentNoItems, 0);
	}

	/**
	 * Tests that method calculates rent successfully when there are base items but
	 * 0 special items
	 */
	@Test
	public void testCalculateRentBaseItems() {
		TitleDeed td = new TitleDeed(validLocation, validOwner, validBaseCount, 0);
		double actualRent = td.calculateRent();
		assertEquals(actualRent, expectedRentBaseItems, 0);
	}

	/**
	 * Tests that method calculates rent successfully when there are base items and
	 * a special item
	 */
	@Test
	public void testCalculateRentBothItems() {
		TitleDeed td = new TitleDeed(validLocation, validOwner, validBaseCountMax, validSpecialCount);
		double actualRent = td.calculateRent();
		assertEquals(actualRent, expectedRentBothItems, 0);
	}

	/**
	 * Tests that baseItemCost is calculated successfully
	 */
	@Test
	public void testCalculateBaseItemCost() {
		TitleDeed td = new TitleDeed(validLocation, validOwner, 0, 0);
		double actualCost = td.calculateBaseItemCost();
		assertEquals(actualCost, expectedBaseItemCost, 0);
	}

	/**
	 * Tests that specialItemCost is calculated successfully
	 */
	@Test
	public void testCalculateSpecialItemCost() {
		TitleDeed td = new TitleDeed(validLocation, validOwner, 0, 0);
		double actualCost = td.calculateSpecialItemCost();
		assertEquals(actualCost, expectedSpecialItemCost, 0);
	}

	/**
	 * Tests that development space for new base items is calculated correctly
	 */
	@Test
	public void testCalculateSpaceForBaseItems() {
		TitleDeed td = new TitleDeed(validLocation, validOwner, validBaseCount, validSpecialCount);
		int actualSpace = td.calculateSpaceForBaseItems();
		assertEquals(actualSpace, expectedSpaceForBase, 0);
	}

	/**
	 * Tests that development space for new special items is calculated correctly
	 */
	@Test
	public void testCalculateSpaceForSpecialItems() {
		TitleDeed td = new TitleDeed(validLocation, validOwner, validBaseCount, validSpecialCount);
		int actualSpace = td.calculateSpaceForSpecialItems();
		assertEquals(actualSpace, expectedSpaceForSpecial, 0);
	}

}
