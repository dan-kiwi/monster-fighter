package items;

import monster.Monster;

// TODO: Auto-generated Javadoc
/**
 * The Class Food, an implementation of Items
 */
public class Food implements Items {
	
	private String itemName;
	private String itemDescription;
	private int itemBuyPrice;
	private int itemSellPrice;
	private int maxHealthIncrease = 10; // Make each food give a monster a permanent +10 to its maxHealth
	private int defenceIncrease;
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
	 * Outputs the item's name and discription
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return itemName + ": " + itemDescription;
	}

}
