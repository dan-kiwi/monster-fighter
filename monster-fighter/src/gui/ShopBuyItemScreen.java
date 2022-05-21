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

import items.Items;
import mainenviro.GameEnviro;

/**
 * The Class ShopBuyItemScreen. This is the class shows the Item's available to buy in a GUI form
 */
public class ShopBuyItemScreen {

	private JFrame frmShopBuyItem;
	private static GameEnviro gameEnviro;
	private JList<Items> listBuyItem;
	private JLabel lblBuyItemCantBuy;
	private JLabel lblBuyItemGoldInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopBuyItemScreen window = new ShopBuyItemScreen(null);
					window.frmShopBuyItem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ShopBuyItemScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		initialize();
	}
	
	/**
	 * Method to call back the ShopBuyItemScreen from other classes
	 */
	public void ShopBuyItem() {
		ShopBuyItemScreen buyItem = new ShopBuyItemScreen(gameEnviro);
		buyItem.frmShopBuyItem.setVisible(true);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShopBuyItem = new JFrame();
		frmShopBuyItem.setTitle("Buy Items");
		frmShopBuyItem.setBounds(100, 100, 560, 575);
		frmShopBuyItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShopBuyItem.getContentPane().setLayout(null);
		
		JLabel lblBuyItemsGold = new JLabel("Player Gold: " + gameEnviro.getUserGoldAmount());
		lblBuyItemsGold.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyItemsGold.setBounds(69, 15, 146, 14);
		frmShopBuyItem.getContentPane().add(lblBuyItemsGold);
		
		JLabel lblBuyItemsCurrentDay = new JLabel("Current Day: " + gameEnviro.getGameDay());
		lblBuyItemsCurrentDay.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyItemsCurrentDay.setBounds(68, 33, 146, 17);
		frmShopBuyItem.getContentPane().add(lblBuyItemsCurrentDay);
		
		JLabel lblBuyItemsItemsCount = new JLabel("Current Itemss: " + gameEnviro.getUserItemList().size());
		lblBuyItemsItemsCount.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyItemsItemsCount.setBounds(67, 54, 156, 14);
		frmShopBuyItem.getContentPane().add(lblBuyItemsItemsCount);
		
		JTextPane textPaneItemsStatistics = new JTextPane();
		textPaneItemsStatistics.setBounds(290, 180, 230, 140);
		frmShopBuyItem.getContentPane().add(textPaneItemsStatistics);
		
		DefaultListModel<Items> listItems = new DefaultListModel<Items>();
		listBuyItem = new JList<Items>(listItems);
		listBuyItem.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				textPaneItemsStatistics.setText(listBuyItem.getSelectedValue().shopString());
			}
		});
		for (int i=0; i < gameEnviro.getUserGameShop().getShopItemList().size(); i++) {
	    	listItems.addElement(gameEnviro.getUserGameShop().getShopItemList().get(i));
	    }
		listBuyItem.setVisibleRowCount(15);
		listBuyItem.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listBuyItem.setBounds(30, 180, 230, 140);
		listBuyItem.setCellRenderer(new NameRenderer());
		frmShopBuyItem.getContentPane().add(listBuyItem);
		
		JScrollPane scrollPaneBuyItems = new JScrollPane(listBuyItem);
		scrollPaneBuyItems.setBounds(30, 180, 230, 140);
		frmShopBuyItem.getContentPane().add(scrollPaneBuyItems);
		
		JLabel lblBuyItemsTitle = new JLabel("Available Itemss");
		lblBuyItemsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyItemsTitle.setFont(new Font("Verdana", Font.BOLD, 18));
		lblBuyItemsTitle.setBounds(50, 89, 450, 35);
		frmShopBuyItem.getContentPane().add(lblBuyItemsTitle);
		
		JButton btnBuyItems = new JButton("Buy Items");
		btnBuyItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listBuyItem.getSelectedValue() == null)) {
					if (!(listBuyItem.getSelectedValue().getItemBuyPrice() > 
					gameEnviro.getUserGoldAmount())) {
						
						gameEnviro.addItem(listBuyItem.getSelectedValue());
						gameEnviro.getUserGameShop().getShopItemList().remove(listBuyItem.getSelectedValue());
						gameEnviro.addUserGoldAmount(-listBuyItem.getSelectedValue().getItemBuyPrice());
						ShopBuyItemScreen newBuyItems = new ShopBuyItemScreen(gameEnviro);
						frmShopBuyItem.dispose();
						newBuyItems.ShopBuyItem();
						
					} else {
						lblBuyItemCantBuy.setVisible(true);
						lblBuyItemGoldInfo.setVisible(true);
					}
				}
			}
		});
		btnBuyItems.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyItems.setBounds(30, 360, 230, 45);
		frmShopBuyItem.getContentPane().add(btnBuyItems);
		
		lblBuyItemCantBuy = new JLabel("Cannot Purchase");
		lblBuyItemCantBuy.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyItemCantBuy.setForeground(Color.RED);
		lblBuyItemCantBuy.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyItemCantBuy.setBounds(50, 430, 450, 25);
		lblBuyItemCantBuy.setVisible(false);
		frmShopBuyItem.getContentPane().add(lblBuyItemCantBuy);
		
		lblBuyItemGoldInfo = new JLabel("(You must have enough gold)");
		lblBuyItemGoldInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyItemGoldInfo.setForeground(Color.BLACK);
		lblBuyItemGoldInfo.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBuyItemGoldInfo.setBounds(50, 450, 450, 22);
		lblBuyItemGoldInfo.setVisible(false);
		frmShopBuyItem.getContentPane().add(lblBuyItemGoldInfo);
		
		JButton btnBuyItemsReturn = new JButton("Return");
		btnBuyItemsReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitShopScreen newVisitShop = new VisitShopScreen(gameEnviro);
				frmShopBuyItem.dispose();
    			newVisitShop.VisitShop();
			}
		});
		btnBuyItemsReturn.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyItemsReturn.setBounds(290, 360, 230, 45);
		frmShopBuyItem.getContentPane().add(btnBuyItemsReturn);
		
	}
}
