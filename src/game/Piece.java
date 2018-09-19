package game;

/**
 * This class represents a piece owned by a player on the monoversity board
 * 
 * @author Helen
 *
 */
public class Piece {
	/**
	 * The type of piece e.g. hat, car
	 */
	private String type; 

	/**
	 * The currentPosition of the piece on the board, represented as the index of
	 * the squares in the board array
	 */
	private int currentPosition = 0; // default to passGo square
	
	/**
	 * Default constructor
	 */
	public Piece() {
		
	}

	/**
	 * Constructor with arguments
	 * 
	 * @param type
	 * @param currentPosition
	 */
	public Piece(String type, int currentPosition) {
		this.setType(type);
		this.setCurrentPosition(currentPosition);
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type
	 * the type to set;
	 * Rules: Type cannot be null and must be between 1 & 20 letters long
	 * 
	 */
	public void setType(String type) throws IllegalArgumentException{
		if (type == null || type.trim().length() == 0 || type.length() > 20) { 
			throw new IllegalArgumentException();
		} else {
			this.type = type;
		}
	}

	/**
	 * @return the currentPosition
	 */
	public int getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @param currentPosition
	 *            the currentPosition to set
	 * Must be more than zero
	 */
	public void setCurrentPosition(int currentPosition) throws IllegalArgumentException{
		if (currentPosition >=0 ) {
			this.currentPosition = currentPosition;
		} else {
			throw new IllegalArgumentException();
		}
	}


	/**
	 * Takes a board and a dieValue and changes the currentPosition of the piece
	 * accordingly
	 * 
	 * @param board
	 * @param dieValue
	 */
	public void move(Board board, int dieValue) {
		int newPosition;
		newPosition = board.getNewPosition(getCurrentPosition(), dieValue);
		setCurrentPosition(newPosition);
	}
	
	/**
	 * Takes a board and a dieValue and returns whether or not the piece has passed go
	 * @param board
	 * @param dieValue
	 * @return
	 */
	public boolean passedGo(Board board, int dieValue) {
		return board.passedGo(getCurrentPosition(), dieValue);
	}
}
