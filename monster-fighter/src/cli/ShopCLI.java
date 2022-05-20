package cli;

import items.Items;
import mainenviro.GameEnviro;
import mainenviro.Shop;
import monster.Monster;

public class ShopCLI {
	
	 private GameEnviro game;
	 private Shop shop;
	 
	 ShopCLI(GameEnviro game) {
		 this.game = game;
		 this.shop = game.getUserGameShop();
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
}
