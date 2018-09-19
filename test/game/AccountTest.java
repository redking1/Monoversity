package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests all the instance variables and associated methods with the
 * class Account
 * 
 * @authors Ryan, Helen
 * 
 */

public class AccountTest {
	// test data
	SiteSquare validSiteAffordable, validSiteExpensive;
	double validBalance, validSiteCostAffordable, validSiteCostExpensive, expectedBalanceAfterPurchase, validWithdrawal,
			expectedBalanceAfterWithdrawal, validDeposit, expectedBalanceAfterDeposit;

	@Before
	public void setUp() throws Exception {
		validBalance = 80.00;

		validSiteAffordable = new SiteSquare();
		validSiteCostAffordable = 60.00; // less than balance
		validSiteAffordable.setSitePrice(validSiteCostAffordable);

		validSiteExpensive = new SiteSquare();
		validSiteCostExpensive = 90.00; // more than balance
		validSiteExpensive.setSitePrice(validSiteCostExpensive);

		expectedBalanceAfterPurchase = validBalance - validSiteCostAffordable; // 20.00

		validWithdrawal = 5.00;
		expectedBalanceAfterWithdrawal = validBalance - validWithdrawal; // 75.00

		validDeposit = 5.00;
		expectedBalanceAfterDeposit = validBalance + validDeposit; // 85.00

	}

	/**
	 * Test of default constructor
	 */
	@Test
	public void testAccount() {
		Account acc = new Account();
		assertNotNull(acc);
	}

	/**
	 * Test of constructor with arguments
	 */
	@Test
	public void testAccountDouble() {
		Account acc = new Account(validBalance);
		assertNotNull(acc);
		assertEquals(validBalance, acc.getBalance(), 0);

	}

	/**
	 * Test of Getter/setter of balance with a valid balance value.
	 */
	@Test
	public void testGetSetBalanceValidValues() {
		Account acc = new Account(validBalance);
		acc.setBalance(validBalance);
		assertEquals(validBalance, acc.getBalance(), 0);
	}

	/**
	 * Testing payForSite when the site is affordable
	 */
	@Test
	public void testPayForSiteValidValueAffordable() {
		Account acc = new Account(validBalance);
		Boolean actualPurchased = acc.payForSite(validSiteAffordable);

		// test deducted correctly
		assertEquals(expectedBalanceAfterPurchase, acc.getBalance(), 0);

		// test returned true
		assertEquals(true, actualPurchased);

	}

	/**
	 * Testing payForSite when the site is too expensive
	 */
	@Test
	public void testPayForSiteValidValueExpensive() {
		Account acc = new Account(validBalance);
		Boolean actualPurchased = acc.payForSite(validSiteExpensive);

		// test nothing deducted
		assertEquals(validBalance, acc.getBalance(), 0);

		// test returned false
		assertEquals(false, actualPurchased);

	}

	/**
	 * Test balanced displayed correctly
	 */
	@Test
	public void testDisplayBalance() {
		Account acc = new Account(validBalance);
		acc.displayBalance();

	}

	/**
	 * Test withdraws money correctly
	 */
	public void testWithdrawal() {
		Account acc = new Account(validBalance);
		double actualResult = acc.withdrawFromBalance(validWithdrawal);
		assertEquals(actualResult, expectedBalanceAfterWithdrawal, 0);
	}

	/**
	 * Test deposits money correctly
	 */
	public void testDeposit() {
		Account acc = new Account(validBalance);
		double actualResult = acc.depositToBalance(validDeposit);
		assertEquals(actualResult, expectedBalanceAfterDeposit, 0);
	}

}
