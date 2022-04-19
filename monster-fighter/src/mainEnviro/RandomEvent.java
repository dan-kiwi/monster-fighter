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
			chance = rand.nextInt(25); //4% chance
		} else {
			chance = rand.nextInt(15); //6.7% chance
		}
		if (chance == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void main() {
		for (int i = 0; i < monsters.size(); i++) {
			Monster currMonster = monsters.get(i);
			if (monsterLeaves(currMonster)) monsters.remove(i);
		}
	}
}
