package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextPane;

import mainenviro.GameEnviro;
import monster.Monster;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/**
 * The Class MainMenuScreen. This is the Main Menu in a GUI form
 */
public class MainMenuScreen {

	private JFrame frmMainMenu;
	private static GameEnviro gameEnviro;
	private Random rand = new Random();

	/**
	 * Launch the application.
	 * will not launch, has to be accessed through main.java or setupscreen.java
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuScreen window = new MainMenuScreen(gameEnviro);
					window.frmMainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenuScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		initialize();
	}
	
	/**
	 * Method to call back the Main Menu class from other class
	 */
	public void MainMenu() {
		if (gameEnviro.getGameDay() > gameEnviro.getMaxGameDays()) {
			GameEndScreen newGameEnd = new GameEndScreen(gameEnviro, true);
			frmMainMenu.dispose();
			newGameEnd.GameEnd();
		} else if (gameEnviro.getUserMonsterList().size() == 0 && 
				gameEnviro.getUserGameShop().shopGetCheapestMonsterPrice() > gameEnviro.getUserGoldAmount()) {
			GameEndScreen newGameEnd = new GameEndScreen(gameEnviro, false);
			frmMainMenu.dispose();
			newGameEnd.GameEnd();
		} else {
		MainMenuScreen currentMenu = new MainMenuScreen(gameEnviro);
		currentMenu.frmMainMenu.setVisible(true);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainMenu = new JFrame();
		frmMainMenu.setTitle("Monster Fighter");
		frmMainMenu.setBounds(100, 100, 560, 575);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainMenu.getContentPane().setLayout(null);
		
		JButton btnMainMenuMonsters = new JButton("View Your Monsters");
		btnMainMenuMonsters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowMonsterScreen newShowMonster = new ShowMonsterScreen(gameEnviro);
    			frmMainMenu.dispose();
    			newShowMonster.ShowMonster();
			}
		});
		btnMainMenuMonsters.setFont(new Font("Verdana", Font.BOLD, 15));
		btnMainMenuMonsters.setBounds(50, 160, 450, 50);
		frmMainMenu.getContentPane().add(btnMainMenuMonsters);
		
		JButton btnMainMenuInventory = new JButton("View Your Inventory");
		btnMainMenuInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewInventoryScreen newViewInventory = new ViewInventoryScreen(gameEnviro);
    			frmMainMenu.dispose();
    			newViewInventory.ViewInventory();
			}
		});
		btnMainMenuInventory.setFont(new Font("Verdana", Font.BOLD, 15));
		btnMainMenuInventory.setBounds(50, 230, 450, 50);
		frmMainMenu.getContentPane().add(btnMainMenuInventory);
		
		JButton btnMainMenuBattles = new JButton("View Daily Battles");
		btnMainMenuBattles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBattleMenuScreen newViewBattle = new ViewBattleMenuScreen(gameEnviro);
    			frmMainMenu.dispose();
//    			newViewBattle.ViewBattleMenu();
			}
		});
		btnMainMenuBattles.setFont(new Font("Verdana", Font.BOLD, 15));
		btnMainMenuBattles.setBounds(50, 300, 450, 50);
		frmMainMenu.getContentPane().add(btnMainMenuBattles);
		
		JButton btnMainMenuShop = new JButton("Visit The Shop");
		btnMainMenuShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitShopScreen newVisitShop = new VisitShopScreen(gameEnviro);
    			frmMainMenu.dispose();
    			newVisitShop.VisitShop();
			}
		});
		btnMainMenuShop.setFont(new Font("Verdana", Font.BOLD, 15));
		btnMainMenuShop.setBounds(50, 370, 450, 50);
		frmMainMenu.getContentPane().add(btnMainMenuShop);
		
		JButton btnMainMenuSleep = new JButton("Go To Sleep");
		btnMainMenuSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean levelUp = false;
				for (int i=0; i < gameEnviro.getUserMonsterList().size(); i++) {
					if (gameEnviro.getRandEvent().monsterLevelUp(gameEnviro.getUserMonsterList().get(i))){
						levelUp = true;
					}
				}
				boolean monsterLeaves = false;
				for (int i=0; i < gameEnviro.getUserMonsterList().size(); i++) {
					if (gameEnviro.getRandEvent().monsterLeaves(gameEnviro.getUserMonsterList().get(i))){
						gameEnviro.getUserMonsterList().remove(gameEnviro.getUserMonsterList().get(i));
						monsterLeaves = true;
					}
				}
				boolean monsterJoins = false;
				if (gameEnviro.getUserMonsterList().size() < 4) {
					if (gameEnviro.getRandEvent().willAddMonster()) {
						String tempMonster = gameEnviro.getRandEvent().addMonster();
						gameEnviro.addMonster(gameEnviro.getNewMonster(tempMonster));
						monsterJoins = true;
					}
				}
				
				GameSleepScreen newGameSleep = new GameSleepScreen(gameEnviro, levelUp, monsterLeaves, monsterJoins);
    			frmMainMenu.dispose();
    			newGameSleep.GameSleep();
			}
		});
		btnMainMenuSleep.setFont(new Font("Verdana", Font.BOLD, 15));
		btnMainMenuSleep.setBounds(50, 440, 450, 50);
		frmMainMenu.getContentPane().add(btnMainMenuSleep);
		
		JLabel lblMainUserName = new JLabel("Player: " + gameEnviro.getUserGameName());
		lblMainUserName.setFont(new Font("Verdana", Font.BOLD, 18));
		lblMainUserName.setBounds(50, 30, 220, 40);
		frmMainMenu.getContentPane().add(lblMainUserName);
		
		JLabel lblMainGoldAmount = new JLabel("Gold: " + gameEnviro.getUserGoldAmount());
		lblMainGoldAmount.setFont(new Font("Verdana", Font.BOLD, 14));
		lblMainGoldAmount.setBounds(150, 80, 124, 25);
		frmMainMenu.getContentPane().add(lblMainGoldAmount);
		
		JLabel lblMainUserScore = new JLabel("Score: " + gameEnviro.getUserGameScore());
		lblMainUserScore.setFont(new Font("Verdana", Font.BOLD, 14));
		lblMainUserScore.setBounds(50, 80, 111, 25);
		frmMainMenu.getContentPane().add(lblMainUserScore);
		
		JLabel lblMainCurrentDay = new JLabel("Day: " + gameEnviro.getGameDay());
		lblMainCurrentDay.setFont(new Font("Verdana", Font.BOLD, 14));
		lblMainCurrentDay.setBounds(240, 80, 111, 25);
		frmMainMenu.getContentPane().add(lblMainCurrentDay);
		
		JLabel lblMainDaysRemaining = new JLabel("Days Remaining: " + (gameEnviro.getMaxGameDays() - gameEnviro.getGameDay()));
		lblMainDaysRemaining.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMainDaysRemaining.setFont(new Font("Verdana", Font.BOLD, 18));
		lblMainDaysRemaining.setBounds(290, 30, 210, 40);
		frmMainMenu.getContentPane().add(lblMainDaysRemaining);
	}
}
