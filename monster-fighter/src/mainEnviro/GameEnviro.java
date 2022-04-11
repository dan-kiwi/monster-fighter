package mainEnviro;

import java.util.ArrayList;

import monster.*;


public class GameEnviro {
	
	//Constants
	private static final double easyMonster = 0.85;
	private static final double easyGold = 1.5;
	private static final int easyUserGold = 300;
	private static final double hardMonster = 1.15;
	private static final double hardGold = 0.85;
	private static final int hardUserGold = 150;
	
	private int gameDay = 1;
	private int maxGameDays;
	private String gameDifficulty;
	private double monsterDifficulty;
	private double goldDifficulty;
	private int userGoldAmount;
	private UserInput gameUserInput;
	private ArrayList<Monster> userMonsterList = new ArrayList<Monster>();
	
	public void startNewGame() {
		gameUserInput = new UserInput();
		starterSetDifficulty(gameUserInput.startDifficulty());
		maxGameDays = gameUserInput.startGameDays();
		starterMonsterSelect(gameUserInput.startSelectMonster());	
	}
	
	public void starterSetDifficulty(String selection) {
		gameDifficulty = selection;
		if (selection == "Easy") {
			monsterDifficulty = easyMonster;
			goldDifficulty = easyGold;
			userGoldAmount = easyUserGold;
		} else if (selection == "Hard") {
			monsterDifficulty = hardMonster;
			goldDifficulty = hardGold;
			userGoldAmount = hardUserGold;
		}
	}
	
	public void starterMonsterSelect(int selection) {
		if (selection == 1) {
			Monster userDragon = new Dragon();
			userMonsterList.add(userDragon);
		}
	}
	
	
	public GameEnviro() {
		startNewGame();
		//gameUserInput = new UserInput();	//Builds a new scanner object to get user input
		
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
		GameEnviro testgame = new GameEnviro();
	}
}
