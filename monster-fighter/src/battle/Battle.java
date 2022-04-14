package battle;

import monster.Monster;
import monster.Dragon;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Battle {
	
	private static ArrayList<Monster> enemies = new ArrayList<Monster>() {{
		add(new Dragon());
	}};
	
	private ArrayList<Monster> potentialBattles = new ArrayList<Monster>();
	private ArrayList<Monster> userMonsterList;
	private Monster currEnemy;
	private Monster currUser;
	private Random rand = new Random();
	private String test;
	
	public Battle() {
		this.userMonsterList = userMonsterList;
		int randNumBattles = rand.nextInt(3, 5);
		for (int i = 0; i < randNumBattles; i++) { // creates random number of battles between 3 & 5
			int randIndexEnemy = rand.nextInt(enemies.size()); 
			potentialBattles.add(enemies.get(randIndexEnemy));
		}
	}
	
	public void selectEnemies() {
		
	}
	
	public static void main(String[] args) {
		Battle test = new Battle();
		System.out.println(enemies);
	}
	
}
