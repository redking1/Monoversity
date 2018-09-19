package game;

/**
 * This class represents the board that Monoversity is played on
 * 
 * @author Helen
 *
 */
public class Board {
	/**
	 * This board holds the positions of the squares relative to each other
	 */
	private Square[] squareLocations;

	/**
	 * Default constructor
	 */
	public Board() {
	}

	/**
	 * Constructor with arguments
	 * 
	 * @param squareLocations
	 */
	public Board(Square[] squareLocations) {
		this.setSquareLocations(squareLocations);
	}

	/**
	 * @return the squareLocations
	 */
	public Square[] getSquareLocations() {
		return squareLocations;
	}

	/**
	 * @param squareLocations
	 *            the squareLocations to set
	 */
	public void setSquareLocations(Square[] squareLocations) {
		this.squareLocations = squareLocations;
	}

	/**
	 * This method takes a position of a piece and a dieValue and calculates the
	 * index of the location of the piece after moving
	 * 
	 * @param currentPosition
	 * @param dieValue
	 * @return
	 */
	public int getNewPosition(int currentPosition, int dieValue) {
		int boardSize;
		boardSize = squareLocations.length;

		currentPosition = (currentPosition + dieValue) % boardSize;

		return currentPosition;
	}

	/**
	 * This checks whether a piece passes go or not during a move
	 * 
	 * @param currentPosition
	 * @param dieValue
	 * @return
	 */
	public boolean passedGo(int currentPosition, int dieValue) {
		int boardSize;
		boardSize = squareLocations.length;

		// boardSize - currentPosition = #squares behind go
		// if dieValue is more than this then they will pass go
		if (dieValue >= boardSize - currentPosition) {
			return true;
		} else {
			return false;
		}
	}

}
