package gui;

import javax.swing.JFrame;

import mainenviro.GameEnviro;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewFightDamageScreen {
	
	private JFrame frmViewFightDamage;
	private static GameEnviro gameEnviro;
	private String userChoice;
	private static String enemyChoice;
	private int userDamageDone;
	private int enemyDamageDone;


	/**
 * Create the application.
 *
 * @param newGame the game environment
 * @param tempUserChoice the user fight choice
 * @param tempEnemyChoice the enemy fight choice
 * @param tempUserDamageDone the user damage incurred
 * @param tempEnemyDamageDone the enemy damage incurred
 */
	public ViewFightDamageScreen(GameEnviro newGame, String tempUserChoice, String tempEnemyChoice, int tempUserDamageDone, int tempEnemyDamageDone) {
		gameEnviro = newGame;
		userChoice = tempUserChoice;
		enemyChoice = tempEnemyChoice;
		userDamageDone = tempUserDamageDone;
		enemyDamageDone = tempEnemyDamageDone;
		initialize();
	}
	
	/**
	 * Method to call back the View Fight Damage class from the Main Menu object
	 */
	public void ViewFightDamage() {
		ViewFightDamageScreen viewFightDamage = new ViewFightDamageScreen(gameEnviro, userChoice, enemyChoice, userDamageDone, enemyDamageDone);
		viewFightDamage.frmViewFightDamage.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewFightDamage = new JFrame();
		frmViewFightDamage.setTitle("Damage Done");
		frmViewFightDamage.setBounds(100, 100, 450, 300);
		frmViewFightDamage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewFightDamage.getContentPane().setLayout(null);
		
		JLabel lblFightDamageUserAction = new JLabel(gameEnviro.getBattle().getCurrUser().getMonsterName()
				+ " used " + userChoice + ".");
		lblFightDamageUserAction.setFont(new Font("Verdana", Font.BOLD, 13));
		lblFightDamageUserAction.setBounds(10, 11, 414, 24);
		frmViewFightDamage.getContentPane().add(lblFightDamageUserAction);
		
		JLabel lblFightDamageUserDamage = new JLabel(gameEnviro.getBattle().getCurrUser().getMonsterName()
				+ " did " + userDamageDone + " Damage.");
		lblFightDamageUserDamage.setFont(new Font("Verdana", Font.BOLD, 13));
		lblFightDamageUserDamage.setBounds(10, 35, 414, 24);
		frmViewFightDamage.getContentPane().add(lblFightDamageUserDamage);
		
		JLabel lblFightDamageEnemyAction = new JLabel(gameEnviro.getBattle().getCurrEnemy().getMonsterName()
				+ " used " + enemyChoice + ".");
		lblFightDamageEnemyAction.setFont(new Font("Verdana", Font.BOLD, 13));
		lblFightDamageEnemyAction.setBounds(10, 87, 414, 24);
		frmViewFightDamage.getContentPane().add(lblFightDamageEnemyAction);
		
		JLabel lblFightDamageEnergyDamage = new JLabel(gameEnviro.getBattle().getCurrEnemy().getMonsterName()
				+ " did " + enemyDamageDone + " Damage.");
		lblFightDamageEnergyDamage.setFont(new Font("Verdana", Font.BOLD, 13));
		lblFightDamageEnergyDamage.setBounds(10, 110, 414, 24);
		frmViewFightDamage.getContentPane().add(lblFightDamageEnergyDamage);
		
		JButton btnFightDamage = new JButton("Continue");
		btnFightDamage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameEnviro.getBattle().getCurrEnemy().getCurrHealth() <= 0) {
					
					gameEnviro.addScoreForMonsterKill();
					BattleResultScreen newBattleResult = new BattleResultScreen
							(gameEnviro, gameEnviro.getBattle().getCurrUser(), gameEnviro.getBattle().getCurrEnemy(), "won");
					gameEnviro.getBattle().getCurrUser().addDailyBattlesWon(1);
					frmViewFightDamage.dispose();
					newBattleResult.BattleResult();
					
				} else if (gameEnviro.getBattle().getCurrUser().getCurrHealth() <= 0) {
					
					BattleResultScreen newBattleResult = new BattleResultScreen
							(gameEnviro, gameEnviro.getBattle().getCurrEnemy(), gameEnviro.getBattle().getCurrUser(), "lost");
					frmViewFightDamage.dispose();
					newBattleResult.BattleResult();
					
				} else {
					
					ViewBattleFightScreen newBattleFight = new ViewBattleFightScreen(gameEnviro);
					frmViewFightDamage.dispose();
					newBattleFight.ViewBattleFight();
				}
			}
		});
		btnFightDamage.setFont(new Font("Verdana", Font.BOLD, 15));
		btnFightDamage.setBounds(134, 174, 170, 51);
		frmViewFightDamage.getContentPane().add(btnFightDamage);
	}

}
