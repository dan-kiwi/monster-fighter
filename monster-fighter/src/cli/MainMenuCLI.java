package cli;

import java.util.Collections;
import java.util.Scanner;

import items.Items;
import mainenviro.GameEnviro;
import monster.Gnome;
import monster.Goblin;
import monster.Imp;
import monster.Monster;

public class MainMenuCLI {
	
	private GameEnviro game;
	private Scanner userInput;
	
	/**
	 * Instantiates a new main menu CLI.
	 *
	 * @param game the game
	 */
	public MainMenuCLI(GameEnviro game) {
		this.game = game;
		this.userInput = new Scanner(System.in);
	}
	
	/**
	 * Collect's what the monster would like to do
	 *
	 * @return the int, representing the option choosen
	 */
	public int basicOptions() {
		System.out.println("What would you like to do?\n");
		System.out.println("1. View Current Gold, Current Day, Days Remaining");
		System.out.println("2. View your Monsters");
		System.out.println("3. View Inventory");
		System.out.println("4. Battle Arena");
		System.out.println("5. Visit the Shop");
		System.out.println("6. Go to Sleep");
		boolean basicValid = false;
		while (!basicValid) {
			String selection = userInput.nextLine();
			if (selection.matches("[0-9]+")) {
				if ((Integer.parseInt(selection) > 0) && (Integer.parseInt(selection) <= 6)) {
					return Integer.parseInt(selection);
				} else {
					System.out.println("please enter a number between 1-6");
				}
			} else {
				System.out.println("Please Enter a number");
			}
		}
		return 0;
	}
	
	/**
	 * A small function that just waits till the user presses enter. 
	 * A way to break up the game so everything doesn't happen all at once.
	 */
	public void gameEnterContinue() {
		System.out.println("Press Enter to continue");
		boolean continueValid = false;
		while (!continueValid) {
			String selection = userInput.nextLine();
			if (selection.matches("")) {
				return;
			} else {
				System.out.println("Press Enter to continue");
			}
		}
	}
	
	/**
	 * Queries the user for a number input between 0 and maxNumber or no input
	 * 
	 * @param maxNumber, the max number the user can input
	 * @return the int, or 0 for no input
	 */
	public int gameGetIntEnter(int maxNumber) {
		boolean viewMonsterValid = false;
		while (!viewMonsterValid) {
			String selection = userInput.nextLine();
			
			if (selection.matches("[0-9]+")) {
				if ((Integer.parseInt(selection) > 0) && (Integer.parseInt(selection) <= maxNumber)) {
					return Integer.parseInt(selection);
				} else {
					System.out.println("Please enter a number between 1-" + maxNumber);
				}
			} else if ((selection.matches(""))) {
				return 0;
			} else {
				System.out.println("Please Enter a number or nothing to go back");
			}
		}
		return 0;
	}
	

	/**
	 * Outputs the current game statistics.
	 */
	public void viewGameInfo() {
		System.out.println("\n");
		System.out.println("Welcome " + game.getUserGameName());
		System.out.println("You have " + game.getUserGoldAmount() + " Gold");
		System.out.println("The Game difficulty is " + game.getGameDifficulty());
		System.out.println("Your current score is: " + game.getUserGameScore());
		System.out.println("Today is Day " + game.getGameDay());
		System.out.println("You have " + (game.getMaxGameDays() - game.getGameDay()) + " Day(s) remaining");
		System.out.println("\n");
		gameEnterContinue();
	}
	
	/**
	 * Output's a the user's current monsters
	 * Gives the user a chance to swap monsters
	 */
	public void viewGameMonsters() {
		boolean returnToMenu = false;
		while (!returnToMenu) {
			if (game.getUserMonsterList().size() == 0) {
				System.out.println("You currently have no Monsters\n");
				gameEnterContinue();
				return;
			}
			System.out.println("\n");
			System.out.println("Welcome " + game.getUserGameName());
			System.out.println("Here are your monsters\n");
			userDisplayMonsters(false);
			System.out.println("\n");
			System.out.println("1. Swap Monster Position");
			System.out.println("2. Rename Monster");
			System.out.println("Press enter to go back to menu");
			int selection = gameGetIntEnter(2);
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
		int monsterSwapChoice = gameGetIntEnter(game.getUserMonsterList().size());
		if (monsterSwapChoice == 0) {
			return;
		}
		System.out.println("Which monster would you like to swap this with?");
		System.out.println("Enter number or press enter to go back\n");
		int monsterSwapToChoice = gameGetIntEnter(game.getUserMonsterList().size());
		if (monsterSwapToChoice == 0) {
			return;
		}
		Collections.swap(game.getUserMonsterList(), monsterSwapChoice - 1, monsterSwapToChoice - 1);
	}
	
	/**
	 * Allow's a user to rename one of their monsters
	 */
	public void userRenameMonster() {
		System.out.println("Which monster would you like to rename?");
		System.out.println("Enter number or press enter to go back\n");
		int monsterRenameChoice = gameGetIntEnter(game.getUserMonsterList().size());
		if (monsterRenameChoice == 0) {
			return;
		}
		game.getUserMonsterList().get(monsterRenameChoice - 1).setMonsterName(startNewMonsterName());
	}
	
	/**
	 * Outputs all of the user's monsters and their statistics line by line
	 *
	 * @param salePrice, boolean to state whether the method should output the sale price for each monster
	 */
	public void userDisplayMonsters(boolean salePrice) {
		int counter = 1;
		for (Monster userMonster : game.getUserMonsterList()) {
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
		for (Items userItem : game.getUserItemList()) {
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
			System.out.println("Welcome to the Shop " + game.getUserGameName());
			System.out.println("You have " + game.getUserGoldAmount() + " Gold Pieces");
			System.out.println("What would you like to do?\n");
			System.out.println("1. View Monsters for Sale");
			System.out.println("2. View Items for Sale");
			System.out.println("3. Sell a Monster or Item to the shop");
			System.out.println("Press enter to go back");
			int selection = gameGetIntEnter(3);
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
		game.getUserGameShop().shopDisplayMonsters();
		System.out.println("Please Enter a number to buy the corresponding Monster");
		System.out.println("Or enter nothing to go back");
		int buyMonsterInt = gameGetIntEnter(game.getUserGameShop().getShopMonsterList().size());
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
		if (game.getUserMonsterList().size() < 4) {
			if ((game.getUserGoldAmount() - game.getUserGameShop().getShopMonsterList().get(buyMonsterInt - 1).
					getMonsterBuyPrice()) >= 0) {
				game.getUserMonsterList().add(game.getUserGameShop().getShopMonsterList().get(buyMonsterInt - 1));
				game.changeUserGoldAmount(-1 * game.getUserGoldAmount() - game.getUserGameShop().getShopMonsterList().get(buyMonsterInt - 1).
						getMonsterBuyPrice());
				game.getUserGameShop().getShopMonsterList().remove(buyMonsterInt - 1);
				System.out.println("\n");
				System.out.println("Congratulations you have purchased a new Monster");
				gameEnterContinue();
			} else {
				System.out.println("Sorry you don't have enough gold to purchase this monster");
				gameEnterContinue();
			}
		} else {
			System.out.println("Sorry you already have 4 Monsters");
			gameEnterContinue();
		}
	}
	
	/**
	 * Collect's the monsters new name from the user
	 * This method only executed if the user has selected to rename their monster
	 *
	 * @return the string, the monster's new name
	 */
	public String startNewMonsterName() {
		System.out.println("Enter your monsters new name:");
		boolean nameValid = false;
		while (!nameValid) {
			String selection = userInput.nextLine();
			if((selection.length() > 0) && (selection.length() <= 15)) {
				if (selection.matches("[A-Za-z0-9]*")) {
					return selection;
				} else {
					System.out.println("Name must only contain letters or numbers");
				}
			} else {
				System.out.println("Monster name must be between 1 and 15 characters long");
			}
		}
		return null;
	}
	
	/**
	 * Allow's the user to purchase a new item
	 */
	public void userBuyItemDisplay() {
		game.getUserGameShop().shopDisplayItems();
		System.out.println("Please Enter a number to buy the corresponding Item");
		System.out.println("Or enter nothing to go back");
		int buyItemInt = gameGetIntEnter(game.getUserGameShop().getShopItemList().size());
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

		if ((game.getUserGoldAmount() - game.getUserGameShop().getShopItemList().get(buyItemInt - 1).
				getItemBuyPrice()) >= 0) {
			game.getUserItemList().add(game.getUserGameShop().getShopItemList().get(buyItemInt - 1));
			game.addUserGoldAmount(-1 * game.getUserGameShop().getShopItemList().
					get(buyItemInt - 1).getItemBuyPrice());	
			game.getUserGameShop().getShopItemList().remove(buyItemInt - 1);
			System.out.println("\n");
			System.out.println("Congratulations you have purchased a new Item");
			gameEnterContinue();
		} else {
			System.out.println("Sorry you don't have enough gold to purchase this Item");
			gameEnterContinue();
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
		int selection = gameGetIntEnter(2);
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
		if (game.getUserMonsterList().size() == 0) {
			System.out.println("You currently own no monsters\n");
			gameEnterContinue();
			return;
		}
		System.out.println("\n");
		System.out.println("What Monster would you like to sell?\n");
		userDisplayMonsters(true);
		System.out.println("Please Enter a number to sell the corresponding Monster");
		System.out.println("Or enter nothing to go back");
		int sellMonsterInt = gameGetIntEnter(game.getUserMonsterList().size());
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
		game.addUserGoldAmount(game.getUserMonsterList().get(sellMonsterInt - 1).getMonsterSellPrice());
		game.getUserMonsterList().remove(sellMonsterInt - 1);
		System.out.println("\n");
		System.out.println("You have sold a Monster");
		gameEnterContinue();
	}
	
	/**
	 * Allow's the user to sell an item
	 */
	public void userSellItemDisplay() {
		if (game.getUserItemList().size() == 0) {
			System.out.println("You currently have no items\n");
			gameEnterContinue();
			return;
		}
		System.out.println("What item would you like to sell?\n");
		userDisplayItems(true);
		System.out.println("Please Enter a number to sell the corresponding Item");
		System.out.println("Or enter nothing to go back");
		int sellItemInt = gameGetIntEnter(game.getUserItemList().size());
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
		game.addUserGoldAmount(game.getUserItemList().get(sellItemInt - 1).getItemSellPrice());
		game.getUserItemList().remove(sellItemInt - 1);
		System.out.println("\n");
		System.out.println("You have sold an item");
		gameEnterContinue();
	}
	
	/**
	 * Allow's the user to view all of their items
	 */
	public void viewGameItems() {
		//Shows user game inventory and asks user to select an item to use
		boolean returnToMenu = false;
		while (!returnToMenu) {
			if (game.getUserItemList().size() == 0) {
				System.out.println("You have no items in your inventory");
				gameEnterContinue();
				break;
			} else {
				System.out.println("\n");
				System.out.println("Welcome " + game.getUserGameName());
				System.out.println("Here is your inventory\n");
				userDisplayItems(false);
				System.out.println("\n");
				System.out.println("Select an item to use on a Monster or Press enter to go back");
				int viewItemInt = gameGetIntEnter(game.getUserItemList().size());
				if (viewItemInt == 0) {
					returnToMenu = true;
				} else {
					if (game.getUserMonsterList().size() == 0) {
						System.out.println("You have no Monsters to use items on");
						gameEnterContinue();
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
		Items selectedItemToUse = game.getUserItemList().get(viewItemInt - 1);
		System.out.println("\n");
		System.out.println("You have Selected " + selectedItemToUse.getItemName());
		System.out.println("\n");
		System.out.println("Which Monster do you want to use " + selectedItemToUse.getItemName() + " on?\n");
		userDisplayMonsters(false);
		System.out.println("\n");
		System.out.println("Select a Monster or Press enter to go back");
		int viewItemMonsterInt = gameGetIntEnter(game.getUserMonsterList().size());
		if (viewItemMonsterInt == 0) {
			return;
		}
		selectedItemToUse.useItemOnMonster(game.getUserMonsterList().get(viewItemMonsterInt - 1));
		game.getUserItemList().remove(viewItemInt - 1);
		System.out.println("You used " + selectedItemToUse.getItemName() + " on " 
				+ game.getUserMonsterList().get(viewItemMonsterInt - 1).getMonsterName());
		gameEnterContinue();
	}
	
	/**
	 * Occurs when the game is over. Different endings depending on how the game ends
	 *
	 * @param gameEndReason, an int relating to which ending has occurred
	 */
	public void userGameOver(int gameEndReason) {
		if (gameEndReason == 1) {
			System.out.println("Well done " + game.getUserGameName() + ", you have completed the Game.");
			gameEnterContinue();
			System.out.println("Your Final Gold Amount is " + game.getUserGoldAmount() + " Gold.");
			gameEnterContinue();
			System.out.println("Your Final Enemy Kill count is: " + game.getUserMonsterKills());
			gameEnterContinue();
			System.out.println("This gives you a Final Score of: " + (game.getUserGameScore() + game.getUserGoldAmount()));
			gameEnterContinue();
			System.out.println("Thank you for playing our game :)");
			gameEnterContinue();
		} else if (gameEndReason == 2) {
			System.out.println("Unfortunately " + game.getUserGameName() + ", you have run out of Monsters and can't buy anymore.");
			gameEnterContinue();
			System.out.println("Your Final Gold Amount is " + game.getUserGoldAmount() + " Gold.");
			gameEnterContinue();
			System.out.println("Your Final Enemy Kill count is: " + game.getUserMonsterKills());
			gameEnterContinue();
			System.out.println("This gives you a Final Score of: " + (game.getUserGameScore() + game.getUserGoldAmount()));
			gameEnterContinue();
			System.out.println("Thank you for playing our game :)");
			gameEnterContinue();
		}
	}
	
	public void randomEvent() {
		boolean happened = false;
		for (int i = 0; i < game.getUserMonsterList().size(); i++) {
			Monster currMonster = game.getUserMonsterList().get(i);
			if (game.getRandEvent().monsterLeaves(currMonster)) {
				System.out.println(currMonster.getMonsterName() + " has ran away overnight");
				game.getUserMonsterList().remove(i);
				i--;
				happened = true;
			} else if (game.getRandEvent().monsterLevelUp(currMonster)) {
				System.out.println(currMonster.getMonsterName() + " has leveled up overnight");
				happened = true;
			}
		}
		if (game.getRandEvent().willAddMonster()) {
			happened = true;
			game.getUserMonsterList().add(game.getRandEvent().addMonster());
		}
		if (!happened) System.out.println("No random event has occured tonight");
	}
	
	public void startNewGame() {
		SetupCLI start = new SetupCLI(game, this);
		start.startNewGame();
	}
	
	public void mainMenu() {
		
		startNewGame(); //runs a function to query the user for game starting information
		 //initalized RandomEvent
		int gameEndReason = 1;
		while (game.getGameDay() <= game.getMaxGameDays()) {	
			
			/*
			Just a bunch of if/elif checking user inputs, the game day only advances after
			sleep is chosen. We can put all of the events under these statements. The battle
			object under 4 etc.
			*/
			
			//if the user has no monsters left, check if the user can buy a new monster
			//if they can't afford one the game ends
			//this is checked everytime the user goes back to the main menu
			if (game.getUserMonsterList().size() == 0) { 
				if (game.getUserGoldAmount() < game.getUserGameShop().shopGetCheapestMonsterPrice()) {
					gameEndReason = 2;
					break;
				}
			}
			int userAction = basicOptions();
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
				System.out.println("You have Chosen to Battle");
				game.getBattle().mainMenu();
			} else if (userAction == 5) {
				System.out.println("You have Chosen to Visit the Shop");
				viewGameShop();
			} else if (userAction == 6) {
				System.out.println("You have Chosen to Sleep");
				game.nightTime();
			}
		}
		System.out.println("Game Over"); //When current day is past the max days game ends.
		userGameOver(gameEndReason);
	}
	
	public static void main(String[] args) {
		GameEnviro game = new GameEnviro();
		MainMenuCLI cli = new MainMenuCLI(game);
		cli.mainMenu();
	}
}
