package game;

import java.util.Random;

/**
 * 
 * @author Chris
 *
 */
public class Die {

	/**
	 * Constant: Die will always have 6 sides
	 */
	public static final int SIDES = 6;

	/**
	 * Default Constructor as no args required
	 */
	public Die() {

	}

	/**
	 * Randomly returns an int between 1 & 6 inclusive
	 * 
	 * @return dieValue
	 */
	public int rollDie() {

		Random ran = new Random();
		int dieValue = ran.nextInt(SIDES) + 1;
		return dieValue;

	}

}
