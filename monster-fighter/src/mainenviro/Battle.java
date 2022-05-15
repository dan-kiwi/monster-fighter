package mainenviro;

import monster.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import items.Items;

/**
 * The battle class is the class that allow's the user to fight enemy monster's
 * This class has the ability to reduce health of user's monster, collect gold and use items
 */
public class Battle {
	
	//Constant fight options
	private static final String[] fightOptions = {"Attack", "Energetic Attack", "Defend", 
											"Energetic Defend", "Use Item", "Quit"};
	
	//Battle variables
	private GameEnviro game;
	private ArrayList<Monster> enemies;
	private ArrayList<Monster> potentialBattles = new ArrayList<Monster>();
	private ArrayList<Monster> userMonsterList;
	private Monster currEnemy;
	private Monster currUser;
	private Random rand = new Random();
	
	/**
	 * Main Battle Constructor
	 * @param game, take's the game environment to be able to get user's monsters and items
	 * Creates a random number of battles between 3 & 5 from random monsters
	 * Since a new battle class is created daily, new monster's appear daily
	 */
	Battle(GameEnviro game) {
		this.game = game;
		this.userMonsterList = this.game.getUserMonsterList();
		this.enemies = GameEnviro.getMasterMonsterList();
		int randNumBattles = rand.nextInt(3, 5);
		for (int i = 0; i < randNumBattles; i++) { // creates random number of battles between 3 & 5
			int randIndexEnemy = rand.nextInt(enemies.size()); 
			Monster curr = enemies.get(randIndexEnemy);
			if (!potentialBattles.contains(curr)) {
				potentialBattles.add(curr);
			} else {
				i--;
			}
		}
	}
	
	//----------------------------- Getters/Setters/Changers----------------------------------
	
	/**
	 * Gets the battles daily enemy list.
	 *
	 * @return the daily enemy Arraylist
	 */
	public ArrayList<Monster> getPotentialBattles() {
		return potentialBattles;
	}
	
	/**
	 * Gets the users currently selected Monster.
	 *
	 * @return the Monster chosen to fight
	 */
	public Monster getCurrUser() {
		return currUser;
	}
	
	/**
	 * Sets the Users Chosen Monster for the fight.
	 *
	 * @param tempMonster, the user's chosen Monster
	 */
	public void setCurrUser(Monster tempMonster) {
		currUser = tempMonster;
	}
	
	/**
	 * Gets the users currently selected Enemy Monster.
	 *
	 * @return the Enemy Monster chosen to fight
	 */
	public Monster getCurrEnemy() {
		return currEnemy;
	}
	
	/**
	 * Sets the Enemy Chosen Monster for the fight.
	 *
	 * @param tempMonster, the user's chosen Enemy Monster to fight
	 */
	public void setCurrEnemy(Monster tempMonster) {
		currEnemy = tempMonster;
	}
	
	//-----------------------------------------------------------------------------------------
	
	/**
	 * Method to allow users to select an enemy to battle
	 * Uses userSelectionInput to obtain a user choice
	 */
	public void selectEnemies() {
		if (potentialBattles.size() == 0) {
			System.out.println("\n");
			System.out.println("There are no more Battles available today\n");
			return;
		}
		System.out.println();
		System.out.println("Choose your opponent:");
		for (int i = 1; i <= potentialBattles.size(); i++) {
			Monster currEnemy = potentialBattles.get(i-1);
			System.out.println(i + ": " + currEnemy + "\n");
		}

		int userChoice = userSelectionInput(potentialBattles.size());
		currEnemy = potentialBattles.get(userChoice - 1);
		System.out.println();
		System.out.println("You have choosen " + currEnemy.getMonsterName() + " as your opponent");		
		System.out.println();
	}
	
	
	/**
	 * Method to allow users to select one of their monsters to fight
	 * Uses userSelectionInput to obtain a user choice
	 */
	public void selectUser() {
		System.out.println();
		System.out.println("Choose your monster to fight:");
		for (int i = 1; i <= userMonsterList.size(); i++) {
			Monster currMon = userMonsterList.get(i-1);
			System.out.println(i + ": " + currMon + "\n");
		}
		int userChoice = userSelectionInput(userMonsterList.size());
		if (userChoice >= 0) {
			if (userMonsterList.get(userChoice - 1).getCurrHealth() > 0) {
				currUser = userMonsterList.get(userChoice - 1);
				System.out.println("You have choosen " + currUser.getMonsterName() + " as your monster");
				System.out.println();
			} else {
				System.out.println("You cannot select this monster as it has no health left\n");
			}
		}
	} 
	
	/**
	 * Method to get a user choice from 0 to a given input
	 * 
	 * Uses scanner to obtain user input
	 * @param size, the number of choices
	 * @return userChoice, an integer representing the choice
	 */
	public int userSelectionInput(int size) {
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		int userChoice = -1;
		
		do {
			System.out.print("Enter a number between 1 and " + size + ": ");
			try {
				userChoice = userInput.nextInt();
				if (userChoice < 1 || userChoice > size) {
					userChoice = -1;
					System.out.println("Error: Wrong input");
				}
			} catch (InputMismatchException e) {
				userInput.nextLine();
			}
		} while (userChoice == -1);
		
		return userChoice;
	}
	
	
	/**
	 * Method to obtain the attack and defense statistics given a monsters fight choice
	 * 
	 * @param user, a monster within the fight
	 * @param choice, which fight choice the monster choose
	 * @return attackDefence, an array of size two, [0] is attack statistics, [1] is defence statistics
	 */
	public int[] getAttackDefence (Monster user, int choice) {
		int[] attackDefence = {0, 0}; //0 = Attack, 1 = Defence
		if (choice == 1) { //Energetic Attack
			attackDefence[0] = user.getCurrAttack() * 5 / 4;
			attackDefence[1] = user.getCurrDefence() / 2;
			user.setEnergy(user.getEnergy() - 1);
		} else if (choice == 0) { //Normal Attack
			attackDefence[0] = user.getCurrAttack();
		} else if (choice == 3) { //Energetic Defence
			attackDefence[0] = user.getCurrAttack() / 2;
			attackDefence[1] = user.getCurrAttack() * 5 / 4;
			user.setEnergy(user.getEnergy() - 1);
		} else  { //Normal Defence
			attackDefence[1] = user.getCurrDefence();
		}
		return attackDefence;
	}
	
	
	/**
	 * Method to get the users fight choice for the current round of the battle
	 * 
	 * Checks user's monster's energy level to ensure energetic fight options are appropriately shown
	 * Uses userSelectionInput method to obtain the users choice
	 * @return userChoice, an integer representing the user choice
	 */
	public int getUserFight() {
		System.out.println("Choose a fight option");
		int userChoice;
		
		if (currUser.getEnergy() > 0) {
			for (int i = 1; i <= fightOptions.length; i++) {
				System.out.println(i + ": " + fightOptions[i-1]);
			}
			userChoice = userSelectionInput(fightOptions.length);
		} else {
			for (int i = 1; i <= (fightOptions.length / 2); i ++) {
				System.out.println(i + ": " + fightOptions[(i-1)*2]);
			}
			System.out.println(4 + ": " + fightOptions[5]);
			int userInput = userSelectionInput(fightOptions.length);
			if (userInput == 1) {
				userChoice = 1;
			} else if (userInput == 2) {
				userChoice = 3;
			} else if (userInput == 3) {
				userChoice = 5;
			} else {
				userChoice = 6;
			}
		}
		return userChoice;
	}
	
	
	/**
	 * Method to randomly select the enemy's fight option
	 * 
	 * @return enemyChoice, an integer representing the enemies choice
	 */
	public int getEnemyFight() {
		int enemyChoice;
		if (currEnemy.getEnergy() > 0) {
			enemyChoice = rand.nextInt(fightOptions.length - 2);
		} else {
			enemyChoice = rand.nextInt((fightOptions.length - 2) / 2);
			enemyChoice = enemyChoice * 2;
		}
		String enemyMove = fightOptions[enemyChoice].toLowerCase();
		System.out.println("Your opponent has choosen " + enemyMove + " as their move");
		return enemyChoice;
	}
	
	/**
	 * Method to randomly select the enemy's fight option
	 * Made for the GUI to Use
	 * Can be 0 to 3 if enemy has Energy
	 * Can be 0 or 2 if enemy has no energy
	 * 
	 * @return enemyChoice, an integer representing the enemies choice
	 */
	public int getGuiEnemyChoice() {
		int enemyChoice;
		if (currEnemy.getEnergy() > 0) {
			enemyChoice = rand.nextInt(fightOptions.length - 2);
		} else {
			enemyChoice = rand.nextInt((fightOptions.length - 2) / 2);
			enemyChoice = enemyChoice * 2;
		}
		return enemyChoice;
	}
	
	/**
	 * Method to fight the user's monster against an enemy monster.
	 * Call's getUserFight to determine method chosen
	 * Call's getAttackDefence to determine damage inflicted on both monsters
	 * Should a monster die, the match ends, returned to the main screen
	 */
	public void fight() {
		boolean fighting = true;
		System.out.println();
		System.out.println("Two Monsters go head to head:");
		System.out.println();
		
		while (fighting) {
			System.out.println("Your monster:");
			System.out.println(currUser);
			System.out.println("Your opponent:");
			System.out.println(currEnemy);
			int userFightIndex = this.getUserFight();
			if (userFightIndex == 5) {
				selectItem();
				continue;
			} else if (userFightIndex == 6){
				fighting = false;
				System.out.println("Quiting match");
				System.out.println();
				continue;
			}
			int enemyFightIndex = this.getEnemyFight();
			System.out.println();
			int[] userAttackDefence = this.getAttackDefence(currUser, userFightIndex - 1);
			int[] enemyAttackDefence = this.getAttackDefence(currEnemy, enemyFightIndex);
			
			//game.getMonsterDifficulty() makes it so on easy enemies hit your monster for less damage 
			//and more for hard difficulty
			int userChangeHealth = (int) (((userAttackDefence[1] - enemyAttackDefence[0] > 0) ? 0 
								   : userAttackDefence[1] - enemyAttackDefence[0]) * game.getMonsterDifficulty());
			currUser.changeCurrHealth(userChangeHealth);
			
			int enemyChangeHealth = ((enemyAttackDefence[1] - userAttackDefence[0] > 0) ? 0 
					   				: enemyAttackDefence[1] - userAttackDefence[0]);
			currEnemy.changeCurrHealth(enemyChangeHealth);

			System.out.println("Your monster was dealt " + userChangeHealth + " damage");
			System.out.println("Your opponent was dealt " + enemyChangeHealth + " damage");
			System.out.println();
			
			if (currEnemy.getCurrHealth() <= 0) {
				//Gold won is based on enemy's buy price, randomness between 80% and 120% and user game difficulty
				int goldWon = (int) ((currEnemy.getMonsterBuyPrice() * rand.nextDouble(0.8, 1.2)) * game.getGoldDifficulty());
				game.changeUserGoldAmount(goldWon);
				game.addScoreForMonsterKill(); //Adds a preset amount of points to the user for each monster kill
				currUser.addDailyBattlesWon(1);
				
				fighting  = false;
				potentialBattles.remove(currEnemy); //remove the dead enemies from the pool of available battles
				currEnemy = null;
				
				System.out.println("You have defeated this opponent! You have won " + goldWon + " gold.");
				System.out.println("Choose another opponent or quit the battle arena");
			}
			if (currUser.getCurrHealth() <= 0) {
				currUser.setCurrHealth(0);
				fighting = false;
				System.out.println("Your monster has lost all it's health. Choose another monster or quit the battle arena");
				currUser = null;
			}
		}
	}
	
	
	/**
	 * Print's user monster and enemy monster.
	 * Will state if a user has not selected either monster
	 */
	public void viewMonsters() {
		String userLine;
		String monsterLine;
		System.out.println();
		if (currUser == null) {
			userLine = "You have not selected a monster yet \n";
		} else {
			userLine = "Your monster: \n" + currUser.toString();
		}
		System.out.println();
		if (currEnemy == null) {
			monsterLine = "You have not selected an opponent yet \n";
		} else {
			monsterLine = "Your opponent: \n" + currEnemy.toString();
		}
		System.out.println(userLine + monsterLine);
	}
	
	
	/**
	 * Allows an item to be selected from the user's list
	 * If the user has no items, it tell's the user and returns
	 * Once an item is used, it is applied to the monster by calling a method on items
	 */
	public void selectItem() {
		Items item;
		ArrayList<Items> userItems = game.getUserItemList();
		if (userItems.size() == 0) {
			System.out.println("You have no items to use");
			return;
		}
		System.out.println("Select item: ");
		for (int i = 1; i <= userItems.size(); i++) {
			System.out.println(i + ": " + userItems.get(i-1));
		}
		System.out.println((userItems.size() + 1) + ": Quit");
		int userChoice = userSelectionInput(userItems.size() + 1);
		if (userChoice <= userItems.size()) {
			item = userItems.get(userChoice - 1);
			userItems.remove(userChoice - 1);
			item.useItemOnMonster(currUser);
			System.out.println(item.getItemName() + " has been used");
			System.out.println(currUser);
		} else {
			System.out.println("No item selected");
		}
	}
	
	/**
	 * Help menu to explain all of the different fighting methods
	 */
	public void helpMenu() {
		System.out.println();
		System.out.println("Total damage inflicted on opponent is your attack power minus their defence power for this round and vice versa");
		System.out.println("Attack: 100% of attack power and 0% defence power");
		System.out.println("Energetic Attack: 125% of attack power and 50% of defence power");
		System.out.println("Defend: 0% of attack power and 100% defence power");
		System.out.println("Energetic Defence: 50% of attack power and 125% of defence power");
		System.out.println("Use item: No damage is inflicted on either party, can use a potion from your inventory");
		System.out.println();
	}
	
	
	/**
	 * The main menu that the user will interact with
	 * Allows all the users to call on other methods
	 */
	public void mainMenu() {
		boolean playing = true;
		boolean bothMonsters = false;
		int userChoice;
		System.out.println();
		System.out.println("Welcome to the battle arena!");
		System.out.println("Please choose an option");
		while (playing) {
			System.out.println("1: Select an opponent");
			System.out.println("2: Select your monster");
			System.out.println("3: View your monster and your opponent");
			System.out.println("4: Help Menu");
			if (!bothMonsters) {
				System.out.println("5: Quit");
				userChoice = userSelectionInput(5);
			} else {
				System.out.println("5: Fight your monster against your selected opponent");
				System.out.println("6: Quit");
				userChoice = userSelectionInput(6);
			}
			if (userChoice == 1) {
				selectEnemies();
			} else if (userChoice == 2) {
				selectUser();
			} else if (userChoice == 3) {
				viewMonsters();
			} else if (userChoice == 4) {
				helpMenu();
			} else if (userChoice == 5 && bothMonsters) {
				fight();
			} else {
				playing = false;
			}
			if (currUser != null && currEnemy != null) {
				bothMonsters = true;
			} else {
				bothMonsters = false;
			}
		}
	}
}
