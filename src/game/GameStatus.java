/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the current Game Status
 * 
 * @author Chris
 *
 */
public class GameStatus {

	/**
	 * Method will display the current game status (called from current player)
	 * 
	 * @param players
	 * @param board
	 * @param scanner
	 */
	public void viewGameStatus(Player[] players, Board board, Scanner scanner) {

		System.out.println("***************** GAME STATUS ******************");

		// display information for both players
		for (int i = 0; i < players.length; i++) {
			// display player's name, piece type and piece position
			int position = players[i].getPlayerPiece().getCurrentPosition();
			System.out.println(players[i].getName().toString());
			System.out.println("Current Position of " + players[i].getPlayerPiece().getType() + " : "
					+ board.getSquareLocations()[position].getSquareName());

			if (i == 0) { // only output current player's account
				System.out.printf("Account: £%.2f \n", players[i].getPlayerAccount().getBalance());
			}

			// display properties each player owns
			ArrayList<TitleDeed> deedPortfolio = players[i].getPlayerPortfolio().getDeedPortfolio();
			System.out.println("Owns:");
			if (deedPortfolio.size() > 0) {
				players[i].getPlayerPortfolio().displayPortfolio();
			} else {
				System.out.println("Nothing.");
			}
			System.out.println();
		}

		System.out.println("Unowned Properties: ");
		// find unowned sites
		for (Square square : board.getSquareLocations()) {
			// if square is a SiteSquare
			if (square instanceof SiteSquare) {
				SiteSquare siteSquare = (SiteSquare) square;
				if (!siteSquare.isOwned()) {
					System.out.printf("[%s]", siteSquare.getSquareName());
				}
			}
		}

		System.out.println();
		System.out.println("************************************************");
		System.out.println("Enter ‘y’ to exit and return to menu options");
		String input = scanner.nextLine();
		while (!"y".equalsIgnoreCase(input)) {
			System.out.println("Sorry, only 'y' is a valid input");
			System.out.println("Enter ‘y’ to exit and return to menu options");
			input = scanner.nextLine();
		}

	}
}
