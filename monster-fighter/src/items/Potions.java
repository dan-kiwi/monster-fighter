
package items;

import monster.Monster;

/**
 * The Class Potions, an implementation of Items
 */
public class Potions implements Items{
	
	private String itemName;
	private String itemDescription;
	private int itemBuyPrice;
	private int itemSellPrice;
	private int energyIncrease = 2; //make each potion of any type give a monster +2 energy
	private int healthIncrease;
	private int defenceIncrease;
	private int attackIncrease;
	
	/**
	 * Instantiates a new potions.
	 *
	 * @param itemName the item's name
	 * @param itemDescription the item's description
	 * @param buyPrice the item's buy price
	 * @param sellPrice the item's sell price
	 * @param healthIncrease the item's health increase
	 * @param defenceIncrease the item's defence increase
	 * @param attackIncrease the item's attack increase
	 */
	Potions(String itemName, String itemDescription, int buyPrice, int sellPrice, 
			int healthIncrease, int defenceIncrease, int attackIncrease) {
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemBuyPrice = buyPrice;
		this.itemSellPrice = sellPrice;
		this.healthIncrease = healthIncrease;
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
	 * Use item on monster.
	 *
	 * @param monsterForItem the monster for item
	 */
	@Override
	public void useItemOnMonster(Monster monsterForItem) {
		monsterForItem.changeCurrHealth(healthIncrease);
		monsterForItem.changeCurrAttack(attackIncrease);
		monsterForItem.changeCurrDefence(defenceIncrease);
		monsterForItem.changeEnergy(energyIncrease);
	}
	
	/**
	 * Outputs the item's name and description
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
		String health = "Current Health Increase: " + healthIncrease + "\n";
		String energy = "Energy Increase: " + energyIncrease + "\n";
		String buyPrice = "Buy Price: " + itemBuyPrice + "\n";
		String sellPrice = "Sell Price: " + itemSellPrice;
		return name + attack + defence + health + energy + buyPrice + sellPrice;
	}

}
