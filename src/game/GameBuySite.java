package game;

/**
 * This class contains methods that enables a user to buy a site
 * 
 * @author Helen
 *
 */
public class GameBuySite {

	/**
	 * Default constructor
	 */
	public GameBuySite() {
	}

	/**
	 * This method attempts to pay for a site, and if successful, creates a title
	 * deed and adds it to the player's portfolio
	 * 
	 * 
	 * @param player
	 * @param siteToPurchase
	 */
	public void buySite(Player player, SiteSquare siteToPurchase) {
		// if payment goes through successfully
		if (player.getPlayerAccount().payForSite(siteToPurchase)) {
			// creates new title deed and adds to portfolio
			TitleDeed titleDeed = new TitleDeed(siteToPurchase, player, 0, 0);
			player.getPlayerPortfolio().addTitleDeedToPortfolio(titleDeed);
			siteToPurchase.setOwned(true);
			// outputs information to user
			System.out.printf("%s now owns %s and now has £%.2f in their account.\n", player.getName(),
					siteToPurchase.getSquareName(), player.getPlayerAccount().getBalance());
		}
	}
}
