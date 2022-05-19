package cli;

import java.util.Scanner;

import mainenviro.GameEnviro;
import monster.Gnome;
import monster.Goblin;
import monster.Imp;
import monster.Monster;

/**
 * The Class SetupCLI. This is the command line interface for setting up the game. 
 * This class and all methods are only called at the start of the game.
 */
public class SetupCLI {
	
	private GameEnviro game; 
	private Scanner userInput;
	private MainMenuCLI mainCLI;
	
	/**
	 * Instantiates a new setup CLI.
	 *
	 * @param game the game
	 * @param userInput the user input
	 */
	SetupCLI(GameEnviro game, MainMenuCLI mainCLI) {
		this.game = game;
		this.mainCLI = mainCLI;
		userInput = new Scanner(System.in);
	}
	
	/**
	 * Method called only at start
	 * Starts a new game. Get's user input using UserInput.
	 * User input is then called into other methods in this class
	 */
	public void startNewGame() {
		game.setUserGameName(startUserName());
		game.setMaxGameDays(startGameDays());
		Monster tempMonster = starterMonsterSelect(startSelectMonster());
		if (willRenameMonster()) {
			String newName = mainCLI.startNewMonsterName();
			tempMonster.setMonsterName(newName);
		}
		game.addMonster(tempMonster);
		game.starterSetDifficulty(startDifficulty());	
	}
	
	/**
	 * Obtain's a user's name.
	 * Checks to ensure that it is an appropriate length and has the correct characters.
	 *
	 * @return the name.
	 */
	public String startUserName() {
		System.out.println("Welcome to Random working title");
		System.out.println("Bits here for the story maybe");
		System.out.println("What is your name?");
		boolean nameValid = false;
		while (!nameValid) {
			String selection = userInput.nextLine();
			//input must be between 3 and 15 characters or enter it again
			if((selection.length() >= 3) && (selection.length() <= 15)) {
				//input must be some configuration of capital and small letters
				if (selection.matches("[A-Za-z]*")) {
					return selection;
				} else {
					System.out.println("Name must only contain letters");
				}
			} else {
				System.out.println("Name must be between 3 and 15 characters long");
			}
		}
		return null;
	}
	
	/**
	 * Obtains from the user how many day's the user would like to play for.
	 *
	 * @return the int number of days.
	 */
	public int startGameDays() {
		System.out.println("How many days do you want to play\n");
		boolean dayValid = false;
		while (!dayValid) {
			String selection = userInput.nextLine();
			if (selection.matches("[0-9]+")) {
				if ((Integer.parseInt(selection) >= 5) && (Integer.parseInt(selection) <= 15)) {
					return Integer.parseInt(selection);
				} else {
					System.out.println("Number of Days must be between 5 and 15");
				}
			} else {
				System.out.println("Please Enter a number");
			}
		}
		return 0;
	}

	
	/**
	 * Allows the user to select a starting monster.
	 *
	 * @return the int, representing which monster the user has selected
	 */
	public int startSelectMonster() {
		System.out.println("Now choose your starting monster\n");
		System.out.println("1. Imp - Health: 50 Attack: 30 Defence: 20");
		System.out.println("2. Gnome - Health: 75 Attack: 10 Defence: 30");
		System.out.println("3. Goblin - Health: 100 Attack: 20 Defence: 10");
		boolean monsterValid = false;
		while (!monsterValid) {
			String selection = userInput.nextLine();
			if (selection.matches("[0-9]+")) {
				if ((Integer.parseInt(selection) > 0) && (Integer.parseInt(selection) <= 3)) {
					return Integer.parseInt(selection);
				} else {
					System.out.println("please enter a number between 1-3");
				}
			} else {
				System.out.println("Please Enter a number");
			}
		}
		return 0;
	}
	
	/**
	 * Checks if the user would like to rename their monster
	 *
	 * @return the string, either y for yes or n for no
	 */
	public boolean willRenameMonster() {
		System.out.println("Would you like to rename your new Monster? (Y/N)\n");
		while (true) {
			String selection = userInput.nextLine();
			if (selection.matches("[Yy]")) {
				return true;
			} else if (selection.matches("[Nn]")){
				return false;
			} else {
				System.out.println("Please Enter Y for yes, N for no");
			}
		}
	}
	

	
	/**
	 * Collect's the user's difficulty level
	 *
	 * @return the string, either "Easy" or "Hard"
	 */
	public String startDifficulty() {
		System.out.println("What difficulty do you want\n");
		System.out.println("Easy means more gold and weaker enemies");
		System.out.println("Hard means less gold and tougher enemies\n");
		System.out.println("1. Easy");
		System.out.println("2. Hard");
		boolean difficultyValid = false;
		while (!difficultyValid) {
			String selection = userInput.nextLine();
			if (selection.matches("[1-2]")) {
				if (Integer.parseInt(selection) == 1) {
					return ("Easy");
				} else if (Integer.parseInt(selection) == 2) {
					return ("Hard");
				}
			} else {
				System.out.println("Please Enter 1 for Easy, 2 for Hard");
			}
		}
		return null;
	}
	/**
	 * Method called only at start
	 * Method that determines the monster selected by user
	 *
	 * @param int, representing the selection
	 */
	public Monster starterMonsterSelect(int selection) {
		Monster tempMonster = new Imp();
		if (selection == 2) {
			tempMonster = new Gnome();
		} else if (selection == 3) {
			tempMonster = new Goblin();
		}
		return tempMonster;
	}	
}
