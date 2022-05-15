package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import mainenviro.Battle;
import mainenviro.GameEnviro;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameSleepScreen {

	private JFrame frmGameSleep;
	private static GameEnviro gameEnviro;
	private static boolean levelUp;
	private boolean monsterLeaves;
	private boolean monsterJoins;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameSleepScreen window = new GameSleepScreen(null, levelUp, levelUp, levelUp);
					window.frmGameSleep.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameSleepScreen(GameEnviro newGame, boolean tempLevelUp, boolean tempMonsterLeaves, boolean tempMonsterJoins) {
		gameEnviro = newGame;
		levelUp = tempLevelUp;
		monsterLeaves = tempMonsterLeaves;
		monsterJoins = tempMonsterJoins;
		initialize();
	}
	
	/**
	 * Method to call back the ShopBuyMonsterScreen from other classes
	 */
	public void GameSleep() {
		GameSleepScreen gameSleep = new GameSleepScreen(gameEnviro, levelUp, monsterLeaves, monsterJoins);
		gameSleep.frmGameSleep.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGameSleep = new JFrame();
		frmGameSleep.setBounds(100, 100, 450, 449);
		frmGameSleep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGameSleep.getContentPane().setLayout(null);
		
		JLabel lblGameSleepTitle = new JLabel("You go to Sleep for the Day");
		lblGameSleepTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameSleepTitle.setFont(new Font("Verdana", Font.BOLD, 17));
		lblGameSleepTitle.setBounds(75, 11, 281, 29);
		frmGameSleep.getContentPane().add(lblGameSleepTitle);
		
		JLabel lblGameSleepDay = new JLabel("Day " + gameEnviro.getGameDay() + " has Ended");
		lblGameSleepDay.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameSleepDay.setFont(new Font("Verdana", Font.BOLD, 14));
		lblGameSleepDay.setBounds(31, 51, 233, 29);
		frmGameSleep.getContentPane().add(lblGameSleepDay);
		
		JLabel lblGameSleepShop = new JLabel("- The Shop will have new stock tomorrow");
		lblGameSleepShop.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameSleepShop.setFont(new Font("Verdana", Font.BOLD, 13));
		lblGameSleepShop.setBounds(31, 80, 325, 29);
		frmGameSleep.getContentPane().add(lblGameSleepShop);
		
		JLabel lblGameSleepBattle = new JLabel("- There will be new Battles available tomorrow");
		lblGameSleepBattle.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameSleepBattle.setFont(new Font("Verdana", Font.BOLD, 13));
		lblGameSleepBattle.setBounds(31, 110, 362, 29);
		frmGameSleep.getContentPane().add(lblGameSleepBattle);
		
		JLabel lblGameSleepMonster = new JLabel("- Your Monster(s) Heal and reset stats");
		lblGameSleepMonster.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameSleepMonster.setFont(new Font("Verdana", Font.BOLD, 13));
		lblGameSleepMonster.setBounds(31, 139, 325, 29);
		frmGameSleep.getContentPane().add(lblGameSleepMonster);
		
		JButton btnGameSleep = new JButton("Continue");
		btnGameSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnviro.getUserGameShop().resetShopStock();
				for (int i=0; i < gameEnviro.getUserMonsterList().size(); i++) {
					gameEnviro.getUserMonsterList().get(i).resetMonsterStats();
				}
				gameEnviro.addGameDay(1);
				Battle newBattle = new Battle(gameEnviro);
				gameEnviro.setBattle(newBattle);
				
				MainMenuScreen newMainMenu = new MainMenuScreen(gameEnviro);
				frmGameSleep.dispose();
    			newMainMenu.MainMenu();
			}
		});
		btnGameSleep.setFont(new Font("Verdana", Font.BOLD, 15));
		btnGameSleep.setBounds(126, 352, 173, 47);
		frmGameSleep.getContentPane().add(btnGameSleep);
		
		if (levelUp) {
			JLabel lblGameSleepMonLvl = new JLabel("- A Monster Leveled up Overnight");
			lblGameSleepMonLvl.setHorizontalAlignment(SwingConstants.LEFT);
			lblGameSleepMonLvl.setFont(new Font("Verdana", Font.BOLD, 13));
			lblGameSleepMonLvl.setBounds(31, 207, 325, 29);
			frmGameSleep.getContentPane().add(lblGameSleepMonLvl);
		}
		
		if (monsterJoins) {
			JLabel lblGameSleepMonJoin = new JLabel("- A Monster Joined your Party Overnight");
			lblGameSleepMonJoin.setHorizontalAlignment(SwingConstants.LEFT);
			lblGameSleepMonJoin.setFont(new Font("Verdana", Font.BOLD, 13));
			lblGameSleepMonJoin.setBounds(31, 247, 325, 29);
			frmGameSleep.getContentPane().add(lblGameSleepMonJoin);
		}
		
		if (monsterLeaves) {
			JLabel lblGameSleepMonLeave = new JLabel("- A Monster Left your Party Overnight");
			lblGameSleepMonLeave.setHorizontalAlignment(SwingConstants.LEFT);
			lblGameSleepMonLeave.setFont(new Font("Verdana", Font.BOLD, 13));
			lblGameSleepMonLeave.setBounds(31, 287, 325, 29);
			frmGameSleep.getContentPane().add(lblGameSleepMonLeave);
		}
		
		
	}
}
