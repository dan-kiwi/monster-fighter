package mainEnviro;

import java.util.ArrayList;
import java.util.Random;

import monster.Monster;

public class RandomEvent {
	
	private GameEnviro game;
	private ArrayList<Monster> monsters = game.getUserMonsterList();
	private Random rand;
	
	RandomEvent(GameEnviro game) {
		this.game = game;
		this.rand = new Random();
	}
	
	private boolean monsterLeaves(Monster monster) {
		int chance;
		if (monster.getCurrHealth() > 0) {
			chance = 25; //4% chance
		} else {
			chance = 15; //6.7% chance
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
