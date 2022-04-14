package mainEnviro;

import monster.Monster;
import monster.Dragon;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battle {
	
	private static ArrayList<Monster> enemies = new ArrayList<Monster>() {{
		add(new Dragon());
	}};
	
	private ArrayList<Monster> potentialBattles = new ArrayList<Monster>();
	private ArrayList<Monster> userMonsterList;
	private Monster currEnemy;
	private Monster currUser;
	private Random rand = new Random();
	
	
	
	public Battle(ArrayList<Monster> userMonsterList) {
		this.userMonsterList = userMonsterList;
		int randNumBattles = rand.nextInt(3, 5);
		for (int i = 0; i < randNumBattles; i++) { // creates random number of battles between 3 & 5
			int randIndexEnemy = rand.nextInt(enemies.size()); 
			potentialBattles.add(enemies.get(randIndexEnemy));
		}
	}
	
	
	public void selectEnemies() {
		
		System.out.println("Choose your opponent:");
		for (int i = 1; i <= potentialBattles.size(); i++) {
			Monster currEnemy = potentialBattles.get(i-1);
			System.out.println(i + ": " + currEnemy + "\n");
		}

		int userChoice = userSelection(potentialBattles.size());
		currEnemy = potentialBattles.get(userChoice - 1);
		System.out.println();
		System.out.println("You have choosen " + currEnemy.getMonsterName() + " as your enemy");		
	}
	
	
	public void selectUser() {
		System.out.println("Choose your monster to fight:");
		for (int i = 1; i <= userMonsterList.size(); i++) {
			Monster currMon = userMonsterList.get(i-1);
			System.out.println(i + ": " + currMon + "\n");
		}
		int userChoice = userSelection(userMonsterList.size());
		
		currUser = userMonsterList.get(userChoice - 1);
		System.out.println();
		System.out.println("You have choosen " + currEnemy.getMonsterName() + " as your enemy");		
	}
	
	public int userSelection(int size) {
		Scanner userInput = new Scanner(System.in);
		int userChoice = -1;
		
		do {
			System.out.print("Enter a number between 1 and " + size + ": ");
			try {
				userChoice = userInput.nextInt();
				if (userChoice < 1 || userChoice > size) {
					userChoice = -1;
					System.out.println("Error: Wrong input");
				}
			} catch (InputMismatchException e) {
				userInput.nextLine();
			}
		} while (userChoice == -1);
		return userChoice;
	}
	
//	public static void main(String[] args) {
//		Battle test = new Battle();
//		test.selectEnemies();
//	}
	
}
