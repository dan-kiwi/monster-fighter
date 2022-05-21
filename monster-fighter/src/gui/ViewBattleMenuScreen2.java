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
import java.awt.List;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewBattleMenuScreen2 extends JFrame {

	private JPanel contentPane;
	private static GameEnviro gameEnviro;
	private JList<Monster> listUserMonster;
	private JList<Monster> listEnemyMonster;
	private DefaultListCellRenderer localeRenderer;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewBattleMenuScreen2 frame = new ViewBattleMenuScreen2();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ViewBattleMenuScreen2(GameEnviro newGame) {
		gameEnviro = newGame;
		this.localeRenderer = new MyListCellRenderer();
		this.listEnemyMonster = new JList(newGame.getBattle().getPotentialBattles().toArray());
		this.listUserMonster = new JList(newGame.getUserMonsterList().toArray());
		listUserMonster.setFont(new Font("Dialog", Font.BOLD, 13));
		this.listEnemyMonster.setCellRenderer(localeRenderer);
		this.listUserMonster.setCellRenderer(localeRenderer);
		initialize();
		this.setVisible(true);
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
		
		JButton btnBattleMenuFight = new JButton("Start Fight");
		btnBattleMenuFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!(listUserMonster.getSelectedValue() == null || listUserMonster.getSelectedValue() == null)) {
					gameEnviro.getBattle().setCurrUser(listUserMonster.getSelectedValue());
					gameEnviro.getBattle().setCurrEnemy(listUserMonster.getSelectedValue());
					ViewBattleFightScreen newBattleFight = new ViewBattleFightScreen(gameEnviro);
					dispose();
					newBattleFight.ViewBattleFight();
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
		
		listUserMonster.setBounds(30, 90, 230, 100);
		contentPane.add(listUserMonster);
		
		listEnemyMonster.setBounds(290, 90, 230, 100);
		contentPane.add(listEnemyMonster);
		
		JTextPane textPaneUserSelectedMonster = new JTextPane();
		textPaneUserSelectedMonster.setBounds(30, 215, 230, 80);
		contentPane.add(textPaneUserSelectedMonster);
		
		JTextPane textPaneUserSelectedMonster_1 = new JTextPane();
		textPaneUserSelectedMonster_1.setBounds(290, 215, 230, 80);
		contentPane.add(textPaneUserSelectedMonster_1);
	}

}


