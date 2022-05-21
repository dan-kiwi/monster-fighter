package mainenviro;

import java.util.ArrayList;
import java.util.List;
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
	

	private String[] masterMonsters;
	private List<Monster> userMonsters;
	private Random rand;
	
	/**
	 * Instantiates a new random event.
	 *
	 * @param game, the game
	 */
	public RandomEvent(String[] masterMonsterList, List<Monster> userMonsterList) {
		this.masterMonsters = masterMonsterList;
		this.userMonsters = userMonsterList;
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
	public boolean monsterLeaves(Monster monster) {
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
	public boolean monsterLevelUp(Monster monster) {
		int chance = (10 - monster.getDailyBattlesWon() <= 0) ? 1 : 10 - monster.getDailyBattlesWon(); //each battle won increases the chance of level up
		if (rand.nextInt(chance) == 0) {
			monster.levelUp();
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
	 * @param boolean, True if used for GUI False if for cmdLine
	 * @return true, if monster is added
	 */
	public boolean willAddMonster() {
		boolean addMonster = false;
		int numMonsters = userMonsters.size();
		if (numMonsters == 0) {
			if (rand.nextInt(5) == 0) addMonster = true; //20% chance
		} else if (numMonsters == 1) {
			if (rand.nextInt(10) == 0) addMonster = true; //10% chance
		} else if (numMonsters == 2) {
			if (rand.nextInt(20) == 0) addMonster = true; //5% chance
		} else if (numMonsters == 3) {
			if (rand.nextInt(50) == 0) addMonster = true; //2% chance
		} //0% for four monsters
		return addMonster;
	}
	
	public String addMonster() {
		return masterMonsters[rand.nextInt(masterMonsters.length)];
	}
	
}
