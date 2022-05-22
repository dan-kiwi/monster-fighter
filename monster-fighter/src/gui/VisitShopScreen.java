package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import mainenviro.GameEnviro;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * The Class ViewInventoryScreen. This class shows the Shop that the user can buy items from
 */
public class VisitShopScreen {

	private JFrame frmVisitShop;
	private static GameEnviro gameEnviro;

	/**
	 * Create the application.
	 * @param newGame, a new game environment
	 */
	public VisitShopScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		initialize();
	}
	
	/**
	 * Method to call back the Visit Shop from the Main Menu Object
	 */
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
		frmVisitShop.setBounds(100, 100, 560, 575);
		frmVisitShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVisitShop.getContentPane().setLayout(null);
		
		JLabel lblVisitShopTitle = new JLabel("Welcome to the Shop");
		lblVisitShopTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisitShopTitle.setFont(new Font("Verdana", Font.BOLD, 18));
		lblVisitShopTitle.setBounds(0, 30, 550, 40);
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
		btnVisitShopBuyMonster.setBounds(49, 90, 450, 60);
		frmVisitShop.getContentPane().add(btnVisitShopBuyMonster);
		
		JButton btnVisitShopBuyItems = new JButton("Buy Items");
		btnVisitShopBuyItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopBuyItemScreen newBuyItem = new ShopBuyItemScreen(gameEnviro);
				frmVisitShop.dispose();
				newBuyItem.ShopBuyItem();
			}
		});
		btnVisitShopBuyItems.setFont(new Font("Verdana", Font.BOLD, 15));
		btnVisitShopBuyItems.setBounds(49, 170, 450, 60);
		frmVisitShop.getContentPane().add(btnVisitShopBuyItems);
		
		JButton btnVisitShopSellMonster = new JButton("Sell Monsters");
		btnVisitShopSellMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopSellMonsterScreen newSellMonster = new ShopSellMonsterScreen(gameEnviro);
				frmVisitShop.dispose();
				newSellMonster.ShopSellMonster();
			}
		});
		btnVisitShopSellMonster.setFont(new Font("Verdana", Font.BOLD, 15));
		btnVisitShopSellMonster.setBounds(49, 250, 450, 60);
		frmVisitShop.getContentPane().add(btnVisitShopSellMonster);
		
		JButton btnVisitShopSellItems = new JButton("Sell Items");
		btnVisitShopSellItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopSellItemScreen newSellItem = new ShopSellItemScreen(gameEnviro);
				frmVisitShop.dispose();
				newSellItem.ShopSellItem();
			}
		});
		btnVisitShopSellItems.setFont(new Font("Verdana", Font.BOLD, 15));
		btnVisitShopSellItems.setBounds(50, 330, 450, 60);
		frmVisitShop.getContentPane().add(btnVisitShopSellItems);
		
		JButton btnVisitShopReturn = new JButton("Return");
		btnVisitShopReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuScreen newMainMenu = new MainMenuScreen(gameEnviro);
				frmVisitShop.dispose();
    			newMainMenu.MainMenu();
			}
		});
		btnVisitShopReturn.setFont(new Font("Verdana", Font.BOLD, 15));
		btnVisitShopReturn.setBounds(100, 430, 350, 60);
		frmVisitShop.getContentPane().add(btnVisitShopReturn);
		
	}
}
