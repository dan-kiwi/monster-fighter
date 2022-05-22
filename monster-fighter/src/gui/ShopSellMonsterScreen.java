package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mainenviro.GameEnviro;
import monster.Monster;

/**
 * The Class ShopSellMonsterScreen.
 */
public class ShopSellMonsterScreen {

	private JFrame frmShopSellMonster;
	private static GameEnviro gameEnviro;
	private JList<Monster> listSellMonster;
	private JLabel lblBuyMonsterCantSell;

	/**
	 * Create the application.
	 * @param newGame, a new game environment
	 */
	public ShopSellMonsterScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		initialize();
	}
	
	/**
	 * Method to call back the ShopSellMonsterScreen from other classes
	 */
	public void ShopSellMonster() {
		ShopSellMonsterScreen sellMonster = new ShopSellMonsterScreen(gameEnviro);
		sellMonster.frmShopSellMonster.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShopSellMonster = new JFrame();
		frmShopSellMonster.setTitle("Buy Monster");
		frmShopSellMonster.setBounds(100, 100, 560, 575);
		frmShopSellMonster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShopSellMonster.getContentPane().setLayout(null);
		
		
		JLabel lblSelectAMonster = new JLabel("(Select A Monster First)");
		lblSelectAMonster.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAMonster.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSelectAMonster.setBounds(50, 450, 450, 22);
		lblSelectAMonster.setVisible(false);
		frmShopSellMonster.getContentPane().add(lblSelectAMonster);
		
		
		JLabel lblBuyMonsterGold = new JLabel("Player Gold: " + gameEnviro.getUserGoldAmount());
		lblBuyMonsterGold.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyMonsterGold.setBounds(69, 15, 146, 14);
		frmShopSellMonster.getContentPane().add(lblBuyMonsterGold);
		
		JLabel lblBuyMonsterCurrentDay = new JLabel("Current Day: " + gameEnviro.getGameDay());
		lblBuyMonsterCurrentDay.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyMonsterCurrentDay.setBounds(68, 33, 146, 17);
		frmShopSellMonster.getContentPane().add(lblBuyMonsterCurrentDay);
		
		JLabel lblBuyMonsterMonsterCount = new JLabel("Current Monsters: " + gameEnviro.getUserMonsterList().size());
		lblBuyMonsterMonsterCount.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyMonsterMonsterCount.setBounds(67, 54, 156, 14);
		frmShopSellMonster.getContentPane().add(lblBuyMonsterMonsterCount);
		
		JTextPane textPaneMonsterStatistics = new JTextPane();
		textPaneMonsterStatistics.setBounds(290, 180, 230, 140);
		frmShopSellMonster.getContentPane().add(textPaneMonsterStatistics);
		
		DefaultListModel<Monster> listMonster = new DefaultListModel<Monster>();
		listSellMonster = new JList<Monster>(listMonster);
		listSellMonster.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				textPaneMonsterStatistics.setText(listSellMonster.getSelectedValue().shopString());
			}
		});
		for (int i=0; i < gameEnviro.getUserMonsterList().size(); i++) {
	    	listMonster.addElement(gameEnviro.getUserMonsterList().get(i));
	    }
		listSellMonster.setVisibleRowCount(15);
		listSellMonster.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listSellMonster.setBounds(30, 180, 230, 140);
		listSellMonster.setCellRenderer(new NameRenderer());
		frmShopSellMonster.getContentPane().add(listSellMonster);
		
		JScrollPane scrollPaneBuyMonster = new JScrollPane(listSellMonster);
		scrollPaneBuyMonster.setBounds(30, 180, 230, 140);
		frmShopSellMonster.getContentPane().add(scrollPaneBuyMonster);
		
		JLabel lblBuyMonsterTitle = new JLabel("Available Monsters");
		lblBuyMonsterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyMonsterTitle.setFont(new Font("Verdana", Font.BOLD, 18));
		lblBuyMonsterTitle.setBounds(50, 89, 450, 35);
		frmShopSellMonster.getContentPane().add(lblBuyMonsterTitle);
		
		JButton btnBuyMonster = new JButton("Sell Monster");
		btnBuyMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listSellMonster.getSelectedValue() == null)) {
					gameEnviro.getUserMonsterList().remove(listSellMonster.getSelectedValue());
					gameEnviro.addUserGoldAmount(listSellMonster.getSelectedValue().getMonsterSellPrice());
					ShopSellMonsterScreen newSellMonster = new ShopSellMonsterScreen(gameEnviro);
					frmShopSellMonster.dispose();
					newSellMonster.ShopSellMonster();
					
				} else {
					lblBuyMonsterCantSell.setVisible(true);
					lblSelectAMonster.setVisible(true);
				}
			}
		});
		btnBuyMonster.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyMonster.setBounds(30, 360, 230, 45);
		frmShopSellMonster.getContentPane().add(btnBuyMonster);
		
		lblBuyMonsterCantSell = new JLabel("Cannot Sell");
		lblBuyMonsterCantSell.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyMonsterCantSell.setForeground(Color.RED);
		lblBuyMonsterCantSell.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyMonsterCantSell.setBounds(50, 430, 450, 25);
		lblBuyMonsterCantSell.setVisible(false);
		frmShopSellMonster.getContentPane().add(lblBuyMonsterCantSell);
		
		JButton btnBuyMonsterReturn = new JButton("Return");
		btnBuyMonsterReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitShopScreen newVisitShop = new VisitShopScreen(gameEnviro);
				frmShopSellMonster.dispose();
    			newVisitShop.VisitShop();
			}
		});
		btnBuyMonsterReturn.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyMonsterReturn.setBounds(290, 360, 230, 45);
		frmShopSellMonster.getContentPane().add(btnBuyMonsterReturn);

	}
}
