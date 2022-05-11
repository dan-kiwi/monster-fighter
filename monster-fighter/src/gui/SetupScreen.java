package gui;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import monster.Gnome;
import monster.Goblin;
import monster.Imp;
import monster.Monster;

import javax.swing.JPanel;
import java.awt.Panel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class SetupScreen {

	private JFrame frmSetup;
	private JTextField textSetupName;
	private JSlider sliderSetupDays;
	private ButtonGroup setupMonsterGroup;
	private JTextField textSetupMonsterName;
	private ButtonGroup setupDifficultyGroup;
	private static GameEnviro gameEnviro;
	private JLabel lblSetupInputIncorrect;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameEnviro newTestGame = new GameEnviro();
					SetupScreen window = new SetupScreen(newTestGame);
					window.frmSetup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param newCmdGame 
	 */
	public SetupScreen(GameEnviro newCmdGame) {
		gameEnviro = newCmdGame;
		initialize();
	}
	
	public void StartGame() {
		SetupScreen window = new SetupScreen(gameEnviro);
		window.frmSetup.setVisible(true);
		
	}
	
	public boolean checkInputName() {
		//input name must be between 3 and 15 characters and must only be letters
		//return true if it is, false if not
		String selection = textSetupName.getText();
		if ((selection.length() >= 3) && (selection.length() <= 15)) {
			if (selection.matches("[A-Za-z]*")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean checkMonsterName() {
		//input name must be between 1 and 15 characters and must be letters or numbers
		//also accepts no input
		//return true if correct length and characters, return true if empty, false if neither
		String selection = textSetupMonsterName.getText();
		if((selection.length() > 0) && (selection.length() <= 15)) {
			if (selection.matches("[A-Za-z0-9]*")) {
				return true;
			} else {
				return false;
			}
		} else if (selection.matches("")){
			return true;
		} else {
			return false;
		}
	}
	
	public void setGameDetails() {
		//Populate the game object with the users selected details
		
		gameEnviro.setMaxGameDays(sliderSetupDays.getValue());
		gameEnviro.setUserGameName(textSetupName.getText());
		
		String chosenMonster = setupMonsterGroup.getSelection().getActionCommand();
		String chosenMonsterName = textSetupMonsterName.getText();
		if (chosenMonster.equals("Imp")){
			Monster tempImp = new Imp();
			if (!chosenMonsterName.equals("")) {
				tempImp.setMonsterName(chosenMonsterName);
			}
			gameEnviro.addMonster(tempImp);
		} else if (chosenMonster.equals("Gnome")) {
			Monster tempGnome = new Gnome();
			if (!chosenMonsterName.equals("")) {
				tempGnome.setMonsterName(chosenMonsterName);
			}
			gameEnviro.addMonster(tempGnome);
		} else if (chosenMonster.equals("Goblin")) {
			Monster tempGoblin = new Goblin();
			if (!chosenMonsterName.equals("")) {
				tempGoblin.setMonsterName(chosenMonsterName);
			}
			gameEnviro.addMonster(tempGoblin);
		}
		
		String chosenDifficulty = setupDifficultyGroup.getSelection().getActionCommand();
		//System.out.println(chosenDifficulty);
		//System.out.println(gameEnviro.getUserMonsterList());
		//System.out.println(gameEnviro.getMaxGameDays());
		gameEnviro.starterSetDifficulty(chosenDifficulty);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSetup = new JFrame();
		frmSetup.setTitle("Monster Fighter");
		frmSetup.setBounds(100, 100, 500, 650);
		frmSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSetup.getContentPane().setLayout(null);
		
		JLabel lblSetupWelcome = new JLabel("Welcome to Monster Fighter");
		lblSetupWelcome.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSetupWelcome.setBounds(76, 11, 342, 32);
		frmSetup.getContentPane().add(lblSetupWelcome);
		
		JLabel lblSetupName = new JLabel("What is your name?");
		lblSetupName.setFont(new Font("Verdana", Font.BOLD, 15));
		lblSetupName.setBounds(10, 78, 173, 20);
		frmSetup.getContentPane().add(lblSetupName);
		
		textSetupName = new JTextField();
		textSetupName.setFont(new Font("Verdana", Font.PLAIN, 15));
		textSetupName.setBounds(193, 71, 261, 35);
		frmSetup.getContentPane().add(textSetupName);
		textSetupName.setColumns(10);
		
		JLabel lblSetupDays = new JLabel("How many Days do you want to play?");
		lblSetupDays.setFont(new Font("Verdana", Font.BOLD, 15));
		lblSetupDays.setBounds(80, 133, 319, 20);
		frmSetup.getContentPane().add(lblSetupDays);
		
		sliderSetupDays = new JSlider();
		sliderSetupDays.setMajorTickSpacing(1);
		sliderSetupDays.setSnapToTicks(true);
		sliderSetupDays.setPaintTicks(true);
		sliderSetupDays.setPaintLabels(true);
		sliderSetupDays.setMaximum(15);
		sliderSetupDays.setMinimum(5);
		sliderSetupDays.setBounds(43, 164, 389, 45);
		frmSetup.getContentPane().add(sliderSetupDays);
		
		JLabel lblSetupMonster = new JLabel("Choose your starting monster");
		lblSetupMonster.setFont(new Font("Verdana", Font.BOLD, 15));
		lblSetupMonster.setBounds(10, 227, 252, 20);
		frmSetup.getContentPane().add(lblSetupMonster);
		
		JLabel lblSetupImp = new JLabel(" - Health: 50, Attack: 30, Defence: 20");
		lblSetupImp.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblSetupImp.setBounds(117, 258, 315, 20);
		frmSetup.getContentPane().add(lblSetupImp);
		
		JLabel lblSetupGnome = new JLabel(" - Health: 75, Attack: 10, Defence: 30");
		lblSetupGnome.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblSetupGnome.setBounds(117, 289, 315, 20);
		frmSetup.getContentPane().add(lblSetupGnome);
		
		JLabel lblSetupGoblin = new JLabel(" - Health: 100, Attack: 20, Defence: 10");
		lblSetupGoblin.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblSetupGoblin.setBounds(117, 320, 315, 20);
		frmSetup.getContentPane().add(lblSetupGoblin);
		
		JRadioButton rdbtnSetupImp = new JRadioButton("Imp");
		rdbtnSetupImp.setSelected(true);
		rdbtnSetupImp.setFont(new Font("Verdana", Font.PLAIN, 15));
		rdbtnSetupImp.setBounds(31, 258, 57, 23);
		rdbtnSetupImp.setActionCommand("Imp");
		frmSetup.getContentPane().add(rdbtnSetupImp);
		
		JRadioButton rdbtnSetupGnome = new JRadioButton("Gnome");
		rdbtnSetupGnome.setFont(new Font("Verdana", Font.PLAIN, 15));
		rdbtnSetupGnome.setBounds(31, 289, 81, 23);
		rdbtnSetupGnome.setActionCommand("Gnome");
		frmSetup.getContentPane().add(rdbtnSetupGnome);
		
		JRadioButton rdbtnSetupGoblin = new JRadioButton("Goblin");
		rdbtnSetupGoblin.setFont(new Font("Verdana", Font.PLAIN, 15));
		rdbtnSetupGoblin.setBounds(31, 320, 80, 20);
		rdbtnSetupGoblin.setActionCommand("Goblin");
		frmSetup.getContentPane().add(rdbtnSetupGoblin);
		
		//Group the monster selection buttons
		//this makes it so only one can be selected
	    setupMonsterGroup = new ButtonGroup();
	    setupMonsterGroup.add(rdbtnSetupImp);
	    setupMonsterGroup.add(rdbtnSetupGnome);
	    setupMonsterGroup.add(rdbtnSetupGoblin);
	    
	    JLabel lblSetupMonsterName = new JLabel("What is your new monsters name?");
	    lblSetupMonsterName.setFont(new Font("Verdana", Font.BOLD, 15));
	    lblSetupMonsterName.setBounds(43, 361, 291, 20);
	    frmSetup.getContentPane().add(lblSetupMonsterName);
	    
	    textSetupMonsterName = new JTextField();
	    textSetupMonsterName.setFont(new Font("Verdana", Font.PLAIN, 15));
	    textSetupMonsterName.setColumns(10);
	    textSetupMonsterName.setBounds(43, 400, 389, 35);
	    frmSetup.getContentPane().add(textSetupMonsterName);
	    
	    JLabel lblSetupDifficulty = new JLabel("What difficulty do you want to play?");
	    lblSetupDifficulty.setFont(new Font("Verdana", Font.BOLD, 15));
	    lblSetupDifficulty.setBounds(84, 466, 315, 20);
	    frmSetup.getContentPane().add(lblSetupDifficulty);
	    
	    JRadioButton rdbtnSetupDifficultyEasy = new JRadioButton("Easy");
	    rdbtnSetupDifficultyEasy.setSelected(true);
	    rdbtnSetupDifficultyEasy.setToolTipText("Enemies do less Damage - You get more Gold - You get less Score");
	    rdbtnSetupDifficultyEasy.setFont(new Font("Verdana", Font.BOLD, 15));
	    rdbtnSetupDifficultyEasy.setBounds(118, 504, 65, 23);
	    rdbtnSetupDifficultyEasy.setActionCommand("Easy");
	    frmSetup.getContentPane().add(rdbtnSetupDifficultyEasy);
	    
	    JRadioButton rdbtnSetupDifficultyHard = new JRadioButton("Hard");
	    rdbtnSetupDifficultyHard.setToolTipText("Enemies do more Damage - You get less Gold - You get more Score");
	    rdbtnSetupDifficultyHard.setFont(new Font("Verdana", Font.BOLD, 15));
	    rdbtnSetupDifficultyHard.setBounds(288, 504, 65, 23);
	    rdbtnSetupDifficultyHard.setActionCommand("Hard");
	    frmSetup.getContentPane().add(rdbtnSetupDifficultyHard);
	    
	    //Group the Difficulty selection buttons
	    //this makes it so only one can be selected
	    setupDifficultyGroup = new ButtonGroup();
	    setupDifficultyGroup.add(rdbtnSetupDifficultyEasy);
	    setupDifficultyGroup.add(rdbtnSetupDifficultyHard);
	    
	    JButton btnSetupConfirm = new JButton("Confirm");
	    btnSetupConfirm.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		if (checkInputName() && checkMonsterName()) {
	    			//if both name inputs are correct hide the setup frame then create and launch main menu screen
	    			frmSetup.setVisible(false);
	    			setGameDetails();
	    			MainMenuScreen newMainMenu = new MainMenuScreen(gameEnviro);
	    			newMainMenu.MainMenu();
	    		} else {
	    			//Makes an error message visible which says Incorrect Input
	    			lblSetupInputIncorrect.setVisible(true);
	    		}
	    	}

	    });
	    btnSetupConfirm.setFont(new Font("Verdana", Font.BOLD, 15));
	    btnSetupConfirm.setBounds(159, 557, 152, 45);
	    frmSetup.getContentPane().add(btnSetupConfirm);
	    
	    JLabel lblSetupNameParameters = new JLabel("(Name must be between 3 and 15 letters)");
	    lblSetupNameParameters.setBounds(193, 108, 261, 14);
	    frmSetup.getContentPane().add(lblSetupNameParameters);
	    
	    JLabel lblSetupMonsterNameParameters = new JLabel("(Name must be between 1 and 15 letters/numbers)");
	    lblSetupMonsterNameParameters.setBounds(43, 441, 389, 14);
	    frmSetup.getContentPane().add(lblSetupMonsterNameParameters);
	    
	    JLabel lblSetupMonsterNameDefault = new JLabel("Leave empty for default name");
	    lblSetupMonsterNameDefault.setFont(new Font("Tahoma", Font.PLAIN, 9));
	    lblSetupMonsterNameDefault.setBounds(43, 384, 389, 14);
	    frmSetup.getContentPane().add(lblSetupMonsterNameDefault);
	    
	    lblSetupInputIncorrect = new JLabel("Incorrect Input");
	    lblSetupInputIncorrect.setForeground(Color.RED);
	    lblSetupInputIncorrect.setFont(new Font("Verdana", Font.PLAIN, 12));
	    lblSetupInputIncorrect.setBounds(342, 570, 100, 20);
	    frmSetup.getContentPane().add(lblSetupInputIncorrect);
	    lblSetupInputIncorrect.setVisible(false);
	    
	}
}
