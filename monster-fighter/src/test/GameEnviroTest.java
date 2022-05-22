package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainenviro.GameEnviro;
import monster.Imp;


class GameEnviroTest{
	
	GameEnviro game = new GameEnviro();
		
	@BeforeEach
	void setupMonster() {
		if (game.getUserMonsterList().size() > 0) {
			game.getUserMonsterList().set(0, new Imp());
		} else {
			game.getUserMonsterList().add(new Imp());
		}
	}

	
	
	@Test
	void testDifficulty() {
		game.starterSetDifficulty("Hard");
		assertEquals(game.getUserGoldAmount(), 150);
		assertEquals(game.getGoldDifficulty(), 0.85);
		assertEquals(game.getMonsterDifficulty(), 1.15);
		game.starterSetDifficulty("Easy");
		assertEquals(game.getUserGoldAmount(), 300);
		assertEquals(game.getGoldDifficulty(), 1.5);
		assertEquals(game.getMonsterDifficulty(), 0.85);
	}
}
