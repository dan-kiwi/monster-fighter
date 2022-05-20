package cli;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import items.Items;
import mainenviro.Battle;
import mainenviro.GameEnviro;
import monster.Monster;

/**
 * The Class BattleCLI.
 * The Command line interface for the battle
 */
public class BattleCLI {
	
	Battle battle;
	GameEnviro game;
	
	/**
	 * Instantiates a new battle CLI.
	 *
	 * @param game the game
	 */
	BattleCLI(GameEnviro game) {
		this.game = game;
		this.battle = game.getBattle();
	}
	
	/**
	 * Method to allow users to select an enemy to battle
	 * Uses userSelectionInput to obtain a user choice
	 */
	public void selectEnemies() {
		if (battle.getPotentialBattles().size() == 0) {
			System.out.println("\n");
			System.out.println("There are no more Battles available today\n");
			return;
		}
		System.out.println();
		System.out.println("Choose your opponent:");
		for (int i = 1; i <= battle.getPotentialBattles().size(); i++) {
			Monster tempMonster = battle.getPotentialBattles().get(i-1);
			System.out.println(i + ": " + tempMonster + "\n");
		}

		int userChoice = userSelectionInput(battle.getPotentialBattles().size());
		battle.setCurrEnemy(battle.getPotentialBattles().get(userChoice - 1));
		System.out.println();
		System.out.println("You have choosen " + battle.getCurrEnemy().getDisplayName() + " as your opponent");		
		System.out.println();
	}
	
	
	/**
	 * Method to allow users to select one of their monsters to fight
	 * Uses userSelectionInput to obtain a user choice
	 */
	public void selectUser() {
		System.out.println();
		System.out.println("Choose your monster to fight:");
		for (int i = 1; i <= game.getUserMonsterList().size(); i++) {
			Monster currMon = game.getUserMonsterList().get(i-1);
			System.out.println(i + ": " + currMon + "\n");
		}
		int userChoice = userSelectionInput(game.getUserMonsterList().size());
		if (game.getUserMonsterList().get(userChoice - 1).getCurrHealth() > 0) {
			battle.setCurrUser(game.getUserMonsterList().get(userChoice - 1));
			System.out.println("You have choosen " + battle.getCurrUser().getDisplayName() + " as your monster");
			System.out.println();
		} else {
			System.out.println("You cannot select this monster as it has no health left\n");
		}
	} 
	
	/**
	 * Method to get a user choice from 0 to a given input
	 * 
	 * Uses scanner to obtain user input
	 * @param size, the number of choices
	 * @return userChoice, an integer representing the choice
	 */
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
	
	/**
	 * Method to get the users fight choice for the current round of the battle
	 * 
	 * Checks user's monster's energy level to ensure energetic fight options are appropriately shown
	 * Uses userSelectionInput method to obtain the users choice
	 * @return userChoice, an integer representing the user choice
	 */
	public int getUserFight() {
		System.out.println("Choose a fight option");
		int userChoice;
		
		if (battle.getCurrUser().getEnergy() > 0) {
			for (int i = 1; i <= Battle.getFightOptions().length; i++) {
				System.out.println(i + ": " + Battle.getFightOptions()[i-1]);
			}
			userChoice = userSelectionInput(Battle.getFightOptions().length);
		} else {
			for (int i = 1; i <= (Battle.getFightOptions().length / 2); i ++) {
				System.out.println(i + ": " + Battle.getFightOptions()[(i-1)*2]);
			}
			System.out.println(4 + ": " + Battle.getFightOptions()[5]);
			int userInput = userSelectionInput(Battle.getFightOptions().length);
			if (userInput == 1) {
				userChoice = 1;
			} else if (userInput == 2) {
				userChoice = 3;
			} else if (userInput == 3) {
				userChoice = 5;
			} else {
				userChoice = 6;
			}
		}
		return userChoice - 1;
	}
	
	
	/**
	 * Method to randomly select the enemy's fight option
	 * 
	 * @return enemyChoice, an integer representing the enemies choice
	 */
	public void enemyFight() {
		String enemyChoice = battle.setEnemyChoice();
		System.out.println("Your opponent has choosen " + enemyChoice + " as their move");
	}
	
	
	/**
	 * Method to fight the user's monster against an enemy monster.
	 * Call's getUserFight to determine method chosen
	 * Call's getAttackDefence to determine damage inflicted on both monsters
	 * Should a monster die, the match ends, returned to the main screen
	 */
	public void fight() {
		boolean fighting = true;
		System.out.println();
		System.out.println("Two Monsters go head to head:");
		System.out.println();
		
		while (fighting) {
			System.out.println("Your monster:");
			System.out.println(battle.getCurrUser());
			System.out.println("Your opponent:");
			System.out.println(battle.getCurrEnemy());
			int userFightIndex = this.getUserFight();
			if (userFightIndex == 5) {
				selectItem();
				continue;
			} else if (userFightIndex == 6){
				fighting = false;
				System.out.println("Quiting match");
				System.out.println();
				continue;
			}
			battle.setUserChoice(userFightIndex);
			enemyFight();
			int[] damageDealt = battle.fight();
			System.out.println();
			System.out.println("Your monster was dealt " + damageDealt[0] + " damage");
			System.out.println("Your opponent was dealt " + damageDealt[1] + " damage");
			System.out.println();
			
			if (battle.checkEnemyDead()) {
				int goldWon = battle.goldWon();
				System.out.println("You have defeated this opponent! You have won " + goldWon + " gold.");
				System.out.println("Choose another opponent or quit the battle arena");
				fighting = false;
			}
			if (battle.checkUserDead()) {
				System.out.println("Your monster has lost all it's health. Choose another monster or quit the battle arena");
				fighting = false;
			}
		}
	}
	
	
	/**
	 * Print's user monster and enemy monster.
	 * Will state if a user has not selected either monster
	 */
	public void viewMonsters() {
		String userLine;
		String monsterLine;
		System.out.println();
		if (battle.getCurrUser() == null) {
			userLine = "You have not selected a monster yet \n";
		} else {
			userLine = "Your monster: \n" + battle.getCurrUser().toString();
		}
		System.out.println();
		if (battle.getCurrEnemy() == null) {
			monsterLine = "You have not selected an opponent yet \n";
		} else {
			monsterLine = "Your opponent: \n" + battle.getCurrEnemy().toString();
		}
		System.out.println(userLine + monsterLine);
	}
	
	/**
	 * Allows an item to be selected from the user's list
	 * If the user has no items, it tell's the user and returns
	 * Once an item is used, it is applied to the monster by calling a method on items
	 */
	public void selectItem() {
		Items item;
		ArrayList<Items> userItems = game.getUserItemList();
		if (userItems.size() == 0) {
			System.out.println("You have no items to use");
			return;
		}
		System.out.println("Select item: ");
		for (int i = 1; i <= userItems.size(); i++) {
			System.out.println(i + ": " + userItems.get(i-1));
		}
		System.out.println((userItems.size() + 1) + ": Quit");
		int userChoice = userSelectionInput(userItems.size() + 1);
		if (userChoice <= userItems.size()) {
			item = userItems.get(userChoice - 1);
			userItems.remove(userChoice - 1);
			item.useItemOnMonster(battle.getCurrUser());
			System.out.println(item.getItemName() + " has been used");
			System.out.println(battle.getCurrUser());
		} else {
			System.out.println("No item selected");
		}
	}
	
	/**
	 * Help menu to explain all of the different fighting methods
	 */
	public void helpMenu() {
		System.out.println();
		System.out.println(battle.helpInfo());
		System.out.println();
	}
	
	
	/**
	 * The main menu that the user will interact with
	 * Allows all the users to call on other methods
	 */
	public void mainMenu() {
		boolean playing = true;
		boolean bothMonsters = false;
		int userChoice;
		System.out.println();
		System.out.println("Welcome to the battle arena!");
		System.out.println("Please choose an option");
		while (playing) {
			System.out.println("1: Select an opponent");
			System.out.println("2: Select your monster");
			System.out.println("3: View your monster and your opponent");
			System.out.println("4: Help Menu");
			if (!bothMonsters) {
				System.out.println("5: Quit");
				userChoice = userSelectionInput(5);
			} else {
				System.out.println("5: Fight your monster against your selected opponent");
				System.out.println("6: Quit");
				userChoice = userSelectionInput(6);
			}
			if (userChoice == 1) {
				selectEnemies();
			} else if (userChoice == 2) {
				selectUser();
			} else if (userChoice == 3) {
				viewMonsters();
			} else if (userChoice == 4) {
				helpMenu();
			} else if (userChoice == 5 && bothMonsters) {
				fight();
			} else {
				playing = false;
			}
			if (battle.getCurrUser() != null && battle.getCurrEnemy() != null) {
				bothMonsters = true;
			} else {
				bothMonsters = false;
			}
		}
	}

}
