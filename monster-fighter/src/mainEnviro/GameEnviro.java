package mainEnviro;

import java.util.ArrayList;
import java.util.Collections;

import items.Berries;
import items.HealthPotion;
import items.Items;
import items.Shop;
import monster.*;


public class GameEnviro {
	
	//Constants
	private static final double easyMonster = 0.85;
	private static final double easyGold = 1.5;
	private static final int easyUserGold = 300;
	private static final double hardMonster = 1.15;
	private static final double hardGold = 0.85;
	private static final int hardUserGold = 150;
	private static ArrayList<Monster> masterMonsterList = new ArrayList<Monster>() {{
		add(new Dragon());
		add(new Gnome());
		add(new Goblin());
		add(new Griffin());
		add(new Imp());
		add(new Unicorn());
	}};
	
	private int gameDay = 1;
	private String userGameName;
	private int maxGameDays;
	private String gameDifficulty;
	private double monsterDifficulty;
	private double goldDifficulty;
	private int userGoldAmount;
	private UserInput gameUserInput;
	private ArrayList<Monster> userMonsterList = new ArrayList<Monster>();
	private ArrayList<Items> userItemList = new ArrayList<Items>();
	private Shop userGameShop = new Shop();
	private RandomEvent randEvent = new RandomEvent(this);
	
	public void startNewGame() {
		gameUserInput = new UserInput();
	
		userGameName = gameUserInput.startUserName();
		maxGameDays = gameUserInput.startGameDays();
		starterMonsterSelect(gameUserInput.startSelectMonster());
		starterMonsterRename(gameUserInput.startRenameMonster());
		starterSetDifficulty(gameUserInput.startDifficulty());	
	}
	
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
	
	public void starterMonsterRename(String selection) {
		if (selection.toLowerCase().equals("y")) {
			String newName = gameUserInput.startNewMonsterName();
			userMonsterList.get(0).setMonsterName(newName); //Starter monster will always be at index 0
		}
	}

	
	//----------------------------- Getters/Setters/Changers----------------------------------
	
	
	/**
	 * @return the userMonsterList
	 */
	public ArrayList<Monster> getUserMonsterList() {
		return userMonsterList;
	}
	
	public void addMonster(Monster tempMonster) {
		if (userMonsterList.size() < 4) {
			userMonsterList.add(tempMonster);
		} else {
			throw new IndexOutOfBoundsException("Cannot have more than four monsters");
		}
	}
	
	/**
	 * @return the userItemList
	 */
	public ArrayList<Items> getUserItemList() {
		return userItemList;
	}

	public static ArrayList<Monster> getMasterMonsterList() {
		return masterMonsterList;
	}

	/**
	 * @return the goldDifficulty
	 */
	public double getGoldDifficulty() {
		return goldDifficulty;
	}
	
	public double getMonsterDifficulty() {
		return monsterDifficulty;
	}
	
	public void changeUserGoldAmount(int amount) {
		this.userGoldAmount += amount;
	}
	
	//---------------------------------------------------------------------------------------------

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
	
	public void viewGameInfo() {
		System.out.println("\n");
		System.out.println("Welcome " + userGameName);
		System.out.println("You have " + userGoldAmount + " Gold");
		System.out.println("The Game difficulty is " + gameDifficulty);
		System.out.println("Today is Day " + gameDay);
		System.out.println("You have " + (maxGameDays - gameDay) + " Day(s) remaining");
		System.out.println("\n");
		gameUserInput.gameEnterContinue();
	}
	
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
	
	public void userRenameMonster() {
		System.out.println("Which monster would you like to rename?");
		System.out.println("Enter number or press enter to go back\n");
		int monsterRenameChoice = gameUserInput.gameGetIntEnter(userMonsterList.size());
		if (monsterRenameChoice == 0) {
			return;
		}
		userMonsterList.get(monsterRenameChoice - 1).setMonsterName(gameUserInput.startNewMonsterName());
	}
	
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
	
	public void userSellMonster(int sellMonsterInt) {
		userGoldAmount += userMonsterList.get(sellMonsterInt - 1).getMonsterSellPrice();
		userMonsterList.remove(sellMonsterInt - 1);
		System.out.println("\n");
		System.out.println("You have sold a Monster");
		gameUserInput.gameEnterContinue();
	}
	
	public void userSellItemDisplay() {
		if (userItemList.size() == 0) {
			System.out.println("You currently have no Monsters\n");
			gameUserInput.gameEnterContinue();
			return;
		}
		System.out.println("What Item would you like to sell?\n");
		userDisplayItems(true);
		System.out.println("Please Enter a number to sell the corresponding Item");
		System.out.println("Or enter nothing to go back");
		int sellItemInt = gameUserInput.gameGetIntEnter(userItemList.size());
		if (sellItemInt == 0) {
			return;
		}
		userSellItem(sellItemInt);
	}
	
	public void userSellItem(int sellItemInt) {
		userGoldAmount += userItemList.get(sellItemInt - 1).getItemSellPrice();
		userItemList.remove(sellItemInt - 1);
		System.out.println("\n");
		System.out.println("You have sold an Item");
		gameUserInput.gameEnterContinue();
	}
	
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
	
	public void resetMonsterStats() {
		if (userMonsterList.size() > 0) {
			for (Monster monster : userMonsterList) {
				monster.resetMonsterStats();
			}
		}
	}
	
	public void userGameOver(int gameEndReason) {
		if (gameEndReason == 1) {
			System.out.println("Well done " + userGameName + ", you have completed the Game.");
			gameUserInput.gameEnterContinue();
			System.out.println("Your Final Gold Amount is " + userGoldAmount + " Gold.");
			gameUserInput.gameEnterContinue();
			System.out.println("Thank you for playing our game :)");
			gameUserInput.gameEnterContinue();
		} else if (gameEndReason == 2) {
			System.out.println("Unfortunately " + userGameName + ", you have run out of Monsters and can't buy anymore.");
			gameUserInput.gameEnterContinue();
			System.out.println("Your Final Gold Amount is " + userGoldAmount + " Gold.");
			gameUserInput.gameEnterContinue();
			System.out.println("Thank you for playing our game :)");
			gameUserInput.gameEnterContinue();
		}
	}

	public void mainMenu() {
		
		startNewGame(); //runs a function to query the user for game starting information
		 //initalized RandomEvent
		int gameEndReason = 1;
		Battle battle = new Battle(this); //Makes a new Battle Object for the first day
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
				if (!randEvent.main()) System.out.println("No random event has occured tonight");
				System.out.println("Day " + gameDay + " is over\n");
				
				userGameShop.resetShopStock();
				battle = new Battle(this); //creates a new battle object when the user sleeps for the next day
				resetMonsterStats();
				gameDay += 1;
			}
		}
		System.out.println("Game Over"); //When current day is past the max days game ends.
		userGameOver(gameEndReason);
	}
	
	public static void main(String[] args) {
		GameEnviro testGame = new GameEnviro();
		testGame.mainMenu();
	}
}
