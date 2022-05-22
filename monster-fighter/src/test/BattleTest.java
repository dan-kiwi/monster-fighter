package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainenviro.Battle;
import mainenviro.GameEnviro;
import monster.Imp;
import monster.Monster;
import monster.Unicorn;

class BattleTest {
	
	Monster user;
	Monster enemy;
	GameEnviro game;
	Battle battle; //need to fix
	
	BattleTest() {
		game = new GameEnviro();
		battle = new Battle(game);
		game.setGameDifficulty("Easy");
	}
	
	@BeforeEach
	void selectMonsters() {
		battle.setCurrUser(new Unicorn());
		battle.setCurrEnemy(new Imp());
	}
	
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

}
