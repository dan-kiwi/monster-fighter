package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import mainenviro.GameEnviro;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The Class GameEndScreen. Shows at the end of the game
 */
public class GameEndScreen {

	private JFrame frmGameEnd;
	private static GameEnviro gameEnviro;
	private static boolean gameDaysRunOut;

	/**
	 * Create the application.
	 * @param newGame, game Environment
	 * @param tempDaysRunOut, boolean showing if days have run out or not
	 */
	public GameEndScreen(GameEnviro newGame, boolean tempDaysRunOut) {
		gameEnviro = newGame;
		gameDaysRunOut = tempDaysRunOut;
		initialize();
	}
	
	/**
	 * Method to call back the View Fight Damage class from the Main Menu object
	 */
	public void GameEnd() {
		GameEndScreen gameEnd = new GameEndScreen(gameEnviro, gameDaysRunOut);
		gameEnd.frmGameEnd.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGameEnd = new JFrame();
		frmGameEnd.setTitle("Game Over");
		frmGameEnd.setBounds(100, 100, 560, 575);
		frmGameEnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGameEnd.getContentPane().setLayout(null);
		
		JLabel lblGameEndTitle = new JLabel("Game Over");
		lblGameEndTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblGameEndTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameEndTitle.setBounds(50, 30, 450, 34);
		frmGameEnd.getContentPane().add(lblGameEndTitle);
		
		if (gameDaysRunOut) {
			
			JLabel lblGameEndEnding = new JLabel("Well done " + gameEnviro.getUserGameName());
			lblGameEndEnding.setHorizontalAlignment(SwingConstants.LEFT);
			lblGameEndEnding.setFont(new Font("Verdana", Font.BOLD, 14));
			lblGameEndEnding.setBounds(10, 56, 414, 28);
			frmGameEnd.getContentPane().add(lblGameEndEnding);
			
			JLabel lblGameEndEndingReason = new JLabel("You have completed the Game.");
			lblGameEndEndingReason.setHorizontalAlignment(SwingConstants.LEFT);
			lblGameEndEndingReason.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblGameEndEndingReason.setBounds(10, 84, 414, 28);
			frmGameEnd.getContentPane().add(lblGameEndEndingReason);
			
		} else {
			
			JLabel lblGameEndEnding = new JLabel("Unfortunately " + gameEnviro.getUserGameName());
			lblGameEndEnding.setHorizontalAlignment(SwingConstants.LEFT);
			lblGameEndEnding.setFont(new Font("Verdana", Font.BOLD, 14));
			lblGameEndEnding.setBounds(10, 56, 414, 28);
			frmGameEnd.getContentPane().add(lblGameEndEnding);
			
			JLabel lblGameEndEndingReason = new JLabel("You have run out of Monsters and can't buy anymore.");
			lblGameEndEndingReason.setHorizontalAlignment(SwingConstants.LEFT);
			lblGameEndEndingReason.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblGameEndEndingReason.setBounds(10, 84, 414, 28);
			frmGameEnd.getContentPane().add(lblGameEndEndingReason);
		}
		
		
		
		JLabel lblGameEndGold = new JLabel("Your Final Gold Amount is: " + 
		gameEnviro.getUserGoldAmount() + " Gold.");
		lblGameEndGold.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameEndGold.setFont(new Font("Verdana", Font.BOLD, 14));
		lblGameEndGold.setBounds(50, 123, 450, 28);
		frmGameEnd.getContentPane().add(lblGameEndGold);
		
		JLabel lblGameEndKills = new JLabel("Your Final Enemy Kill count is: " + 
		gameEnviro.getUserMonsterKills());
		lblGameEndKills.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameEndKills.setFont(new Font("Verdana", Font.BOLD, 14));
		lblGameEndKills.setBounds(50, 162, 414, 28);
		frmGameEnd.getContentPane().add(lblGameEndKills);
		
		JLabel lblGameEndScore = new JLabel("This gives you a Final Score of: " + 
		(gameEnviro.getUserGameScore() + gameEnviro.getUserGoldAmount()));
		lblGameEndScore.setHorizontalAlignment(SwingConstants.LEFT);
		lblGameEndScore.setFont(new Font("Verdana", Font.BOLD, 14));
		lblGameEndScore.setBounds(50, 201, 414, 28);
		frmGameEnd.getContentPane().add(lblGameEndScore);
		
		JButton btnGameEnd = new JButton("Thank You For Playing");
		btnGameEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGameEnd.dispose();
			}
		});
		btnGameEnd.setFont(new Font("Verdana", Font.BOLD, 15));
		btnGameEnd.setBounds(150, 420, 250, 47);
		frmGameEnd.getContentPane().add(btnGameEnd);
	}

}
