package game;
/**
 * This class represents all methods involved in paying rent from one player to another.
 * @author Helen
 *
 */

import java.util.ArrayList;

public class GameRent {

	/**
	 * Default constructor
	 */
	public GameRent() {
	}

	/**
	 * Attempts to pay rent from first player in an array to the second player in
	 * the array.
	 * 
	 * @param players
	 * @param currentSquare
	 * @return boolean - returns true if payRent was successful, return false if
	 *         payRent was unsuccessful and the player is now bankrupt
	 */
	public boolean payRent(Player[] players, Square currentSquare) {
		// find the title deed of the current Square from the other player's portfolio
		TitleDeed currentDeed = findTitleDeedInPortfolio(players[1], currentSquare); // what if null
		if (currentDeed == null) {
			System.out.println("Error: could not find square");
			return true;
		}
		double rentOwed = currentDeed.calculateRent();
		System.out.printf("You owe £%.2f \n", rentOwed);

		if (rentOwed < players[0].getPlayerAccount().getBalance()) {
			// can afford it
			players[0].getPlayerAccount().withdrawFromBalance(rentOwed);
			players[1].getPlayerAccount().depositToBalance(rentOwed);
			System.out.println("Rent payment successful.");
			System.out.printf("%s's balance is now £%.2f and %s's balance is now £%.2f \n", players[0].getName(),
					players[0].getPlayerAccount().getBalance(), players[1].getName(),
					players[1].getPlayerAccount().getBalance());
			return true;
		} else {
			// can't afford it
			System.out.println("You can't afford to pay your rent. You've gone bankrupt!");
			players[0].getPlayerAccount().withdrawFromBalance(rentOwed);
			players[1].getPlayerAccount().depositToBalance(rentOwed);
			return false;
		}
	}

	/**
	 * This method finds a title deed in a player's portfolio, given a siteSquare
	 * 
	 * @param player
	 * @param squareToSearch
	 * @return
	 */
	public TitleDeed findTitleDeedInPortfolio(Player player, Square squareToSearch) {
		ArrayList<TitleDeed> deedPortfolio = player.getPlayerPortfolio().getDeedPortfolio();
		// searches loop
		for (TitleDeed td : deedPortfolio) {
			if (td.getLocation() == squareToSearch) {
				return td;
			}
		}
		return null; // if no successful match was located
	}

}
