
package items;

import monster.Monster;

public interface Items {
	
	public String getItemName();
	public String getItemDescription();
	public int getItemBuyPrice();
	public int getItemSellPrice();
	public void useItemOnMonster(Monster monster);
}
