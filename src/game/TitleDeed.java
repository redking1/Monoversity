package game;

/**
 * This class represents a receipt that states a player owns a site, and what
 * properties have been developed on the site. It holds the methods for
 * calculating the rent, baseItem and specialItem cost
 * 
 * @author Helen
 *
 */
public class TitleDeed {
	/**
	 * The maximum number of base items allowed to be developed on a siteSquare that
	 * the titleDeed holds
	 */
	private static final int MAX_BASE_ITEMS = 3;

	/**
	 * The maximum number of special items allowed to be developed on a siteSquare
	 * that the titleDeed holds
	 */
	private static final int MAX_SPECIAL_ITEMS = 1;

	/**
	 * Constant for calculation the ratio of rent for each site
	 */
	private static final double RENT_RATIO = 0.25;

	/**
	 * Constant to define the ratio for baseItemCost
	 */
	private static final double BASEITEMCOST_RATIO = 1;

	/**
	 * Constant to define the radio for specialItemCost
	 */
	private static final double SPECIALITEMCOST_RATIO = 1;

	/**
	 * Where the site that the title deed owns is located
	 */
	private SiteSquare location;

	/**
	 * Which player holds the title deed
	 */
	private Player Owner;

	/**
	 * How many base items have been developed on the siteSquare that the titleDeed
	 * holds
	 */
	private int baseItemCount = 0; // can only be 0, 1, 2 or 3

	/**
	 * How many special items have been developed on the siteSquare that the
	 * titleDeed holds
	 */
	private int specialItemCount = 0; // can only be 0 or 1

	/**
	 * Default constructor
	 */
	public TitleDeed() {
	}

	/**
	 * Constructor with arguments
	 * 
	 * @param location
	 * @param owner
	 * @param baseItemCount
	 * @param specialItemCount
	 */
	public TitleDeed(SiteSquare location, Player owner, int baseItemCount, int specialItemCount) {
		this.setLocation(location);
		this.setOwner(owner);
		this.setBaseItemCount(baseItemCount);
		this.setSpecialItemCount(specialItemCount);
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return Owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(Player owner) {
		Owner = owner;
	}

	/**
	 * @return the baseItemCount
	 */
	public int getBaseItemCount() {
		return baseItemCount;
	}

	/**
	 * Validation to ensure that the baseItemCount is not negative and does not
	 * exceed MAX_BASE_ITEMS
	 * 
	 * @param baseItemCount
	 *            the baseItemCount to set
	 */
	public void setBaseItemCount(int baseItemCount) throws IllegalArgumentException {
		if (baseItemCount >= 0 && baseItemCount <= MAX_BASE_ITEMS) {
			this.baseItemCount = baseItemCount;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return the specialItemCount
	 */
	public int getSpecialItemCount() {
		return specialItemCount;
	}

	/**
	 * Validation to ensure that the specialItemCount is not negative and does not
	 * exceed MAX_SPECIAL_ITEMS
	 * 
	 * @param specialItemCount
	 *            the specialItemCount to set
	 */
	public void setSpecialItemCount(int specialItemCount) throws IllegalArgumentException {
		if (specialItemCount >= 0 && specialItemCount <= MAX_SPECIAL_ITEMS) {
			this.specialItemCount = specialItemCount;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return the location
	 */
	public SiteSquare getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(SiteSquare location) {
		this.location = location;
	}

	/**
	 * This method calculates the rent that another player landing on the siteSquare
	 * owes the holder of the TitleDeed based on number of special items and base
	 * items
	 * 
	 * @return
	 */
	public double calculateRent() {
		double ratio = RENT_RATIO;

		double rent = 0; // initialise as zero
		double sitePrice = location.getSitePrice(); // get the site price from the square
		rent = ratio * sitePrice;
		if (baseItemCount > 0) {
			rent += (baseItemCount * sitePrice); // checks if has base item and calculates rent
		}
		if (specialItemCount > 0) { // checks if has special item and works out rent
			rent += sitePrice;
		}
		return rent;
	}

	/**
	 * This method finds the sitePrice from the location and uses a ratio (currently
	 * set to 1) to calculate the cost to build a base item on this property
	 * 
	 * @return
	 */
	public double calculateBaseItemCost() {
		double baseItemRatio = BASEITEMCOST_RATIO;
		double baseItemCost = 0; // initialise as zero
		double sitePrice = location.getSitePrice(); // get the site price from the square

		baseItemCost = baseItemRatio * sitePrice;
		return baseItemCost;
	}

	/**
	 * This method finds the sitePrice from the location and uses a ratio (currently
	 * set to 1) to calculate the cost to build a special item on this property
	 * 
	 * @return
	 */
	public double calculateSpecialItemCost() {
		double specialItemRatio = SPECIALITEMCOST_RATIO;
		double specialItemCost = 0;// initialise as zero
		double sitePrice = location.getSitePrice(); // get the site price from the square

		specialItemCost = specialItemRatio * sitePrice;
		return specialItemCost;
	}

	/**
	 * This method calculates how many base items a player can add to the siteSquare
	 * that the titleDeed holds
	 * 
	 * @return
	 */
	public int calculateSpaceForBaseItems() {
		return MAX_BASE_ITEMS - baseItemCount;
	}

	/**
	 * This method calculates how many special items a player can add to the
	 * siteSquare that the titleDeed holds
	 * 
	 * @return
	 */
	public int calculateSpaceForSpecialItems() {
		return MAX_SPECIAL_ITEMS - specialItemCount;
	}

}
