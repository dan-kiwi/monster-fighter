package mainEnviro;

public class GameEnviro {
	
	private int gameDay = 1;
	private int maxGameDays;
	private UserInput gameUserInput;
	
	
	public GameEnviro(int maxDays) {
		maxGameDays = maxDays;
		gameUserInput = new UserInput();	//Builds a new scanner object to get user input
		
		while (gameDay <= maxGameDays) {	
			
			/*
			Just a bunch of if/elif checking user inputs, the game day only advances after
			sleep is chosen. We can put all of the events under these statements. The battle
			object under 1 etc.
			*/
			int userAction = gameUserInput.basicOptions();
			if (userAction == 1) {
				System.out.println("You have Chosen to Battle Monsters\n");
			} else if (userAction == 2){
				System.out.println("You have Chosen to Go to the shop\n");
			} else if (userAction == 3) {
				System.out.println("You have Chosen to Inspect Monsters\n");
			} else if (userAction == 4) {
				System.out.println("You have Chosen to Sleep\n");
				System.out.println("Day " + gameDay + " is over\n");
				gameDay += 1;
			}
			
		}
		System.out.println("Game Over"); //When current day is past the max days game ends.
	}
	
	public static void main(String[] args) {
		GameEnviro testgame = new GameEnviro(10);
	}
}
