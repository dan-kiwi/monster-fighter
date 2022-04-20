package mainEnviro;

import java.util.ArrayList;
import java.util.Random;

import monster.Monster;

public class RandomEvent {
	
	private GameEnviro game;
	private ArrayList<Monster> monsters;
	private Random rand;
	
	RandomEvent(GameEnviro game) {
		this.game = game;
		this.rand = new Random();
		this.monsters = game.getUserMonsterList();
	}
	
	private boolean monsterLeaves(Monster monster) {
		int chance;
		if (monster.getCurrHealth() > 0) {
			chance = 50; //2% chance
		} else {
			chance = 25; //4% chance
		}
		if (rand.nextInt(chance) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean monsterLevelUp(Monster monster) {
		int chance = (10 - monster.getDailyBattlesWon() <= 0) ? 1 : 10 - monster.getDailyBattlesWon(); //each battle won increases the chance of level up
		if (rand.nextInt(chance) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private void addMonster() {
		boolean addedMonster = false;
		int numMonsters = game.getUserMonsterList().size();
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
			Monster tempMonster = game.getMasterMonsterList().get(rand.nextInt(game.getMasterMonsterList().size()));
			game.addMonster(tempMonster);
			System.out.println("A " + tempMonster.getMonsterName() + " has joined your team overnight");
		}
	}

	
	public void main() {
		for (int i = 0; i < monsters.size(); i++) {
			Monster currMonster = monsters.get(i);
			if (monsterLeaves(currMonster)) {
				System.out.println(currMonster.getMonsterName() + " has ran away overnight");
				monsters.remove(i);
				i--;
			} else if (monsterLevelUp(currMonster)) {
				System.out.println(currMonster.getMonsterName() + "has leveled up overnight");
				currMonster.levelUp();
			}
		}
		addMonster();
	}
}
