package gui;

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
 * The Class ShopSellItemScreen. 
 * The gui representation of selling items within the shop. Cannot be lauched directly
 */
public class ShopSellItemScreen {

	private JFrame frmShopSellItem;
	private static GameEnviro gameEnviro;
	private JList<Items> listSellItem;

	/**
	 * Create the application.
	 * @param newGame, new game environment
	 */
	public ShopSellItemScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		initialize();
	}
	
	/**
	 * Method to call back the ShopSellItemScreen from other classes
	 */
	public void ShopSellItem() {
		ShopSellItemScreen sellItem = new ShopSellItemScreen(gameEnviro);
		sellItem.frmShopSellItem.setVisible(true);
	}

	private void initialize() {
		frmShopSellItem = new JFrame();
		frmShopSellItem.setTitle("Buy Items");
		frmShopSellItem.setBounds(100, 100, 560, 575);
		frmShopSellItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShopSellItem.getContentPane().setLayout(null);
		
		JLabel lblBuyItemsGold = new JLabel("Player Gold: " + gameEnviro.getUserGoldAmount());
		lblBuyItemsGold.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyItemsGold.setBounds(69, 15, 146, 14);
		frmShopSellItem.getContentPane().add(lblBuyItemsGold);
		
		JLabel lblBuyItemsCurrentDay = new JLabel("Current Day: " + gameEnviro.getGameDay());
		lblBuyItemsCurrentDay.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyItemsCurrentDay.setBounds(68, 33, 146, 17);
		frmShopSellItem.getContentPane().add(lblBuyItemsCurrentDay);
		
		JLabel lblBuyItemsItemsCount = new JLabel("Current Items: 0");
		lblBuyItemsItemsCount.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyItemsItemsCount.setBounds(67, 54, 156, 14);
		frmShopSellItem.getContentPane().add(lblBuyItemsItemsCount);
		
		JTextPane textPaneItemsStatistics = new JTextPane();
		textPaneItemsStatistics.setBounds(290, 180, 230, 140);
		frmShopSellItem.getContentPane().add(textPaneItemsStatistics);
		
		JLabel lblSelectItem = new JLabel("(You must select an item)");
		lblSelectItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectItem.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSelectItem.setBounds(50, 450, 450, 22);
		lblSelectItem.setVisible(false);
		frmShopSellItem.getContentPane().add(lblSelectItem);
		
		DefaultListModel<Items> listItems = new DefaultListModel<Items>();
		listSellItem = new JList<Items>(listItems);
		listSellItem.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				textPaneItemsStatistics.setText(listSellItem.getSelectedValue().shopString());
			}
		});
		for (int i=0; i < gameEnviro.getUserItemList().size(); i++) {
			listItems.addElement(gameEnviro.getUserItemList().get(i));
	    }
		listSellItem.setVisibleRowCount(15);
		listSellItem.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listSellItem.setBounds(30, 180, 230, 140);
		listSellItem.setCellRenderer(new NameRenderer());
		frmShopSellItem.getContentPane().add(listSellItem);
		
		JScrollPane scrollPaneBuyItems = new JScrollPane(listSellItem);
		scrollPaneBuyItems.setBounds(30, 180, 230, 140);
		frmShopSellItem.getContentPane().add(scrollPaneBuyItems);
		
		JLabel lblBuyItemsTitle = new JLabel("Available Items");
		lblBuyItemsTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyItemsTitle.setFont(new Font("Verdana", Font.BOLD, 18));
		lblBuyItemsTitle.setBounds(50, 89, 450, 35);
		frmShopSellItem.getContentPane().add(lblBuyItemsTitle);
		
		JButton btnBuyItems = new JButton("Sell Items");
		btnBuyItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listSellItem.getSelectedValue() == null)) {						
					gameEnviro.getUserItemList().remove(listSellItem.getSelectedValue());
					gameEnviro.addUserGoldAmount(listSellItem.getSelectedValue().getItemSellPrice());
					ShopSellItemScreen newSellItem = new ShopSellItemScreen(gameEnviro);
					frmShopSellItem.dispose();
					newSellItem.ShopSellItem();
				} else {
					lblSelectItem.setVisible(true);
				}
			}
		});
		btnBuyItems.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyItems.setBounds(30, 360, 230, 45);
		frmShopSellItem.getContentPane().add(btnBuyItems);
		
		JButton btnBuyItemsReturn = new JButton("Return");
		btnBuyItemsReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitShopScreen newVisitShop = new VisitShopScreen(gameEnviro);
				frmShopSellItem.dispose();
    			newVisitShop.VisitShop();
			}
		});
		btnBuyItemsReturn.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyItemsReturn.setBounds(290, 360, 230, 45);
		frmShopSellItem.getContentPane().add(btnBuyItemsReturn);

	}
}
