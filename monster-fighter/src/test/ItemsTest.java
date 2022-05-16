package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import items.Apple;
import items.HealthPotion;
import items.Items;
import monster.Goblin;
import monster.Monster;

class ItemsTest {
	
	private Items food;
	private Items potion;
	private Monster monster;
	private Monster monster2;

	@BeforeEach
	public void testEachItem() {
		food = new Apple();
		potion = new HealthPotion();
	}
	
	@BeforeEach
	public void resetMonsters() {
		monster = new Goblin();
		monster2 = new Goblin();
	}
	
	@Test
	public void testFood() {
		food.useItemOnMonster(monster);
		assertTrue(monster.getBaseAttack() >= monster2.getBaseAttack());
		assertTrue(monster.getCurrAttack() >= monster2.getCurrAttack());
		assertTrue(monster.getBaseDefence() >= monster2.getBaseDefence());
		assertTrue(monster.getCurrDefence() >= monster2.getCurrDefence());
		assertTrue(monster.getMaxHealth() >= monster2.getMaxHealth());
	}
	
	@Test
	public void testPotion() {
		potion.useItemOnMonster(monster);
		assertTrue(monster.getCurrHealth() >= monster2.getCurrHealth());
		assertTrue(monster.getCurrAttack() >= monster2.getCurrAttack());
		assertTrue(monster.getCurrDefence() >= monster2.getCurrDefence());
		assertTrue(monster.getEnergy() >= monster2.getEnergy());
	}


}
