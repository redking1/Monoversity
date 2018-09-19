package game;

/**
 * This class represents a square on the Monoversity board
 * 
 * @author Helen
 *
 */
public class Square {

	/**
	 * The name of the square
	 */
	private String squareName;

	/**
	 * Default constructor
	 */
	public Square() {
	}

	/**
	 * Constructor with arguments
	 * 
	 * @param squareName
	 */
	public Square(String squareName) {
		setSquareName(squareName);
	}

	/**
	 * @return the squareType
	 */
	public String getSquareName() {
		return squareName;
	}

	/**
	 * Validation to ensure square name cannot be null or have whitespace or over 50
	 * characters
	 * 
	 * @param squareName
	 *            the squareName to set
	 */
	public void setSquareName(String squareName) throws IllegalArgumentException {
		if (squareName == null || squareName.trim().length() == 0 || squareName.length() > 50) {
			throw new IllegalArgumentException();
		} else {
			this.squareName = squareName;
		}
	}
}
