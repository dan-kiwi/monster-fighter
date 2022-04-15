package mainEnviro;

import java.util.Scanner;

public class UserInput {
	
	private Scanner userInput;
	
	public UserInput() {
		Scanner input = new Scanner(System.in);
		userInput = input;
	}
	
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
	
	public String startRenameMonster() {
		System.out.println("Would you like to rename your new Monster? (Y/N)\n");
		boolean renameValid = false;
		while (!renameValid) {
			String selection = userInput.nextLine();
			if (selection.matches("[YyNn]")) {
				return selection;
			} else {
				System.out.println("Please Enter Y for yes, N for no");
			}
		}
		return userInput.next();
	}
	
	public String startNewMonsterName() {
		System.out.println("Enter your monsters new name");
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
				System.out.println("Monster ame must be between 1 and 15 characters long");
			}
		}
		return null;
	}
	
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
	
	public int basicOptions() {
		System.out.println("What would you like to do?\n");
		System.out.println("1. View Current Gold, Current Day, Days Remaining");
		System.out.println("2. View your Monsters");
		System.out.println("3. View Inventory");
		System.out.println("4. View Battles");
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
	
	public void gameEnterContinue() {
		// a small function that just waits till enter is input, just a way to break up the game
		// so everything doesn't happen all at once.
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
	
	public int gameViewMonster() {
		boolean viewMonsterValid = false;
		while (!viewMonsterValid) {
			String selection = userInput.nextLine();
			//check if the input is all numbers or nothing, numbers for the input, nothing 
			//to return to menu
			if (selection.matches("[0-9]+")) {
				if ((Integer.parseInt(selection) > 0) && (Integer.parseInt(selection) <= 2)) {
					return Integer.parseInt(selection);
				} else {
					System.out.println("please enter a number between 1-1");
				}
			} else if ((selection.matches(""))) {
				return 0;
			} else {
				System.out.println("Please Enter a number or nothing to go back");
			}
		}
		return 0;
	}
	
	public int gameRenameSwapMonster(int maxNumber) {
		boolean viewRenameValid = false;
		while (!viewRenameValid) {
			String selection = userInput.nextLine();
			//check if the input is all numbers or nothing, numbers for the input, nothing 
			//to return to menu
			if (selection.matches("[0-9]+")) {
				if ((Integer.parseInt(selection) > 0) && (Integer.parseInt(selection) <= maxNumber)) {
					return Integer.parseInt(selection);
				} else {
					System.out.println("Please enter a number between 1-" + maxNumber);
				}
			} else {
				System.out.println("Please Enter a number");
			}
		}
		return 0;
	}
	
}
