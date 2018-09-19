package game;

import java.util.ArrayList;

/**
 * This class represents a player of the game Monoversity
 * 
 * @author Helen
 *
 */
public class Player {

	/**
	 * The name that the System calls the player
	 */
	private String name;

	/**
	 * The player's account
	 */
	private Account playerAccount;

	/**
	 * The player's portfolio of title deeds
	 */
	private Portfolio playerPortfolio;

	/**
	 * The player's piece
	 */
	private Piece playerPiece;

	/**
	 * Default constructor
	 */
	public Player() {
	}

	/**
	 * Constructor with arguments
	 * 
	 * @param name
	 * @param Piece
	 * @param OpeningBalance
	 */
	public Player(String name, Piece playerPiece, double openingBalance) {
		this.setName(name);
		this.setPlayerPiece(playerPiece);
		createAccount(openingBalance);
		createPortfolio(); // will be empty - doesn't take arguments
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Rules: Name cannot be null and must be between 1 & 20 letters long
	 * 
	 * @param name
	 *            the name to set;
	 * 
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (name == null || name.trim().length() == 0 || name.length() > 20) {
			throw new IllegalArgumentException();
		} else {
			this.name = name;
		}
	}

	/**
	 * @return the playerAccount
	 */
	public Account getPlayerAccount() {
		return playerAccount;
	}

	/**
	 * @param playerAccount
	 *            the playerAccount to set
	 */
	public void setPlayerAccount(Account playerAccount) {
		this.playerAccount = playerAccount;
	}

	/**
	 * @return the playerPortfolio
	 */
	public Portfolio getPlayerPortfolio() {
		return playerPortfolio;
	}

	/**
	 * @param playerPortfolio
	 *            the playerPortfolio to set
	 */
	public void setPlayerPortfolio(Portfolio playerPortfolio) {
		this.playerPortfolio = playerPortfolio;
	}

	/**
	 * @return the playerPiece
	 */
	public Piece getPlayerPiece() {
		return playerPiece;
	}

	/**
	 * @param playerPiece
	 *            the playerPiece to set
	 */
	public void setPlayerPiece(Piece playerPiece) {
		this.playerPiece = playerPiece;
	}

	/**
	 * This creates a balance with a hard-coded value at the start of the game
	 * 
	 * @param balance
	 */
	public void createAccount(double balance) {
		Account account = new Account(balance);
		setPlayerAccount(account);
	}

	/**
	 * Creates an empty portfolio at the start of the game
	 */
	public void createPortfolio() {
		ArrayList<TitleDeed> emptyPortfolio = new ArrayList<>(0); // empty array list
		Portfolio portfolio = new Portfolio(emptyPortfolio);
		setPlayerPortfolio(portfolio);
	}

}
