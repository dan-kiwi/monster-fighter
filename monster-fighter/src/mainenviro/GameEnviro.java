package mainenviro;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

import items.Items;
import monster.*;


/**
 * The Class GameEnviro. The main class that handles all the game machanics
 */
public class GameEnviro {
	

	private static final int baseScoreForKill = 100;
	private static final double easyMonster = 0.85;
	private static final double easyGold = 1.5;
	private static final int easyUserGold = 300;
	private static final double hardMonster = 1.15;
	private static final double hardGold = 0.85;
	private static final int hardUserGold = 150;
	private static final String[] masterMonsterList = 
		{"Dragon", "Gnome", "Goblin", "Griffin", "Imp", "Unicorn"};
	
	private int gameDay = 1;
	private String userGameName;
	private int maxGameDays;
	private String gameDifficulty;
	private double monsterDifficulty;
	private double goldDifficulty;
	private int userGoldAmount;
	private int userGameScore = 0;
	private int userMonsterKills = 0;
	private List<Monster> userMonsterList = new ArrayList<Monster>();
	private List<Items> userItemList = new ArrayList<Items>();
	private Shop userGameShop = new Shop();
	private RandomEvent randEvent = new RandomEvent(masterMonsterList, userMonsterList);
	private Battle battle = new Battle(this);

	
	/** -----------------------------Getters/Setters/Changers----------------------------------*/
	
	/**
	 * Gets the max game days.
	 *
	 * @return the maxGameDays
	 */
	public int getMaxGameDays() {
		return maxGameDays;
	}
	
	/**
	 * Set maxGameDays.
	 *
	 * @param tempChange the new max game days
	 */
	public void setMaxGameDays(int tempChange) {
		maxGameDays = tempChange;
	}
	
	/**
	 * Gets the game day.
	 *
	 * @return the gameDay
	 */
	public int getGameDay() {
		return gameDay;
	}
	
	/**
	 * Changes the game day 
	 * 
	 * @param tempChange, change in game day
	 */
	public void addGameDay(int tempChange) {
		gameDay += tempChange;
	}
	
	/**
	 * Gets the user game name.
	 *
	 * @return userGameName, the name of the game
	 */
	public String getUserGameName() {
		return userGameName;
	}
	
	/**
	 * Sets the game name.
	 *
	 * @param tempChange the new user game name
	 */
	public void setUserGameName(String tempChange) {
		userGameName = tempChange;
	}
	
	/**
	 * Gets the game difficulty.
	 *
	 * @return the game difficulty
	 */
	public String getGameDifficulty() {
		return gameDifficulty;
	}

	/**
	 * Sets the game difficulty.
	 *
	 * @param gameDifficulty the new game difficulty
	 */
	public void setGameDifficulty(String gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
	}

	/**
	 * Gets the user gold amount.
	 *
	 * @return the user gold amount
	 */
	public int getUserGoldAmount() {
		return userGoldAmount;
	}
	
	/**
	 * Adds the user gold amount.
	 *
	 * @param tempChange, the change amount
	 */
	public void addUserGoldAmount(int tempChange) {
		userGoldAmount += tempChange;
	}
	
	/**
	 * Gets the user game score.
	 *
	 * @return the user game score
	 */
	public int getUserGameScore() {
		return userGameScore;
	}
	
	/**
	 * Adds the user game score.
	 *
	 * @param tempChange the temp change
	 */
	public void addUserGameScore(int tempChange) {
		userGameScore += tempChange;
	}
	
	/**
	 * Gets the user monster list.
	 *
	 * @return the user monster list
	 */
	public List<Monster> getUserMonsterList() {
		return userMonsterList;
	}
	
	/**
	 * Adds a monster to the user monster list.
	 *
	 * @param tempMonster, the new monster
	 */
	public void addMonster(Monster tempMonster) {
		if (userMonsterList.size() < 4) {
			userMonsterList.add(tempMonster);
		} else {
			throw new IndexOutOfBoundsException("Cannot have more than four monsters");
		}
	}
	
	/**
	 * Gets the user item list.
	 *
	 * @return the userItemList
	 */
	public List<Items> getUserItemList() {
		return userItemList;
	}
	
	/**
	 * Adds the item.
	 *
	 * @param tempItem the temp item
	 */
	public void addItem(Items tempItem) {
		userItemList.add(tempItem);
	}

	/**
	 * Gets the master monster list.
	 *
	 * @return the master monster list
	 */
	public static String[] getMasterMonsterList() {
		return masterMonsterList;
	}

	/**
	 * Gets the gold difficulty.
	 *
	 * @return the goldDifficulty
	 */
	public double getGoldDifficulty() {
		return goldDifficulty;
	}
	
	/**
	 * Gets the monster difficulty.
	 *
	 * @return the monster difficulty
	 */
	public double getMonsterDifficulty() {
		return monsterDifficulty;
	}
	
	/**
	 * Change user gold amount.
	 *
	 * @param amount the amount
	 */
	public void changeUserGoldAmount(int amount) {
		this.userGoldAmount += amount;
	}
	
	/**
	 * Gets the user game shop.
	 *
	 * @return the user game shop
	 */
	public Shop getUserGameShop() {
		return userGameShop;
	}
	
	/**
	 * Gets the users daily battles.
	 *
	 * @return the user daily battles
	 */
	public Battle getBattle() {
		return battle;
	}
	
	/**
	 * Sets a new daily Battle Object.
	 *
	 *@param tempBattle, a new battle object to replace the old one
	 */
	public void setBattle(Battle tempBattle) {
		battle = tempBattle;
	}
	
	/**
	 * Gets the users random event object.
	 *
	 * @return the random event object
	 */
	public RandomEvent getRandEvent() {
		return randEvent;
	}
	
	/**
	 * Gets the users total Monster kills.
	 *
	 * @return the amount of user kills.
	 */
	public int getUserMonsterKills() {
		return userMonsterKills;
	}
	
	
	//---------------------------------------------------------------------------------------------

	/**
	 * Method only called at the start
	 * Sets the user's difficulty.
	 *
	 * @param selection, the user's difficulty choice
	 */
	public void starterSetDifficulty(String selection) {
		setGameDifficulty(selection);
		if (selection.equals("Easy")) {
			monsterDifficulty = easyMonster;
			goldDifficulty = easyGold;
			userGoldAmount = easyUserGold;
		} else if (selection.equals("Hard")) {
			monsterDifficulty = hardMonster;
			goldDifficulty = hardGold;
			userGoldAmount = hardUserGold;
		}
	}
	
	
	
	/**
	 * Reset all monster statistics.
	 */
	public void resetMonsterStats() {
		if (userMonsterList.size() > 0) {
			for (Monster monster : userMonsterList) {
				monster.resetMonsterStats();
			}
		}
	}
	
	/**
	 * When a monster's kills in battle, adds to the game score and monster's kills stats.
	 */
	public void addScoreForMonsterKill() {
		userGameScore = (int) (baseScoreForKill * monsterDifficulty);
		userMonsterKills += 1;
	}
	
	/**
	 * When a monster's kills in battle, adds to the game score and monster's kills stats.
	 * @param monsterName, a name of a monster which will be returned
	 * @return a new monster depending on the string parameter
	 */
	public Monster getNewMonster(String monsterName) {
		if (monsterName == "Imp") {
			return new Imp();
		} else if (monsterName == "Unicorn") {
			return new Unicorn();
		} else if (monsterName == "Dragon") {
			return new Dragon();
		} else if (monsterName == "Goblin") {
			return new Goblin();
		} else if (monsterName == "Griffin") {
			return new Griffin();
		} else if (monsterName == "Gnome") {
			return new Gnome();
		} else {
			throw new MissingFormatArgumentException("Invalid Monster Name");
		}
	}

	
	/**
	 * Occurs when the user choose to progress to the next day.
	 * Random event occurs by calling randEvent
	 * Outputs which game day is over
	 * Resets shop stock and monster statistics
	 * Adds one to game day
	 */
	public void nightTime() {
		userGameShop.resetShopStock();
		battle = new Battle(this); //creates a new battle object when the user sleeps for the next day
		resetMonsterStats();
		gameDay += 1;
	}
	
	/**
	 * @param salePrice, boolean to state whether the method should output the sale price for each item
	 * @return a string all of the user's items and their statistics line by line
	 */
	public String userDisplayItems(boolean salePrice) {
		int counter = 1;
		String userItems = "";
		for (Items item : this.getUserItemList()) {
			userItems += counter + ": " + item.getItemName() + " - " + item.getItemDescription() + "\n";
			if (salePrice) {
				userItems += "Sell For: " + item.getItemSellPrice() + " Gold\n";
			}
			counter += 1;
		}
		return userItems;
	}
	
	/**
	 * Outputs all of the user's monsters and their statistics line by line
	 *
	 * @param salePrice, boolean to state whether the method should output the sale price for each monster
	 * @return a string detailing what can be sold for how much
	 */
	public String userDisplayMonsters(boolean salePrice) {
		int counter = 1;
		String userMonsters = "";
		for (Monster userMonster : this.getUserMonsterList()) {
			userMonsters += counter + ": " + userMonster.toString() + "\n";
			if (salePrice) {
				userMonsters += "Sell For: " + userMonster.getMonsterSellPrice() + " Gold\n";
			}
			counter += 1;
		}
		return userMonsters;
	}
}
