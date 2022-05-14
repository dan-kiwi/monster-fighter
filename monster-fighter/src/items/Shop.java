package items;

import java.util.ArrayList;
import java.util.Random;

import monster.Dragon;
import monster.Gnome;
import monster.Goblin;
import monster.Griffin;
import monster.Imp;
import monster.Monster;
import monster.Unicorn;

// TODO: Auto-generated Javadoc
/**
 * The Class Shop. It facilitates the user being able to purchase monsters, potions and food. 
 */
public class Shop {
	
	private static final int amountOfItemsAvailable = 3;
	private static final int amountOfMonstersAvailable = 3;
	
	private ArrayList<String> availableMonsterList = new ArrayList<String>();
	private ArrayList<Monster> shopMonsterList = new ArrayList<Monster>();
	private ArrayList<String> availableItemList = new ArrayList<String>();
	private ArrayList<Items> shopItemList = new ArrayList<Items>();
	private Random rand = new Random();
	
	
	/**
	 * Instantiates a new shop.
	 */
	public Shop() {
		this.setupAvailableMonsters();
		this.setupAvailableItems();
		this.setupShopStock();
	}
	
	/**
	 * This fills an arraylist with the names of all possible monsters you can buy
	 */
	public void setupAvailableMonsters() {
		
		availableMonsterList.add("Imp");
		availableMonsterList.add("Gnome");
		availableMonsterList.add("Goblin");
		availableMonsterList.add("Unicorn");
		availableMonsterList.add("Griffin");
		availableMonsterList.add("Dragon");
	}
	
	/**
	 * This fills an arraylist with the names of all possible items you can buy
	 */
	public void setupAvailableItems() {
		
		availableItemList.add("HealthPotion");
		availableItemList.add("DefencePotion");
		availableItemList.add("AttackPotion");
		availableItemList.add("Berries");
		availableItemList.add("Apple");
	}
	
	/**
	 * This gets called on shop object creation to fill up arraylists with monsters and items to buy
	 */
	public void setupShopStock() {
		
		this.setupMonsterList();
		this.setupItemList();
	}
	
	/**
	 * This gets called when the player sleeps to change the item and monster stock
	 * Clears existing shop lists and runs the list setup again
	 */
	public void resetShopStock() {
		
		shopItemList.clear();
		shopMonsterList.clear();
		this.setupShopStock();
	}
	
	/**
	 * Loops through 3(constant) times, gets a random index and adds a monster based on the random number 
	 */
	public void setupMonsterList() {
		
		for (int i = 0; i < amountOfMonstersAvailable; i++) {
			int selection = rand.nextInt(availableMonsterList.size());
			String monsterName = availableMonsterList.get(selection);
			if (monsterName.equals("Imp")){
				Monster randImp = new Imp();
				shopMonsterList.add(randImp);
			} else if (monsterName.equals("Gnome")) {
				Monster randGnome = new Gnome();
				shopMonsterList.add(randGnome);
			} else if (monsterName.equals("Goblin")) {
				Monster randGoblin = new Goblin();
				shopMonsterList.add(randGoblin);
			} else if (monsterName.equals("Unicorn")) {
				Monster randUnicorn = new Unicorn();
				shopMonsterList.add(randUnicorn);
			} else if (monsterName.equals("Griffin")) {
				Monster randGriffin = new Griffin();
				shopMonsterList.add(randGriffin);
			} else if (monsterName.equals("Dragon")) {
				Monster randDragon = new Dragon();
				shopMonsterList.add(randDragon);
			}
		}
	}
	
	/**
	 * Loops through 3(constant) times, gets a random index and adds an item based on the random number
	 */
	public void setupItemList() {
		for (int i = 0; i < amountOfItemsAvailable; i++) {
			int selection = rand.nextInt(availableItemList.size());
			String itemName = availableItemList.get(selection);
			if (itemName.equals("HealthPotion")){
				Items randHealthPotion = new HealthPotion();
				shopItemList.add(randHealthPotion);
			} else if (itemName.equals("DefencePotion")) {
				Items randDefencePotion = new DefencePotion();
				shopItemList.add(randDefencePotion);
			} else if (itemName.equals("AttackPotion")) {
				Items randAttackPotion = new AttackPotion();
				shopItemList.add(randAttackPotion);
			} else if (itemName.equals("Berries")) {
				Items randBerries = new Berries();
				shopItemList.add(randBerries);
			} else if (itemName.equals("Apple")) {
				Items randApple = new Apple();
				shopItemList.add(randApple);
			}
		}
	}
	
	/**
	 * Outputs the monsters available to purchase
	 */
	public void shopDisplayMonsters() {
		int counter = 1;
		for (Monster userMonster : shopMonsterList) {
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
		for (Items userItem : shopItemList) {
			System.out.println(counter + ". " + userItem.getItemName() + " - " 
							+ userItem.getItemDescription());
			System.out.println("Cost: " + userItem.getItemBuyPrice() + " Gold\n");
			counter += 1;
		}
	}
	
	/**
	 * Gets the lowest buy price of all the monsters available in todays shop
	 *
	 * @return the int
	 */
	public int shopGetCheapestMonsterPrice() {
		int cheapest = 0;
		for (Monster monster : shopMonsterList) {
			if (cheapest == 0 || cheapest > monster.getMonsterBuyPrice()) {
				cheapest = monster.getMonsterBuyPrice();
			}
		}
		return cheapest;
	}
	
	/**
	 * Gets the shop monster list.
	 *
	 * @return the shop monster list
	 */
	public ArrayList<Monster> getShopMonsterList() {
		return shopMonsterList;
	}
	
	/**
	 * Gets the shop item list.
	 *
	 * @return the shop item list
	 */
	public ArrayList<Items> getShopItemList() {
		return shopItemList;
	}
}
