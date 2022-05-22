package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import mainenviro.GameEnviro;
import mainenviro.RandomEvent;
import monster.Dragon;
import monster.Monster;

class RandomEventTest {
	
	private List<Monster> userMonsters = new ArrayList<Monster>();
	private String[] masterMonsters = GameEnviro.getMasterMonsterList();
	private RandomEvent rand = new RandomEvent(masterMonsters, userMonsters);
	
	/**
	 * Helper method to check how many times a monster leaves after 1,000,000 trials
	 *
	 * @param monster the monster
	 * @return the number of times a monster leaves
	 */
	int monsterLeaves(Monster monster) {
		int tfCouner = 0;
		for (int i = 0; i < 1000000; i++) {
			if (rand.monsterLeaves(monster)) tfCouner++;
		}
		return tfCouner;
	}
	
	/**
	 * A monster with health above zero should leave 1% of the time when the monsterLeaves method is called
	 * Allows for some difference around 10,000 for expected varaiance
	 */
	@Test
	void monsterLeavesHealthy() {
		int tfCounter = monsterLeaves(new Dragon());
		assertTrue(tfCounter > 9500 && tfCounter < 10500);
	}
	
	/**
	 * A monster with health zero should leave 2.5% of the time when the monsterLeaves method is called
	 * Allows for some difference around 25,000 for expected varaiance
	 */
	@Test
	void monsterLeavesUnhealthy() {
		Monster monster = new Dragon();
		monster.setCurrHealth(0);
		int tfCounter = monsterLeaves(monster);
		assertTrue(tfCounter > 24500 && tfCounter < 25500);
	}
	
	/**
	 * Test checks if the levelUp method in randomEvent is working
	 * Monster should level up based on how many battles they won that day
	 * 10% for no wins then add 10 percentage points for every win until 100% at 9 wins
	 * Checks for 10 and 11 wins to ensure no unexpected behaviour
	 */
	@Test
	void monsterLevelUp() {
		Monster monster = new Dragon();
		int tfCounter;
		int expected;
		int trials = 2000000;
		for (int battles = 0; battles < 10; battles ++) {
			monster.setDailyBattlesWon(battles);
			tfCounter = 0;
			expected = trials / (10 - battles);
			for (int i = 0; i < trials; i++) {
				if (rand.monsterLevelUp(monster)) tfCounter++;
			}
			assertTrue(tfCounter > (expected - 2000) && tfCounter < (expected + 2000));
		}
		monster.setDailyBattlesWon(10);
		assertTrue(rand.monsterLevelUp(monster));
		monster.setDailyBattlesWon(11);
		assertTrue(rand.monsterLevelUp(monster));
	}

}
