package mainEnviro;

import monster.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Battle {
	
	//private static List<Monster> enemies = List.of(new Monster Dragon);
	
	private ArrayList<Monster> potentialBattles = new ArrayList<Monster>();
	private Monster currEnemy;
	private Monster currUser;
	private Random rand = new Random();
	
	public Battle(ArrayList<Monster> enemyList) {
		int randNumBattles = rand.nextInt(3, 5);
		for (int i = 0; i < randNumBattles; i++) { // creates random number of battles between 3 & 5
			int randIndexEnemy = rand.nextInt(enemyList.size()); 
			BattleEvent potentialBattle = new BattleEvent(enemyList.get(randIndexEnemy));
			//potentialBattles.add(potentialBattle);
		}
	}
	
}
