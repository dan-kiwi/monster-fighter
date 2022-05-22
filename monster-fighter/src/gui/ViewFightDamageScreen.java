package gui;

import javax.swing.JFrame;

import mainenviro.GameEnviro;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

/**
 * The Class ViewFightDamageScreen.
 */
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
		frmViewFightDamage.setBounds(100, 100, 560, 575);
		frmViewFightDamage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewFightDamage.getContentPane().setLayout(null);
		
		JLabel lblFightDamageUserAction = new JLabel(gameEnviro.getBattle().getCurrUser().getMonsterName()
				+ " used " + userChoice + ".");
		lblFightDamageUserAction.setHorizontalAlignment(SwingConstants.CENTER);
		lblFightDamageUserAction.setFont(new Font("Verdana", Font.BOLD, 13));
		lblFightDamageUserAction.setBounds(50, 130, 450, 24);
		frmViewFightDamage.getContentPane().add(lblFightDamageUserAction);
		
		JLabel lblFightDamageUserDamage = new JLabel(gameEnviro.getBattle().getCurrUser().getMonsterName()
				+ " did " + userDamageDone + " Damage.");
		lblFightDamageUserDamage.setHorizontalAlignment(SwingConstants.CENTER);
		lblFightDamageUserDamage.setFont(new Font("Verdana", Font.BOLD, 13));
		lblFightDamageUserDamage.setBounds(50, 165, 450, 24);
		frmViewFightDamage.getContentPane().add(lblFightDamageUserDamage);
		
		JLabel lblFightDamageEnemyAction = new JLabel(gameEnviro.getBattle().getCurrEnemy().getMonsterName()
				+ " used " + enemyChoice + ".");
		lblFightDamageEnemyAction.setHorizontalAlignment(SwingConstants.CENTER);
		lblFightDamageEnemyAction.setFont(new Font("Verdana", Font.BOLD, 13));
		lblFightDamageEnemyAction.setBounds(50, 285, 450, 25);
		frmViewFightDamage.getContentPane().add(lblFightDamageEnemyAction);
		
		JLabel lblFightDamageEnergyDamage = new JLabel(gameEnviro.getBattle().getCurrEnemy().getMonsterName()
				+ " did " + enemyDamageDone + " Damage.");
		lblFightDamageEnergyDamage.setHorizontalAlignment(SwingConstants.CENTER);
		lblFightDamageEnergyDamage.setFont(new Font("Verdana", Font.BOLD, 13));
		lblFightDamageEnergyDamage.setBounds(50, 320, 450, 25);
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
		btnFightDamage.setBounds(193, 400, 170, 51);
		frmViewFightDamage.getContentPane().add(btnFightDamage);
		
		JLabel lblYourEnemy = new JLabel("Your Enemy:");
		lblYourEnemy.setFont(new Font("Dialog", Font.BOLD, 16));
		lblYourEnemy.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourEnemy.setBounds(50, 243, 450, 30);
		frmViewFightDamage.getContentPane().add(lblYourEnemy);
		
		JLabel lblYourMonster = new JLabel("Your Monster:");
		lblYourMonster.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourMonster.setFont(new Font("Dialog", Font.BOLD, 16));
		lblYourMonster.setBounds(50, 90, 450, 30);
		frmViewFightDamage.getContentPane().add(lblYourMonster);
		
		JLabel lblFightOutcome = new JLabel("Fight Damage");
		lblFightOutcome.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFightOutcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblFightOutcome.setBounds(50, 30, 450, 30);
		frmViewFightDamage.getContentPane().add(lblFightOutcome);
	}

}
