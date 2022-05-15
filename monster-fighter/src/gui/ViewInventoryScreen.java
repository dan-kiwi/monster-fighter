package gui;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import mainenviro.GameEnviro;
import monster.Monster;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;

import items.Items;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The Class ViewInventoryScreen. This class shows all the user's item's in GUI form
 */
public class ViewInventoryScreen {

	private JFrame frmViewInventory;
	private static GameEnviro gameEnviro;
	private JList<Monster> listViewInvMonsters;
	private JList<Items> listViewInvItems;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewInventoryScreen window = new ViewInventoryScreen(null);
					window.frmViewInventory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewInventoryScreen(GameEnviro newGame) {
		gameEnviro = newGame;
		initialize();
	}
	
	/**
	 * Method to call back the View Inventory class from the Main Menu object
	 */
	public void ViewInventory() {
		ViewInventoryScreen viewInventory = new ViewInventoryScreen(gameEnviro);
		viewInventory.frmViewInventory.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewInventory = new JFrame();
		frmViewInventory.setTitle("View Your Inventory");
		frmViewInventory.setBounds(100, 100, 500, 500);
		frmViewInventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewInventory.getContentPane().setLayout(null);
		
		JLabel lblViewInvItemTitle = new JLabel("Here is your Inventory");
		lblViewInvItemTitle.setFont(new Font("Verdana", Font.BOLD, 15));
		lblViewInvItemTitle.setBounds(136, 11, 203, 31);
		frmViewInventory.getContentPane().add(lblViewInvItemTitle);
		
		DefaultListModel<Items> listItems = new DefaultListModel<Items>();
	    listViewInvItems = new JList<Items>(listItems);
	    listViewInvItems.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    for (int i=0; i < gameEnviro.getUserItemList().size(); i++) {
	    	listItems.addElement(gameEnviro.getUserItemList().get(i));
	    }
		listViewInvItems.setVisibleRowCount(15);
		listViewInvItems.setFont(new Font("Verdana", Font.PLAIN, 14));
		listViewInvItems.setBounds(10, 53, 442, 151);
		frmViewInventory.getContentPane().add(listViewInvItems);
		
		JScrollPane scrollPaneItems = new JScrollPane(listViewInvItems);
		scrollPaneItems.setBounds(10, 53, 464, 151);
		frmViewInventory.getContentPane().add(scrollPaneItems);
		
		DefaultListModel<Monster> listMonsters = new DefaultListModel<Monster>();
		listViewInvMonsters = new JList<Monster>(listMonsters);
		for (int i=0; i < gameEnviro.getUserMonsterList().size(); i++) {
			listMonsters.addElement(gameEnviro.getUserMonsterList().get(i));
	    }
		listViewInvMonsters.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listViewInvMonsters.setFont(new Font("Verdana", Font.PLAIN, 14));
		listViewInvMonsters.setBounds(10, 248, 141, 163);
		frmViewInventory.getContentPane().add(listViewInvMonsters);
		
		JScrollPane scrollPaneMonsters = new JScrollPane(listViewInvMonsters);
		scrollPaneMonsters.setBounds(10, 257, 223, 154);
		frmViewInventory.getContentPane().add(scrollPaneMonsters);
		
		JLabel lblViewInvMonsterTitle = new JLabel("Monster List");
		lblViewInvMonsterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewInvMonsterTitle.setFont(new Font("Verdana", Font.BOLD, 15));
		lblViewInvMonsterTitle.setBounds(20, 215, 203, 31);
		frmViewInventory.getContentPane().add(lblViewInvMonsterTitle);
		
		JButton btnViewInvUseItem = new JButton("Use Item");
		btnViewInvUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(listViewInvItems.getSelectedValue() == null || listViewInvMonsters.getSelectedValue() == null)) {
					Items selectedItem = listViewInvItems.getSelectedValue();
					selectedItem.useItemOnMonster(listViewInvMonsters.getSelectedValue());
					gameEnviro.getUserItemList().remove(selectedItem);
					ViewInventoryScreen newViewInventory = new ViewInventoryScreen(gameEnviro);
					frmViewInventory.dispose();
					newViewInventory.ViewInventory();
				}
			}
		});
		btnViewInvUseItem.setFont(new Font("Verdana", Font.BOLD, 14));
		btnViewInvUseItem.setBounds(270, 261, 165, 48);
		frmViewInventory.getContentPane().add(btnViewInvUseItem);
		
		JLabel lblViewInvUseItemTitle = new JLabel("(Use Item on Selected Monster)");
		lblViewInvUseItemTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewInvUseItemTitle.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblViewInvUseItemTitle.setBounds(241, 312, 233, 15);
		frmViewInventory.getContentPane().add(lblViewInvUseItemTitle);
		
		JButton btnViewInvReturn = new JButton("Return");
		btnViewInvReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuScreen newMainMenu = new MainMenuScreen(gameEnviro);
				frmViewInventory.dispose();
    			newMainMenu.MainMenu();
			}
		});
		btnViewInvReturn.setFont(new Font("Verdana", Font.BOLD, 14));
		btnViewInvReturn.setBounds(270, 363, 165, 48);
		frmViewInventory.getContentPane().add(btnViewInvReturn);
		
	}
}
