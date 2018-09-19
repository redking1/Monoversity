package game;

/**
 * This class represents a GoSquare, a square which does not hold a property but
 * gives prize money to a player that either lands on it or moves over it during
 * a turn.
 * 
 * @author Helen
 *
 */
public class GoSquare extends Square {
	/**
	 * The prizeMoney one receives for either landing on or moving over the GoSquare
	 */
	private double prizeMoney;

	/**
	 * Default constructor
	 */
	public GoSquare() {
	}

	/**
	 * Constructor with arguments
	 * 
	 * @param squareName
	 */
	public GoSquare(String squareName, double prizeMoney) {
		super(squareName);
		this.setPrizeMoney(prizeMoney);
	}

	/**
	 * @return the prizeMoney
	 */
	public double getPrizeMoney() {
		return prizeMoney;
	}

	/**
	 * @param prizeMoney
	 *            the prizeMoney to set
	 */
	public void setPrizeMoney(double prizeMoney) {
		this.prizeMoney = prizeMoney;
	}

}
