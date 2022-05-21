package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainenviro.GameEnviro;
import monster.Monster;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;

public class ViewBattleMenuScreen extends JFrame {

	private JPanel contentPane;
	private GameEnviro gameEnviro;
	private Monster[] arrayUserMonster;
	private Monster[] arrayEnemyMonster;
	private DefaultListCellRenderer localeRenderer;


	/**
	 * Create the application.
	 */
	public ViewBattleMenuScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		this.localeRenderer = new MyListCellRenderer();
		createArrays();
		initialize();
		this.setVisible(true);
	}
	
	public void createArrays() {
		List<Monster> listUser = gameEnviro.getUserMonsterList();
		List<Monster> listEnemy = gameEnviro.getBattle().getPotentialBattles();
		arrayUserMonster = new Monster[listUser.size()];
		arrayEnemyMonster = new Monster[listEnemy.size()];
		for (int i = 0; i < arrayUserMonster.length; i++) {
			arrayUserMonster[i] = listUser.get(i);
		}
		for (int i = 0; i < arrayEnemyMonster.length; i++) {
			arrayEnemyMonster[i] = listEnemy.get(i);
		}
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBattleMenuTitle = new JLabel("Here are your Daily Battles");
		lblBattleMenuTitle.setBounds(131, 11, 263, 29);
		lblBattleMenuTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblBattleMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBattleMenuTitle);
		
		JLabel lblUserMonsters = new JLabel("Your Available Monsters");
		lblUserMonsters.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserMonsters.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUserMonsters.setBounds(30, 50, 230, 29);
		contentPane.add(lblUserMonsters);
		
		JLabel lblYourAvailableEnemies = new JLabel("Your Available Enemies");
		lblYourAvailableEnemies.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourAvailableEnemies.setFont(new Font("Dialog", Font.BOLD, 14));
		lblYourAvailableEnemies.setBounds(290, 50, 230, 29);
		contentPane.add(lblYourAvailableEnemies);
		
		JLabel lblIncorrectInputWarning = new JLabel("Please select one of your monsters and one of the enemies");
		lblIncorrectInputWarning.setForeground(Color.RED);
		lblIncorrectInputWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncorrectInputWarning.setBounds(30, 309, 490, 30);
		contentPane.add(lblIncorrectInputWarning);
		lblIncorrectInputWarning.setVisible(false);
		
		JTextPane textPaneUserSelectedMonster = new JTextPane();
		textPaneUserSelectedMonster.setBounds(30, 215, 230, 80);
		contentPane.add(textPaneUserSelectedMonster);
		
		JTextPane textPaneEnemySelectedMonster = new JTextPane();
		textPaneEnemySelectedMonster.setBounds(290, 215, 230, 80);
		contentPane.add(textPaneEnemySelectedMonster);
		
		JList<Monster> listEnemyMonster = new JList(arrayEnemyMonster);
		listEnemyMonster.setCellRenderer(localeRenderer);
		listEnemyMonster.setFont(new Font("Dialog", Font.BOLD, 14));
		listEnemyMonster.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				textPaneEnemySelectedMonster.setText(listEnemyMonster.getSelectedValue().toString());
			}
		});
		listEnemyMonster.setBounds(290, 90, 230, 100);
		contentPane.add(listEnemyMonster);
		
		
		JList<Monster> listUserMonster = new JList(arrayUserMonster);
		listUserMonster.setFont(new Font("Dialog", Font.BOLD, 14));
		listUserMonster.setCellRenderer(localeRenderer);
		listUserMonster.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				textPaneUserSelectedMonster.setText(listUserMonster.getSelectedValue().toString());
			}
		});
		listUserMonster.setBounds(30, 90, 230, 100);
		contentPane.add(listUserMonster);
		
		JButton btnBattleMenuFight = new JButton("Start Fight");
		btnBattleMenuFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!(listUserMonster.getSelectedValue() == null || listUserMonster.getSelectedValue() == null)) {
					gameEnviro.getBattle().setCurrUser(listUserMonster.getSelectedValue());
					gameEnviro.getBattle().setCurrEnemy(listEnemyMonster.getSelectedValue());
					ViewBattleFightScreen newBattleFight = new ViewBattleFightScreen(gameEnviro);
					dispose();
					newBattleFight.ViewBattleFight();
				} else {
					lblIncorrectInputWarning.setVisible(true);
				}
			}
		});
		btnBattleMenuFight.setBounds(30, 350, 230, 40);
		contentPane.add(btnBattleMenuFight);
		
		JButton btnBattleMenuReturn = new JButton("Return");
		btnBattleMenuReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuScreen newMainMenu = new MainMenuScreen(gameEnviro);
				dispose();
    			newMainMenu.MainMenu();
			}
		});
		btnBattleMenuReturn.setBounds(290, 350, 230, 40);
		contentPane.add(btnBattleMenuReturn);
		


	}
}


