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
	private List<Monster> enemies;
	private List<Monster> potentialBattles = new ArrayList<Monster>();
	private List<Monster> userMonsterList;
	private Monster currEnemy;
	private Monster currUser;
	private Random rand = new Random();
	private int currEnemyChoice;
	private int currUserChoice;
	
	/**
	 * Main Battle Constructor
	 * @param game, take's the game environment to be able to get user's monsters and items
	 * Creates a random number of battles between 3 & 5 from random monsters
	 * Since a new battle class is created daily, new monster's appear daily
	 */
	public Battle(GameEnviro game) {
		this.game = game;
		this.userMonsterList = this.game.getUserMonsterList();
		this.enemies = game.getMasterMonsterList();
		int randNumBattles = rand.nextInt(3, 6);
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
	public List<Monster> getPotentialBattles() {
		return potentialBattles;
	}
	
	/**
	 * @return the fightoptions
	 */
	public static String[] getFightOptions() {
		return fightOptions;
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
	

	/**
	 * Sets the current user fight choice.
	 *
	 * @param currUserChoice the new current user choice
	 */
	public void setUserChoice(int currUserChoice) {
		this.currUserChoice = currUserChoice;
	}
	
	//-----------------------------------------------------------------------------------------
	
	
	
	
	/**
	 * Method to obtain the attack and defense statistics given a monsters fight choice
	 * 
	 * @param user, a monster within the fight
	 * @param choice, which fight choice the monster choose
	 * @return attackDefence, an array of size two, [0] is attack statistics, [1] is defence statistics
	 */
	public int[] getAttackDefence (Monster monster, int choice) {
		int[] attackDefence = {0, 0}; //0 = Attack, 1 = Defence
		if (choice == 1) { //Energetic Attack
			attackDefence[0] = monster.getCurrAttack() * 5 / 4;
			attackDefence[1] = monster.getCurrDefence() / 2;
			monster.setEnergy(monster.getEnergy() - 1);
		} else if (choice == 0) { //Normal Attack
			attackDefence[0] = monster.getCurrAttack();
		} else if (choice == 3) { //Energetic Defence
			attackDefence[0] = monster.getCurrAttack() / 2;
			attackDefence[1] = monster.getCurrAttack() * 5 / 4;
			monster.setEnergy(monster.getEnergy() - 1);
		} else  { //Normal Defence
			attackDefence[1] = monster.getCurrDefence();
		}
		return attackDefence;
	}
	
	/**
	 * Method to randomly select the enemy's fight option
	 * 
	 * Can be 0 to 3 if enemy has Energy
	 * Can be 0 or 2 if enemy has no energy
	 * 
	 * @return enemyChoice, an integer representing the enemies choice
	 */
	public String setEnemyChoice() {
		int enemyChoice;
		if (currEnemy.getEnergy() > 0) {
			enemyChoice = rand.nextInt(fightOptions.length - 2);
		} else {
			enemyChoice = rand.nextInt((fightOptions.length - 2) / 2);
			enemyChoice = enemyChoice * 2;
		}
		this.currEnemyChoice = enemyChoice;
		return fightOptions[enemyChoice];
	}
	
	public String getStringUserChoice() {
		return fightOptions[currUserChoice];
	}
	
	/**
	 * Calculate damage.
	 *
	 * @param monsterDefence the monster defence
	 * @param opponentAttack the opponent attack
	 * @return the damage dealt
	 */
	public int calculateDamage(int monsterDefence, int opponentAttack) {
		return (opponentAttack - monsterDefence < 0) ? 0 : opponentAttack - monsterDefence;
	}


	/**
	 * Damage dealt to user monster. Take's account of the monster difficulty.
	 * Inflicts the health change in this method
	 *
	 * @param monsterDefence the monster defence
	 * @param opponentAttack the opponent attack
	 * @return the damagedealt
	 */
	public int userDamage(int monsterDefence, int opponentAttack) {
		int damage = (int) (calculateDamage(monsterDefence, opponentAttack) * game.getMonsterDifficulty());
		currUser.changeCurrHealth(-damage);
		System.out.println("User: " + damage);
		return damage;
	}
	
	/**
	 * Damage dealt to the enemy monster.
	 * Inflicts the health change in this method
	 *
	 * @param monsterDefence the monster defence
	 * @param opponentAttack the opponent attack
	 * @return the int
	 */
	public int enemyDamage(int monsterDefence, int opponentAttack) {
		int damage = calculateDamage(monsterDefence, opponentAttack);
		currEnemy.changeCurrHealth(-damage);
		System.out.println("Enemy: " + damage);
		return damage;
	}

	
	/**
	 * Fights the two monster's based on their current fight choices.
	 * Calls on other methods to calculate the damage dealt to both monsters
	 *
	 * @return the int[], the damage inflicted on both monsters
	 * [0] is the damage dealt to the user's monster
	 * [1] is the damage dealt to the enemy's monster
	 */
	public int[] fight() {
		int[] damage = new int[2];
		int[] userAttackDefence = getAttackDefence(this.currUser, this.currUserChoice);
		int[] enemyAttackDefence = getAttackDefence(this.currEnemy, this.currEnemyChoice);
		System.out.println(Arrays.toString(userAttackDefence));
		System.out.println(Arrays.toString(enemyAttackDefence));
		damage[0] = userDamage(userAttackDefence[1], enemyAttackDefence[0]);
		damage[1] = enemyDamage(enemyAttackDefence[1], userAttackDefence[0]);
		return damage;
	}
	
	/**
	 * Only called after the enemy win's
	 * Calculates the gold won from battle. 
	 * Gold won is based on enemy's buy price, randomness between 80% and 120% and user game difficulty
	 *
	 * @return the gold won during battle
	 */
	public int goldWon() {
		int goldWon = (int) ((currEnemy.getMonsterBuyPrice() * rand.nextDouble(0.8, 1.2)) * game.getGoldDifficulty());
		game.changeUserGoldAmount(goldWon);
		return goldWon;
	}
	
	/**
	 * Method called when the enemy is dead, even if user also dies.
	 * Updates battle statistics
	 * Removes enemy as currEnemy and from potential battles list
	 */
	public void enemyDead() {
		game.addScoreForMonsterKill(); 
		currUser.addDailyBattlesWon(1);
		potentialBattles.remove(currEnemy); 
		currEnemy = null;
	}
	
	/**
	 * method called when the user is dead, even if the enemy also dies.
	 * Set's the currUser to null
	 */
	public void userDead() {
		currUser.setCurrHealth(0);
		currUser = null;
	}
	
	/**
	 * Checks if the enemy is dead.
	 *
	 * @return true, if dead
	 */
	public boolean checkEnemyDead() {
		if (currEnemy.getCurrHealth() <= 0) {
			enemyDead();
			return true;
		}
		return false;
	}
	
	/**
	 * Check if the user is dead.
	 *
	 * @return true, if dead
	 */
	public boolean checkUserDead() {
		if (currUser.getCurrHealth() <= 0) {
			userDead();
			return true;
		}
		return false;
	}
	
	/**
	 * Help info to inform the user what each of the battle options do
	 *
	 * @return the help information
	 */
	public String helpInfo() {
		String first = "Total damage inflicted on opponent is your attack power minus their defence power for this round and vice versa \n";
		String second = "Damage inflicted to your monster is scaled based on difficulty, 85% for easy, 115% for hard";
		String third = "Attack: 100% of attack power and 0% defence power \n";
		String forth = "Energetic Attack: 125% of attack power and 50% of defence power \n";
		String fifth = "Defend: 0% of attack power and 100% defence power \n";
		String sixth = "Energetic Defence: 50% of attack power and 125% of defence power \n";
		String seventh = "Use item: No damage is inflicted on either party, can use an item from your inventory";
		return first + second + third + forth + fifth + sixth + seventh;
	}
}