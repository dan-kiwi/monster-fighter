package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import mainenviro.GameEnviro;
import monster.Monster;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionListener;


import javax.swing.event.ListSelectionEvent;

/**
 * The Class ShopBuyMonsterScreen. This is the class shows the Monster's available to buy in a GUI form
 */
public class ShopBuyMonsterScreen {

	private JFrame frmShopBuyMonster;
	private static GameEnviro gameEnviro;
	private JList<Monster> listBuyMonster;
	private JLabel lblBuyMonsterCantBuy;
	private JLabel lblBuyMonsterGoldInfo;
	private JLabel lblBuyMonsterSizeInfo;

	/**
	 * Create the application.
	 * @param newGame, a new game environment
	 */
	public ShopBuyMonsterScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		initialize();
	}
	
	/**
	 * Method to call back the ShopBuyMonsterScreen from other classes
	 */
	public void ShopBuyMonster() {
		ShopBuyMonsterScreen buyMonster = new ShopBuyMonsterScreen(gameEnviro);
		buyMonster.frmShopBuyMonster.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShopBuyMonster = new JFrame();
		frmShopBuyMonster.setTitle("Buy Monster");
		frmShopBuyMonster.setBounds(100, 100, 560, 575);
		frmShopBuyMonster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShopBuyMonster.getContentPane().setLayout(null);
		
		
		JLabel lblSelectAMonster = new JLabel("(Select A Monster First)");
		lblSelectAMonster.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAMonster.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSelectAMonster.setBounds(50, 450, 450, 22);
		lblSelectAMonster.setVisible(false);
		frmShopBuyMonster.getContentPane().add(lblSelectAMonster);
		
		
		JLabel lblBuyMonsterGold = new JLabel("Player Gold: " + gameEnviro.getUserGoldAmount());
		lblBuyMonsterGold.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyMonsterGold.setBounds(69, 15, 146, 14);
		frmShopBuyMonster.getContentPane().add(lblBuyMonsterGold);
		
		JLabel lblBuyMonsterCurrentDay = new JLabel("Current Day: " + gameEnviro.getGameDay());
		lblBuyMonsterCurrentDay.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyMonsterCurrentDay.setBounds(68, 33, 146, 17);
		frmShopBuyMonster.getContentPane().add(lblBuyMonsterCurrentDay);
		
		JLabel lblBuyMonsterMonsterCount = new JLabel("Current Monsters: " + gameEnviro.getUserMonsterList().size());
		lblBuyMonsterMonsterCount.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyMonsterMonsterCount.setBounds(67, 54, 156, 14);
		frmShopBuyMonster.getContentPane().add(lblBuyMonsterMonsterCount);
		
		JTextPane textPaneMonsterStatistics = new JTextPane();
		textPaneMonsterStatistics.setBounds(290, 180, 230, 140);
		frmShopBuyMonster.getContentPane().add(textPaneMonsterStatistics);
		
		DefaultListModel<Monster> listMonster = new DefaultListModel<Monster>();
		listBuyMonster = new JList<Monster>(listMonster);
		listBuyMonster.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				textPaneMonsterStatistics.setText(listBuyMonster.getSelectedValue().shopString());
			}
		});
		for (int i=0; i < gameEnviro.getUserGameShop().getShopMonsterList().size(); i++) {
	    	listMonster.addElement(gameEnviro.getUserGameShop().getShopMonsterList().get(i));
	    }
		listBuyMonster.setVisibleRowCount(15);
		listBuyMonster.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listBuyMonster.setBounds(30, 180, 230, 140);
		listBuyMonster.setCellRenderer(new NameRenderer());
		frmShopBuyMonster.getContentPane().add(listBuyMonster);
		
		JScrollPane scrollPaneBuyMonster = new JScrollPane(listBuyMonster);
		scrollPaneBuyMonster.setBounds(30, 180, 230, 140);
		frmShopBuyMonster.getContentPane().add(scrollPaneBuyMonster);
		
		JLabel lblBuyMonsterTitle = new JLabel("Available Monsters");
		lblBuyMonsterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyMonsterTitle.setFont(new Font("Verdana", Font.BOLD, 18));
		lblBuyMonsterTitle.setBounds(50, 89, 450, 35);
		frmShopBuyMonster.getContentPane().add(lblBuyMonsterTitle);
		
		JButton btnBuyMonster = new JButton("Buy Monster");
		btnBuyMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listBuyMonster.getSelectedValue() == null)) {
					lblSelectAMonster.setVisible(false);
					if (!(listBuyMonster.getSelectedValue().getMonsterBuyPrice() > 
					gameEnviro.getUserGoldAmount() || (gameEnviro.getUserMonsterList().size() >= 4))) {
						
						gameEnviro.addMonster(listBuyMonster.getSelectedValue());
						gameEnviro.getUserGameShop().getShopMonsterList().remove(listBuyMonster.getSelectedValue());
						gameEnviro.addUserGoldAmount(-listBuyMonster.getSelectedValue().getMonsterBuyPrice());
						ShopBuyMonsterScreen newBuyMonster = new ShopBuyMonsterScreen(gameEnviro);
						frmShopBuyMonster.dispose();
						newBuyMonster.ShopBuyMonster();
						
					} else {
						lblBuyMonsterCantBuy.setVisible(true);
						if (listBuyMonster.getSelectedValue().getMonsterBuyPrice() > 
						gameEnviro.getUserGoldAmount()) {
							lblBuyMonsterGoldInfo.setVisible(true);
						} else { 
							lblBuyMonsterSizeInfo.setVisible(true);
						}
					}
				} else {
					lblBuyMonsterCantBuy.setVisible(true);
					lblSelectAMonster.setVisible(true);
				}
			}
		});
		btnBuyMonster.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyMonster.setBounds(30, 360, 230, 45);
		frmShopBuyMonster.getContentPane().add(btnBuyMonster);
		
		lblBuyMonsterCantBuy = new JLabel("Cannot Purchase");
		lblBuyMonsterCantBuy.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyMonsterCantBuy.setForeground(Color.RED);
		lblBuyMonsterCantBuy.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyMonsterCantBuy.setBounds(50, 430, 450, 25);
		lblBuyMonsterCantBuy.setVisible(false);
		frmShopBuyMonster.getContentPane().add(lblBuyMonsterCantBuy);
		
		lblBuyMonsterGoldInfo = new JLabel("(You must have enough gold)");
		lblBuyMonsterGoldInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyMonsterGoldInfo.setForeground(Color.BLACK);
		lblBuyMonsterGoldInfo.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBuyMonsterGoldInfo.setBounds(50, 450, 450, 22);
		lblBuyMonsterGoldInfo.setVisible(false);
		frmShopBuyMonster.getContentPane().add(lblBuyMonsterGoldInfo);
		
		lblBuyMonsterSizeInfo = new JLabel("(You can only have 4 Monsters)");
		lblBuyMonsterSizeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyMonsterSizeInfo.setForeground(Color.BLACK);
		lblBuyMonsterSizeInfo.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBuyMonsterSizeInfo.setBounds(50, 450, 450, 22);
		lblBuyMonsterSizeInfo.setVisible(false);
		frmShopBuyMonster.getContentPane().add(lblBuyMonsterSizeInfo);
		
		JButton btnBuyMonsterReturn = new JButton("Return");
		btnBuyMonsterReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitShopScreen newVisitShop = new VisitShopScreen(gameEnviro);
				frmShopBuyMonster.dispose();
    			newVisitShop.VisitShop();
			}
		});
		btnBuyMonsterReturn.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyMonsterReturn.setBounds(290, 360, 230, 45);
		frmShopBuyMonster.getContentPane().add(btnBuyMonsterReturn);

	}
}
