package mainEnviro;

public class Main {
	
	//Application entry point for the Monster Fighter Application
	
	public static void main(String[] args) {
		
		//When calling the game from the command line, if the cmd argument is given
		//eg. java -cp bin mainEnviro.Main cmd
		//the game will run as a command line application
		if (args.length > 0 && (args[0].equals("cmd"))) {
			GameEnviro newGame = new GameEnviro();
			newGame.mainMenu();
			
		//When calling the game from the command line, if no argument is given
		//eg. java -cp bin mainEnviro.Main
		//the game will run as a GUI Application
		} else {
			System.out.println("GUI Application is not yet implemented");
		}
		
	}
}
