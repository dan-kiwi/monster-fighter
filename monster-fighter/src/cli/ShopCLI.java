package cli;

import java.util.Scanner;

import items.Items;
import mainenviro.GameEnviro;
import mainenviro.Shop;
import monster.Monster;

public class ShopCLI {
	
	 private GameEnviro game;
	 private Shop shop;
	 private Scanner userInput;
	 
	 ShopCLI(GameEnviro game) {
		 this.game = game;
		 this.shop = game.getUserGameShop();
		 this.userInput = new Scanner(System.in);
	 }
	 
	/**
	 * Outputs the monsters available to purchase
	 */
	public void shopDisplayMonsters() {
		int counter = 1;
		for (Monster userMonster : shop.getShopMonsterList()) {
			System.out.println(counter + ". " + userMonster.getMonsterName() + " - Health: " +
								userMonster.getCurrHealth() + " , Attack: " + userMonster.getCurrAttack() 
								+ " , Defence: " + userMonster.getCurrDefence());
			System.out.println("Cost: " + userMonster.getMonsterBuyPrice() + " Gold\n");
			counter += 1;
		}
	}
	
	/**
	 * Outputs the monsters available to purchase
	 */
	public void shopDisplayItems() {
		int counter = 1;
		for (Items userItem : shop.getShopItemList()) {
			System.out.println(counter + ". " + userItem.getItemName() + " - " 
							+ userItem.getItemDescription());
			System.out.println("Cost: " + userItem.getItemBuyPrice() + " Gold\n");
			counter += 1;
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
	 * Allow's the user to purchase a new monster
	 */
	public void userBuyMonsterDisplay(){
		shopDisplayMonsters();
		System.out.println("Please Enter a number to buy the corresponding Monster");
		System.out.println("Or enter nothing to go back");
		int buyMonsterInt = gameGetIntEnter(game.getUserGameShop().getShopMonsterList().size());
		if (buyMonsterInt == 0) {
			return;
		}
		userBuyMonster(buyMonsterInt);
	}
	
	/**
	 * Allow's the user to purchase a new item
	 */
	public void userBuyItemDisplay() {
		shopDisplayItems();
		System.out.println("Please Enter a number to buy the corresponding Item");
		System.out.println("Or enter nothing to go back");
		int buyItemInt = gameGetIntEnter(game.getUserGameShop().getShopItemList().size());
		if (buyItemInt == 0) {
			return;
		}
		userBuyItem(buyItemInt);
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
			return;
		}
		System.out.println("\n");
		System.out.println("What Monster would you like to sell?\n");
		System.out.println(game.userDisplayMonsters(true));
		System.out.println("Please Enter a number to sell the corresponding Monster");
		System.out.println("Or enter nothing to go back");
		int sellMonsterInt = gameGetIntEnter(game.getUserMonsterList().size());
		if (sellMonsterInt == 0) {
			return;
		}
		userSellMonster(sellMonsterInt);
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
	 * Processes the monster sell transaction.
	 *
	 * @param sellMonsterInt, int relating to which monster in the list
	 */
	public void userSellMonster(int sellMonsterInt) {
		game.addUserGoldAmount(game.getUserMonsterList().get(sellMonsterInt - 1).getMonsterSellPrice());
		game.getUserMonsterList().remove(sellMonsterInt - 1);
		System.out.println("\n");
		System.out.println("You have sold a Monster");
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
		} else {
			System.out.println("Sorry you don't have enough gold to purchase this Item");
		}
	}
	
	/**
	 * Allow's the user to sell an item
	 */
	public void userSellItemDisplay() {
		if (game.getUserItemList().size() == 0) {
			System.out.println("You currently have no items\n");
			return;
		}
		System.out.println("What item would you like to sell?\n");
		System.out.println(game.userDisplayItems(true));
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
			} else {
				System.out.println("Sorry you don't have enough gold to purchase this monster");
			}
		} else {
			System.out.println("Sorry you already have 4 Monsters");
		}
	}
}
