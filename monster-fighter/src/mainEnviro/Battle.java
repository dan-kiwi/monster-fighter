package mainEnviro;

import monster.*;
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
	private static String[] fightOptions = {"Energetic Attack", "Normal Attack", "Energetic Defence", 
												  "Normal Defence", "Use Potion"};
	//private static String[] normalFightOptions = {"Normal Attack", "Normal Defence", "Use Potion"};
	
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

		int userChoice = userSelectionInput(potentialBattles.size());
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
		int userChoice = userSelectionInput(userMonsterList.size());
		if (userChoice >= 0) {
			currUser = userMonsterList.get(userChoice - 1);
			System.out.println();
			System.out.println("You have choosen " + currUser.getMonsterName() + " as your enemy");
		}
	}
	
	public int userSelectionInput(int size) {
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
//				if (e.toString() == "q") {
//					userChoice = -2;
//				} else {
//					userInput.nextLine();
//				}
			}
		} while (userChoice == -1);
		
		return userChoice;
	}
	
	
	public int[] getAttackDefence (Monster user, int choice) {
		int[] attackDefence = {0, 0}; //0 = Attack, 1 = Defence
		if (choice == 1) { //Energetic Attack
			attackDefence[0] = user.getCurrAttack() * 5 / 4;
			attackDefence[1] = user.getCurrDefence() / 2;
			currUser.setEnergy(currUser.getEnergy() - 1);
		} else if (choice == 2) { //Normal Attack
			attackDefence[0] = user.getCurrAttack();
		} else if (choice == 3) { //Energetic Defence
			attackDefence[0] = user.getCurrAttack() / 2;
			attackDefence[1] = user.getCurrAttack() * 5 / 4;
			currUser.setEnergy(currUser.getEnergy() - 1);
		} else if (choice == 4) { //Normal Defence
			attackDefence[1] = user.getCurrDefence();
		} else { //Use Potion
			System.out.println("Potion not setup yet");
		}
		return attackDefence;
	}
	
	
	public int[] getUserFight() {
		System.out.println("Choose a fight option");
		int userChoice;
		
		if (currUser.getEnergy() > 0) {
			for (int i = 1; i <= fightOptions.length; i++) {
				System.out.println(i + ": " + fightOptions[i-1]);
			}
			userChoice = userSelectionInput(fightOptions.length);
		} else {
			for (int i = 1; i <= (fightOptions.length / 2) + 1; i ++) {
				System.out.println(i + ": " + fightOptions[i*2]);
			}
			userChoice = userSelectionInput(fightOptions.length);
			}
		int[] userAttackDefence = getAttackDefence(currUser, userChoice);
		return userAttackDefence;
	}
	
	
	public int[] getEnemyFight() {
		int enemyChoice;
		if (currEnemy.getEnergy() > 0) {
			enemyChoice = rand.nextInt(fightOptions.length); //lower chance of potion
		} else {
			enemyChoice = rand.nextInt(fightOptions.length / 2 + 1);
			enemyChoice = enemyChoice * 2 + 1;
		}
		int[] enemyAttackDefence = getAttackDefence(currEnemy, enemyChoice);
		return enemyAttackDefence;
	}
	
	
	public void fight() {
		boolean battling;
		this.selectUser();
		if (currUser == null) {
			battling = false;
		} else {
			battling = true;
		}
		this.selectEnemies();
		 
		
		while (battling) {
			boolean fighting = true;
			System.out.println();
//			System.out.println("Two Monsters go head to head:");
//			System.out.println("Your monster:");
//			System.out.println(currUser);
//			System.out.println("Your opponent");
//			System.out.println(currEnemy);
			
			while (fighting) {
				int[] userAttackDefence = this.getUserFight();
				int[] enemyAttackDefence = this.getEnemyFight();
				int userChangeHealth = ((userAttackDefence[1] - enemyAttackDefence[0] > 0) ? 0 
									   : userAttackDefence[1] - enemyAttackDefence[0]);
				currUser.setCurrHealth(currUser.getCurrHealth() + userChangeHealth);
				int enemyChangeHealth = ((enemyAttackDefence[1] - userAttackDefence[0] > 0) ? 0 
						   				: enemyAttackDefence[1] - userAttackDefence[0]);
				currEnemy.setCurrHealth(currEnemy.getCurrHealth() + enemyChangeHealth);
				if (currUser.getCurrHealth() <= 0) {
					currUser.setCurrHealth(0);
					fighting = false;
					System.out.println("Your monster has lost all it's health. Choose another monster or quit battle");
				} else if (currEnemy.getCurrHealth() <= 0) {
					fighting  = false;
					System.out.println("You have defeated this enemy. Choose another enemy or quit battle");
				} else {
					System.out.println("Your monster:");
					System.out.println(currUser);
					System.out.println("Your opponent");
					System.out.println(currEnemy);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Monster> testList = new ArrayList<Monster>();
		testList.add(new Dragon());
		testList.add(new Unicorn());
		Battle test = new Battle(testList);
		test.fight();
	}
	
}
