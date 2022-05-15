package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monster.*;

class MonstersTest {

	private Monster monster;
	private Monster monster2;
	
	@BeforeEach
	public void testEachMonster() {
		monster = new Unicorn();
		monster2 = new Unicorn();
	}
	
	@Test
	public void checkHealth() {
		assertEquals(75, monster.getCurrHealth());
		monster.setCurrHealth(50);
		assertEquals(50, monster.getCurrHealth());
		monster.setCurrHealth(100);
		assertEquals(75, monster.getCurrHealth());
		monster.changeCurrHealth(50);
		assertEquals(75, monster.getCurrHealth());
		monster.changeCurrHealth(-50);
		assertEquals(25, monster.getCurrHealth());
		monster.changeCurrHealth(-50);
		assertEquals(0, monster.getCurrHealth());
	}
	
	@Test
	public void resetMonsterStats() {
		monster.addDailyBattlesWon(1);
		assertEquals(1, monster.getDailyBattlesWon());
		assertEquals(1, monster.getTotalBattlesWon());
		monster.resetMonsterStats();
		assertEquals(0, monster.getDailyBattlesWon());
		assertEquals(1, monster.getTotalBattlesWon());
		assertEquals(75, monster.getCurrHealth());
	}
	
	@Test
	public void checkLevelUp() {
		monster.levelUp();
		assertTrue(monster.getMaxHealth() > monster2.getMaxHealth());
		assertTrue(monster.getBaseDefence() > monster2.getBaseDefence());
		assertTrue(monster.getBaseAttack() > monster2.getBaseAttack());
		assertTrue(monster.getEnergy() > monster2.getEnergy());
	}
	

}
