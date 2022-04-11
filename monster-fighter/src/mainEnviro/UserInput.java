package mainEnviro;

import java.util.Scanner;

public class UserInput {
	
	private Scanner userInput;
	
	public UserInput() {
		Scanner input = new Scanner(System.in);
		userInput = input;
	}
	
	public String startDifficulty() {
		System.out.println("Welcome to Random working title\n");
		System.out.println("Bits here for the story maybe\n");
		System.out.println("What difficulty do you want\n");
		System.out.println("Easy means more gold and weaker enemies\n");
		System.out.println("Hard means less gold and tougher enemies\n");
		System.out.println("1. Easy");
		System.out.println("2. Hard");
		System.out.println("Please enter a number 1-2");
		if (userInput.nextInt() == 1){
			return ("Easy");
		} else if (userInput.nextInt() == 2) {
			return ("Hard");
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
