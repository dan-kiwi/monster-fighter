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
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import items.Items;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VisitShopScreen {

	private JFrame frmVisitShop;
	private static GameEnviro gameEnviro;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisitShopScreen window = new VisitShopScreen(null);
					window.frmVisitShop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisitShopScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		initialize();
	}
	
	public void VisitShop() {
		VisitShopScreen visitShop = new VisitShopScreen(gameEnviro);
		visitShop.frmVisitShop.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVisitShop = new JFrame();
		frmVisitShop.setTitle("Visit The Shop");
		frmVisitShop.setBounds(100, 100, 350, 400);
		frmVisitShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVisitShop.getContentPane().setLayout(null);
		
		JLabel lblVisitShopTitle = new JLabel("Welcome to the Shop");
		lblVisitShopTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisitShopTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblVisitShopTitle.setBounds(61, 11, 207, 28);
		frmVisitShop.getContentPane().add(lblVisitShopTitle);
		
		JButton btnVisitShopBuyMonster = new JButton("Buy Monsters");
		btnVisitShopBuyMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopBuyMonsterScreen newBuyMonster = new ShopBuyMonsterScreen(gameEnviro);
				frmVisitShop.dispose();
				newBuyMonster.ShopBuyMonster();
			}
		});
		btnVisitShopBuyMonster.setFont(new Font("Verdana", Font.BOLD, 15));
		btnVisitShopBuyMonster.setBounds(49, 63, 219, 49);
		frmVisitShop.getContentPane().add(btnVisitShopBuyMonster);
		
		JButton btnVisitShopBuyItems = new JButton("Buy Items");
		btnVisitShopBuyItems.setFont(new Font("Verdana", Font.BOLD, 15));
		btnVisitShopBuyItems.setBounds(49, 123, 219, 49);
		frmVisitShop.getContentPane().add(btnVisitShopBuyItems);
		
		JButton btnVisitShopSellMonsterItems = new JButton("Sell Monsters or Items");
		btnVisitShopSellMonsterItems.setFont(new Font("Verdana", Font.BOLD, 15));
		btnVisitShopSellMonsterItems.setBounds(49, 183, 219, 49);
		frmVisitShop.getContentPane().add(btnVisitShopSellMonsterItems);
		
		JButton btnVisitShopReturn = new JButton("Return");
		btnVisitShopReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuScreen newMainMenu = new MainMenuScreen(gameEnviro);
				frmVisitShop.dispose();
    			newMainMenu.MainMenu();
			}
		});
		btnVisitShopReturn.setFont(new Font("Verdana", Font.BOLD, 15));
		btnVisitShopReturn.setBounds(79, 301, 163, 49);
		frmVisitShop.getContentPane().add(btnVisitShopReturn);
		
		
	}

}
