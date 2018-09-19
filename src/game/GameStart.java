package game;
/**
 * This class deals with the methods that set up the gameplay
 *@author Helen
 *
 */

import java.util.Scanner;
import java.util.Random;

public class GameStart {
	/**
	 * This stores the opening balance used for a player's account
	 */
	public static final double OPENING_BALANCE = 100.00;

	/**
	 * Default constructor
	 */
	public GameStart() {
	}

	/**
	 * Welcomes players to monoversity
	 */
	public void welcomeToMonoversity() {
		System.out.println("**************** Welcome to Monoversity *****************");
	}

	/**
	 * Prompts player to enter their name and then creates a player, complete with
	 * account and (empty) portfolio
	 * 
	 * @param playerPosition
	 * @param scanner
	 * @return
	 */
	public Player enterNameAndCreatePlayer(int playerPosition, Scanner scanner) {
		String name = "Player " + playerPosition; // default name is Player 1 or Player 2
		String input = "";
		boolean unhappy = true;

		do {
			System.out.println("Player " + playerPosition + ": please enter your name and hit enter when you're done.");
			name = scanner.nextLine();

			// if name fails validation
			if (name == null || name.trim().length() == 0 || name.length() > 18) { // note: namelength is shorter in
																					// case we need to append
				System.out.println("Sorry, names must be at least 1 character and at most 18 characters. ");
				unhappy = true;
				continue; // skip everything below
			}

			// otherwise check user likes name
			System.out.println("Your name is " + name + ". Are you happy with this? Type y for yes");
			input = scanner.nextLine(); // get input
			if (input.equalsIgnoreCase("y")) {
				unhappy = false;
			} else {
				unhappy = true;
			}
		} while (unhappy);

		Player player = new Player(name, null, OPENING_BALANCE);
		return player;
	}

	/**
	 * Assign piece to player - take in array of piece and an array of player and
	 * randomly assign them to each other. Return players with the pieces assigned
	 * as an instance variable.
	 * 
	 * @param players
	 * @param pieces
	 * @return players with the pieces assigned
	 */
	public Player[] assignPieceToPlayer(Player[] players, Piece[] pieces) {
		Random random = new Random();
		boolean samePiece;

		do {
			samePiece = false; // reset
			// randomly assign pieces to players
			players[0].setPlayerPiece(pieces[random.nextInt(pieces.length)]);
			players[1].setPlayerPiece(pieces[random.nextInt(pieces.length)]);

			// check if same piece assigned
			if (players[0].getPlayerPiece().getType().equals(players[1].getPlayerPiece().getType())) {
				samePiece = true; // do loop again
			}
		} while (samePiece);

		// output information to players
		for (Player p : players) {
			System.out.println(p.getName() + ", your piece is the " + p.getPlayerPiece().getType());
		}
		System.out.println("-----------------------------------------");
		return players;
	}

	/**
	 * This method checks whether player's have the same name. If they do, it will
	 * append A to player 1's name and B to player 2's name
	 * 
	 * @param players
	 * @return
	 */
	public Player[] sameNameCheck(Player[] players) {
		if (players[0].getName().equals(players[1].getName())) {
			System.out.println("You have chosen the same name!");
			players[0].setName(players[0].getName() + " A"); // append A to player 1's name
			System.out.println("Player 1 your name is now: " + players[0].getName());
			players[1].setName(players[1].getName() + " B"); // append B to player 2's name
			System.out.println("Player 2 your name is now: " + players[1].getName());
		}
		return players;
	}

	/**
	 * Each player rolls a die, and the one that rolls the higher number goes first
	 * 
	 * @param players
	 * @param die
	 * @param scanner
	 * @return the players in the correct order
	 */
	public Player[] rollAndCalculatePlayOrder(Player[] players, Die die, Scanner scanner) {
		int p1Roll = 0;
		int p2Roll = 0;
		boolean tie = false;

		System.out.println("Please roll to determine who goes first!");

		do {
			System.out.println(players[0].getName() + ", ready to roll? Hit enter to proceed ");
			scanner.nextLine(); // waits to hit enter
			p1Roll = die.rollDie();
			System.out.println("You rolled " + p1Roll);

			System.out.println(players[1].getName() + ", ready to roll? Hit enter to proceed ");
			scanner.nextLine(); // waits to hit enter
			p2Roll = die.rollDie();
			System.out.println("You rolled " + p2Roll);

			if (p1Roll > p2Roll) {
				System.out.println(players[0].getName() + ", you go first.");
				// don't change order
				tie = false; // break out of while loop

			} else if (p2Roll > p1Roll) {
				System.out.println(players[1].getName() + ", you go first.");
				// reverse order - only works for array of size2
				Player temp;
				temp = players[0];
				players[0] = players[1];
				players[1] = temp;

				tie = false; // break out of while loop

			} else {
				System.out.println("Looks like it's a tie. Please roll again");
				tie = true; // roll again
			}
		} while (tie);

		return players; // in correct order
	}

	/**
	 * Displays the game's description
	 * 
	 * @param players
	 */
	public void displayGameDescription(Player[] players) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("Welcome " + players[0].getName() + " and " + players[1].getName());
		System.out.println("The year is 1845." + "\r\n"
				+ "The munificent British government has passed a bill to establish three university colleges in Ireland, to be founded at Belfast, Galway, and Cork, for the instruction of Irish students in\r\n"
				+ "‘arts, law, physic, and other useful learning [like Software Development]’. \r\n");

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(
				"A ‘rural’ site adjoining Botanic Gardens in Belfast, on the estate owned by indomitable Ulster landlord Miss Jane Gregg, has been earmarked as the location of the new Queen’s College. \r\n");
		System.out.println(players[0].getName() + " and " + players[1].getName()
				+ ", you are employees of the Irish Board of Works, and your task is to purchase five sites from Miss Gregg’s estate, on which shall be built the following college Schools:\r\n");
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Logic & Metaphysics\r\n" + "Natural Philosophy \r\n" + "Zoology & Botany\r\n"
				+ "Agriculture\r\n" + "Medicine\r\n");
		System.out.println(
				"Once the sites have been acquired, they can be developed further (but at a price!) : each site able to hold up to three reading rooms, and upon erection of the reading rooms,\r\n"
						+ "a grand lecture theatre shall be built.\r\n");

		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(
				"If an opponent drops in on one of your sites, you are entitled to charge them a fee. The aim of the game is to bankrupt your opponent!");
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\r\n" + "Both players will start with £100 of public money. Use it wisely.");
		System.out.println(
				"If at any point you want to quit (perhaps when tired of dealing with Miss Gregg and her army of solicitors, or that upstart architect Charles Lanyon),\r\n"
						+ " you may select ‘q’ when asked if you are “ready to roll”, and confirm your exit from the game.\r\n"
						+ "");
		System.out.println("----------------------------------------------------------------");
	}

	/**
	 * Confirms both players ready to play
	 * 
	 * @param players
	 * @param scanner
	 * @return
	 */
	public boolean readyToPlay(Player[] players, Scanner scanner) {

		System.out.println(
				players[0].getName() + " and " + players[1].getName() + ", are you ready to play? Hit enter to begin ");
		scanner.nextLine(); // get input
		System.out.println("Thanks!");

		return true;
	}

}
