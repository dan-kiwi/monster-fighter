package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import items.*;
import monster.*;

/**
 * The Class ItemsTest. A JUnit class to test everything associated with the items package
 * This includes all items and the shop.
 */
class ItemsTest {
	
	private Items food;
	private Items potion;
	private Monster monster;
	private Monster monster2;
	private Shop shop = new Shop();

	/**
	 * Resets each item after each test.
	 * Used Apple and HealthPotion as a test of the main super class
	 */
	@BeforeEach
	public void testEachItem() {
		food = new Apple();
		potion = new HealthPotion();
	}
	
	/**
	 * Resets each monster after each test.
	 * Used two monsters of the same class to compare the effect of using an item on them
	 * Goblin choosen by random as a test oif the main super class
	 */
	@BeforeEach
	public void resetMonsters() {
		monster = new Goblin();
		monster2 = new Goblin();
	}
	
	/**
	 * JUnit test to ensure that food does not decrease any attributes
	 * All food increases maxHealth by 10, so that is checked
	 */
	@Test
	public void testFood() {
		food.useItemOnMonster(monster);
		assertTrue(monster.getBaseAttack() >= monster2.getBaseAttack());
		assertTrue(monster.getCurrAttack() >= monster2.getCurrAttack());
		assertTrue(monster.getBaseDefence() >= monster2.getBaseDefence());
		assertTrue(monster.getCurrDefence() >= monster2.getCurrDefence());
		assertEquals(monster.getMaxHealth(), monster2.getMaxHealth() + 10);
	}
	
	/**
	 * JUnit test to ensure that potion does not decrease any attributes
	 * All potion increases energy by 2, so that is checked
	 */
	@Test
	public void testPotion() {
		potion.useItemOnMonster(monster);
		assertTrue(monster.getCurrHealth() >= monster2.getCurrHealth());
		assertTrue(monster.getCurrAttack() >= monster2.getCurrAttack());
		assertTrue(monster.getCurrDefence() >= monster2.getCurrDefence());
		assertEquals(monster.getEnergy(), monster2.getEnergy() + 2);
	}
	
	/**
	 * Tests the shop to ensure that every monster is appearing in the shop at least
	 * once over 30 different shops. 
	 * Also checks to ensure that the shop is properly resetting
	 */
	@Test
	public void testShopMonster() {
		boolean imp = false;
		boolean gnome = false;
		boolean goblin = false;
		boolean unicorn = false;
		boolean griffin = false;
		boolean dragon = false;
		for (int i = 0; i < 30; i++) {
			shop.resetShopStock();;
			ArrayList<Monster> monsters = shop.getShopMonsterList();
			for (Monster mon: monsters) {
				if (mon instanceof Goblin) goblin = true;
				if (mon instanceof Imp) imp = true;
				if (mon instanceof Gnome) gnome = true;
				if (mon instanceof Unicorn) unicorn = true;
				if (mon instanceof Griffin) griffin = true;
				if (mon instanceof Dragon) dragon = true;
			}
		}
		assertTrue(imp);
		assertTrue(goblin);
		assertTrue(gnome);
		assertTrue(unicorn);
		assertTrue(griffin);
		assertTrue(dragon);
	}
	
	/**
	 * Tests the shop to ensure that every item is appearing in the shop at least
	 * once over 30 different shops. 
	 */
	@Test
	public void testShopItems() {
		boolean apple = false;
		boolean berry = false;
		boolean ap = false;
		boolean dp = false;
		boolean hp = false;
		for (int i = 0; i < 30; i++) {
			shop = new Shop();
			ArrayList<Items> items = shop.getShopItemList();
			for (Items item: items) {
				if (item instanceof Apple) apple = true;
				if (item instanceof Berries) berry = true;
				if (item instanceof HealthPotion) hp = true;
				if (item instanceof AttackPotion) ap = true;
				if (item instanceof DefencePotion) dp = true;
			}
		}
		assertTrue(apple);
		assertTrue(berry);
		assertTrue(hp);
		assertTrue(ap);
		assertTrue(dp);
		assertTrue(hp);
	}

}
