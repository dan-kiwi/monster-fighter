package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainenviro.Battle;
import mainenviro.GameEnviro;
import monster.Imp;
import monster.Monster;
import monster.Unicorn;

/**
 * The Class BattleTest. A JUnit class to tests methods in the Battle class
 */
class BattleTest {
	
	Monster user;
	Monster enemy;
	GameEnviro game;
	Battle battle; //need to fix
	
	/**
	 * Instantiates a new battle test.
	 */
	BattleTest() {
		game = new GameEnviro();
		battle = new Battle(game);
		game.starterSetDifficulty("Easy");
	}
	
	/**
	 * Resets the monsters before each test
	 */
	@BeforeEach
	void selectMonsters() {
		battle.setCurrUser(new Unicorn());
		battle.setCurrEnemy(new Imp());
	}
	
	/**
	 * Check's the user damage method is working correctly
	 */
	@Test
	void checkUserDamage() {
		int originalUserHealth = battle.getCurrUser().getCurrHealth();
		int originalEnemyHealth = battle.getCurrEnemy().getCurrHealth();
		int userDefence = 0;
		int enemyAttack = 20;
		battle.userDamage(userDefence, enemyAttack);
		int expectedDamage = (int) ((userDefence - enemyAttack) * game.getMonsterDifficulty());
		assertEquals(originalUserHealth, battle.getCurrUser().getCurrHealth() - expectedDamage);
		assertEquals(originalEnemyHealth, battle.getCurrEnemy().getCurrHealth());
	}
	
	/**
	 * Checks that a users health doesn't change when defence is greater than or equal to attack
	 */
	@Test
	void checkNoUserDamage() {
		int originalUserHealth = battle.getCurrUser().getCurrHealth();
		int originalEnemyHealth = battle.getCurrEnemy().getCurrHealth();
		int userDefence = 20;
		int enemyAttack = 20;
		battle.userDamage(userDefence, enemyAttack);
		int expectedDamage = 0;
		assertEquals(originalUserHealth, battle.getCurrUser().getCurrHealth() - expectedDamage);
		assertEquals(originalEnemyHealth, battle.getCurrEnemy().getCurrHealth());
		userDefence = 40;
		battle.userDamage(userDefence, enemyAttack);
		assertEquals(originalUserHealth, battle.getCurrUser().getCurrHealth() - expectedDamage);
		assertEquals(originalEnemyHealth, battle.getCurrEnemy().getCurrHealth());
	}
	
	/**
	 * Check's the enemy damage method is working correctly
	 */
	@Test
	void checkEnemyDamage() {
		int originalUserHealth = battle.getCurrUser().getCurrHealth();
		int originalEnemyHealth = battle.getCurrEnemy().getCurrHealth();
		int enemyDefence = 0;
		int userAttack = 20;
		battle.enemyDamage(enemyDefence, userAttack);
		int expectedDamage = enemyDefence - userAttack;
		assertEquals(originalEnemyHealth, battle.getCurrEnemy().getCurrHealth() - expectedDamage);
		assertEquals(originalUserHealth, battle.getCurrUser().getCurrHealth());
	}
	
	/**
	 * Checks that a enemy's health doesn't change when defence is greater than or equal to attack
	 */
	@Test
	void checkNoEnemyDamage() {
		int originalUserHealth = battle.getCurrUser().getCurrHealth();
		int originalEnemyHealth = battle.getCurrEnemy().getCurrHealth();
		int enemyDefence = 30;
		int userAttack = 20;
		battle.enemyDamage(enemyDefence, userAttack);
		int expectedDamage = 0;
		assertEquals(originalEnemyHealth, battle.getCurrEnemy().getCurrHealth() - expectedDamage);
		assertEquals(originalUserHealth, battle.getCurrUser().getCurrHealth());
	}
	
	/**
	 * Check's that the enemy is not choosing any options not avaliable to them
	 */
	@Test
	void checkIncorrectEnemyChoice() {
		battle.getCurrEnemy().setEnergy(0);
		for (int i = 0; i < 200; i++) {
			String enemyChoice = battle.setEnemyChoice();
			assertFalse(enemyChoice == "Energetic Attack");
			assertFalse(enemyChoice == "Energetic Defend");
			assertFalse(enemyChoice == "use Item");
			assertFalse(enemyChoice == "Quit");
		}
	}
	
	@Test
	void checkCorrectEnemyChoice() {
		boolean attack = false;
		boolean eAttack = false;
		boolean defence = false;
		boolean eDefence = false;
		for (int i = 0; i < 200; i++) {
			String enemyChoice = battle.setEnemyChoice();
			if (enemyChoice == "Attack") attack = true;
			if (enemyChoice == "Energetic Attack") eAttack = true;
			if (enemyChoice == "Defend") defence = true;
			if (enemyChoice == "Energetic Defend") eDefence = true;
		}
		assertTrue(attack);
		assertTrue(eAttack);
		assertTrue(defence);
		assertTrue(eDefence);
	}
	
	@Test
	void checkEnemyDying() {
		game.setGameDifficulty("Easy");
		int originalScore = game.getUserGameScore();
		int originalBattles = battle.getCurrUser().getDailyBattlesWon();
		battle.enemyDead();
		assertNull(battle.getCurrEnemy());
		assertTrue(game.getUserGameScore() > originalScore);
		assertTrue(battle.getCurrUser().getDailyBattlesWon() > originalBattles);
	}
}
