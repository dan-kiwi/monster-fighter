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
		this.localeRenderer = new BattleRenderer();
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
		setBounds(100, 100, 560, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBattleMenuTitle = new JLabel("Here are your Daily Battles");
		lblBattleMenuTitle.setBounds(131, 20, 263, 29);
		lblBattleMenuTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblBattleMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBattleMenuTitle);
		
		JLabel lblUserMonsters = new JLabel("Your Available Monsters");
		lblUserMonsters.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserMonsters.setFont(new Font("Verdana", Font.BOLD, 14));
		lblUserMonsters.setBounds(30, 70, 230, 29);
		contentPane.add(lblUserMonsters);
		
		JLabel lblYourAvailableEnemies = new JLabel("Your Available Enemies");
		lblYourAvailableEnemies.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourAvailableEnemies.setFont(new Font("Verdana", Font.BOLD, 14));
		lblYourAvailableEnemies.setBounds(290, 70, 230, 29);
		contentPane.add(lblYourAvailableEnemies);
		
		JLabel lblIncorrectInputWarning = new JLabel("Please select one of your monsters and one of the enemies");
		lblIncorrectInputWarning.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblIncorrectInputWarning.setForeground(Color.RED);
		lblIncorrectInputWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncorrectInputWarning.setBounds(30, 410, 490, 40);
		contentPane.add(lblIncorrectInputWarning);
		lblIncorrectInputWarning.setVisible(false);
		
		JTextPane textPaneUserSelectedMonster = new JTextPane();
		textPaneUserSelectedMonster.setBackground(Color.WHITE);
		textPaneUserSelectedMonster.setBounds(30, 290, 230, 100);
		contentPane.add(textPaneUserSelectedMonster);
		
		JTextPane textPaneEnemySelectedMonster = new JTextPane();
		textPaneEnemySelectedMonster.setBackground(Color.WHITE);
		textPaneEnemySelectedMonster.setBounds(290, 290, 230, 100);
		contentPane.add(textPaneEnemySelectedMonster);
		
		JList<Monster> listEnemyMonster = new JList(arrayEnemyMonster);
		listEnemyMonster.setCellRenderer(localeRenderer);
		listEnemyMonster.setFont(new Font("Dialog", Font.BOLD, 14));
		listEnemyMonster.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				textPaneEnemySelectedMonster.setText(listEnemyMonster.getSelectedValue().toString());
			}
		});
		listEnemyMonster.setBounds(290, 110, 230, 110);
		contentPane.add(listEnemyMonster);
		
		
		JList<Monster> listUserMonster = new JList(arrayUserMonster);
		listUserMonster.setFont(new Font("Dialog", Font.BOLD, 14));
		listUserMonster.setCellRenderer(localeRenderer);
		listUserMonster.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				textPaneUserSelectedMonster.setText(listUserMonster.getSelectedValue().toString());
			}
		});
		listUserMonster.setBounds(30, 110, 230, 110);
		contentPane.add(listUserMonster);
		
		JButton btnBattleMenuFight = new JButton("Start Fight");
		btnBattleMenuFight.setFont(new Font("Verdana", Font.BOLD, 15));
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
		btnBattleMenuFight.setBounds(30, 460, 230, 50);
		contentPane.add(btnBattleMenuFight);
		
		JButton btnBattleMenuReturn = new JButton("Return");
		btnBattleMenuReturn.setFont(new Font("Verdana", Font.BOLD, 15));
		btnBattleMenuReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainMenuScreen newMainMenu = new MainMenuScreen(gameEnviro);
				dispose();
    			newMainMenu.MainMenu();
			}
		});
		btnBattleMenuReturn.setBounds(290, 460, 230, 50);
		contentPane.add(btnBattleMenuReturn);
		
		JLabel lblEnemyStat = new JLabel("Enemy Statistics");
		lblEnemyStat.setFont(new Font("Verdana", Font.BOLD, 14));
		lblEnemyStat.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemyStat.setBounds(290, 250, 230, 30);
		contentPane.add(lblEnemyStat);
		
		JLabel lblYourMonstersStatistics = new JLabel("Your Monster's Statistics");
		lblYourMonstersStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourMonstersStatistics.setFont(new Font("Verdana", Font.BOLD, 14));
		lblYourMonstersStatistics.setBounds(30, 250, 230, 30);
		contentPane.add(lblYourMonstersStatistics);
		


	}
}


