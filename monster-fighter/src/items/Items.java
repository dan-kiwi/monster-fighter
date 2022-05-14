package items;

import monster.Monster;

/**
 * The Interface Items. Provides an interface for both potions and food
 */
public interface Items {
	
	/**
	 * Gets the item name.
	 *
	 * @return the item name
	 */
	public String getItemName();
	
	/**
	 * Gets the item description.
	 *
	 * @return the item description
	 */
	public String getItemDescription();
	
	/**
	 * Gets the item buy price.
	 *
	 * @return the item buy price
	 */
	public int getItemBuyPrice();
	
	/**
	 * Gets the item sell price.
	 *
	 * @return the item sell price
	 */
	public int getItemSellPrice();
	
	/**
	 * Use item on monster.
	 *
	 * @param monster the monster
	 */
	public void useItemOnMonster(Monster monster);
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();
}
