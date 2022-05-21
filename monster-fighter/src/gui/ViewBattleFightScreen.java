package gui;

import java.awt.EventQueue;

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

public class ViewBattleFightScreen {

	private JFrame frmViewBattleFight;
	private static GameEnviro gameEnviro;
	private Monster selectedUserMonster;
	private Monster selectedEnemyMonster;
	private Battle battle;
	private JList<Items> listBattleFightItem;
	private boolean enoughEnergy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBattleFightScreen window = new ViewBattleFightScreen(null);
					window.frmViewBattleFight.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewBattleFightScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		battle = newGame.getBattle();
		selectedUserMonster = gameEnviro.getBattle().getCurrUser();
		selectedEnemyMonster = gameEnviro.getBattle().getCurrEnemy();
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
		battle.setUserChoice(userChoice); //int for battle class representing energetic defence
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
		frmViewBattleFight.setBounds(100, 100, 450, 575);
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
		lblBattleFightUserMonsterTitle.setBounds(28, 65, 129, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterTitle);
		
		JLabel lblBattleFightUserMonsterName = new JLabel(gameEnviro.getBattle().getCurrUser()
				.getDisplayName());
		lblBattleFightUserMonsterName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleFightUserMonsterName.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBattleFightUserMonsterName.setBounds(10, 102, 166, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterName);
		
		JLabel lblBattleFightUserMonsterHealth = new JLabel("Current Health : " + gameEnviro.getBattle()
		.getCurrUser().getCurrHealth() + "/" + gameEnviro.getBattle().getCurrUser().getMaxHealth());
		lblBattleFightUserMonsterHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightUserMonsterHealth.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightUserMonsterHealth.setBounds(10, 129, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterHealth);
		
		JLabel lblBattleFightUserMonsterAttack = new JLabel("Current Attack : " + gameEnviro.getBattle()
		.getCurrUser().getCurrAttack() + "/" + gameEnviro.getBattle().getCurrUser().getBaseAttack());
		lblBattleFightUserMonsterAttack.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightUserMonsterAttack.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightUserMonsterAttack.setBounds(10, 156, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterAttack);
		
		JLabel lblBattleFightUserMonsterDefence = new JLabel("Current Defence : " + gameEnviro.getBattle()
		.getCurrUser().getCurrDefence() + "/" + gameEnviro.getBattle().getCurrUser().getBaseDefence());
		lblBattleFightUserMonsterDefence.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightUserMonsterDefence.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightUserMonsterDefence.setBounds(10, 183, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterDefence);
		
		JLabel lblBattleFightUserMonsterEnergy = new JLabel("Current Energy : " + gameEnviro.getBattle()
		.getCurrUser().getEnergy() + "/" + gameEnviro.getBattle().getCurrUser().getBaseEnergy());
		lblBattleFightUserMonsterEnergy.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightUserMonsterEnergy.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightUserMonsterEnergy.setBounds(10, 209, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightUserMonsterEnergy);
		
		JLabel lblBattleFightEnemyTitle = new JLabel("Enemy");
		lblBattleFightEnemyTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleFightEnemyTitle.setFont(new Font("Verdana", Font.BOLD, 15));
		lblBattleFightEnemyTitle.setBounds(250, 65, 129, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyTitle);
		
		JLabel lblBattleFightEnemyName = new JLabel(gameEnviro.getBattle().getCurrEnemy()
				.getDisplayName());
		lblBattleFightEnemyName.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleFightEnemyName.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBattleFightEnemyName.setBounds(229, 102, 166, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyName);
		
		JLabel lblBattleFightEnemyHealth = new JLabel("Current Health : " + gameEnviro.getBattle()
		.getCurrEnemy().getCurrHealth() + "/" + gameEnviro.getBattle().getCurrEnemy().getMaxHealth());
		lblBattleFightEnemyHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightEnemyHealth.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightEnemyHealth.setBounds(229, 129, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyHealth);
		
		JLabel lblBattleFightEnemyAttack = new JLabel("Current Attack : " + gameEnviro.getBattle()
		.getCurrEnemy().getCurrAttack() + "/" + gameEnviro.getBattle().getCurrEnemy().getBaseAttack());
		lblBattleFightEnemyAttack.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightEnemyAttack.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightEnemyAttack.setBounds(229, 156, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyAttack);
		
		JLabel lblBattleFightEnemyDefence = new JLabel("Current Defence : " + gameEnviro.getBattle()
		.getCurrEnemy().getCurrDefence() + "/" + gameEnviro.getBattle().getCurrEnemy().getBaseDefence());
		lblBattleFightEnemyDefence.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightEnemyDefence.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightEnemyDefence.setBounds(229, 183, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyDefence);
		
		JLabel lblBattleFightEnemyEnergy = new JLabel("Current Energy : " + gameEnviro.getBattle()
		.getCurrEnemy().getEnergy() + "/" + gameEnviro.getBattle().getCurrEnemy().getBaseEnergy());
		lblBattleFightEnemyEnergy.setHorizontalAlignment(SwingConstants.LEFT);
		lblBattleFightEnemyEnergy.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBattleFightEnemyEnergy.setBounds(229, 209, 195, 26);
		frmViewBattleFight.getContentPane().add(lblBattleFightEnemyEnergy);
		
		JButton btnBattleFightActionAttack = new JButton("Attack");
		btnBattleFightActionAttack.setToolTipText("Attack: 100% of attack power and 0% defence power");
		btnBattleFightActionAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBattleDamage(0);
			}
		});
		btnBattleFightActionAttack.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBattleFightActionAttack.setBounds(26, 268, 166, 46);
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
		btnBattleFightActionEnerAttack.setBounds(229, 268, 166, 46);
		frmViewBattleFight.getContentPane().add(btnBattleFightActionEnerAttack);
		
		JButton btnBattleFightActionDefend = new JButton("Defend");
		btnBattleFightActionDefend.setToolTipText("Defend: 0% of attack power and 100% defence power");
		btnBattleFightActionDefend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBattleDamage(2);
			}
		});
		btnBattleFightActionDefend.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBattleFightActionDefend.setBounds(28, 339, 166, 46);
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
		btnBattleFightActionEnerDefence.setBounds(229, 339, 166, 46);
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
				}
			}
		});
		btnBattleFightActionUseItem.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBattleFightActionUseItem.setBounds(28, 404, 166, 46);
		frmViewBattleFight.getContentPane().add(btnBattleFightActionUseItem);
		
		JButton btnBattleFightActionQuit = new JButton("Quit");
		btnBattleFightActionQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBattleMenuScreen newViewBattle = new ViewBattleMenuScreen(gameEnviro);
				frmViewBattleFight.dispose();
			}
		});
		btnBattleFightActionQuit.setFont(new Font("Verdana", Font.BOLD, 14));
		btnBattleFightActionQuit.setBounds(28, 467, 166, 46);
		frmViewBattleFight.getContentPane().add(btnBattleFightActionQuit);
		
		DefaultListModel<Items> listItem = new DefaultListModel<Items>();
		listBattleFightItem = new JList<Items>(listItem);
		for (int i=0; i < gameEnviro.getUserItemList().size(); i++) {
			listItem.addElement(gameEnviro.getUserItemList().get(i));
	    }
		listBattleFightItem.setBounds(229, 404, 166, 106);
		frmViewBattleFight.getContentPane().add(listBattleFightItem);
		
		JScrollPane scrollPane = new JScrollPane(listBattleFightItem);
		scrollPane.setBounds(229, 404, 166, 80);
		frmViewBattleFight.getContentPane().add(scrollPane);
		
		JLabel lblBattleFightItemTitle = new JLabel("(Select item to use on your Monster)");
		lblBattleFightItemTitle.setBounds(225, 490, 183, 19);
		frmViewBattleFight.getContentPane().add(lblBattleFightItemTitle);
	}
}
