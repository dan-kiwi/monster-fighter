package mainEnviro;

import monster.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import items.Items;

public class Battle {
	
	private static ArrayList<Monster> enemies = new ArrayList<Monster>() {{
		add(new Dragon());
		add(new Gnome());
		add(new Goblin());
		add(new Griffin());
		add(new Imp());
		add(new Unicorn());
	}};
	private static String[] fightOptions = {"Attack", "Energetic Attack", "Defend", 
											"Energetic Defend", "Use Potion", "Quit"};
	//private static String[] normalFightOptions = {"Normal Attack", "Normal Defence", "Use Potion"};
	
	private GameEnviro game;
	private ArrayList<Monster> potentialBattles = new ArrayList<Monster>();
	private ArrayList<Monster> userMonsterList;
	private Monster currEnemy;
	private Monster currUser;
	private Random rand = new Random();
	
	Battle(GameEnviro game) {
		this.game = game;
		this.userMonsterList = game.getUserMonsterList();
		int randNumBattles = rand.nextInt(3, 5);
		for (int i = 0; i < randNumBattles; i++) { // creates random number of battles between 3 & 5
			int randIndexEnemy = rand.nextInt(enemies.size()); 
			Monster curr = enemies.get(randIndexEnemy);
			if (!potentialBattles.contains(curr)) {
				potentialBattles.add(curr);
			} else {
				i--;
			}
		}
	}
	
	Battle(ArrayList<Monster> userMonsterList) {
		this.userMonsterList = userMonsterList;
		int randNumBattles = rand.nextInt(3, 5);
		for (int i = 0; i < randNumBattles; i++) { // creates random number of battles between 3 & 5
			int randIndexEnemy = rand.nextInt(enemies.size()); 
			Monster curr = enemies.get(randIndexEnemy);
			if (!potentialBattles.contains(curr)) {
				potentialBattles.add(curr);
			} else {
				i--;
			}
		}
	}
	
	
	public void selectEnemies() {
		System.out.println();
		System.out.println("Choose your opponent:");
		for (int i = 1; i <= potentialBattles.size(); i++) {
			Monster currEnemy = potentialBattles.get(i-1);
			System.out.println(i + ": " + currEnemy + "\n");
		}

		int userChoice = userSelectionInput(potentialBattles.size());
		currEnemy = potentialBattles.get(userChoice - 1);
		System.out.println();
		System.out.println("You have choosen " + currEnemy.getMonsterName() + " as your opponent");		
		System.out.println();
	}
	
	
	public void selectUser() {
		System.out.println();
		System.out.println("Choose your monster to fight:");
		for (int i = 1; i <= userMonsterList.size(); i++) {
			Monster currMon = userMonsterList.get(i-1);
			System.out.println(i + ": " + currMon + "\n");
		}
		int userChoice = userSelectionInput(userMonsterList.size());
		if (userChoice >= 0) {
			currUser = userMonsterList.get(userChoice - 1);
			System.out.println("You have choosen " + currUser.getMonsterName() + " as your monster");
			System.out.println();
		}
	}
	
	public int userSelectionInput(int size) {
		@SuppressWarnings("resource")
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
	
	
	public int[] getAttackDefence (Monster user, int choice) {
		int[] attackDefence = {0, 0}; //0 = Attack, 1 = Defence
		if (choice == 1) { //Energetic Attack
			attackDefence[0] = user.getCurrAttack() * 5 / 4;
			attackDefence[1] = user.getCurrDefence() / 2;
			user.setEnergy(user.getEnergy() - 1);
		} else if (choice == 0) { //Normal Attack
			attackDefence[0] = user.getCurrAttack();
		} else if (choice == 3) { //Energetic Defence
			attackDefence[0] = user.getCurrAttack() / 2;
			attackDefence[1] = user.getCurrAttack() * 5 / 4;
			user.setEnergy(user.getEnergy() - 1);
		} else if (choice == 2) { //Normal Defence
			attackDefence[1] = user.getCurrDefence();
		} else { //Use Potion
			System.out.println("Potion not setup yet");
		}
		return attackDefence;
	}
	
	
	public int[] getUserFight() throws InputMismatchException{
		System.out.println("Choose a fight option");
		int userChoice;
		
		if (currUser.getEnergy() > 0) {
			for (int i = 1; i <= fightOptions.length; i++) {
				System.out.println(i + ": " + fightOptions[i-1]);
			}
			userChoice = userSelectionInput(fightOptions.length);
			if (userChoice == 6) throw new InputMismatchException("Quits fight");
		} else {
			for (int i = 1; i <= (fightOptions.length / 2) + 1; i ++) {
				System.out.println(i + ": " + fightOptions[(i-1)*2]);
			}
			System.out.println(4 + ": " + fightOptions[5]);
			userChoice = userSelectionInput(fightOptions.length);
			if (userChoice == 4) throw new InputMismatchException("Quits fight");
			}
		int[] userAttackDefence = getAttackDefence(currUser, userChoice - 1);
		return userAttackDefence;
	}
	
	
	public int[] getEnemyFight() {
		int enemyChoice;
		if (currEnemy.getEnergy() > 0) {
			enemyChoice = rand.nextInt(fightOptions.length - 1);
		} else {
			enemyChoice = rand.nextInt((fightOptions.length - 1) / 2);
			enemyChoice = enemyChoice * 2;
		}
		String enemyMove = fightOptions[enemyChoice].toLowerCase();
		System.out.println("Your opponent has choosen " + enemyMove + " as their move");
		int[] enemyAttackDefence = getAttackDefence(currEnemy, enemyChoice);
		return enemyAttackDefence;
	}
	
	
	public void fight() {
		boolean fighting = true;
		System.out.println();
		System.out.println("Two Monsters go head to head:");
		System.out.println("Your monster:");
		System.out.println(currUser);
		System.out.println("Your opponent:");
		System.out.println(currEnemy);
		System.out.println();
		
		while (fighting) {
			int[] userAttackDefence;
			try {
				userAttackDefence = this.getUserFight();
			} catch (InputMismatchException e) {
				fighting = false;
				System.out.println();
				continue;
			}
			int[] enemyAttackDefence = this.getEnemyFight();
			int userChangeHealth = ((userAttackDefence[1] - enemyAttackDefence[0] > 0) ? 0 
								   : userAttackDefence[1] - enemyAttackDefence[0]);
			currUser.setCurrHealth(currUser.getCurrHealth() + userChangeHealth);
			int enemyChangeHealth = ((enemyAttackDefence[1] - userAttackDefence[0] > 0) ? 0 
					   				: enemyAttackDefence[1] - userAttackDefence[0]);
			currEnemy.setCurrHealth(currEnemy.getCurrHealth() + enemyChangeHealth);

			System.out.println();
			System.out.println("Your monster was dealt " + userChangeHealth + " damage");
			System.out.println("Your opponent was dealt " + enemyChangeHealth + " damage");
			System.out.println();
			
			if (currUser.getCurrHealth() <= 0) {
				currUser.setCurrHealth(0);
				fighting = false;
				System.out.println("Your monster has lost all it's health. Choose another monster or quit the battle arena");
			} else if (currEnemy.getCurrHealth() <= 0) {
				fighting  = false;
				System.out.println("You have defeated this opponent. Choose another opponent or quit the battle arena");
			} else {
				System.out.println("Your monster:");
				System.out.println(currUser);
				System.out.println("Your opponent:");
				System.out.println(currEnemy);
			}
		}
	}
	
	
	public String viewMonsters() {
		String userLine;
		String monsterLine;
		System.out.println();
		if (currUser == null) {
			userLine = "You have not selected a monster yet \n";
		} else {
			userLine = "Your monster: \n" + currUser.toString();
		}
		if (currEnemy == null) {
			monsterLine = "You have not selected an opponent yet \n";
		} else {
			monsterLine = "Your opponent: \n" + currEnemy.toString();
		}
		return userLine + monsterLine;
	}
	
//	public void selectPotion() {
//		for (Items userItem : GameEnviro.userItemList))
//	}
	
	
	public void mainMenu() {
		boolean playing = true;
		boolean bothMonsters = false;
		int userChoice;
		System.out.println("Welcome to the battle arena!");
		System.out.println("Please choose an option");
		while (playing) {
			System.out.println("1: Select an opponent");
			System.out.println("2: Select your monster");
			System.out.println("3: View your monster and your opponent");
			if (!bothMonsters) {
				System.out.println("4: Quit");
				userChoice = userSelectionInput(4);
			} else {
				System.out.println("4: Fight your monster against your selected opponent");
				System.out.println("5: Quit");
				userChoice = userSelectionInput(5);
			}
			if (userChoice == 1) {
				selectEnemies();
			} else if (userChoice == 2) {
				selectUser();
			} else if (userChoice == 3) {
				System.out.println(viewMonsters());
			} else if (userChoice == 4 && bothMonsters) {
				fight();
			} else {
				playing = false;
			}
			if (currUser != null && currEnemy != null) {
				bothMonsters = true;
			}
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Monster> testList = new ArrayList<Monster>();
		testList.add(new Dragon());
		testList.add(new Unicorn());
		Battle test = new Battle(testList);
		test.mainMenu();
	}
	
}
