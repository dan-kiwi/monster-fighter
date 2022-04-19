package mainEnviro;

import java.util.ArrayList;
import java.util.Collections;

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
	
	/**
	 * @return the userMonsterList
	 */
	public ArrayList<Monster> getUserMonsterList() {
		return userMonsterList;
	}

	/**
	 * @param userMonsterList the userMonsterList to set
	 */
	public void setUserMonsterList(ArrayList<Monster> userMonsterList) {
		this.userMonsterList = userMonsterList;
	}
	
	/**
	 * @return the userItemList
	 */
	public ArrayList<Items> getUserItemList() {
		return userItemList;
	}

	/**
	 * @param userItemList the userItemList to set
	 */
	public void setUserItemList(ArrayList<Items> userItemList) {
		this.userItemList = userItemList;
	}

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
		System.out.println("Today is Day " + gameDay);
		System.out.println("You have " + (maxGameDays - gameDay) + " Day(s) remaining");
		System.out.println("\n");
		gameUserInput.gameEnterContinue();
	}
	
	public void viewGameMonsters() {
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
		System.out.println("Press enter to go back");
		int selection = gameUserInput.gameViewMonster();
		if (selection == 1) {
			System.out.println("Which monster would you like to swap? (Enter Position number)");
			int monsterSwapChoice = gameUserInput.gameRenameSwapMonster(userMonsterList.size());
			System.out.println("Which monster would you like to swap this with? (Enter Position number)");
			int monsterSwapToChoice = gameUserInput.gameRenameSwapMonster(userMonsterList.size());
			Collections.swap(userMonsterList, monsterSwapChoice - 1, monsterSwapToChoice - 1);
		} else if (selection == 2) {
			System.out.println("Which monster would you like to rename? (Enter Position number)");
			int monsterRenameChoice = gameUserInput.gameRenameSwapMonster(userMonsterList.size());
			userMonsterList.get(monsterRenameChoice - 1).setMonsterName(gameUserInput.startNewMonsterName());
		}
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
		System.out.println("\n");
		System.out.println("Welcome to the Shop " + userGameName);
		System.out.println("You have " + userGoldAmount + " Gold Pieces");
		System.out.println("What would you like to do?\n");
		System.out.println("1. View Monsters for Sale");
		System.out.println("2. View Items for Sale");
		System.out.println("3. Sell a Monster or Item to the shop");
		System.out.println("Press enter to go back");
		int selection = gameUserInput.gameGetIntEnter(3);
		if (selection == 1) {
			userBuyMonsterDisplay();
		} else if (selection == 2) {
			userBuyItemDisplay();
		} else if (selection == 3) {
			userSellMonsterItemDisplay();
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

	public GameEnviro() {
		
		startNewGame(); //runs a function to query the user for game starting information
		
		while (gameDay <= maxGameDays) {	
			
			/*
			Just a bunch of if/elif checking user inputs, the game day only advances after
			sleep is chosen. We can put all of the events under these statements. The battle
			object under 4 etc.
			*/
			Battle battle = new Battle(this); //creates a new battle object everyday
			int userAction = gameUserInput.basicOptions();
			if (userAction == 1) {
				System.out.println("You have Chosen to View Gold/Days");
				viewGameInfo();
			} else if (userAction == 2){
				System.out.println("You have Chosen to View your Monsters");
				viewGameMonsters();
			} else if (userAction == 3) {
				System.out.println("You have Chosen to View Inventory");
			} else if (userAction == 4) {
				System.out.println("You have Chosen to View Battles");
				battle.mainMenu();
			} else if (userAction == 5) {
				System.out.println("You have Chosen to Visit the Shop");
				viewGameShop();
			} else if (userAction == 6) {
				System.out.println("You have Chosen to Sleep");
				System.out.println("Random Event may happen");
				System.out.println("Day " + gameDay + " is over\n");
				userGameShop.resetShopStock();
				gameDay += 1;
			}
		}
		System.out.println("Game Over"); //When current day is past the max days game ends.
	}
	
	public static void main(String[] args) {
		GameEnviro testgame = new GameEnviro();
	}
}
