package items;

import monster.Monster;

// TODO: Auto-generated Javadoc
/**
 * The Class Food, an implementation of Items.
 */
public class Food implements Items {
	
	/** The item name. */
	private String itemName;
	
	/** The item description. */
	private String itemDescription;
	
	/** The item buy price. */
	private int itemBuyPrice;
	
	/** The item sell price. */
	private int itemSellPrice;
	
	/** The max health increase. */
	private int maxHealthIncrease = 10; // Make each food give a monster a permanent +10 to its maxHealth
	
	/** The defence increase. */
	private int defenceIncrease;
	
	/** The attack increase. */
	private int attackIncrease;
	
	/**
	 * Instantiates a new food.
	 *
	 * @param itemName the item's name
	 * @param itemDescription the item's description
	 * @param buyPrice the item's buy price
	 * @param sellPrice the item's sell price
	 * @param defenceIncrease the item's defence increase
	 * @param attackIncrease the item's attack increase
	 */
	Food(String itemName, String itemDescription, int buyPrice, int sellPrice,
			int defenceIncrease, int attackIncrease) {
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemBuyPrice = buyPrice;
		this.itemSellPrice = sellPrice;
		this.defenceIncrease = defenceIncrease;
		this.attackIncrease = attackIncrease;
	}

	/**
	 * Gets the item name.
	 *
	 * @return the item name
	 */
	@Override
	public String getItemName() {
		return itemName;
	}

	/**
	 * Gets the item description.
	 *
	 * @return the item description
	 */
	@Override
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * Gets the item buy price.
	 *
	 * @return the item buy price
	 */
	@Override
	public int getItemBuyPrice() {
		return itemBuyPrice;
	}

	/**
	 * Gets the item sell price.
	 *
	 * @return the item sell price
	 */
	@Override
	public int getItemSellPrice() {
		return itemSellPrice;
	}

	/**
	 * Use item on monster. Takes each parameter of the object and applies it to the monster
	 *
	 * @param monsterForItem the monster for item
	 */
	@Override
	public void useItemOnMonster(Monster monsterForItem) {
		monsterForItem.setBaseAttack(attackIncrease);
		monsterForItem.changeCurrAttack(attackIncrease);
		monsterForItem.setBaseDefence(defenceIncrease);
		monsterForItem.changeCurrDefence(defenceIncrease);
		monsterForItem.changeMaxHealth(maxHealthIncrease);
	}
	
	/**
	 * Outputs the item's name and discription.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return itemName + ": " + itemDescription;
	}

	/**
	 * Return's the food's name, statistics, buy price and sell price
	 */
	@Override
	public String shopString() {
		String name = "Name: " + itemName + "\n";
		String attack = "Permanent Attack Increase: " + attackIncrease + "\n";
		String defence = "Permanent Defence Increase: " + defenceIncrease + "\n";
		String health = "Current Health Increase: 0" + "\n";
		String maxHealth = "Max Health Increase: " + maxHealthIncrease + "\n";
		String buyPrice = "Buy Price: " + itemBuyPrice + "\n";
		String sellPrice = "Sell Price: " + itemSellPrice;
		return name + attack + defence + health + maxHealth + buyPrice + sellPrice;
	}

}
