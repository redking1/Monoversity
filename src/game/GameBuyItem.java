package game;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This method holds all the methods involved in buying an item
 * 
 * @author Helen
 *
 */
public class GameBuyItem {

	/**
	 * Default constructor
	 */
	public GameBuyItem() {
	}

	/**
	 * Checks if a player has /a/ monopoly
	 * 
	 * @param player
	 * @param board
	 * @return
	 */
	public boolean CheckIfHasMonopoly(Player player, Board board) {
		if (player.getPlayerPortfolio().checkMonopoly(board, Group.RED)
				|| player.getPlayerPortfolio().checkMonopoly(board, Group.BLUE)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Enables a player to buy items on titleDeeds with development space and in
	 * groups where the player has a monopoly
	 * 
	 * @param player
	 * @param board
	 * @param scanner
	 */
	public void developOnSite(Player player, Board board, Scanner scanner) {
		if (CheckIfHasMonopoly(player, board)) {

			ArrayList<TitleDeed> buildableSites = new ArrayList<TitleDeed>();
			boolean purchasing = true;

			while (purchasing) {
				// using -1 instead of 0 to differentiate a zero being input
				int input = -1;

				buildableSites = player.getPlayerPortfolio().calculateBuildableSites(board);
				if (buildableSites.size() == 0) { // if empty, we break out of rest of loop
					System.out.println("Sorry, you have no space to develop on any of your properties.");
					purchasing = false;
					break;
				}

				displayBuildableSitesAndDevelopmentState(buildableSites);
				TitleDeed currentBuild = buildableSites.get(0);

				// output options
				System.out.println(
						"Which site would you like to buy for? Enter number of site or '0' to quit buying items");
				if (scanner.hasNextInt()) {
					input = scanner.nextInt(); // get input
					if (input < 0) {
						System.out.println("Please enter a number greater than or equal to 0.");
						continue; // loop again
					}
				} else {
					scanner.next(); // consume the non-int value
					System.out.println("Please enter a number greater than or equal to 0.");
					continue; // loop again
				}

				if (input == 0) { // chosen to quit
					purchasing = false;
					break;
				} else if (input > 0 && input <= buildableSites.size()) { // in range
					// setting currentBuild as the option that matches their input
					for (int index = 0; index < buildableSites.size(); index++) { // !!what if number entered not an
																					// index match?
						if (input == (index + 1)) { // checking that input matches some index
							currentBuild = buildableSites.get(index);
						}
					}
				} else { // outside of range
					System.out.println("Number out of range.");
					continue; // loop again
				}

				System.out.println("You have selected " + currentBuild.getLocation().getSquareName());
				String response;

				// check if they have space to add items
				if (currentBuild.calculateSpaceForBaseItems() > 0) {
					int buildableBaseItems = currentBuild.calculateSpaceForBaseItems();
					int itemsToBuild = -1; // always reset - using -1 instead of 0 to differentiate a zero being input

					// do they want to build
					System.out.printf("You have room for %d reading rooms. Each room costs £%.2f. \n",
							buildableBaseItems, currentBuild.calculateBaseItemCost());

					// while they want to build too many items or value entered is invalid or
					// negative
					while (itemsToBuild < 0 || itemsToBuild > buildableBaseItems) {
						System.out.println("How many items would you like to add?");
						if (scanner.hasNextInt()) {
							itemsToBuild = scanner.nextInt();
							if (itemsToBuild > buildableBaseItems) {
								System.out.println("Sorry, not enough room!");
							} else if (itemsToBuild < 0) {
								System.out.println("Please enter a positive number.");
							}
						} else {
							scanner.next(); // consume the non-int value
							System.out.println("Please enter a valid number.");
						}
					}
					// once they break out of that loop - i.e. have a good number of itemsToBuild
					if (itemsToBuild != 0) {
						payForBaseItem(player, currentBuild, itemsToBuild);
					} else {
						System.out.println("No items added. \n");
					}
					// check if they have space to add special items
				} else if (currentBuild.calculateSpaceForSpecialItems() > 0) {
					int buildableSpecialItems = currentBuild.calculateSpaceForSpecialItems();
					boolean validResponse = false;

					// do they want to build
					while (!validResponse) {
						System.out.printf("You have room for %d lecture theatre. Each room costs £%.2f. \n",
								buildableSpecialItems, currentBuild.calculateSpecialItemCost());
						System.out.print("Would you like to add this? y/n");

						scanner.nextLine(); // fix scanner bug
						response = scanner.nextLine();

						if (response.equalsIgnoreCase("y")) {
							// build item
							payForSpecialItem(player, currentBuild);
							validResponse = true;
						} else if (response.equalsIgnoreCase("n")) {
							// break out of loop
							System.out.println("Fair enough");
							validResponse = true;
						} else {
							// try again
							System.out.println("Sorry - could not read input.");
						}
					}
				} else {
					// something went wrong
					System.out.println(
							"Sorry, but that site has no space for any more reading rooms or lecture theaters.");
				}

				// purchasing = false;
			}
		} else {
			System.out.println("Sorry you have no monopolies so you cannot develop on any sites.");
		}
	}

	/**
	 * Displays an array list of buildableSites
	 * 
	 * @param buildableSites
	 *            - arraylist of titleDeeds
	 */
	public void displayBuildableSitesAndDevelopmentState(ArrayList<TitleDeed> buildableSites) {
		System.out.println("These are the sites you can develop");
		for (int index = 0; index < buildableSites.size(); index++) {
			TitleDeed td = buildableSites.get(index);
			System.out.println((index + 1) + ". " + td.getLocation().getSquareName() + ": Currently "
					+ td.getBaseItemCount() + " Reading Rooms and " + td.getSpecialItemCount() + " Lecture Theatre");
		}

		System.out.println(); // formatting
	}

	/**
	 * This method pays for a number of base items, provided that the player has
	 * siteToDevelop and adequate funds. It then amends the title deed accordingly.
	 * 
	 * @param player
	 * @param siteToDevelop
	 * @param numberOfItems
	 */
	public void payForBaseItem(Player player, TitleDeed siteToDevelop, int numberOfItems) {
		double costOfItems = siteToDevelop.calculateBaseItemCost() * numberOfItems;
		if (player.getPlayerAccount().getBalance() > costOfItems) { // check if you have enough money
			// withdraw money
			player.getPlayerAccount().withdrawFromBalance(costOfItems);
			// amend title deed
			int finalBaseItemCount = siteToDevelop.getBaseItemCount() + numberOfItems;
			siteToDevelop.setBaseItemCount(finalBaseItemCount);
			System.out.println("Item(s) purchased!\n");
			System.out.printf("Your balance is now £%.2f \n", player.getPlayerAccount().getBalance());
		} else {
			System.out.println("Sorry, you can't afford this!");
		}
		System.out.println(); // formatting

	}

	/**
	 * This method pays for a special item, provided that the player has
	 * siteToDevelop and adequate funds. It then amends the title deed accordingly.
	 * 
	 * @param player
	 * @param siteToDevelop
	 */
	public void payForSpecialItem(Player player, TitleDeed siteToDevelop) {
		double costOfItem = siteToDevelop.calculateSpecialItemCost();
		if (player.getPlayerAccount().getBalance() > costOfItem) {
			// withdraw money
			player.getPlayerAccount().withdrawFromBalance(costOfItem);
			// amend title deed
			int finalSpecialItemCount = siteToDevelop.getSpecialItemCount() + 1;
			siteToDevelop.setSpecialItemCount(finalSpecialItemCount);
			System.out.println("Item purchased!\n");
			System.out.printf("Your balance is now £%.2f \n", player.getPlayerAccount().getBalance());
		} else {
			System.out.println("Sorry, you can't afford this!");
		}
		System.out.println(); // formatting

	}

}
