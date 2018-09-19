package game;

/**
 * This class contains all the methods involved in the completion of a game of
 * Monoversity.
 * 
 * @authors Helen, Chris
 *
 */
public class GameEnd {

	/**
	 * Default constructor
	 */
	public GameEnd() {

	}

	/**
	 * Method to calculate the final balance of each players account and output the
	 * appropriate final message
	 * 
	 * @param players
	 */
	public void declareWinner(Player[] players) {
		double finalBalanceA = 0.0;
		double finalBalanceB = 0.0;

		finalBalanceA = players[0].getPlayerAccount().getBalance();
		finalBalanceB = players[1].getPlayerAccount().getBalance();

		System.out.println("***************** GAME OVER ******************");

		if (finalBalanceA > finalBalanceB) {
			// players[0] has won
			System.out.printf(
					"\nGame has ended. %s has invested wisely, thrown well and finishes this session with £%.2f. "
							+ "\n%s finishes in last place with £%.2f. \nThank you for playing.",
					players[0].getName().toString(), players[0].getPlayerAccount().getBalance(),
					players[1].getName().toString(), players[1].getPlayerAccount().getBalance());
		} else if (finalBalanceA == finalBalanceB) {
			// draw
			System.out.printf("Game has ended. It's a draw! %s has £%.2f and %s has £%.2f. Thank you for playing",
					players[0].getName().toString(), players[0].getPlayerAccount().getBalance(),
					players[1].getName().toString(), players[1].getPlayerAccount().getBalance());
		} else {
			// players[1] has won
			System.out.printf(
					"Game has ended. %s has invested wisely, thrown well and finishes this session with £%.2f. "
							+ "\n%s finishes in last place with £%.2f. \nThank you for playing.",
					players[1].getName().toString(), players[1].getPlayerAccount().getBalance(),
					players[0].getName().toString(), players[0].getPlayerAccount().getBalance());
		}

	}

}
