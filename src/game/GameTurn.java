package game;

/**
 * This class controls the mechanics involved in a game turn
 * 
 * @author Helen
 *
 */

public class GameTurn {

	/**
	 * Default constructor
	 */
	public GameTurn() {

	}

	/**
	 * This switches the order of the players array at the end of the turn Note:
	 * only works for two players
	 * 
	 * @param players
	 * @return
	 */
	public Player[] switchOrderAtEndOfTurn(Player[] players) {
		// used this code in gameStart rollAndCalculateOrder
		Player temp;
		temp = players[0];
		players[0] = players[1];
		players[1] = temp;
		return players;
	}

}
