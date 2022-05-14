package gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import mainenviro.GameEnviro;
import monster.Monster;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import items.Items;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewBattleMenuScreen {

	private JFrame frmBattleMenu;
	private static GameEnviro gameEnviro;
	private JList<Monster> listBattleMenuUserMonster;
	private JList<Monster> listBattleMenuEnemyMonster;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBattleMenuScreen window = new ViewBattleMenuScreen(null);
					window.frmBattleMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewBattleMenuScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		initialize();
	}
	
	/**
	 * Method to call back the Battle Menu class from other class
	 */
	public void ViewBattleMenu() {
		ViewBattleMenuScreen currentBattleMenu = new ViewBattleMenuScreen(gameEnviro);
		currentBattleMenu.frmBattleMenu.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBattleMenu = new JFrame();
		frmBattleMenu.setTitle("Battle Menu");
		frmBattleMenu.setBounds(100, 100, 550, 450);
		frmBattleMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattleMenu.getContentPane().setLayout(null);
		
		JLabel lblBattleMenuTitle = new JLabel("Here are your Daily Battles");
		lblBattleMenuTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleMenuTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblBattleMenuTitle.setBounds(131, 11, 263, 29);
		frmBattleMenu.getContentPane().add(lblBattleMenuTitle);
		
		JLabel lblBattleMenuUserMonster = new JLabel("Your Available Monsters");
		lblBattleMenuUserMonster.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleMenuUserMonster.setFont(new Font("Verdana", Font.BOLD, 14));
		lblBattleMenuUserMonster.setBounds(39, 86, 209, 29);
		frmBattleMenu.getContentPane().add(lblBattleMenuUserMonster);
		
		DefaultListModel<Monster> listUserMonster = new DefaultListModel<Monster>();
		listBattleMenuUserMonster = new JList(listUserMonster);
		for (int i=0; i < gameEnviro.getUserMonsterList().size(); i++) {
			if (gameEnviro.getUserMonsterList().get(i).getCurrHealth() > 0) {
				listUserMonster.addElement(gameEnviro.getUserMonsterList().get(i));
			}	
	    }
		listBattleMenuUserMonster.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listBattleMenuUserMonster.setVisibleRowCount(4);
		listBattleMenuUserMonster.setBounds(30, 114, 230, 103);
		frmBattleMenu.getContentPane().add(listBattleMenuUserMonster);
		
		JScrollPane scrollPaneUserMonster = new JScrollPane(listBattleMenuUserMonster);
		scrollPaneUserMonster.setBounds(30, 114, 230, 103);
		frmBattleMenu.getContentPane().add(scrollPaneUserMonster);
		
		JLabel lblBattleMenuEnemyMonster = new JLabel("Available Enemies");
		lblBattleMenuEnemyMonster.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleMenuEnemyMonster.setFont(new Font("Verdana", Font.BOLD, 14));
		lblBattleMenuEnemyMonster.setBounds(282, 86, 209, 29);
		frmBattleMenu.getContentPane().add(lblBattleMenuEnemyMonster);
		
		DefaultListModel<Monster> listEnemyMonster = new DefaultListModel<Monster>();
		listBattleMenuEnemyMonster = new JList(listEnemyMonster);
		for (int i=0; i < gameEnviro.getBattle().getPotentialBattles().size(); i++) {
			if (gameEnviro.getBattle().getPotentialBattles().get(i).getCurrHealth() > 0) {
				listEnemyMonster.addElement(gameEnviro.getBattle().getPotentialBattles().get(i));
			}
	    }
		listBattleMenuEnemyMonster.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listBattleMenuEnemyMonster.setVisibleRowCount(5);
		listBattleMenuEnemyMonster.setBounds(275, 114, 230, 103);
		frmBattleMenu.getContentPane().add(listBattleMenuEnemyMonster);
		
		JScrollPane scrollPaneEnemyMonster = new JScrollPane(listBattleMenuEnemyMonster);
		scrollPaneEnemyMonster.setBounds(275, 114, 230, 103);
		frmBattleMenu.getContentPane().add(scrollPaneEnemyMonster);
		
		JButton btnBattleMenuFight = new JButton("Start Fight");
		btnBattleMenuFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listBattleMenuUserMonster.getSelectedValue() == null || listBattleMenuEnemyMonster.getSelectedValue() == null)) {
					gameEnviro.getBattle().setCurrUser(listBattleMenuUserMonster.getSelectedValue());
					gameEnviro.getBattle().setCurrEnemy(listBattleMenuEnemyMonster.getSelectedValue());
					ViewBattleFightScreen newBattleFight = new ViewBattleFightScreen(gameEnviro);
					frmBattleMenu.dispose();
					newBattleFight.ViewBattleFight();
				}
			}
		});
		btnBattleMenuFight.setFont(new Font("Verdana", Font.BOLD, 15));
		btnBattleMenuFight.setBounds(179, 265, 179, 52);
		frmBattleMenu.getContentPane().add(btnBattleMenuFight);
		
		JLabel lblBattleMenuSelectTitle = new JLabel("(Select your Monster and the Enemy you want to Fight)");
		lblBattleMenuSelectTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleMenuSelectTitle.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleMenuSelectTitle.setBounds(78, 228, 380, 29);
		frmBattleMenu.getContentPane().add(lblBattleMenuSelectTitle);
		
		JButton btnBattleMenuReturn = new JButton("Return");
		btnBattleMenuReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuScreen newMainMenu = new MainMenuScreen(gameEnviro);
				frmBattleMenu.dispose();
    			newMainMenu.MainMenu();
			}
		});
		btnBattleMenuReturn.setFont(new Font("Verdana", Font.BOLD, 15));
		btnBattleMenuReturn.setBounds(179, 348, 179, 52);
		frmBattleMenu.getContentPane().add(btnBattleMenuReturn);
	}

}
