package gui;

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

import items.Items;
import mainenviro.GameEnviro;
import monster.Monster;

public class ShopSellItemScreen {

	private JFrame frmShopSellItem;
	private static GameEnviro gameEnviro;
	private JList<Items> listSellItem;
	private JList<String> listSellItemPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopSellItemScreen window = new ShopSellItemScreen(null);
					window.frmShopSellItem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShopSellItem = new JFrame();
		frmShopSellItem.setTitle("Sell Items");
		frmShopSellItem.setBounds(100, 100, 414, 464);
		frmShopSellItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShopSellItem.getContentPane().setLayout(null);
		
		JLabel lblSellItemGold = new JLabel("Player Gold: " + gameEnviro.getUserGoldAmount());
		lblSellItemGold.setFont(new Font("Verdana", Font.BOLD, 13));
		lblSellItemGold.setBounds(69, 15, 146, 14);
		frmShopSellItem.getContentPane().add(lblSellItemGold);
		
		JLabel lblSellItemCurrentDay = new JLabel("Current Day: " + gameEnviro.getGameDay());
		lblSellItemCurrentDay.setFont(new Font("Verdana", Font.BOLD, 13));
		lblSellItemCurrentDay.setBounds(68, 33, 146, 17);
		frmShopSellItem.getContentPane().add(lblSellItemCurrentDay);
		
		DefaultListModel<Items> listItem = new DefaultListModel<Items>();
		listSellItem = new JList<Items>(listItem);
		for (int i=0; i < gameEnviro.getUserItemList().size(); i++) {
			listItem.addElement(gameEnviro.getUserItemList().get(i));
	    }
		listSellItem.setVisibleRowCount(15);
		listSellItem.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listSellItem.setBounds(33, 128, 216, 89);
		frmShopSellItem.getContentPane().add(listSellItem);
		
		JScrollPane scrollPaneSellItem = new JScrollPane(listSellItem);
		scrollPaneSellItem.setBounds(33, 128, 216, 89);
		frmShopSellItem.getContentPane().add(scrollPaneSellItem);
		
		JLabel lblSellItemTitle = new JLabel("Items to Sell");
		lblSellItemTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSellItemTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblSellItemTitle.setBounds(44, 89, 178, 28);
		frmShopSellItem.getContentPane().add(lblSellItemTitle);
		
		JButton btnSellItem = new JButton("Sell Item");
		btnSellItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listSellItem.getSelectedValue() == null)) {
						
					gameEnviro.getUserItemList().remove(listSellItem.getSelectedValue());
					gameEnviro.addUserGoldAmount(listSellItem.getSelectedValue().getItemSellPrice());
					ShopSellItemScreen newSellItem = new ShopSellItemScreen(gameEnviro);
					frmShopSellItem.dispose();
					newSellItem.ShopSellItem();
					
				}
			}
		});
		btnSellItem.setFont(new Font("Verdana", Font.BOLD, 13));
		btnSellItem.setBounds(95, 243, 154, 44);
		frmShopSellItem.getContentPane().add(btnSellItem);
		
		
		JButton btnSellItemReturn = new JButton("Return");
		btnSellItemReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitShopScreen newVisitShop = new VisitShopScreen(gameEnviro);
				frmShopSellItem.dispose();
    			newVisitShop.VisitShop();
			}
		});
		btnSellItemReturn.setFont(new Font("Verdana", Font.BOLD, 13));
		btnSellItemReturn.setBounds(95, 361, 154, 44);
		frmShopSellItem.getContentPane().add(btnSellItemReturn);
		
		DefaultListModel<String> listItemPrice = new DefaultListModel<String>();
		listSellItemPrice = new JList<String>(listItemPrice);
		for (int i=0; i < gameEnviro.getUserItemList().size(); i++) {
			listItemPrice.addElement(gameEnviro.getUserItemList().get(i).getItemName() 
					+ ": " + gameEnviro.getUserItemList().get(i).getItemSellPrice() + " Gold");
	    }
		listSellItemPrice.setVisibleRowCount(15);
		listSellItemPrice.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listSellItemPrice.setBounds(259, 128, 115, 89);
		frmShopSellItem.getContentPane().add(listSellItemPrice);
		
		JScrollPane scrollPaneSellItemPrice = new JScrollPane(listSellItemPrice);
		scrollPaneSellItemPrice.setBounds(259, 128, 115, 89);
		frmShopSellItem.getContentPane().add(scrollPaneSellItemPrice);
		
		JLabel lblSellItemPriceTitle = new JLabel("Sell Price");
		lblSellItemPriceTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSellItemPriceTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblSellItemPriceTitle.setBounds(261, 89, 113, 28);
		frmShopSellItem.getContentPane().add(lblSellItemPriceTitle);
	}

}
