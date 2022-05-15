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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopBuyMonsterScreen window = new ShopBuyMonsterScreen(null);
					window.frmShopBuyMonster.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
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
		frmShopBuyMonster.setTitle("Buy Monsters");
		frmShopBuyMonster.setBounds(100, 100, 366, 464);
		frmShopBuyMonster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShopBuyMonster.getContentPane().setLayout(null);
		
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
		
		DefaultListModel<Monster> listMonster = new DefaultListModel<Monster>();
		listBuyMonster = new JList<Monster>(listMonster);
		for (int i=0; i < gameEnviro.getUserGameShop().getShopMonsterList().size(); i++) {
	    	listMonster.addElement(gameEnviro.getUserGameShop().getShopMonsterList().get(i));
	    }
		listBuyMonster.setVisibleRowCount(15);
		listBuyMonster.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listBuyMonster.setBounds(33, 128, 216, 89);
		frmShopBuyMonster.getContentPane().add(listBuyMonster);
		
		JScrollPane scrollPaneBuyMonster = new JScrollPane(listBuyMonster);
		scrollPaneBuyMonster.setBounds(33, 128, 216, 89);
		frmShopBuyMonster.getContentPane().add(scrollPaneBuyMonster);
		
		JLabel lblBuyMonsterTitle = new JLabel("Available Monsters");
		lblBuyMonsterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyMonsterTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblBuyMonsterTitle.setBounds(77, 89, 201, 28);
		frmShopBuyMonster.getContentPane().add(lblBuyMonsterTitle);
		
		JButton btnBuyMonster = new JButton("Buy Monster");
		btnBuyMonster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listBuyMonster.getSelectedValue() == null)) {
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
						lblBuyMonsterGoldInfo.setVisible(true);
						lblBuyMonsterSizeInfo.setVisible(true);
					}
				}
			}
		});
		btnBuyMonster.setFont(new Font("Verdana", Font.BOLD, 13));
		btnBuyMonster.setBounds(95, 243, 154, 44);
		frmShopBuyMonster.getContentPane().add(btnBuyMonster);
		
		lblBuyMonsterCantBuy = new JLabel("Cannot Purchase");
		lblBuyMonsterCantBuy.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyMonsterCantBuy.setForeground(Color.RED);
		lblBuyMonsterCantBuy.setFont(new Font("Verdana", Font.BOLD, 13));
		lblBuyMonsterCantBuy.setBounds(95, 289, 154, 22);
		lblBuyMonsterCantBuy.setVisible(false);
		frmShopBuyMonster.getContentPane().add(lblBuyMonsterCantBuy);
		
		lblBuyMonsterGoldInfo = new JLabel("(You must have enough gold)");
		lblBuyMonsterGoldInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyMonsterGoldInfo.setForeground(Color.BLACK);
		lblBuyMonsterGoldInfo.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBuyMonsterGoldInfo.setBounds(67, 306, 216, 22);
		lblBuyMonsterGoldInfo.setVisible(false);
		frmShopBuyMonster.getContentPane().add(lblBuyMonsterGoldInfo);
		
		lblBuyMonsterSizeInfo = new JLabel("(You can only have 4 Monsters)");
		lblBuyMonsterSizeInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuyMonsterSizeInfo.setForeground(Color.BLACK);
		lblBuyMonsterSizeInfo.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblBuyMonsterSizeInfo.setBounds(64, 328, 216, 22);
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
		btnBuyMonsterReturn.setBounds(95, 361, 154, 44);
		frmShopBuyMonster.getContentPane().add(btnBuyMonsterReturn);
		
		if (gameEnviro.getUserGameShop().getShopMonsterList().size() >= 1) {
			
			JLabel lblVisitShopCostTitle = new JLabel("Cost");
			lblVisitShopCostTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblVisitShopCostTitle.setFont(new Font("Verdana", Font.PLAIN, 14));
			lblVisitShopCostTitle.setBounds(254, 100, 46, 28);
			frmShopBuyMonster.getContentPane().add(lblVisitShopCostTitle);
			
			JLabel lblVisitShopCostMon1 = new JLabel(gameEnviro.getUserGameShop().getShopMonsterList()
					.get(0).getMonsterBuyPrice() + " Gold");
			lblVisitShopCostMon1.setHorizontalAlignment(SwingConstants.CENTER);
			lblVisitShopCostMon1.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblVisitShopCostMon1.setBounds(252, 132, 68, 18);
			frmShopBuyMonster.getContentPane().add(lblVisitShopCostMon1);
		}
		
		if (gameEnviro.getUserGameShop().getShopMonsterList().size() >= 2) {
			
			JLabel lblVisitShopCostMon2 = new JLabel(gameEnviro.getUserGameShop().getShopMonsterList()
					.get(1).getMonsterBuyPrice() + " Gold");
			lblVisitShopCostMon2.setHorizontalAlignment(SwingConstants.CENTER);
			lblVisitShopCostMon2.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblVisitShopCostMon2.setBounds(252, 149, 68, 18);
			frmShopBuyMonster.getContentPane().add(lblVisitShopCostMon2);
		}
		
		if (gameEnviro.getUserGameShop().getShopMonsterList().size() >= 3) {
			
			JLabel lblVisitShopCostMon3 = new JLabel(gameEnviro.getUserGameShop().getShopMonsterList()
					.get(2).getMonsterBuyPrice() + " Gold");
			lblVisitShopCostMon3.setHorizontalAlignment(SwingConstants.CENTER);
			lblVisitShopCostMon3.setFont(new Font("Verdana", Font.PLAIN, 12));
			lblVisitShopCostMon3.setBounds(252, 166, 68, 18);
			frmShopBuyMonster.getContentPane().add(lblVisitShopCostMon3);
		}
		
	}

}
