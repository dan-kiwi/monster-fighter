package mainenviro;

import java.util.ArrayList;
import java.util.Random;

import monster.Monster;

/**
 * The Class RandomEvent. This class is called when that player chooses to continue to the next day.
 * There are two random events that can occur:
 * There is a small chance that the player will gain a random monster
 * There is a small chance that the player will lose a random monster from their collection
 * There is a small chance that a player's monster will level up overnight
 */
public class RandomEvent {
	
	private GameEnviro game;
	
	/** The user's monster list. */
	private ArrayList<Monster> monsters;
	
	/** The random variable. */
	private Random rand;
	
	/**
	 * Instantiates a new random event.
	 *
	 * @param game, the game
	 */
	RandomEvent(GameEnviro game) {
		this.game = game;
		this.monsters = game.getUserMonsterList();
		this.rand = new Random();
	}
	
	/**
	 * This method determines if a monster leaves overnight.
	 * 1% chance the monster will leave if they did not lose all health during the day
	 * 2.5% chance the monster will leave if they did not lose all health during the day
	 *
	 * @param monster the monster
	 * @return true, if monster leaves
	 */
	private boolean monsterLeaves(Monster monster) {
		int chance;
		if (monster.getCurrHealth() > 0) {
			chance = 100; //1% chance
		} else {
			chance = 40; //2.5% chance
		}
		if (rand.nextInt(chance) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method determines by random chance if a monster level's up
	 * If the monster has won more battles that day, it's more likely to level up
	 *
	 * @param monster, the monster
	 * @return true, if level's up
	 */
	private boolean monsterLevelUp(Monster monster) {
		int chance = (10 - monster.getDailyBattlesWon() <= 0) ? 1 : 10 - monster.getDailyBattlesWon(); //each battle won increases the chance of level up
		if (rand.nextInt(chance) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method determines by randomly chance if a new will be added Monster to the user's list.
	 * The chance of this occuring is inversely correlated to the number of monsters the user has
	 * The monster choosen will be random
	 * 
	 * @return true, if monster is added
	 */
	private boolean addMonster() {
		boolean addedMonster = false;
		int numMonsters = monsters.size();
		if (numMonsters == 0) {
			if (rand.nextInt(5) == 0) addedMonster = true; //20% chance
		} else if (numMonsters == 1) {
			if (rand.nextInt(10) == 0) addedMonster = true; //10% chance
		} else if (numMonsters == 2) {
			if (rand.nextInt(20) == 0) addedMonster = true; //5% chance
		} else if (numMonsters == 3) {
			if (rand.nextInt(50) == 0) addedMonster = true; //2% chance
		} //0% for four monsters
		if (addedMonster) {
			//Gets monster randomly from masterlist
			Monster tempMonster = GameEnviro.getMasterMonsterList().get(rand.nextInt(GameEnviro.getMasterMonsterList().size()));
			game.addMonster(tempMonster);
			System.out.println("A " + tempMonster.getMonsterName() + " has joined your team overnight");
		}
		return addedMonster;
	}

	
	/**
	 * This is the main method that will be called from the gameEnviro
	 * This is the only method that can be called in this class
	 * Calls all methods in the function.
	 *
	 * @return true, if an event has occurred overnight
	 */
	public void main() {
		boolean happened = false;
		for (int i = 0; i < monsters.size(); i++) {
			Monster currMonster = monsters.get(i);
			if (monsterLeaves(currMonster)) {
				System.out.println(currMonster.getMonsterName() + " has ran away overnight");
				monsters.remove(i);
				i--;
				happened = true;
			} else if (monsterLevelUp(currMonster)) {
				System.out.println(currMonster.getMonsterName() + " has leveled up overnight");
				currMonster.levelUp();
				happened = true;
			}
		}
		if (addMonster()) happened = true;
		if (!happened) System.out.println("No random event has occured tonight");
	}
}
