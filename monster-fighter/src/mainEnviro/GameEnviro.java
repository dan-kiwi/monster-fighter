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
	private String userGameName;
	private int maxGameDays;
	private String gameDifficulty;
	private double monsterDifficulty;
	private double goldDifficulty;
	private int userGoldAmount;
	private UserInput gameUserInput;
	private ArrayList<Monster> userMonsterList = new ArrayList<Monster>();
	
	public void startNewGame() {
		gameUserInput = new UserInput();
	
		userGameName = gameUserInput.startUserName();
		maxGameDays = gameUserInput.startGameDays();
		starterMonsterSelect(gameUserInput.startSelectMonster());
		starterMonsterRename(gameUserInput.startRenameMonster());
		starterSetDifficulty(gameUserInput.startDifficulty());	
	}
	
	public void starterMonsterSelect(int selection) {
		if (selection == 1) {
			Monster userDragon = new Dragon();
			userMonsterList.add(userDragon);
		}
	}
	
	public void starterMonsterRename(String selection) {
		if (selection.toLowerCase().equals("y")) {
			String newName = gameUserInput.startNewMonsterName();
			userMonsterList.get(0).setMonsterName(newName); //Starter monster will always be at index 0
		}
	}
	
	public void starterSetDifficulty(String selection) {
		gameDifficulty = selection;
		if (selection.equals("Easy")) {
			monsterDifficulty = easyMonster;
			goldDifficulty = easyGold;
			userGoldAmount = easyUserGold;
		} else if (selection.equals("Hard")) {
			monsterDifficulty = hardMonster;
			goldDifficulty = hardGold;
			userGoldAmount = hardUserGold;
		}
	}
	
	public void viewGameInfo() {
		System.out.println("\n");
		System.out.println("Welcome " + userGameName);
		System.out.println("You have " + userGoldAmount + " Gold");
		System.out.println("Today is Day " + gameDay);
		System.out.println("You have " + (maxGameDays - gameDay) + " Day(s) remaining");
		System.out.println("\n");
		gameUserInput.gameEnterContinue();
	}
	
	public GameEnviro() {
		
		startNewGame(); //runs a function to query the user for game starting information
		
		while (gameDay <= maxGameDays) {	
			
			/*
			Just a bunch of if/elif checking user inputs, the game day only advances after
			sleep is chosen. We can put all of the events under these statements. The battle
			object under 4 etc.
			*/
			int userAction = gameUserInput.basicOptions();
			if (userAction == 1) {
				System.out.println("You have Chosen to View Gold/Days");
				viewGameInfo();
			} else if (userAction == 2){
				System.out.println("You have Chosen to View your Monsters");
			} else if (userAction == 3) {
				System.out.println("You have Chosen to View Inventory");
			} else if (userAction == 4) {
				System.out.println("You have Chosen to View Battles");
			} else if (userAction == 5) {
				System.out.println("You have Chosen to Visit the Shop");
			} else if (userAction == 6) {
				System.out.println("You have Chosen to Sleep");
				System.out.println("Random Event may happen");
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
