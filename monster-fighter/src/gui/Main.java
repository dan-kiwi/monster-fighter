package gui;

import cli.CLI;
import mainenviro.GameEnviro;

/**
 * Class which is the entry point for the Monster Fighter Application
 */

public class Main {
	
	/**
	 * Method the starts the Monster Fighter Application
	 */
	
	public static void main(String[] args) {
		
		//Makes a new game environment which is then used by either the CLA or the GUI
		GameEnviro newGame = new GameEnviro();
		
		//When calling the game from the command line, if the cmd argument is given
		//eg. java -cp bin mainEnviro.Main cmd
		//the game will run as a command line application
		if (args.length > 0 && (args[0].equals("cmd"))) {
			CLI newCliGame = new CLI(newGame);
			newCliGame.mainMenu();
			
		//When calling the game from the command line, if no argument is given
		//eg. java -cp bin mainEnviro.Main
		//the game will run as a GUI Application
		} else {
			SetupScreen newGuiGame = new SetupScreen(newGame);
			newGuiGame.StartGame();
		}
		
	}
}
