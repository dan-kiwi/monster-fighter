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

import items.Items;
import mainenviro.GameEnviro;
import monster.Monster;

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
		frmShopBuyItem.setBounds(100, 100, 366, 464);
		frmShopBuyItem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShopBuyItem.getContentPane().setLayout(null);
		
		JLabel lblBuyItemGold = new JLabel("Player Gold: " + gameEnviro.getUserGoldAmount());
		lblBuyItemGold.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyItemGold.setBounds(69, 15, 146, 14);
		frmShopBuyItem.getContentPane().add(lblBuyItemGold);
		
		JLabel lblBuyItemCurrentDay = new JLabel("Current Day: " + gameEnviro.getGameDay());
		lblBuyItemCurrentDay.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyItemCurrentDay.setBounds(68, 33, 146, 17);
		frmShopBuyItem.getContentPane().add(lblBuyItemCurrentDay);
		
		DefaultListModel<Items> listItem = new DefaultListModel<Items>();
		listBuyItem = new JList<Items>(listItem);
		for (int i=0; i < gameEnviro.getUserGameShop().getShopItemList().size(); i++) {
			listItem.addElement(gameEnviro.getUserGameShop().getShopItemList().get(i));
	    }
		listBuyItem.setVisibleRowCount(15);
		listBuyItem.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listBuyItem.setBounds(33, 128, 216, 89);
		frmShopBuyItem.getContentPane().add(listBuyItem);
		
		JScrollPane scrollPaneBuyMonster = new JScrollPane(listBuyItem);
		scrollPaneBuyMonster.setBounds(33, 128, 216, 89);
		frmShopBuyItem.getContentPane().add(scrollPaneBuyMonster);
		
		JLabel lblBuyItemTitle = new JLabel("Available Itemss");
		lblBuyItemTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyItemTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblBuyItemTitle.setBounds(77, 89, 201, 28);
		frmShopBuyItem.getContentPane().add(lblBuyItemTitle);
		
		JButton btnBuyItem = new JButton("Buy Item");
		btnBuyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listBuyItem.getSelectedValue() == null)) {
					if (!(listBuyItem.getSelectedValue().getItemBuyPrice() > gameEnviro.getUserGoldAmount())) {
						
						gameEnviro.addItem(listBuyItem.getSelectedValue());
						gameEnviro.getUserGameShop().getShopItemList().remove(listBuyItem.getSelectedValue());
						gameEnviro.addUserGoldAmount(-listBuyItem.getSelectedValue().getItemBuyPrice());
						ShopBuyItemScreen newBuyItem = new ShopBuyItemScreen(gameEnviro);
						frmShopBuyItem.dispose();
						newBuyItem.ShopBuyItem();
						
					} else {
						lblBuyItemCantBuy.setVisible(true);
						lblBuyItemGoldInfo.setVisible(true);
					}
				}
			}
		});
		btnBuyItem.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyItem.setBounds(95, 243, 154, 44);
		frmShopBuyItem.getContentPane().add(btnBuyItem);
		
		lblBuyItemCantBuy = new JLabel("Cannot Purchase");
		lblBuyItemCantBuy.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyItemCantBuy.setForeground(Color.RED);
		lblBuyItemCantBuy.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyItemCantBuy.setBounds(95, 289, 154, 22);
		lblBuyItemCantBuy.setVisible(false);
		frmShopBuyItem.getContentPane().add(lblBuyItemCantBuy);
		
		lblBuyItemGoldInfo = new JLabel("(You must have enough gold)");
		lblBuyItemGoldInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyItemGoldInfo.setForeground(Color.BLACK);
		lblBuyItemGoldInfo.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBuyItemGoldInfo.setBounds(67, 306, 216, 22);
		lblBuyItemGoldInfo.setVisible(false);
		frmShopBuyItem.getContentPane().add(lblBuyItemGoldInfo);
		
		JButton btnBuyItemReturn = new JButton("Return");
		btnBuyItemReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisitShopScreen newVisitShop = new VisitShopScreen(gameEnviro);
				frmShopBuyItem.dispose();
    			newVisitShop.VisitShop();
			}
		});
		btnBuyItemReturn.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyItemReturn.setBounds(95, 361, 154, 44);
		frmShopBuyItem.getContentPane().add(btnBuyItemReturn);
		
		if (gameEnviro.getUserGameShop().getShopItemList().size() >= 1) {
			
			JLabel lblVisitShopCostTitle = new JLabel("Cost");
			lblVisitShopCostTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblVisitShopCostTitle.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblVisitShopCostTitle.setBounds(254, 100, 46, 28);
			frmShopBuyItem.getContentPane().add(lblVisitShopCostTitle);
			
			JLabel lblVisitShopCostItem1 = new JLabel(gameEnviro.getUserGameShop().getShopItemList()
					.get(0).getItemBuyPrice() + " Gold");
			lblVisitShopCostItem1.setHorizontalAlignment(SwingConstants.CENTER);
			lblVisitShopCostItem1.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblVisitShopCostItem1.setBounds(252, 132, 68, 18);
			frmShopBuyItem.getContentPane().add(lblVisitShopCostItem1);
		}
		
		if (gameEnviro.getUserGameShop().getShopItemList().size() >= 2) {
			
			JLabel lblVisitShopCostItem2 = new JLabel(gameEnviro.getUserGameShop().getShopItemList()
					.get(1).getItemBuyPrice() + " Gold");
			lblVisitShopCostItem2.setHorizontalAlignment(SwingConstants.CENTER);
			lblVisitShopCostItem2.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblVisitShopCostItem2.setBounds(252, 149, 68, 18);
			frmShopBuyItem.getContentPane().add(lblVisitShopCostItem2);
		}
		
		if (gameEnviro.getUserGameShop().getShopItemList().size() >= 3) {
			
			JLabel lblVisitShopCostItem3 = new JLabel(gameEnviro.getUserGameShop().getShopItemList()
					.get(2).getItemBuyPrice() + " Gold");
			lblVisitShopCostItem3.setHorizontalAlignment(SwingConstants.CENTER);
			lblVisitShopCostItem3.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblVisitShopCostItem3.setBounds(252, 166, 68, 18);
			frmShopBuyItem.getContentPane().add(lblVisitShopCostItem3);
		}
	}

}
