package game;

/**
 * This class creates the board and pieces that the game is played with.
 * 
 * @author Helen
 *
 */
public class GameCreator {
	/**
	 * The prize money a player receives for passing go
	 */
	public final double PRIZE_MONEY = 10.00;

	/**
	 * Default Constructor
	 */
	public GameCreator() {
	}

	/**
	 * Creates squares and returns them, based on their relative positions changed
	 * red1 to blue3
	 * 
	 * @return squareLocations
	 */

	public Square[] createSquares() {
		// create squares
		Square goSquare = new GoSquare("Pass Go", PRIZE_MONEY);
		Square blue1 = new SiteSquare("School of Logic & Metaphysics", 20.00, Group.BLUE, false);
		Square blue2 = new SiteSquare("School of Natural Philosophy", 25.00, Group.BLUE, false);
		Square blue3 = new SiteSquare("School of Zoology & Botany", 30.00, Group.BLUE, false);
		Square red1 = new SiteSquare("School of Agriculture", 35.00, Group.RED, false);
		Square red2 = new SiteSquare("School of Medicine", 40.00, Group.RED, false);

		// set relative positions
		Square[] squareLocations = { goSquare, blue1, blue2, blue3, red1, red2 };

		return squareLocations;

	}

	/**
	 * Adds squares, sorted by relative positions to the board
	 * 
	 * @param squareLocations
	 * @return
	 */
	public Board createBoard(Square[] squareLocations) {
		Board board = new Board();
		board.setSquareLocations(squareLocations);
		return board;
	}

	/**
	 * This creates an instance of the die
	 */
	public Die createDie() {
		Die die = new Die();
		return die;
	}

	/**
	 * This creates some pieces that the players can use
	 * 
	 * @return pieces
	 */
	public Piece[] createPieces() {
		Piece pieceA = new Piece("hat", 0);
		Piece pieceB = new Piece("wheelbarrow", 0);
		Piece pieceC = new Piece("boot", 0);
		Piece pieceD = new Piece("dog", 0);
		Piece pieceE = new Piece("ship", 0);
		Piece[] pieces = { pieceA, pieceB, pieceC, pieceD, pieceE };
		return pieces;
	}

}
