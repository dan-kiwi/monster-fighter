package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import mainenviro.GameEnviro;

class GameEnviroTest {
	
	GameEnviro game = new GameEnviro();

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
