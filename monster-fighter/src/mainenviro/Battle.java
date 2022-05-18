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
	private int[] currEnemyAttackDefence;
	private int[] currUserAttackDefence;
	
	/**
	 * Main Battle Constructor
	 * @param game, take's the game environment to be able to get user's monsters and items
	 * Creates a random number of battles between 3 & 5 from random monsters
	 * Since a new battle class is created daily, new monster's appear daily
	 */
	public Battle(GameEnviro game) {
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
	 * Made for the GUI to Use
	 * Can be 0 to 3 if enemy has Energy
	 * Can be 0 or 2 if enemy has no energy
	 * 
	 * @return enemyChoice, an integer representing the enemies choice
	 */
	public int getEnemyChoice() {
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
	 * Damage dealt to a monster given the current attack/defence choice of the user and the enemy.
	 *
	 * @param monster the monster
	 * @param monsterDefence the monster defence
	 * @param opponentAttack the opponent attack
	 * @return the damage dealt
	 */
	public int damageDealt(Monster monster, int monsterDefence, int opponentAttack) {
		int damage = (opponentAttack - monsterDefence < 0) ? 0 : opponentAttack - monsterDefence;
		monster.changeCurrHealth(damage);
		return damage;
	}
	
	/**
	 * The damage inflicted on the user by the enemy.
	 *
	 * @return the damage dealt
	 */
	public int userDamage() {
		return damageDealt(this.currUser, this.currUserAttackDefence[1], this.currEnemyAttackDefence[0]);
	}
	
	/**
	 * The damage inflicted on the enemy by the user
	 *
	 * @return the damage dealt
	 */
	public int enemyDamage() {
		return damageDealt(this.currEnemy, this.currEnemyAttackDefence[1], this.currUserAttackDefence[0])
	}
}