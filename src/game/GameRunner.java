package game;

/**
 * This class holds the process of the game itself
 * @author Helen, Chris
 *
 */

import java.util.Scanner;

public class GameRunner {
	/**
	 * Main method of game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// create all necessary control objects
		Scanner scanner = new Scanner(System.in);
		GameCreator creator = new GameCreator();
		GameStart start = new GameStart();
		GameTurn turn = new GameTurn();
		GameMove move = new GameMove();
		GameBuyItem buyItem = new GameBuyItem();
		GameStatus status = new GameStatus();
		GameEnd end = new GameEnd();

		// set up the game itself
		Board board = creator.createBoard(creator.createSquares());
		Die die = creator.createDie();
		Piece[] pieces = creator.createPieces();

		// output information to players and set up their associated objects
		start.welcomeToMonoversity();
		Player player1 = start.enterNameAndCreatePlayer(1, scanner);
		Player player2 = start.enterNameAndCreatePlayer(2, scanner);
		Player[] players = { player1, player2 }; // put in array
		start.sameNameCheck(players);
		start.assignPieceToPlayer(players, pieces);
		players = start.rollAndCalculatePlayOrder(players, die, scanner);
		start.displayGameDescription(players);

		boolean playing = false; // will use this for a while loop later
		if (start.readyToPlay(players, scanner)) {
			playing = true;
		}

		// start playing
		Scanner scTemp = new Scanner(System.in); // passing in temporary scanner
		// every loop here represents one turn
		while (playing) {
			System.out.println("----------------------------------------------------------------");
			System.out.println("It's " + players[0].getName() + "'s turn.");

			String input = "";
			boolean undecided = true;

			while (undecided) {
				// output menu to user
				System.out.println("Enter 'r' to roll, 's' to view game status or 'q' to quit");
				input = scanner.nextLine();
				if (input.equals("s")) {
					// view status
					status.viewGameStatus(players, board, scanner);
					// undecided is still true, will loop again

				} else if (input.equals("q")) {
					// quit game
					System.out.println("Are you sure you want to quit?");
					System.out.println("Enter 'y' to confirm");
					input = scanner.nextLine();
					if (input.equalsIgnoreCase("y")) {
						System.out.println("Quit game selected \n");
						undecided = false;
						playing = false;
						break;
					} else {
						continue; // skipped switching player - cancelling game quit
					}
				} else if (input.equals("r")) {
					// rolling die
					undecided = false; // don't loop again

					int dieValue = die.rollDie();
					System.out.println("You rolled a " + dieValue);
					move.moveAndCheckPassGo(players[0], dieValue, board);
					boolean continuePlaying = move.displayOptionsAndContinuePlaying(players, board, scanner);
					if (!continuePlaying) { // bankruptcy has occurred
						playing = false; // stop turns
						break; // out of turn
					}

					System.out.println(); // makes output nicer
					buyItem.developOnSite(players[0], board, scTemp);
					// switch players at end
					turn.switchOrderAtEndOfTurn(players);
				} else {
					// other input
					System.out.println("Couldn't read input, please try again.");
				}
			}
		}
		// end game
		scTemp.close();
		end.declareWinner(players);
		scanner.close();
	}

}
