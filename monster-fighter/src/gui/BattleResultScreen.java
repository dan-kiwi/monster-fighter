package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import mainenviro.GameEnviro;
import monster.Monster;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Random;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The Class BattleResultScreen. Shows BattleResults.
 */
public class BattleResultScreen {
	
	/** The Constant baseScoreForKill. */
	//Constants
	private static final int baseScoreForKill = 100;

	private JFrame frmBattleResult;
	private static GameEnviro gameEnviro;
	private Monster winningMonster;
	private static Monster losingMonster;
	private static String userResult;
	private int userRewardGold;
	private int userRewardScore;
	private Random rand = new Random();

	/**
	 * Launch the application.
	 * @param args, args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleResultScreen window = new BattleResultScreen(null, losingMonster, losingMonster, userResult);
					window.frmBattleResult.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param newGame, the game environment
	 * @param tempWinner, monster who won
	 * @param tempLoser, monster who lost
	 * @param tempResult, String of either 'won' or 'lost'
	 */
	public BattleResultScreen(GameEnviro newGame, Monster tempWinner, Monster tempLoser, String tempResult) {
		gameEnviro = newGame;
		winningMonster = tempWinner;
		losingMonster = tempLoser;
		userResult = tempResult;
		if (userResult.equals("won")) {
			userRewardGold = (int) ((gameEnviro.getBattle().getCurrEnemy().getMonsterBuyPrice() * rand.nextDouble(0.8, 1.2)) * gameEnviro.getGoldDifficulty());
			userRewardScore = (int) (baseScoreForKill * gameEnviro.getMonsterDifficulty());
			gameEnviro.getBattle().getCurrUser().addDailyBattlesWon(1);
		} else {
			userRewardGold = 0;
			userRewardScore = 0;
		}
		initialize();
	}
	
	/**
	 * Method to call back the Battle Result class from other class
	 */
	public void BattleResult() {
		BattleResultScreen currentBattleResult = new BattleResultScreen(gameEnviro, winningMonster, losingMonster, userResult);
		currentBattleResult.frmBattleResult.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBattleResult = new JFrame();
		frmBattleResult.setTitle("Battle Result");
		frmBattleResult.setBounds(100, 100, 560, 575);
		frmBattleResult.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattleResult.getContentPane().setLayout(null);
		
		JLabel lblBattleResultTitle = new JLabel("The Battle is Over");
		lblBattleResultTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleResultTitle.setFont(new Font("Verdana", Font.BOLD, 15));
		lblBattleResultTitle.setBounds(50, 30, 450, 30);
		frmBattleResult.getContentPane().add(lblBattleResultTitle);
		
		JLabel lblBattleResultWinnerLoser = new JLabel(winningMonster.getMonsterName() 
				+ " has Defeated " + losingMonster.getMonsterName());
		lblBattleResultWinnerLoser.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleResultWinnerLoser.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBattleResultWinnerLoser.setBounds(30, 140, 414, 29);
		frmBattleResult.getContentPane().add(lblBattleResultWinnerLoser);
		
		JLabel lblBattleResultRewardTitle = new JLabel("You Receive:");
		lblBattleResultRewardTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleResultRewardTitle.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBattleResultRewardTitle.setBounds(30, 220, 104, 29);
		frmBattleResult.getContentPane().add(lblBattleResultRewardTitle);
		
		JLabel lblBattleResultResultGold = new JLabel(userRewardGold + " Gold");
		lblBattleResultResultGold.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleResultResultGold.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBattleResultResultGold.setBounds(51, 250, 187, 29);
		frmBattleResult.getContentPane().add(lblBattleResultResultGold);
		
		JLabel lblBattleResultResultScore = new JLabel(userRewardScore + " Score");
		lblBattleResultResultScore.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleResultResultScore.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBattleResultResultScore.setBounds(51, 280, 187, 29);
		frmBattleResult.getContentPane().add(lblBattleResultResultScore);
		
		JButton btnBattleResultContinue = new JButton("Continue");
		btnBattleResultContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameEnviro.changeUserGoldAmount(userRewardGold);
				@SuppressWarnings("unused")
				ViewBattleMenuScreen newViewBattle = new ViewBattleMenuScreen(gameEnviro);
				frmBattleResult.dispose();
			}
		});
		btnBattleResultContinue.setFont(new Font("Verdana", Font.BOLD, 15));
		btnBattleResultContinue.setBounds(195, 420, 160, 53);
		frmBattleResult.getContentPane().add(btnBattleResultContinue);
		
		if (userResult.equals("won")) {
			JLabel lblWonLabel = new JLabel("You Won!");
			lblWonLabel.setFont(new Font("Dialog", Font.BOLD, 16));
			lblWonLabel.setBounds(30, 110, 300, 30);
			frmBattleResult.getContentPane().add(lblWonLabel);
		} else {
			JLabel lblLoseLabel = new JLabel("You Lose!");
			lblLoseLabel.setFont(new Font("Dialog", Font.BOLD, 16));
			lblLoseLabel.setBounds(30, 110, 300, 30);
			frmBattleResult.getContentPane().add(lblLoseLabel);
		}
		
		
	}

}
