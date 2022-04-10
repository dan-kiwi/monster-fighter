package mainEnviro;

import java.util.Scanner;

public class UserInput {
	
	private Scanner userInput;
	
	public UserInput() {
		Scanner input = new Scanner(System.in);
		userInput = input;
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
