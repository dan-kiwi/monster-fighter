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
		System.out.println("Please enter a number");
		return userInput.nextInt();
	}
	
	public int startSelectMonster() {
		System.out.println("Now choose your starting monster\n");
		System.out.println("1. Dragon - Health: 100 Attack: 100 Defence: 100\n");
		return userInput.nextInt();
	}
	
	public String startRenameMonster() {
		System.out.println("Would you like to rename your new Monster? (Y/N)\n");
		return userInput.next();
	}
	
	public String startNewMonsterName() {
		System.out.println("Enter your monsters new name");
		return userInput.next();
	}
	
	public String startDifficulty() {
		
		System.out.println("What difficulty do you want\n");
		System.out.println("Easy means more gold and weaker enemies");
		System.out.println("Hard means less gold and tougher enemies\n");
		System.out.println("1. Easy");
		System.out.println("2. Hard");
		System.out.println("Please enter a number 1-2");
		int selection = userInput.nextInt();
		if (selection == 1){
			return ("Easy");
		} else if (selection == 2) {
			return ("Hard");
		}
		return null;
	}
	
	public int basicOptions() {
		System.out.println("What would you like to do?\n");
		System.out.println("1. Battle Monsters");
		System.out.println("2. Go to the Shop");
		System.out.println("3. Inspect Monsters");
		System.out.println("4. Sleep");
		System.out.println("Please enter a number 1-4");
		return userInput.nextInt();
	}
}
