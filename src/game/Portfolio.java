package game;

/**
 * This class represents a player's portfolio of title deeds
 * @author Helen
 *
 */

import java.util.ArrayList;

public class Portfolio {
	/**
	 * An array list that stores the title deeds a player owns
	 */
	private ArrayList<TitleDeed> deedPortfolio;

	/**
	 * Default constructor
	 */
	public Portfolio() {
	}

	/**
	 * Constructor with arguments
	 * 
	 * @param portfolio
	 */
	public Portfolio(ArrayList<TitleDeed> deedPortfolio) {
		this.setDeedPortfolio(deedPortfolio);
	}

	/**
	 * @return the portfolio
	 */
	public ArrayList<TitleDeed> getDeedPortfolio() {
		return deedPortfolio;
	}

	/**
	 * @param portfolio
	 *            the portfolio to set
	 */
	public void setDeedPortfolio(ArrayList<TitleDeed> deedPortfolio) {
		this.deedPortfolio = deedPortfolio;
	}

	/**
	 * Add a titleDeed to your deedPortfolio
	 * 
	 * @param titleDeed
	 */
	public void addTitleDeedToPortfolio(TitleDeed titleDeed) {
		getDeedPortfolio().add(titleDeed);
	}

	/**
	 * Check if a player's portfolio includes a title deed for a specific siteSquare
	 * 
	 * @param locationToCheck
	 * @return
	 */
	public boolean checkInPortfolio(SiteSquare locationToCheck) {
		if (locationToCheck.isOwned()) {
			for (TitleDeed td : deedPortfolio) {
				if (td.getLocation() == locationToCheck) {
					return true;
				}
			}
			// if we went through whole loop and did not find a match, the player does not
			// own it
			return false;
		} else { // not owned, so not in player's portfolio
			return false;
		}
	}

	/**
	 * Displays all the names of the properties in a player's portfolio
	 */
	public void displayPortfolio() {
		for (TitleDeed td : deedPortfolio) {
			System.out.println("[" + td.getLocation().getSquareName() + "]" + " from group = "
					+ td.getLocation().getGroup() + ": Currently " + td.getBaseItemCount() + " Reading Rooms and "
					+ td.getSpecialItemCount() + " Lecture Theatre");
		}
	}

	/**
	 * Method checks if players portfolio has a monopoly for the given group
	 * 
	 * @param board
	 * @param group
	 * @return
	 */
	public boolean checkMonopoly(Board board, Group group) {
		// loop through the array
		for (Square square : board.getSquareLocations()) {
			// if square is a SiteSquare
			if (square instanceof SiteSquare) {
				SiteSquare siteSquare = (SiteSquare) square;
				// if square belongs to group
				if (siteSquare.getGroup().equals(group)) {
					// then checkInPortfolio
					if (!checkInPortfolio(siteSquare)) {
						// return false as found a site that was not within this portfolio
						return false;
					}
				}
			}
		}
		// return true as no square for group was found that was not in this portfolio
		return true;
	}

	/**
	 * This method calculates and returns an array list of title deeds that the
	 * player holds when they have a monopoly for the respective group and space to
	 * develop more item(s).
	 * 
	 * @param board
	 * @return
	 */
	public ArrayList<TitleDeed> calculateBuildableSites(Board board) {
		ArrayList<TitleDeed> buildableSites = new ArrayList<TitleDeed>();

		for (Group group : Group.values()) {
			if (checkMonopoly(board, group)) { // if we have a monopoly for that group
				for (TitleDeed td : deedPortfolio) { // we go through our portfolio looking for that colour
					if (td.getLocation().getGroup().equals(group)) {
						if (td.calculateSpaceForBaseItems() > 0 || td.calculateSpaceForSpecialItems() > 0) {
							buildableSites.add(td);
						}
					}
				}
			}
		}

		return buildableSites;
	}

}
