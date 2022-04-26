package mainEnviro;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Panel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;

public class SetupScreen {

	private JFrame frmMonsterFighter;
	private JTextField textSetupName;
	private JTextField textSetupMonsterName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupScreen window = new SetupScreen();
					window.frmMonsterFighter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SetupScreen() {
		initialize();
	}
	
	public void StartGame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupScreen window = new SetupScreen();
					window.frmMonsterFighter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMonsterFighter = new JFrame();
		frmMonsterFighter.setTitle("Monster Fighter");
		frmMonsterFighter.setBounds(100, 100, 500, 650);
		frmMonsterFighter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMonsterFighter.getContentPane().setLayout(null);
		
		JLabel lblSetupWelcome = new JLabel("Welcome to Monster Fighter");
		lblSetupWelcome.setFont(new Font("Verdana", Font.BOLD, 20));
		lblSetupWelcome.setBounds(76, 11, 342, 32);
		frmMonsterFighter.getContentPane().add(lblSetupWelcome);
		
		JLabel lblSetupName = new JLabel("What is your name?");
		lblSetupName.setFont(new Font("Verdana", Font.BOLD, 15));
		lblSetupName.setBounds(10, 78, 173, 20);
		frmMonsterFighter.getContentPane().add(lblSetupName);
		
		textSetupName = new JTextField();
		textSetupName.setFont(new Font("Verdana", Font.PLAIN, 15));
		textSetupName.setBounds(193, 71, 261, 35);
		frmMonsterFighter.getContentPane().add(textSetupName);
		textSetupName.setColumns(10);
		
		JLabel lblSetupDays = new JLabel("How many Days do you want to play?");
		lblSetupDays.setFont(new Font("Verdana", Font.BOLD, 15));
		lblSetupDays.setBounds(80, 133, 319, 20);
		frmMonsterFighter.getContentPane().add(lblSetupDays);
		
		JSlider sliderSetupDays = new JSlider();
		sliderSetupDays.setMajorTickSpacing(1);
		sliderSetupDays.setSnapToTicks(true);
		sliderSetupDays.setPaintTicks(true);
		sliderSetupDays.setPaintLabels(true);
		sliderSetupDays.setMaximum(15);
		sliderSetupDays.setMinimum(5);
		sliderSetupDays.setBounds(43, 164, 389, 45);
		frmMonsterFighter.getContentPane().add(sliderSetupDays);
		
		JLabel lblSetupMonster = new JLabel("Choose your starting monster");
		lblSetupMonster.setFont(new Font("Verdana", Font.BOLD, 15));
		lblSetupMonster.setBounds(10, 227, 252, 20);
		frmMonsterFighter.getContentPane().add(lblSetupMonster);
		
		JLabel lblSetupImp = new JLabel("Imp - Health: 50, Attack: 30, Defence: 20");
		lblSetupImp.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblSetupImp.setBounds(76, 258, 378, 20);
		frmMonsterFighter.getContentPane().add(lblSetupImp);
		
		JLabel lblSetupGnome = new JLabel("Gnome - Health: 75, Attack: 10, Defence: 30");
		lblSetupGnome.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblSetupGnome.setBounds(76, 289, 389, 20);
		frmMonsterFighter.getContentPane().add(lblSetupGnome);
		
		JLabel lblSetupGoblin = new JLabel("Goblin - Health: 100, Attack: 20, Defence: 10");
		lblSetupGoblin.setFont(new Font("Verdana", Font.PLAIN, 15));
		lblSetupGoblin.setBounds(76, 320, 378, 20);
		frmMonsterFighter.getContentPane().add(lblSetupGoblin);
		
		JRadioButton rdbtnSetupImp = new JRadioButton("");
		rdbtnSetupImp.setBounds(31, 258, 27, 23);
		frmMonsterFighter.getContentPane().add(rdbtnSetupImp);
		
		JRadioButton rdbtnSetupGnome = new JRadioButton("");
		rdbtnSetupGnome.setBounds(31, 289, 27, 23);
		frmMonsterFighter.getContentPane().add(rdbtnSetupGnome);
		
		JRadioButton rdbtnSetupGoblin = new JRadioButton("");
		rdbtnSetupGoblin.setBounds(31, 320, 27, 20);
		frmMonsterFighter.getContentPane().add(rdbtnSetupGoblin);
		
		//Group the monster selection buttons
		//this makes it so only one can be selected
	    ButtonGroup setupMonsterGroup = new ButtonGroup();
	    setupMonsterGroup.add(rdbtnSetupImp);
	    setupMonsterGroup.add(rdbtnSetupGnome);
	    setupMonsterGroup.add(rdbtnSetupGoblin);
	    
	    JLabel lblSetupMonsterName = new JLabel("What is your new monsters name?");
	    lblSetupMonsterName.setFont(new Font("Verdana", Font.BOLD, 15));
	    lblSetupMonsterName.setBounds(42, 369, 291, 20);
	    frmMonsterFighter.getContentPane().add(lblSetupMonsterName);
	    
	    textSetupMonsterName = new JTextField();
	    textSetupMonsterName.setFont(new Font("Verdana", Font.PLAIN, 15));
	    textSetupMonsterName.setColumns(10);
	    textSetupMonsterName.setBounds(43, 400, 389, 35);
	    frmMonsterFighter.getContentPane().add(textSetupMonsterName);
	    
	    JLabel lblSetupDifficulty = new JLabel("What difficulty do you want to play?");
	    lblSetupDifficulty.setFont(new Font("Verdana", Font.BOLD, 15));
	    lblSetupDifficulty.setBounds(84, 466, 315, 20);
	    frmMonsterFighter.getContentPane().add(lblSetupDifficulty);
	    
	    JRadioButton rdbtnSetupDifficultyEasy = new JRadioButton("Easy");
	    rdbtnSetupDifficultyEasy.setToolTipText("Enemies do less Damage - You get more Gold - You get less Score");
	    rdbtnSetupDifficultyEasy.setFont(new Font("Verdana", Font.BOLD, 15));
	    rdbtnSetupDifficultyEasy.setBounds(118, 504, 65, 23);
	    frmMonsterFighter.getContentPane().add(rdbtnSetupDifficultyEasy);
	    
	    JRadioButton rdbtnSetupDifficultyHard = new JRadioButton("Hard");
	    rdbtnSetupDifficultyHard.setToolTipText("Enemies do more Damage - You get less Gold - You get more Score");
	    rdbtnSetupDifficultyHard.setFont(new Font("Verdana", Font.BOLD, 15));
	    rdbtnSetupDifficultyHard.setBounds(288, 504, 65, 23);
	    frmMonsterFighter.getContentPane().add(rdbtnSetupDifficultyHard);
	    
	    //Group the Difficulty selection buttons
	    //this makes it so only one can be selected
	    ButtonGroup setupDifficultyGroup = new ButtonGroup();
	    setupDifficultyGroup.add(rdbtnSetupDifficultyEasy);
	    setupDifficultyGroup.add(rdbtnSetupDifficultyHard);
	    
	    JButton btnSetupConfirm = new JButton("Confirm");
	    btnSetupConfirm.setFont(new Font("Verdana", Font.BOLD, 15));
	    btnSetupConfirm.setBounds(158, 555, 152, 45);
	    frmMonsterFighter.getContentPane().add(btnSetupConfirm);
	    
	}
}
