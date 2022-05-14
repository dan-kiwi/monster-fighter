package mainenviro;

import java.util.ArrayList;
import java.util.Collections;

import items.Berries;
import items.HealthPotion;
import items.Items;
import items.Shop;
import monster.*;


/**
 * The Class GameEnviro.
 */
public class GameEnviro {
	
	/** The Constant baseScoreForKill. */
	//Constants
	private static final int baseScoreForKill = 100;
	
	/** The Constant easyMonster. */
	private static final double easyMonster = 0.85;
	
	/** The Constant easyGold. */
	private static final double easyGold = 1.5;
	
	/** The Constant easyUserGold. */
	private static final int easyUserGold = 300;
	
	/** The Constant hardMonster. */
	private static final double hardMonster = 1.15;
	
	/** The Constant hardGold. */
	private static final double hardGold = 0.85;
	
	/** The Constant hardUserGold. */
	private static final int hardUserGold = 150;
	
	/** The master monster list. */
	private static ArrayList<Monster> masterMonsterList = new ArrayList<Monster>() {{
		add(new Dragon());
		add(new Gnome());
		add(new Goblin());
		add(new Griffin());
		add(new Imp());
		add(new Unicorn());
	}};
	
	/** The game day. */
	private int gameDay = 1;
	
	/** The user game name. */
	private String userGameName;
	
	/** The max game days. */
	private int maxGameDays;
	
	/** The game difficulty. */
	private String gameDifficulty;
	
	/** The monster difficulty. */
	private double monsterDifficulty;
	
	/** The gold difficulty. */
	private double goldDifficulty;
	
	/** The user gold amount. */
	private int userGoldAmount;
	
	/** The user game score. */
	private int userGameScore = 0;
	
	/** The user monster kills. */
	private int userMonsterKills = 0;
	
	/** The game user input. */
	private UserInput gameUserInput;
	
	/** The user monster list. */
	private ArrayList<Monster> userMonsterList = new ArrayList<Monster>();
	
	/** The user item list. */
	private ArrayList<Items> userItemList = new ArrayList<Items>();
	
	/** The user game shop. */
	private Shop userGameShop = new Shop();
	
	/** The rand event. */
	private RandomEvent randEvent = new RandomEvent(this);
	
	/** The battle. */
	private Battle battle = new Battle(this);
	

	/**
	 * Method called only at start
	 * Starts a new game. Get's user input using UserInput.
	 * User input is then called into other methods in this class
	 */
	public void startNewGame() {
		gameUserInput = new UserInput();
	
		userGameName = gameUserInput.startUserName();
		maxGameDays = gameUserInput.startGameDays();
		starterMonsterSelect(gameUserInput.startSelectMonster());
		starterMonsterRename(gameUserInput.startRenameMonster());
		starterSetDifficulty(gameUserInput.startDifficulty());	
	}
	
	/**
	 * Method called only at start
	 * Method that determines the monster selected by user
	 *
	 * @param int, representing the selection
	 */
	public void starterMonsterSelect(int selection) {
		if (selection == 1) {
			Monster userImp = new Imp();
			userMonsterList.add(userImp);
		} else if (selection == 2) {
			Monster userGnome = new Gnome();
			userMonsterList.add(userGnome);
		} else if (selection == 3) {
			Monster userGoblin = new Goblin();
			userMonsterList.add(userGoblin);
		}
	}
	
	/**
	 * Method called only at start
	 * Method that allow's the user to rename their monster
	 *
	 * @param selection the selection
	 */
	public void starterMonsterRename(String selection) {
		if (selection.toLowerCase().equals("y")) {
			String newName = gameUserInput.startNewMonsterName();
			userMonsterList.get(0).setMonsterName(newName); //Starter monster will always be at index 0
		}
	}

	
	//----------------------------- Getters/Setters/Changers----------------------------------
	
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
	public ArrayList<Monster> getUserMonsterList() {
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
	public ArrayList<Items> getUserItemList() {
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
	public static ArrayList<Monster> getMasterMonsterList() {
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
	
	//---------------------------------------------------------------------------------------------

	/**
	 * Method only called at the start
	 * Sets the user's difficulty.
	 *
	 * @param selection, the user's difficulty choice
	 */
	public void starterSetDifficulty(String selection) {
		gameDifficulty = selection;
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
	 * Outputs the current game statistics.
	 */
	public void viewGameInfo() {
		System.out.println("\n");
		System.out.println("Welcome " + userGameName);
		System.out.println("You have " + userGoldAmount + " Gold");
		System.out.println("The Game difficulty is " + gameDifficulty);
		System.out.println("Your current score is: " + userGameScore);
		System.out.println("Today is Day " + gameDay);
		System.out.println("You have " + (maxGameDays - gameDay) + " Day(s) remaining");
		System.out.println("\n");
		gameUserInput.gameEnterContinue();
	}
	
	/**
	 * Output's a the user's current monsters
	 * Gives the user a chance to swap monsters
	 */
	public void viewGameMonsters() {
		boolean returnToMenu = false;
		while (!returnToMenu) {
			if (userMonsterList.size() == 0) {
				System.out.println("You currently have no Monsters\n");
				gameUserInput.gameEnterContinue();
				return;
			}
			System.out.println("\n");
			System.out.println("Welcome " + userGameName);
			System.out.println("Here are your monsters\n");
			userDisplayMonsters(false);
			System.out.println("\n");
			System.out.println("1. Swap Monster Position");
			System.out.println("2. Rename Monster");
			System.out.println("Press enter to go back to menu");
			int selection = gameUserInput.gameGetIntEnter(2);
			if (selection == 0) {
				returnToMenu = true;
			} else if (selection == 1) {
				userSwapMonster();
			} else if (selection == 2) {
				userRenameMonster();
			}
		}
	}
	
	/**
	 * Method that allows the user to swap the order of their monsters in their list
	 */
	public void userSwapMonster() {
		System.out.println("Which monster would you like to swap?");
		System.out.println("Enter number or press enter to go back\n");
		int monsterSwapChoice = gameUserInput.gameGetIntEnter(userMonsterList.size());
		if (monsterSwapChoice == 0) {
			return;
		}
		System.out.println("Which monster would you like to swap this with?");
		System.out.println("Enter number or press enter to go back\n");
		int monsterSwapToChoice = gameUserInput.gameGetIntEnter(userMonsterList.size());
		if (monsterSwapToChoice == 0) {
			return;
		}
		Collections.swap(userMonsterList, monsterSwapChoice - 1, monsterSwapToChoice - 1);
	}
	
	/**
	 * Allow's a user to rename one of their monsters
	 */
	public void userRenameMonster() {
		System.out.println("Which monster would you like to rename?");
		System.out.println("Enter number or press enter to go back\n");
		int monsterRenameChoice = gameUserInput.gameGetIntEnter(userMonsterList.size());
		if (monsterRenameChoice == 0) {
			return;
		}
		userMonsterList.get(monsterRenameChoice - 1).setMonsterName(gameUserInput.startNewMonsterName());
	}
	
	/**
	 * Outputs all of the user's monsters and their statistics line by line
	 *
	 * @param salePrice, boolean to state whether the method should output the sale price for each monster
	 */
	public void userDisplayMonsters(boolean salePrice) {
		int counter = 1;
		for (Monster userMonster : userMonsterList) {
			System.out.println(counter + ". " + userMonster.getMonsterName() + " - Health: " +
								userMonster.getCurrHealth() + " , Attack: " + userMonster.getCurrAttack() 
								+ " , Defence: " + userMonster.getCurrDefence());
			if (salePrice) {
				System.out.println("Sell For: " + userMonster.getMonsterSellPrice() + " Gold\n");
			}
			counter += 1;
		}
	}
	
	/**
	 * Outputs all of the user's items and their statistics line by line
	 *
	 * @param salePrice, boolean to state whether the method should output the sale price for each item
	 */
	public void userDisplayItems(boolean salePrice) {
		int counter = 1;
		for (Items userItem : userItemList) {
			System.out.println(counter + ". " + userItem.getItemName() + " - " 
						+ userItem.getItemDescription());
			if (salePrice) {
				System.out.println("Sell For: " + userItem.getItemSellPrice() + " Gold\n");
			}
			counter += 1;
		}
	}
	
	/**
	 * Method that allows the user to view the game shop
	 */
	public void viewGameShop() {
		boolean returnToMenu = false;
		while (!returnToMenu) {
			System.out.println("\n");
			System.out.println("Welcome to the Shop " + userGameName);
			System.out.println("You have " + userGoldAmount + " Gold Pieces");
			System.out.println("What would you like to do?\n");
			System.out.println("1. View Monsters for Sale");
			System.out.println("2. View Items for Sale");
			System.out.println("3. Sell a Monster or Item to the shop");
			System.out.println("Press enter to go back");
			int selection = gameUserInput.gameGetIntEnter(3);
			if (selection == 0) {
				returnToMenu = true;
			} else if (selection == 1) {
				userBuyMonsterDisplay();
			} else if (selection == 2) {
				userBuyItemDisplay();
			} else if (selection == 3) {
				userSellMonsterItemDisplay();
			}
		}
	}
	
	/**
	 * Allow's the user to purchase a new monster
	 */
	public void userBuyMonsterDisplay(){
		userGameShop.shopDisplayMonsters();
		System.out.println("Please Enter a number to buy the corresponding Monster");
		System.out.println("Or enter nothing to go back");
		int buyMonsterInt = gameUserInput.gameGetIntEnter(userGameShop.getShopMonsterList().size());
		if (buyMonsterInt == 0) {
			return;
		}
		userBuyMonster(buyMonsterInt);
	}
	
	/**
	 * Method that processes a user's request to purchase a new monster
	 * Check's whether a user has enough gold to purchase the monster
	 * Should there be enough gold, the transaction is processed in this method
	 * If there is not enough gold, the user is informed and the method is returned
	 *
	 * @param buyMonsterInt, integer relating to which monster has been selected
	 */
	public void userBuyMonster(int buyMonsterInt) {
		if (userMonsterList.size() < 4) {
			if ((userGoldAmount - userGameShop.getShopMonsterList().get(buyMonsterInt - 1).
					getMonsterBuyPrice()) >= 0) {
				userMonsterList.add(userGameShop.getShopMonsterList().get(buyMonsterInt - 1));
				userGoldAmount = userGoldAmount - userGameShop.getShopMonsterList().get(buyMonsterInt - 1).
						getMonsterBuyPrice();
				userGameShop.getShopMonsterList().remove(buyMonsterInt - 1);
				System.out.println("\n");
				System.out.println("Congratulations you have purchased a new Monster");
				gameUserInput.gameEnterContinue();
			} else {
				System.out.println("Sorry you don't have enough gold to purchase this monster");
				gameUserInput.gameEnterContinue();
			}
		} else {
			System.out.println("Sorry you already have 4 Monsters");
			gameUserInput.gameEnterContinue();
		}
	}
	
	/**
	 * Allow's the user to purchase a new item
	 */
	public void userBuyItemDisplay() {
		userGameShop.shopDisplayItems();
		System.out.println("Please Enter a number to buy the corresponding Item");
		System.out.println("Or enter nothing to go back");
		int buyItemInt = gameUserInput.gameGetIntEnter(userGameShop.getShopItemList().size());
		if (buyItemInt == 0) {
			return;
		}
		userBuyItem(buyItemInt);
	}
	
	/**
	 * Method that processes a user's request to purchase a new item
	 * Check's whether a user has enough gold to purchase the item
	 * Should there be enough gold, the transaction is processed in this method
	 * If there is not enough gold, the user is informed and the method is returned
	 *
	 * @param buyMonsterInt, integer relating to which monster has been selected
	 */
	public void userBuyItem(int buyItemInt) {

		if ((userGoldAmount - userGameShop.getShopItemList().get(buyItemInt - 1).
				getItemBuyPrice()) >= 0) {
			userItemList.add(userGameShop.getShopItemList().get(buyItemInt - 1));
			userGoldAmount = userGoldAmount - userGameShop.getShopItemList().get(buyItemInt - 1).
					getItemBuyPrice();
			userGameShop.getShopItemList().remove(buyItemInt - 1);
			System.out.println("\n");
			System.out.println("Congratulations you have purchased a new Item");
			gameUserInput.gameEnterContinue();
		} else {
			System.out.println("Sorry you don't have enough gold to purchase this Item");
			gameUserInput.gameEnterContinue();
		}
	}
	
	/**
	 * Method that allow's users to selected what they would like to sell
	 */
	public void userSellMonsterItemDisplay() {
		System.out.println("What would you like to sell?");
		System.out.println("1. Monster");
		System.out.println("2. Item");
		System.out.println("Please Enter a number or enter nothing to go back");
		int selection = gameUserInput.gameGetIntEnter(2);
		if (selection == 1) {
			userSellMonsterDisplay();
		} else if (selection == 2) {
			userSellItemDisplay();
		}
	}
	
	/**
	 * Allow's the user to sell a monster
	 */
	public void userSellMonsterDisplay() {
		if (userMonsterList.size() == 0) {
			System.out.println("You currently own no monsters\n");
			gameUserInput.gameEnterContinue();
			return;
		}
		System.out.println("\n");
		System.out.println("What Monster would you like to sell?\n");
		userDisplayMonsters(true);
		System.out.println("Please Enter a number to sell the corresponding Monster");
		System.out.println("Or enter nothing to go back");
		int sellMonsterInt = gameUserInput.gameGetIntEnter(userMonsterList.size());
		if (sellMonsterInt == 0) {
			return;
		}
		userSellMonster(sellMonsterInt);
	}
	
	/**
	 * Processes the monster sell transaction.
	 *
	 * @param sellMonsterInt, int relating to which monster in the list
	 */
	public void userSellMonster(int sellMonsterInt) {
		userGoldAmount += userMonsterList.get(sellMonsterInt - 1).getMonsterSellPrice();
		userMonsterList.remove(sellMonsterInt - 1);
		System.out.println("\n");
		System.out.println("You have sold a Monster");
		gameUserInput.gameEnterContinue();
	}
	
	/**
	 * Allow's the user to sell an item
	 */
	public void userSellItemDisplay() {
		if (userItemList.size() == 0) {
			System.out.println("You currently have no items\n");
			gameUserInput.gameEnterContinue();
			return;
		}
		System.out.println("What item would you like to sell?\n");
		userDisplayItems(true);
		System.out.println("Please Enter a number to sell the corresponding Item");
		System.out.println("Or enter nothing to go back");
		int sellItemInt = gameUserInput.gameGetIntEnter(userItemList.size());
		if (sellItemInt == 0) {
			return;
		}
		userSellItem(sellItemInt);
	}
	
	/**
	 * Processes the user's request to sell an item
	 *
	 * @param sellItemInt, an int relating which item to sell in the list
	 */
	public void userSellItem(int sellItemInt) {
		userGoldAmount += userItemList.get(sellItemInt - 1).getItemSellPrice();
		userItemList.remove(sellItemInt - 1);
		System.out.println("\n");
		System.out.println("You have sold an item");
		gameUserInput.gameEnterContinue();
	}
	
	/**
	 * Allow's the user to view all of their items
	 */
	public void viewGameItems() {
		//Shows user game inventory and asks user to select an item to use
		boolean returnToMenu = false;
		while (!returnToMenu) {
			if (userItemList.size() == 0) {
				System.out.println("You have no items in your inventory");
				gameUserInput.gameEnterContinue();
				break;
			} else {
				System.out.println("\n");
				System.out.println("Welcome " + userGameName);
				System.out.println("Here is your inventory\n");
				userDisplayItems(false);
				System.out.println("\n");
				System.out.println("Select an item to use on a Monster or Press enter to go back");
				int viewItemInt = gameUserInput.gameGetIntEnter(userItemList.size());
				if (viewItemInt == 0) {
					returnToMenu = true;
				} else {
					if (userMonsterList.size() == 0) {
						System.out.println("You have no Monsters to use items on");
						gameUserInput.gameEnterContinue();
					} else {
						useItemOnMonster(viewItemInt);
					}
				}
			}
		}
	}
	
	/**
	 * Allow's a selected item to be used on a monster
	 *
	 * @param viewItemInt the view item int
	 */
	public void useItemOnMonster(int viewItemInt) {
		//Given an integer selected by the user, gets the item and asks user to select Monster to use it on
		Items selectedItemToUse = userItemList.get(viewItemInt - 1);
		System.out.println("\n");
		System.out.println("You have Selected " + selectedItemToUse.getItemName());
		System.out.println("\n");
		System.out.println("Which Monster do you want to use " + selectedItemToUse.getItemName() + " on?\n");
		userDisplayMonsters(false);
		System.out.println("\n");
		System.out.println("Select a Monster or Press enter to go back");
		int viewItemMonsterInt = gameUserInput.gameGetIntEnter(userMonsterList.size());
		if (viewItemMonsterInt == 0) {
			return;
		}
		selectedItemToUse.useItemOnMonster(userMonsterList.get(viewItemMonsterInt - 1));
		userItemList.remove(viewItemInt - 1);
		System.out.println("You used " + selectedItemToUse.getItemName() + " on " 
				+ userMonsterList.get(viewItemMonsterInt - 1).getMonsterName());
		gameUserInput.gameEnterContinue();
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
	 * Occurs when the game is over. Different endings depending on how the game ends
	 *
	 * @param gameEndReason, an int relating to which ending has occurred
	 */
	public void userGameOver(int gameEndReason) {
		if (gameEndReason == 1) {
			System.out.println("Well done " + userGameName + ", you have completed the Game.");
			gameUserInput.gameEnterContinue();
			System.out.println("Your Final Gold Amount is " + userGoldAmount + " Gold.");
			gameUserInput.gameEnterContinue();
			System.out.println("Your Final Enemy Kill count is: " + userMonsterKills);
			gameUserInput.gameEnterContinue();
			System.out.println("This gives you a Final Score of: " + (userGameScore + userGoldAmount));
			gameUserInput.gameEnterContinue();
			System.out.println("Thank you for playing our game :)");
			gameUserInput.gameEnterContinue();
		} else if (gameEndReason == 2) {
			System.out.println("Unfortunately " + userGameName + ", you have run out of Monsters and can't buy anymore.");
			gameUserInput.gameEnterContinue();
			System.out.println("Your Final Gold Amount is " + userGoldAmount + " Gold.");
			gameUserInput.gameEnterContinue();
			System.out.println("Your Final Enemy Kill count is: " + userMonsterKills);
			gameUserInput.gameEnterContinue();
			System.out.println("This gives you a Final Score of: " + (userGameScore + userGoldAmount));
			gameUserInput.gameEnterContinue();
			System.out.println("Thank you for playing our game :)");
			gameUserInput.gameEnterContinue();
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
	 * Occurs when the user choose to progress to the next day.
	 * Random event occurs by calling randEvent
	 * Outputs which game day is over
	 * Resets shop stock and monster statistics
	 * Adds one to game day
	 */
	public void nightTime() {
		randEvent.main();
		System.out.println("Day " + gameDay + " is over\n");
		
		userGameShop.resetShopStock();
		battle = new Battle(this); //creates a new battle object when the user sleeps for the next day
		resetMonsterStats();
		gameDay += 1;
	}

	/**
	 * Main menu.
	 */
	public void mainMenu() {
		
		startNewGame(); //runs a function to query the user for game starting information
		 //initalized RandomEvent
		int gameEndReason = 1;
		while (gameDay <= maxGameDays) {	
			
			/*
			Just a bunch of if/elif checking user inputs, the game day only advances after
			sleep is chosen. We can put all of the events under these statements. The battle
			object under 4 etc.
			*/
			
			//if the user has no monsters left, check if the user can buy a new monster
			//if they can't afford one the game ends
			//this is checked everytime the user goes back to the main menu
			if (userMonsterList.size() == 0) { 
				if (userGoldAmount < userGameShop.shopGetCheapestMonsterPrice()) {
					gameEndReason = 2;
					break;
				}
			}
			int userAction = gameUserInput.basicOptions();
			if (userAction == 1) {
				System.out.println("You have Chosen to View Gold/Days");
				viewGameInfo();
			} else if (userAction == 2){
				System.out.println("You have Chosen to View your Monsters");
				viewGameMonsters();
			} else if (userAction == 3) {
				System.out.println("You have Chosen to View Inventory");
				viewGameItems();
			} else if (userAction == 4) {
				System.out.println("You have Chosen to View Battles");
				battle.mainMenu();
			} else if (userAction == 5) {
				System.out.println("You have Chosen to Visit the Shop");
				viewGameShop();
			} else if (userAction == 6) {
				System.out.println("You have Chosen to Sleep");
				nightTime();
			}
		}
		System.out.println("Game Over"); //When current day is past the max days game ends.
		userGameOver(gameEndReason);
	}
}
