/**
 * 
 */
package game;

/**
 * This class represents a square that contains a site
 * 
 * @author Helen
 *
 */
public class SiteSquare extends Square {

	/**
	 * The cost to purchase that site
	 */
	private double sitePrice;

	/**
	 * Either red or blue
	 */
	private Group group;

	/**
	 * If owned = true, some player owns the site, owned = false, the site is free
	 * to be purchased
	 */
	private boolean owned = false; // initialise all as not owned

	/**
	 * Default constructor
	 */
	public SiteSquare() {
	}

	/**
	 * Constructor with arguments
	 * 
	 * @param squareType
	 */
	public SiteSquare(String squareName, double sitePrice, Group group, boolean owned) {
		super(squareName);
		setSitePrice(sitePrice);
		this.setGroup(group);
		this.setOwned(owned);
	}

	/**
	 * @return the sitePrice
	 */
	public double getSitePrice() {
		return sitePrice;
	}

	/**
	 * Validation to ensure sitePrice cannot be less than 1 or more than 99
	 * 
	 * @param sitePrice
	 *            the sitePrice to set
	 */
	public void setSitePrice(double sitePrice) throws IllegalArgumentException {
		if (sitePrice <= 0 || sitePrice >= 100) {
			throw new IllegalArgumentException();
		} else {
			this.sitePrice = sitePrice;
		}

	}

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * @return the owned
	 */
	public boolean isOwned() {
		return owned;
	}

	/**
	 * @param owned
	 *            the owned to set
	 */
	public void setOwned(boolean owned) {
		this.owned = owned;
	}

}
