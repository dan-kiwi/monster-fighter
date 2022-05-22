package gui;

import javax.swing.JFrame;

import mainenviro.GameEnviro;
import mainenviro.Battle;
import monster.Monster;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import items.Items;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Color;

/**
 * The Class ViewbattleFightScreen.
 */
public class ViewBattleFightScreen {

	private JFrame frmViewBattleFight;
	private static GameEnviro gameEnviro;
	private Monster selectedUserMonster;
	private Battle battle;
	private JList<Items> listBattleFightItem;
	private boolean enoughEnergy;

	/**
	 * Create the application.
	 * @param newGame, a new game environment
	 */
	public ViewBattleFightScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		battle = newGame.getBattle();
		selectedUserMonster = gameEnviro.getBattle().getCurrUser();
		enoughEnergy = selectedUserMonster.getEnergy() > 0;
		initialize();
	}
	
	/**
	 * Method to call back the View Battle Fight class from the Main Menu object
	 */
	public void ViewBattleFight() {
		ViewBattleFightScreen viewBattleFight = new ViewBattleFightScreen(gameEnviro);
		viewBattleFight.frmViewBattleFight.setVisible(true);
	}
	
	/**
	 * Method called after the user selects one of the fight choice buttons.
	 * Inputs the user choice into the fight class
	 * Calls the battle damage gui class
	 * Disposes of this window
	 *
	 * @param userChoice the user fight choice
	 */
	public void ViewBattleDamage(int userChoice) {
		battle.setUserChoice(userChoice);
		String enemyChoice = battle.setEnemyChoice();
		int[] damageDealt = battle.fight();
		ViewFightDamageScreen newFightDamage = new ViewFightDamageScreen(gameEnviro, battle.getStringUserChoice(),
				enemyChoice, Math.abs(damageDealt[1]), Math.abs(damageDealt[0]));
		frmViewBattleFight.dispose();
		newFightDamage.ViewFightDamage();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewBattleFight = new JFrame();
		frmViewBattleFight.setTitle("View Fight");
		frmViewBattleFight.setBounds(100, 100, 560, 575);
		frmViewBattleFight.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewBattleFight.getContentPane().setLayout(null);
		
		JLabel lblBattleFightTitle = new JLabel("Fight!");
		lblBattleFightTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleFightTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblBattleFightTitle.setBounds(159, 11, 96, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightTitle);
		
		JLabel lblBattleFightUserMonsterTitle = new JLabel("Your Monster");
		lblBattleFightUserMonsterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleFightUserMonsterTitle.setFont(new Font("Verdana", Font.BOLD, 15));
		lblBattleFightUserMonsterTitle.setBounds(30, 65, 230, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterTitle);
		
		JLabel lblBattleFightUserMonsterName = new JLabel(gameEnviro.getBattle().getCurrUser()
				.getMonsterName());
		lblBattleFightUserMonsterName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleFightUserMonsterName.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBattleFightUserMonsterName.setBounds(30, 102, 230, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterName);
		
		JLabel lblBattleFightUserMonsterHealth = new JLabel("Current Health : " + gameEnviro.getBattle()
		.getCurrUser().getCurrHealth() + "/" + gameEnviro.getBattle().getCurrUser().getMaxHealth());
		lblBattleFightUserMonsterHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightUserMonsterHealth.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightUserMonsterHealth.setBounds(30, 129, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterHealth);
		
		JLabel lblBattleFightUserMonsterAttack = new JLabel("Current Attack : " + gameEnviro.getBattle()
		.getCurrUser().getCurrAttack() + "/" + gameEnviro.getBattle().getCurrUser().getBaseAttack());
		lblBattleFightUserMonsterAttack.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightUserMonsterAttack.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightUserMonsterAttack.setBounds(30, 156, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterAttack);
		
		JLabel lblBattleFightUserMonsterDefence = new JLabel("Current Defence : " + gameEnviro.getBattle()
		.getCurrUser().getCurrDefence() + "/" + gameEnviro.getBattle().getCurrUser().getBaseDefence());
		lblBattleFightUserMonsterDefence.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightUserMonsterDefence.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightUserMonsterDefence.setBounds(30, 183, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterDefence);
		
		JLabel lblBattleFightUserMonsterEnergy = new JLabel("Current Energy : " + gameEnviro.getBattle()
		.getCurrUser().getEnergy() + "/" + gameEnviro.getBattle().getCurrUser().getBaseEnergy());
		lblBattleFightUserMonsterEnergy.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightUserMonsterEnergy.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightUserMonsterEnergy.setBounds(30, 209, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterEnergy);
		
		JLabel lblBattleFightEnemyTitle = new JLabel("Enemy");
		lblBattleFightEnemyTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleFightEnemyTitle.setFont(new Font("Verdana", Font.BOLD, 15));
		lblBattleFightEnemyTitle.setBounds(290, 65, 230, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyTitle);
		
		JLabel lblNoItemSelected = new JLabel("No Item Selected");
		lblNoItemSelected.setFont(new Font("Dialog", Font.BOLD, 10));
		lblNoItemSelected.setForeground(Color.RED);
		lblNoItemSelected.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoItemSelected.setBounds(290, 508, 230, 15);
		lblNoItemSelected.setVisible(false);
		frmViewBattleFight.getContentPane().add(lblNoItemSelected);
		
		JLabel lblBattleFightEnemyName = new JLabel(gameEnviro.getBattle().getCurrEnemy()
				.getMonsterName());
		lblBattleFightEnemyName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleFightEnemyName.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBattleFightEnemyName.setBounds(290, 102, 230, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyName);
		
		JLabel lblBattleFightEnemyHealth = new JLabel("Current Health : " + gameEnviro.getBattle()
		.getCurrEnemy().getCurrHealth() + "/" + gameEnviro.getBattle().getCurrEnemy().getMaxHealth());
		lblBattleFightEnemyHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightEnemyHealth.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightEnemyHealth.setBounds(290, 129, 230, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyHealth);
		
		JLabel lblBattleFightEnemyAttack = new JLabel("Current Attack : " + gameEnviro.getBattle()
		.getCurrEnemy().getCurrAttack() + "/" + gameEnviro.getBattle().getCurrEnemy().getBaseAttack());
		lblBattleFightEnemyAttack.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightEnemyAttack.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightEnemyAttack.setBounds(290, 156, 230, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyAttack);
		
		JLabel lblBattleFightEnemyDefence = new JLabel("Current Defence : " + gameEnviro.getBattle()
		.getCurrEnemy().getCurrDefence() + "/" + gameEnviro.getBattle().getCurrEnemy().getBaseDefence());
		lblBattleFightEnemyDefence.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightEnemyDefence.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightEnemyDefence.setBounds(290, 183, 230, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyDefence);
		
		JLabel lblBattleFightEnemyEnergy = new JLabel("Current Energy : " + gameEnviro.getBattle()
		.getCurrEnemy().getEnergy() + "/" + gameEnviro.getBattle().getCurrEnemy().getBaseEnergy());
		lblBattleFightEnemyEnergy.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightEnemyEnergy.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightEnemyEnergy.setBounds(290, 209, 230, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyEnergy);
		
		JButton btnBattleFightActionAttack = new JButton("Attack");
		btnBattleFightActionAttack.setToolTipText("Attack: 100% of attack power and 0% defence power");
		btnBattleFightActionAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBattleDamage(0);
			}
		});
		btnBattleFightActionAttack.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBattleFightActionAttack.setBounds(30, 268, 230, 46);
		frmViewBattleFight.getContentPane().add(btnBattleFightActionAttack);
		
		JButton btnBattleFightActionEnerAttack = new JButton("Energetic Attack");
		btnBattleFightActionEnerAttack.setVisible(enoughEnergy);
		btnBattleFightActionEnerAttack.setToolTipText("Energetic Attack: 125% of attack power and 50% of defence power");
		btnBattleFightActionEnerAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBattleDamage(1);
			}
		});
		btnBattleFightActionEnerAttack.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBattleFightActionEnerAttack.setBounds(290, 268, 230, 46);
		frmViewBattleFight.getContentPane().add(btnBattleFightActionEnerAttack);
		
		JButton btnBattleFightActionDefend = new JButton("Defend");
		btnBattleFightActionDefend.setToolTipText("Defend: 0% of attack power and 100% defence power");
		btnBattleFightActionDefend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBattleDamage(2);
			}
		});
		btnBattleFightActionDefend.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBattleFightActionDefend.setBounds(30, 339, 230, 46);
		frmViewBattleFight.getContentPane().add(btnBattleFightActionDefend);
		
		JButton btnBattleFightActionEnerDefence = new JButton("Energetic Defend");
		btnBattleFightActionEnerDefence.setVisible(enoughEnergy);
		btnBattleFightActionEnerDefence.setToolTipText("Energetic Defence: 50% of attack power and 125% of defence power");
		btnBattleFightActionEnerDefence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBattleDamage(3);
			}
		});
		btnBattleFightActionEnerDefence.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBattleFightActionEnerDefence.setBounds(290, 339, 230, 46);
		frmViewBattleFight.getContentPane().add(btnBattleFightActionEnerDefence);
		
		JButton btnBattleFightActionUseItem = new JButton("Use Item");
		btnBattleFightActionUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listBattleFightItem.getSelectedValue() == null)) {
					listBattleFightItem.getSelectedValue().useItemOnMonster(gameEnviro.getBattle().getCurrUser());
					gameEnviro.getUserItemList().remove(listBattleFightItem.getSelectedValue());
					ViewBattleFightScreen newBattleFight = new ViewBattleFightScreen(gameEnviro);
					frmViewBattleFight.dispose();
					newBattleFight.ViewBattleFight();
				} else {
					lblNoItemSelected.setVisible(true);
				}
			}
		});
		btnBattleFightActionUseItem.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBattleFightActionUseItem.setBounds(30, 404, 230, 46);
		frmViewBattleFight.getContentPane().add(btnBattleFightActionUseItem);
		
		JButton btnBattleFightActionQuit = new JButton("Quit");
		btnBattleFightActionQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ViewBattleMenuScreen newViewBattle = new ViewBattleMenuScreen(gameEnviro);
				frmViewBattleFight.dispose();
			}
		});
		btnBattleFightActionQuit.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBattleFightActionQuit.setBounds(30, 467, 230, 46);
		frmViewBattleFight.getContentPane().add(btnBattleFightActionQuit);
		
		DefaultListModel<Items> listItem = new DefaultListModel<Items>();
		listBattleFightItem = new JList<Items>(listItem);
		for (int i=0; i < gameEnviro.getUserItemList().size(); i++) {
			listItem.addElement(gameEnviro.getUserItemList().get(i));
	    }
		listBattleFightItem.setBounds(290, 404, 230, 106);
		frmViewBattleFight.getContentPane().add(listBattleFightItem);
		
		JScrollPane scrollPane = new JScrollPane(listBattleFightItem);
		scrollPane.setBounds(290, 404, 230, 80);
		frmViewBattleFight.getContentPane().add(scrollPane);
		
		JLabel lblBattleFightItemTitle = new JLabel("(Select item to use on your Monster)");
		lblBattleFightItemTitle.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblBattleFightItemTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleFightItemTitle.setBounds(290, 490, 230, 19);
		frmViewBattleFight.getContentPane().add(lblBattleFightItemTitle);
	}
}
