package game;

import java.util.Scanner;

/**
 * 
 * @author Helen
 *
 */
public class GameMove {
	/**
	 * Default constructor
	 */
	public GameMove() {
	}

	/**
	 * This moves a piece on the board based on a given dieValue
	 * 
	 * @param Player
	 * @param dieValue
	 * @param board
	 */
	public void moveAndCheckPassGo(Player player, int dieValue, Board board) {
		Boolean passedGo;
		passedGo = player.getPlayerPiece().passedGo(board, dieValue); // check if passed go
		player.getPlayerPiece().move(board, dieValue); // move player piece
		// display this information to the player
		int newPosition = player.getPlayerPiece().getCurrentPosition();
		System.out.println("You moved to " + board.getSquareLocations()[newPosition].getSquareName());

		if (passedGo) {
			// increase the balance in their account by prize money
			double prizeMoney = ((GoSquare) board.getSquareLocations()[0]).getPrizeMoney();
			double tempBalance = player.getPlayerAccount().getBalance();
			tempBalance += prizeMoney;
			player.getPlayerAccount().setBalance(tempBalance);
			// output info to player
			System.out.printf(
					"You passed go during that move. Your account has been credited with £%.2f and your new balance is £%.2f \n",
					prizeMoney, player.getPlayerAccount().getBalance());
		}
	}

	/**
	 * Based on the player's location, tell them what their options are and return
	 * to the system whether or not they are able to keep playing.
	 * 
	 * @param players
	 * @param board
	 * @param scanner
	 * @return true if player is not bankrupt and can continue playing, false if
	 *         player is bankrupt
	 */
	public boolean displayOptionsAndContinuePlaying(Player[] players, Board board, Scanner scanner) {
		String input = "";

		int currentLocation = players[0].getPlayerPiece().getCurrentPosition();
		Square currentSquare = board.getSquareLocations()[currentLocation];

		if (currentSquare instanceof SiteSquare) { // landed on siteSquare

			SiteSquare currentSiteSquare = ((SiteSquare) currentSquare); // cast as siteSquare

			if (currentSiteSquare.isOwned()) { // siteSquare owned
				if (players[0].getPlayerPortfolio().checkInPortfolio(currentSiteSquare)) {
					// in your portfolio
					System.out.println("You already own " + currentSiteSquare.getSquareName());
					return true; // not gone bankrupt
				} else {
					// not in your portfolio
					// pay rent
					System.out.println("This site is owned by " + players[1].getName() + ". You now owe rent.");
					GameRent rent = new GameRent();
					return rent.payRent(players, currentSiteSquare); // could have gone bankrupt
				}
			} else {
				// siteSquare isn't owned so user has option to buy the site
				System.out.printf("%s is unoccupied and costs £%.2f. \n", currentSiteSquare.getSquareName(),
						currentSiteSquare.getSitePrice());
				// loop to check for valid response of y or n
				boolean validResponse = false;
				while (!validResponse) {
					System.out.println("Would you like to buy this site? Input 'y' for yes or 'n' for no.");
					input = scanner.nextLine();
					if (input.equalsIgnoreCase("y")) {
						GameBuySite buySite = new GameBuySite();
						buySite.buySite(players[0], currentSiteSquare);
						validResponse = true;
					} else if (input.equalsIgnoreCase("n")) {
						System.out.println("Fair enough.");
						validResponse = true;
					}
				}
				return true; // not gone bankrupt
			}

		} else { // landed on goSquare
			System.out.println("As you landed on " + currentSquare.getSquareName() + ", there are no more options.");
			return true; // not gone bankrupt
		}
	}

}
