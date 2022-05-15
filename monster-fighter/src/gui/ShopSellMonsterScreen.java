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
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import mainenviro.GameEnviro;
import monster.Monster;

public class ShopSellMonsterScreen {

	private JFrame frmShopSellMonster;
	private static GameEnviro gameEnviro;
	private JList<Monster> listSellMonster;
	private JList<String> listSellMonsterPrice;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopSellMonsterScreen window = new ShopSellMonsterScreen(null);
					window.frmShopSellMonster.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
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
		frmShopSellMonster.setTitle("Sell Monsters");
		frmShopSellMonster.setBounds(100, 100, 414, 464);
		frmShopSellMonster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShopSellMonster.getContentPane().setLayout(null);
		
		JLabel lblSellMonsterGold = new JLabel("Player Gold: " + gameEnviro.getUserGoldAmount());
		lblSellMonsterGold.setFont(new Font("Verdana", Font.BOLD, 13));
		lblSellMonsterGold.setBounds(69, 15, 146, 14);
		frmShopSellMonster.getContentPane().add(lblSellMonsterGold);
		
		JLabel lblSellMonsterCurrentDay = new JLabel("Current Day: " + gameEnviro.getGameDay());
		lblSellMonsterCurrentDay.setFont(new Font("Verdana", Font.BOLD, 13));
		lblSellMonsterCurrentDay.setBounds(68, 33, 146, 17);
		frmShopSellMonster.getContentPane().add(lblSellMonsterCurrentDay);
		
		JLabel lblSellMonsterMonsterCount = new JLabel("Current Monsters: " + gameEnviro.getUserMonsterList().size());
		lblSellMonsterMonsterCount.setFont(new Font("Verdana", Font.BOLD, 13));
		lblSellMonsterMonsterCount.setBounds(67, 54, 156, 14);
		frmShopSellMonster.getContentPane().add(lblSellMonsterMonsterCount);
		
		DefaultListModel<Monster> listMonster = new DefaultListModel<Monster>();
		listSellMonster = new JList<Monster>(listMonster);
		for (int i=0; i < gameEnviro.getUserMonsterList().size(); i++) {
	    	listMonster.addElement(gameEnviro.getUserMonsterList().get(i));
	    }
		listSellMonster.setVisibleRowCount(15);
		listSellMonster.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listSellMonster.setBounds(33, 128, 216, 89);
		frmShopSellMonster.getContentPane().add(listSellMonster);
		
		JScrollPane scrollPaneSellMonster = new JScrollPane(listSellMonster);
		scrollPaneSellMonster.setBounds(33, 128, 216, 89);
		frmShopSellMonster.getContentPane().add(scrollPaneSellMonster);
		
		JLabel lblSellMonsterTitle = new JLabel("Monsters to Sell");
		lblSellMonsterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSellMonsterTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblSellMonsterTitle.setBounds(44, 89, 178, 28);
		frmShopSellMonster.getContentPane().add(lblSellMonsterTitle);
		
		JButton btnSellMonster = new JButton("Sell Monster");
		btnSellMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listSellMonster.getSelectedValue() == null)) {
						
					gameEnviro.getUserMonsterList().remove(listSellMonster.getSelectedValue());
					gameEnviro.addUserGoldAmount(listSellMonster.getSelectedValue().getMonsterSellPrice());
					ShopSellMonsterScreen newSellMonster = new ShopSellMonsterScreen(gameEnviro);
					frmShopSellMonster.dispose();
					newSellMonster.ShopSellMonster();
					
				}
			}
		});
		btnSellMonster.setFont(new Font("Verdana", Font.BOLD, 13));
		btnSellMonster.setBounds(95, 243, 154, 44);
		frmShopSellMonster.getContentPane().add(btnSellMonster);
		
		
		JButton btnSellMonsterReturn = new JButton("Return");
		btnSellMonsterReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitShopScreen newVisitShop = new VisitShopScreen(gameEnviro);
				frmShopSellMonster.dispose();
    			newVisitShop.VisitShop();
			}
		});
		btnSellMonsterReturn.setFont(new Font("Verdana", Font.BOLD, 13));
		btnSellMonsterReturn.setBounds(95, 361, 154, 44);
		frmShopSellMonster.getContentPane().add(btnSellMonsterReturn);
		
		DefaultListModel<String> listMonsterPrice = new DefaultListModel<String>();
		listSellMonsterPrice = new JList<String>(listMonsterPrice);
		for (int i=0; i < gameEnviro.getUserMonsterList().size(); i++) {
			listMonsterPrice.addElement(gameEnviro.getUserMonsterList().get(i).getMonsterName() 
					+ ": " + gameEnviro.getUserMonsterList().get(i).getMonsterSellPrice() + " Gold");
	    }
		listSellMonsterPrice.setVisibleRowCount(15);
		listSellMonsterPrice.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listSellMonsterPrice.setBounds(259, 128, 115, 89);
		frmShopSellMonster.getContentPane().add(listSellMonsterPrice);
		
		JScrollPane scrollPaneSellMonsterPrice = new JScrollPane(listSellMonsterPrice);
		scrollPaneSellMonsterPrice.setBounds(259, 128, 115, 89);
		frmShopSellMonster.getContentPane().add(scrollPaneSellMonsterPrice);
		
		JLabel lblSellMonsterPriceTitle = new JLabel("Sell Price");
		lblSellMonsterPriceTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSellMonsterPriceTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblSellMonsterPriceTitle.setBounds(261, 89, 113, 28);
		frmShopSellMonster.getContentPane().add(lblSellMonsterPriceTitle);
		
	}
}
