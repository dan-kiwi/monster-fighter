package gui;

import cli.MainMenuCLI;
import mainenviro.GameEnviro;

/**
 * Class which is the entry point for the Monster Fighter Application
 */

public class Main {
	
	/**
	 * Method the starts the Monster Fighter Application
	 * 
	 * When calling the game from the command line, if the cmd argument is given
	 * the game will run as a command line application
	 * eg. java -cp bin mainEnviro.Main cmd
	 * 
	 * When calling the game from the command line, if no argument is given
	 * the game will run as a GUI Application
	 * eg. java -cp bin mainEnviro.Main
	 */
	
	public static void main(String[] args) {
		
		GameEnviro newGame = new GameEnviro();
	
		if (args.length > 0 && (args[0].equals("cmd"))) {
			MainMenuCLI newCliGame = new MainMenuCLI(newGame);
			newCliGame.mainMenu();
		} else {
			SetupScreen newGuiGame = new SetupScreen(newGame);
			newGuiGame.StartGame();
		}
		
	}
}
