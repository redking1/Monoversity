package game;

/**
 * This class represents an account that holds a player's balance
 * 
 * @author Helen
 *
 */
public class Account {
	private double balance;

	/**
	 * Default constructor
	 */
	public Account() {
	}

	/**
	 * Constructor with arguments
	 * 
	 * @param balance
	 */
	public Account(double balance) {
		this.setBalance(balance);
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Attempts to pay for a site - if unsuccessful, returns false, if successful,
	 * deducts sitePrice from account and returns true. Called makePayment on
	 * sequence diagram
	 * 
	 * @param siteSquare
	 */
	public boolean payForSite(SiteSquare siteSquare) {
		boolean sitePurchased;

		double siteCost = siteSquare.getSitePrice();
		// check balanceForSale
		if (balance < siteCost) {
			System.out.println(
					"Insufficient funds! (This is why you should check with your accountant before spending big money)");
			sitePurchased = false;
		} else {
			withdrawFromBalance(siteCost);
			System.out.println("Payment successful!");
			sitePurchased = true;
		}

		return sitePurchased;
	}

	/**
	 * Displays an account's balance
	 */
	public void displayBalance() {
		System.out.printf("\nYour balance is £%.2f", balance);
	}

	/**
	 * Adds a deposit to the account's balance
	 * 
	 * @param deposit
	 * @return
	 */
	public double depositToBalance(double deposit) {
		balance += deposit;
		return balance;
	}

	/**
	 * Withdraws a withdrawal from an account's balance
	 * 
	 * @param withdrawal
	 * @return
	 */
	public double withdrawFromBalance(double withdrawal) {
		balance -= withdrawal;
		return balance;
	}

}
