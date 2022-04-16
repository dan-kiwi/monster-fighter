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

public class Shop {
	
	private static final int amountOfItemsAvailable = 3;
	private static final int amountOfMonstersAvailable = 3;
	
	private ArrayList<String> availableMonsterList = new ArrayList<String>();
	private ArrayList<Monster> shopMonsterList = new ArrayList<Monster>();
	private ArrayList<String> availableItemList = new ArrayList<String>();
	private ArrayList<Items> shopItemList = new ArrayList<Items>();
	private Random rand = new Random();
	
	
	public Shop() {
		this.setupAvailableMonsters();
		this.setupAvailableItems();
		this.setupShopStock();
	}
	
	public void setupAvailableMonsters() {
		//this fills an arraylist with the names of all possible monsters you can buy
		availableMonsterList.add("Imp");
		availableMonsterList.add("Gnome");
		availableMonsterList.add("Goblin");
		availableMonsterList.add("Unicorn");
		availableMonsterList.add("Griffin");
		availableMonsterList.add("Dragon");
	}
	
	public void setupAvailableItems() {
		//this fills an arraylist with the names of all possible items you can buy
		availableItemList.add("HealthPotion");
		availableItemList.add("DefencePotion");
		availableItemList.add("AttackPotion");
		availableItemList.add("Berries");
		availableItemList.add("Apple");
	}
	
	public void setupShopStock() {
		//this gets called on shop object creation to fill up arraylists with monsters and items to buy
		this.setupMonsterList();
		this.setupItemList();
	}
	
	public void resetShopStock() {
		//this gets called when the player sleeps to change the item and monster stock
		//Clears existing shop lists and runs the list setup again
		shopItemList.clear();
		shopMonsterList.clear();
		this.setupShopStock();
	}
	
	public void setupMonsterList() {
		//loops through 3(constant) times, gets a random index and adds a monster based on the random number 
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
	
	public void setupItemList() {
		//loops through 3(constant) times, gets a random index and adds an item based on the random number
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
	
	public ArrayList<Monster> getShopMonsterList() {
		return shopMonsterList;
	}
	
	public ArrayList<Items> getShopItemList() {
		return shopItemList;
	}
	
	
	public static void main(String[] args) {
		
		Shop test = new Shop();
		System.out.println(test.getShopMonsterList().toString());
		System.out.println(test.getShopItemList().toString());
	}
}
