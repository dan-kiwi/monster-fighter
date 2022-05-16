package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import monster.*;

/**
 * The class MonstersTest. This is a JUnit class that tests all attrtibutes relating to
 * the Monster class
 */
class MonstersTest {

	private Monster monster;
	private Monster monster2;
	
	/**
	 * Resets each monster before each test
	 * Uses two objects of the same class to compare the effect of level up compared to another
	 */
	@BeforeEach
	public void testEachMonster() {
		monster = new Unicorn();
		monster2 = new Unicorn();
	}
	
	/**
	 * Tests the change of Health on the monsters
	 * Ensures that it does not go above max health or below zero
	 */
	@Test
	public void checkHealth() {
		assertEquals(75, monster.getCurrHealth());
		monster.setCurrHealth(50);
		assertEquals(50, monster.getCurrHealth());
		monster.setCurrHealth(monster.getMaxHealth() + 10); //checks setCurrHealth
		assertEquals(monster.getMaxHealth(), monster.getCurrHealth());
		monster.changeCurrHealth(50); //checks changeCurrHealth
		assertEquals(monster.getMaxHealth(), monster.getCurrHealth());
		monster.changeCurrHealth(-50);
		assertEquals(monster.getMaxHealth() - 50, monster.getCurrHealth());
		monster.changeCurrHealth(-50000);
		assertEquals(0, monster.getCurrHealth());
		monster.setCurrHealth(-50000);
		assertEquals(0, monster.getCurrHealth());
	}
	
	/**
	 * Checks monsters stats are reset
	 * Ensures that not everything gets reset
	 */
	@Test
	public void resetMonsterStats() {
		monster.addDailyBattlesWon(1);
		monster.changeCurrHealth(-50);
		assertEquals(1, monster.getDailyBattlesWon());
		assertEquals(1, monster.getTotalBattlesWon());
		monster.resetMonsterStats();
		assertEquals(0, monster.getDailyBattlesWon());
		assertEquals(1, monster.getTotalBattlesWon());
		assertEquals(75, monster.getCurrHealth());
	}
	
	/**
	 * Checks that when leveling up, all attributes are greater
	 */
	@Test
	public void checkLevelUp() {
		monster.levelUp();
		assertTrue(monster.getMaxHealth() > monster2.getMaxHealth());
		assertTrue(monster.getBaseDefence() > monster2.getBaseDefence());
		assertTrue(monster.getBaseAttack() > monster2.getBaseAttack());
		assertTrue(monster.getEnergy() > monster2.getEnergy());
	}
	

}
