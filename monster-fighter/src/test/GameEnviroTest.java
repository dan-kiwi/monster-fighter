//package test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//
//import org.junit.Before;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//
//import mainenviro.GameEnviro;
//import mainenviro.UserInput;
//import monster.Gnome;
//import monster.Imp;
//import monster.Monster;
//
//class GameEnviroTest{
//	
//	GameEnviro game = new GameEnviro();
//	UserInput userInput = new UserInput();
//	
//	@Nested
//	class geMonsterTest {
//		
//		@BeforeEach
//		void setupMonster() {
//			if (game.getUserMonsterList().size() > 0) {
//				game.getUserMonsterList().set(0, new Imp());
//			} else {
//				game.getUserMonsterList().add(new Imp());
//			}
//			
//		}
//		
//		void enterName() {
//			InputStream in = new ByteArrayInputStream(input.getBytes());
//		    System.setIn(in);
//		    Monster monster = game.getUserMonsterList().get(0);
//		    assertEquals(monster.getMonsterName(), "SENG201");
//		}
//		
//		@Test
//		void setupGame() {
//			game.startNewGame();
//			enterName();
//		}
//
//		@Test
//		void checkMonster() {
//			game.starterMonsterSelect(1);
//			Monster monster = game.getUserMonsterList().get(1);
//			assertTrue(monster instanceof Imp);
//			game.getUserMonsterList().remove(1);
//			game.starterMonsterSelect(2);
//			monster = game.getUserMonsterList().get(1);
//			assertTrue(monster instanceof Gnome);
//		}
//		
//		@Test
//		void checkName() {
//			Monster monster = game.getUserMonsterList().get(0);
//			assertEquals(monster.getMonsterName(), "Imp");
//			game.starterMonsterRename("n");
//			assertEquals(monster.getMonsterName(), "Imp");
//		}
//		
//		@Test
//		void rename() {
//			game.starterMonsterRename("y");
//			String input = "SENG201";
//		    InputStream in = new ByteArrayInputStream(input.getBytes());
//		    System.setIn(in);
//		    Monster monster = game.getUserMonsterList().get(0);
//		    assertEquals(monster.getMonsterName(), "SENG201");
//		}
//	}
//	
//	
//	@Test
//	void testDifficulty() {
//		game.starterSetDifficulty("Hard");
//		assertEquals(game.getUserGoldAmount(), 150);
//		assertEquals(game.getGoldDifficulty(), 0.85);
//		assertEquals(game.getMonsterDifficulty(), 1.15);
//		game.starterSetDifficulty("Easy");
//		assertEquals(game.getUserGoldAmount(), 300);
//		assertEquals(game.getGoldDifficulty(), 1.5);
//		assertEquals(game.getMonsterDifficulty(), 0.85);
//	}
//
//	
//	
//
//}
