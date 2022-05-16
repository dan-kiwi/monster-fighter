package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainenviro.GameEnviro;
import mainenviro.RandomEvent;
import monster.Dragon;
import monster.Monster;

class RandomEventTest {
	
	private ArrayList<Monster> userMonsters = new ArrayList<Monster>();
	private ArrayList<Monster> masterMonsters = GameEnviro.getMasterMonsterList();
	private RandomEvent rand;
	
	@BeforeEach
	void initilize() {
		rand = new RandomEvent(masterMonsters, userMonsters);
	}
	
	int monsterLeaves(Monster monster) {
		int tfCouner = 0;
		for (int i = 0; i < 1000000; i++) {
			if (rand.monsterLeaves(monster)) tfCouner++;
		}
		return tfCouner;
	}
	
	@Test
	void monsterLeavesHealthy() {
		int tfCounter = monsterLeaves(new Dragon());
		assertTrue(tfCounter > 9500 && tfCounter < 10500);
	}
	
	@Test
	void monsterLeavesUnhealthy() {
		Monster monster = new Dragon();
		monster.setCurrHealth(0);
		int tfCounter = monsterLeaves(monster);
		assertTrue(tfCounter > 24500 && tfCounter < 25500);
	}
	
	@Test
	void monsterLevelUp() {
		
	}

}
