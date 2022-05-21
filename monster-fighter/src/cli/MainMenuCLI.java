package cli;

import java.util.Collections;
import java.util.Scanner;

import items.Items;
import mainenviro.GameEnviro;
import monster.Gnome;
import monster.Goblin;
import monster.Imp;
import monster.Monster;

/**
 * The Class MainMenuCLI. 
 * The command line interface for the main menu.
 */
public class MainMenuCLI {
	
	private GameEnviro game;
	private Scanner userInput;
	
	/**
	 * Instantiates a new main menu CLI.
	 *
	 * @param game the game
	 */
	public MainMenuCLI(GameEnviro game) {
		this.game = game;
		this.userInput = new Scanner(System.in);
	}
	
	/**
	 * Collect's what the monster would like to do
	 *
	 * @return the int, representing the option choosen
	 */
	public int basicOptions() {
		System.out.println("What would you like to do?\n");
		System.out.println("1. View Current Gold, Current Day, Days Remaining");
		System.out.println("2. View your Monsters");
		System.out.println("3. View Inventory");
		System.out.println("4. Battle Arena");
		System.out.println("5. Visit the Shop");
		System.out.println("6. Go to Sleep");
		boolean basicValid = false;
		while (!basicValid) {
			String selection = userInput.nextLine();
			if (selection.matches("[0-9]+")) {
				if ((Integer.parseInt(selection) > 0) && (Integer.parseInt(selection) <= 6)) {
					return Integer.parseInt(selection);
				} else {
					System.out.println("please enter a number between 1-6");
				}
			} else {
				System.out.println("Please Enter a number");
			}
		}
		return 0;
	}
	
	/**
	 * A small function that just waits till the user presses enter. 
	 * A way to break up the game so everything doesn't happen all at once.
	 */
	public void gameEnterContinue() {
		System.out.println("Press Enter to continue");
		boolean continueValid = false;
		while (!continueValid) {
			String selection = userInput.nextLine();
			if (selection.matches("")) {
				return;
			} else {
				System.out.println("Press Enter to continue");
			}
		}
	}
	
	/**
	 * Queries the user for a number input between 0 and maxNumber or no input
	 * 
	 * @param maxNumber, the max number the user can input
	 * @return the int, or 0 for no input
	 */
	public int gameGetIntEnter(int maxNumber) {
		boolean viewMonsterValid = false;
		while (!viewMonsterValid) {
			String selection = userInput.nextLine();
			
			if (selection.matches("[0-9]+")) {
				if ((Integer.parseInt(selection) > 0) && (Integer.parseInt(selection) <= maxNumber)) {
					return Integer.parseInt(selection);
				} else {
					System.out.println("Please enter a number between 1-" + maxNumber);
				}
			} else if ((selection.matches(""))) {
				return 0;
			} else {
				System.out.println("Please Enter a number or nothing to go back");
			}
		}
		return 0;
	}
	

	/**
	 * Outputs the current game statistics.
	 */
	public void viewGameInfo() {
		System.out.println("\n");
		System.out.println("Welcome " + game.getUserGameName());
		System.out.println("You have " + game.getUserGoldAmount() + " Gold");
		System.out.println("The Game difficulty is " + game.getGameDifficulty());
		System.out.println("Your current score is: " + game.getUserGameScore());
		System.out.println("Today is Day " + game.getGameDay());
		System.out.println("You have " + (game.getMaxGameDays() - game.getGameDay()) + " Day(s) remaining");
		System.out.println("\n");
		gameEnterContinue();
	}
	
	/**
	 * Output's a the user's current monsters
	 * Gives the user a chance to swap monsters
	 */
	public void viewGameMonsters() {
		boolean returnToMenu = false;
		while (!returnToMenu) {
			if (game.getUserMonsterList().size() == 0) {
				System.out.println("You currently have no Monsters\n");
				gameEnterContinue();
				return;
			}
			System.out.println("\n");
			System.out.println("Welcome " + game.getUserGameName());
			System.out.println("Here are your monsters\n");
			System.out.println(game.userDisplayMonsters(false));
			System.out.println("\n");
			System.out.println("1. Swap Monster Position");
			System.out.println("2. Rename Monster");
			System.out.println("Press enter to go back to menu");
			int selection = gameGetIntEnter(2);
			if (selection == 0) {
				returnToMenu = true;
			} else if (selection == 1) {
				userSwapMonster();
			} else if (selection == 2) {
				userRenameMonster();
			}
		}
	}
	
	/**
	 * Method that allows the user to swap the order of their monsters in their list
	 */
	public void userSwapMonster() {
		System.out.println("Which monster would you like to swap?");
		System.out.println("Enter number or press enter to go back\n");
		int monsterSwapChoice = gameGetIntEnter(game.getUserMonsterList().size());
		if (monsterSwapChoice == 0) {
			return;
		}
		System.out.println("Which monster would you like to swap this with?");
		System.out.println("Enter number or press enter to go back\n");
		int monsterSwapToChoice = gameGetIntEnter(game.getUserMonsterList().size());
		if (monsterSwapToChoice == 0) {
			return;
		}
		Collections.swap(game.getUserMonsterList(), monsterSwapChoice - 1, monsterSwapToChoice - 1);
	}
	
	/**
	 * Allow's a user to rename one of their monsters
	 */
	public void userRenameMonster() {
		System.out.println("Which monster would you like to rename?");
		System.out.println("Enter number or press enter to go back\n");
		int monsterRenameChoice = gameGetIntEnter(game.getUserMonsterList().size());
		if (monsterRenameChoice == 0) {
			return;
		}
		game.getUserMonsterList().get(monsterRenameChoice - 1).setMonsterName(startNewMonsterName());
	}
	
	/**
	 * Collect's the monsters new name from the user
	 * This method only executed if the user has selected to rename their monster
	 *
	 * @return the string, the monster's new name
	 */
	public String startNewMonsterName() {
		System.out.println("Enter your monsters new name:");
		boolean nameValid = false;
		while (!nameValid) {
			String selection = userInput.nextLine();
			if((selection.length() > 0) && (selection.length() <= 15)) {
				if (selection.matches("[A-Za-z0-9]*")) {
					return selection;
				} else {
					System.out.println("Name must only contain letters or numbers");
				}
			} else {
				System.out.println("Monster name must be between 1 and 15 characters long");
			}
		}
		return null;
	}
	
	/**
	 * Allow's the user to view all of their items
	 */
	public void viewGameItems() {
		//Shows user game inventory and asks user to select an item to use
		boolean returnToMenu = false;
		while (!returnToMenu) {
			if (game.getUserItemList().size() == 0) {
				System.out.println("You have no items in your inventory");
				gameEnterContinue();
				break;
			} else {
				System.out.println("\n");
				System.out.println("Welcome " + game.getUserGameName());
				System.out.println("Here is your inventory\n");
				System.out.println(game.userDisplayItems(false));
				System.out.println("\n");
				System.out.println("Select an item to use on a Monster or Press enter to go back");
				int viewItemInt = gameGetIntEnter(game.getUserItemList().size());
				if (viewItemInt == 0) {
					returnToMenu = true;
				} else {
					if (game.getUserMonsterList().size() == 0) {
						System.out.println("You have no Monsters to use items on");
						gameEnterContinue();
					} else {
						useItemOnMonster(viewItemInt);
					}
				}
			}
		}
	}
	
	/**
	 * Allow's a selected item to be used on a monster
	 *
	 * @param viewItemInt the view item int
	 */
	public void useItemOnMonster(int viewItemInt) {
		//Given an integer selected by the user, gets the item and asks user to select Monster to use it on
		Items selectedItemToUse = game.getUserItemList().get(viewItemInt - 1);
		System.out.println("\n");
		System.out.println("You have Selected " + selectedItemToUse.getItemName());
		System.out.println("\n");
		System.out.println("Which Monster do you want to use " + selectedItemToUse.getItemName() + " on?\n");
		System.out.println(game.userDisplayMonsters(false));
		System.out.println("\n");
		System.out.println("Select a Monster or Press enter to go back");
		int viewItemMonsterInt = gameGetIntEnter(game.getUserMonsterList().size());
		if (viewItemMonsterInt == 0) {
			return;
		}
		selectedItemToUse.useItemOnMonster(game.getUserMonsterList().get(viewItemMonsterInt - 1));
		game.getUserItemList().remove(viewItemInt - 1);
		System.out.println("You used " + selectedItemToUse.getItemName() + " on " 
				+ game.getUserMonsterList().get(viewItemMonsterInt - 1).getDisplayName());
		gameEnterContinue();
	}
	
	/**
	 * Occurs when the game is over. Different endings depending on how the game ends
	 *
	 * @param gameEndReason, an int relating to which ending has occurred
	 */
	public void userGameOver(int gameEndReason) {
		if (gameEndReason == 1) {
			System.out.println("Well done " + game.getUserGameName() + ", you have completed the Game.");
			gameEnterContinue();
			System.out.println("Your Final Gold Amount is " + game.getUserGoldAmount() + " Gold.");
			gameEnterContinue();
			System.out.println("Your Final Enemy Kill count is: " + game.getUserMonsterKills());
			gameEnterContinue();
			System.out.println("This gives you a Final Score of: " + (game.getUserGameScore() + game.getUserGoldAmount()));
			gameEnterContinue();
			System.out.println("Thank you for playing our game :)");
			gameEnterContinue();
		} else if (gameEndReason == 2) {
			System.out.println("Unfortunately " + game.getUserGameName() + ", you have run out of Monsters and can't buy anymore.");
			gameEnterContinue();
			System.out.println("Your Final Gold Amount is " + game.getUserGoldAmount() + " Gold.");
			gameEnterContinue();
			System.out.println("Your Final Enemy Kill count is: " + game.getUserMonsterKills());
			gameEnterContinue();
			System.out.println("This gives you a Final Score of: " + (game.getUserGameScore() + game.getUserGoldAmount()));
			gameEnterContinue();
			System.out.println("Thank you for playing our game :)");
			gameEnterContinue();
		}
	}
	
	/**
	 * Method is called when player decides to sleep.
	 * Interacts with the random event class to decide if a random event has occurred overnight
	 * If a random event happens or not, the player is informed
	 */
	public void randomEvent() {
		boolean happened = false;
		for (int i = 0; i < game.getUserMonsterList().size(); i++) {
			Monster currMonster = game.getUserMonsterList().get(i);
			if (game.getRandEvent().monsterLeaves(currMonster)) {
				System.out.println(currMonster.getDisplayName() + " has ran away overnight");
				game.getUserMonsterList().remove(i);
				i--;
				happened = true;
			} else if (game.getRandEvent().monsterLevelUp(currMonster)) {
				System.out.println(currMonster.getDisplayName() + " has leveled up overnight");
				happened = true;
			}
		}
		if (game.getRandEvent().willAddMonster()) {
			happened = true;
			String stringMonster = game.getRandEvent().addMonster();
			game.addMonster(game.getNewMonster(stringMonster));
		}
		if (!happened) System.out.println("No random event has occured tonight");
	}
	
	public void startNewGame() {
		SetupCLI start = new SetupCLI(game, this);
		start.startNewGame();
	}
	
	public void mainMenu() {
		
		startNewGame(); //runs a function to query the user for game starting information
		 //initalized RandomEvent
		int gameEndReason = 1;
		while (game.getGameDay() <= game.getMaxGameDays()) {	
			
			/*
			Just a bunch of if/elif checking user inputs, the game day only advances after
			sleep is chosen. We can put all of the events under these statements. The battle
			object under 4 etc.
			*/
			
			//if the user has no monsters left, check if the user can buy a new monster
			//if they can't afford one the game ends
			//this is checked everytime the user goes back to the main menu
			if (game.getUserMonsterList().size() == 0) { 
				if (game.getUserGoldAmount() < game.getUserGameShop().shopGetCheapestMonsterPrice()) {
					gameEndReason = 2;
					break;
				}
			}
			int userAction = basicOptions();
			if (userAction == 1) {
				System.out.println("You have Chosen to View Gold/Days");
				viewGameInfo();
			} else if (userAction == 2){
				System.out.println("You have Chosen to View your Monsters");
				viewGameMonsters();
			} else if (userAction == 3) {
				System.out.println("You have Chosen to View Inventory");
				viewGameItems();
			} else if (userAction == 4) {
				System.out.println("You have Chosen to Battle");
				BattleCLI battleCLI = new BattleCLI(game);
				battleCLI.mainMenu();
			} else if (userAction == 5) {
				System.out.println("You have Chosen to Visit the Shop");
				ShopCLI shopCLI = new ShopCLI(game);
				shopCLI.viewGameShop();
			} else if (userAction == 6) {
				System.out.println("You have Chosen to Sleep");
				game.nightTime();
			}
		}
		System.out.println("Game Over"); //When current day is past the max days game ends.
		userGameOver(gameEndReason);
	}
	
	public static void main(String[] args) {
		GameEnviro game = new GameEnviro();
		MainMenuCLI cli = new MainMenuCLI(game);
		cli.mainMenu();
	}
}
