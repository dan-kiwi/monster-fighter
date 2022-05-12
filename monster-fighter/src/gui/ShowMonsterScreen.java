package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import mainenviro.GameEnviro;
import monster.Gnome;
import monster.Goblin;
import monster.Imp;
import monster.Monster;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ShowMonsterScreen {

	private JFrame frmShowMonster;
	private static GameEnviro gameEnviro;
	private JTextField txtShowMonstersRename;
	private JLabel lblShowMonstersRenameInvalid;
	private ButtonGroup renameMonsterGroup;

	/**
	 * Launch the application.
	 * will not launch, has to be accessed through main.java or setupscreen.java
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowMonsterScreen window = new ShowMonsterScreen(null);
					window.frmShowMonster.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShowMonsterScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		initialize();
	}
	
	public void ShowMonster() {
		ShowMonsterScreen showMonster = new ShowMonsterScreen(gameEnviro);
		showMonster.frmShowMonster.setVisible(true);
	}
	
	public boolean checkRenameName() {
		//input name must be between 3 and 15 characters and must only be letters
		//return true if it is, false if not
		String selection = txtShowMonstersRename.getText();
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
	
	public void renameMonster() {
		
		String chosenMonster = renameMonsterGroup.getSelection().getActionCommand();
		String chosenMonsterName = txtShowMonstersRename.getText();
		
		if (chosenMonster.equals("1")){
			gameEnviro.getUserMonsterList().get(0).setMonsterName(chosenMonsterName);
		} else if (chosenMonster.equals("2")) {
			gameEnviro.getUserMonsterList().get(1).setMonsterName(chosenMonsterName);
		} else if (chosenMonster.equals("3")) {
			gameEnviro.getUserMonsterList().get(2).setMonsterName(chosenMonsterName);
		} else if (chosenMonster.equals("4")) {
			gameEnviro.getUserMonsterList().get(3).setMonsterName(chosenMonsterName);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShowMonster = new JFrame();
		frmShowMonster.setTitle("View Your Monsters");
		frmShowMonster.setBounds(100, 100, 500, 575);
		frmShowMonster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShowMonster.getContentPane().setLayout(null);
		
		JLabel lblShowMonsterInfo = new JLabel("Here are Your Monsters");
		lblShowMonsterInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowMonsterInfo.setToolTipText("");
		lblShowMonsterInfo.setFont(new Font("Verdana", Font.BOLD, 15));
		lblShowMonsterInfo.setBounds(104, 11, 237, 39);
		frmShowMonster.getContentPane().add(lblShowMonsterInfo);
		
		//Group the monster rename radio buttons
		//this makes it so only one can be selected
		renameMonsterGroup = new ButtonGroup();
		
		//If there is no monsters in the users monster list show greyed out radio button
		if (gameEnviro.getUserMonsterList().size() < 1)  {
			
			JRadioButton rdbtnShowMonster1 = new JRadioButton("No Monster Here");
			rdbtnShowMonster1.setEnabled(false);
			rdbtnShowMonster1.setFont(new Font("Verdana", Font.BOLD, 15));
			rdbtnShowMonster1.setBounds(23, 66, 209, 39);
			frmShowMonster.getContentPane().add(rdbtnShowMonster1);
			
		//if the is 1 or more monsters in the monsterlist show all of the first monsters stats
		//also show the rename box and text field
		} else {
			
			Monster chosenMonster = gameEnviro.getUserMonsterList().get(0);
			
			JRadioButton rdbtnShowMonster1 = new JRadioButton(chosenMonster.getMonsterName());
			rdbtnShowMonster1.setSelected(true);
			rdbtnShowMonster1.setFont(new Font("Verdana", Font.BOLD, 15));
			rdbtnShowMonster1.setBounds(23, 66, 209, 39);
			frmShowMonster.getContentPane().add(rdbtnShowMonster1);
			rdbtnShowMonster1.setActionCommand("1");
			
			renameMonsterGroup.add(rdbtnShowMonster1); //add radio button to the button group
			
			JLabel lblShowMonster1Health = new JLabel("Health: " + chosenMonster.getCurrHealth() 
			+ "/" + chosenMonster.getMaxHealth());
			lblShowMonster1Health.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster1Health.setBounds(47, 101, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster1Health);
			
			JLabel lblShowMonster1Attack = new JLabel("Attack: " + chosenMonster.getCurrAttack() 
			+ "/" + chosenMonster.getBaseAttack());
			lblShowMonster1Attack.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster1Attack.setBounds(47, 126, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster1Attack);
			
			JLabel lblShowMonster1Defence = new JLabel("Defence: " + chosenMonster.getCurrDefence() 
			+ "/" + chosenMonster.getBaseDefence());
			lblShowMonster1Defence.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster1Defence.setBounds(47, 151, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster1Defence);
			
			JLabel lblShowMonster1Energy = new JLabel("Energy: " + chosenMonster.getEnergy() 
			+ "/" + chosenMonster.getBaseEnergy());
			lblShowMonster1Energy.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster1Energy.setBounds(47, 176, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster1Energy);
		
			JButton btnShowMonstersRename = new JButton("Rename Selected");
			btnShowMonstersRename.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkRenameName()) {
						renameMonster();
						ShowMonsterScreen newShowMonster = new ShowMonsterScreen(gameEnviro);
						frmShowMonster.dispose();
		    			newShowMonster.ShowMonster();
					} else {
						lblShowMonstersRenameInvalid.setVisible(true);
					}
				}
			});
			btnShowMonstersRename.setFont(new Font("Verdana", Font.BOLD, 13));
			btnShowMonstersRename.setBounds(47, 415, 162, 39);
			frmShowMonster.getContentPane().add(btnShowMonstersRename);
			
			txtShowMonstersRename = new JTextField();
			txtShowMonstersRename.setBounds(220, 415, 168, 20);
			frmShowMonster.getContentPane().add(txtShowMonstersRename);
			txtShowMonstersRename.setColumns(10);
			
			JLabel lblShowMonstersRenameParameter = new JLabel("(Up to 15 letters/numbers)");
			lblShowMonstersRenameParameter.setHorizontalAlignment(SwingConstants.CENTER);
			lblShowMonstersRenameParameter.setFont(new Font("Verdana", Font.PLAIN, 11));
			lblShowMonstersRenameParameter.setBounds(219, 440, 169, 14);
			frmShowMonster.getContentPane().add(lblShowMonstersRenameParameter);
		}
		
		
		//If there is 1 or less monsters in the users monster list show greyed out radio button
		if (gameEnviro.getUserMonsterList().size() < 2)  {
			
			JRadioButton rdbtnShowMonster2 = new JRadioButton("No Monster Here");
			rdbtnShowMonster2.setEnabled(false);
			rdbtnShowMonster2.setFont(new Font("Verdana", Font.BOLD, 15));
			rdbtnShowMonster2.setBounds(234, 66, 209, 39);
			frmShowMonster.getContentPane().add(rdbtnShowMonster2);
			
		//if the is 2 or more monsters in the monsterlist show all of the second monsters stats	
		} else {
			
			Monster chosenMonster = gameEnviro.getUserMonsterList().get(1);
			
			JRadioButton rdbtnShowMonster2 = new JRadioButton(chosenMonster.getMonsterName());
			rdbtnShowMonster2.setFont(new Font("Verdana", Font.BOLD, 15));
			rdbtnShowMonster2.setBounds(234, 66, 209, 39);
			frmShowMonster.getContentPane().add(rdbtnShowMonster2);
			rdbtnShowMonster2.setActionCommand("2");
			
			renameMonsterGroup.add(rdbtnShowMonster2); //add radio button to the button group
			
			JLabel lblShowMonster2Health = new JLabel("Health: " + chosenMonster.getCurrHealth() 
			+ "/" + chosenMonster.getMaxHealth());
			lblShowMonster2Health.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster2Health.setBounds(257, 101, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster2Health);
			
			JLabel lblShowMonster2Attack = new JLabel("Attack: " + chosenMonster.getCurrAttack() 
			+ "/" + chosenMonster.getBaseAttack());
			lblShowMonster2Attack.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster2Attack.setBounds(257, 126, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster2Attack);
			
			JLabel lblShowMonster2Defence = new JLabel("Defence: " + chosenMonster.getCurrDefence() 
			+ "/" + chosenMonster.getBaseDefence());
			lblShowMonster2Defence.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster2Defence.setBounds(257, 151, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster2Defence);
			
			JLabel lblShowMonster2Energy = new JLabel("Energy: " + chosenMonster.getEnergy() 
			+ "/" + chosenMonster.getBaseEnergy());
			lblShowMonster2Energy.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster2Energy.setBounds(257, 176, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster2Energy);
		}
		
		//If there is 2 or less monsters in the users monster list show greyed out radio button
		if (gameEnviro.getUserMonsterList().size() < 3)  {
			
			JRadioButton rdbtnShowMonster3 = new JRadioButton("No Monster Here");
			rdbtnShowMonster3.setEnabled(false);
			rdbtnShowMonster3.setFont(new Font("Verdana", Font.BOLD, 15));
			rdbtnShowMonster3.setBounds(23, 235, 209, 39);
			frmShowMonster.getContentPane().add(rdbtnShowMonster3);
			
		//if the is 3 or more monsters in the monsterlist show all of the third monsters stats	
		} else {
			
			Monster chosenMonster = gameEnviro.getUserMonsterList().get(2);
			
			JRadioButton rdbtnShowMonster3 = new JRadioButton(chosenMonster.getMonsterName());
			rdbtnShowMonster3.setFont(new Font("Verdana", Font.BOLD, 15));
			rdbtnShowMonster3.setBounds(23, 235, 209, 39);
			frmShowMonster.getContentPane().add(rdbtnShowMonster3);
			rdbtnShowMonster3.setActionCommand("3");
			
			renameMonsterGroup.add(rdbtnShowMonster3); //add radio button to the button group
			
			JLabel lblShowMonster3Health = new JLabel("Health: " + chosenMonster.getCurrHealth() 
			+ "/" + chosenMonster.getMaxHealth());
			lblShowMonster3Health.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster3Health.setBounds(47, 271, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster3Health);
			
			JLabel lblShowMonster3Attack = new JLabel("Attack: " + chosenMonster.getCurrAttack() 
			+ "/" + chosenMonster.getBaseAttack());
			lblShowMonster3Attack.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster3Attack.setBounds(47, 294, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster3Attack);
			
			JLabel lblShowMonster3Defence = new JLabel("Defence: " + chosenMonster.getCurrDefence() 
			+ "/" + chosenMonster.getBaseDefence());
			lblShowMonster3Defence.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster3Defence.setBounds(47, 317, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster3Defence);
			
			JLabel lblShowMonster3Energy = new JLabel("Energy: " + chosenMonster.getEnergy() 
			+ "/" + chosenMonster.getBaseEnergy());
			lblShowMonster3Energy.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster3Energy.setBounds(47, 341, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster3Energy);	
		}
		
		//If there is 3 or less monsters in the users monster list show greyed out radio button
		if (gameEnviro.getUserMonsterList().size() < 4)  {
					
			JRadioButton rdbtnShowMonster4 = new JRadioButton("No Monster Here");
			rdbtnShowMonster4.setEnabled(false);
			rdbtnShowMonster4.setFont(new Font("Verdana", Font.BOLD, 15));
			rdbtnShowMonster4.setBounds(234, 235, 209, 39);
			frmShowMonster.getContentPane().add(rdbtnShowMonster4);
					
		//if the is 4 or more monsters in the monsterlist show all of the third monsters stats	
		} else {
					
			Monster chosenMonster = gameEnviro.getUserMonsterList().get(3);
					
			JRadioButton rdbtnShowMonster4 = new JRadioButton(chosenMonster.getMonsterName());
			rdbtnShowMonster4.setFont(new Font("Verdana", Font.BOLD, 15));
			rdbtnShowMonster4.setBounds(234, 235, 209, 39);
			frmShowMonster.getContentPane().add(rdbtnShowMonster4);
			rdbtnShowMonster4.setActionCommand("4");
			
			renameMonsterGroup.add(rdbtnShowMonster4); //add radio button to the button group
			
			JLabel lblShowMonster4Health = new JLabel("Health: " + chosenMonster.getCurrHealth() 
			+ "/" + chosenMonster.getMaxHealth());
			lblShowMonster4Health.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster4Health.setBounds(257, 271, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster4Health);
			
			JLabel lblShowMonster4Attack = new JLabel("Attack: " + chosenMonster.getCurrAttack() 
			+ "/" + chosenMonster.getBaseAttack());
			lblShowMonster4Attack.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster4Attack.setBounds(257, 294, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster4Attack);
			
			JLabel lblShowMonster4Defence = new JLabel("Defence: " + chosenMonster.getCurrDefence() 
			+ "/" + chosenMonster.getBaseDefence());
			lblShowMonster4Defence.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster4Defence.setBounds(257, 317, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster4Defence);
			
			JLabel lblShowMonster4Energy = new JLabel("Energy: " + chosenMonster.getEnergy() 
			+ "/" + chosenMonster.getBaseEnergy());
			lblShowMonster4Energy.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblShowMonster4Energy.setBounds(257, 341, 130, 23);
			frmShowMonster.getContentPane().add(lblShowMonster4Energy);
		}
		
		
		JButton btnShowMonstersReturn = new JButton("Return");
		btnShowMonstersReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuScreen newMainMenu = new MainMenuScreen(gameEnviro);
				frmShowMonster.dispose();
    			newMainMenu.MainMenu();
			}
		});
		btnShowMonstersReturn.setFont(new Font("Verdana", Font.BOLD, 13));
		btnShowMonstersReturn.setBounds(143, 472, 162, 53);
		frmShowMonster.getContentPane().add(btnShowMonstersReturn);
		
		lblShowMonstersRenameInvalid = new JLabel("Invalid");
		lblShowMonstersRenameInvalid.setForeground(Color.RED);
		lblShowMonstersRenameInvalid.setBounds(398, 418, 46, 14);
		frmShowMonster.getContentPane().add(lblShowMonstersRenameInvalid);
		lblShowMonstersRenameInvalid.setVisible(false);
		
	}
}
