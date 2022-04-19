
package items;

import monster.Monster;

public class Potions implements Items{
	
	private String itemName;
	private String itemDescription;
	private int itemBuyPrice;
	private int itemSellPrice;
	private int energyIncrease = 2; //make each potion of any type give a monster +10 energy
	private int healthIncrease;
	private int defenceIncrease;
	private int attackIncrease;
	
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
	
	@Override
	public String getItemName() {
		return itemName;
	}
	
	@Override
	public String getItemDescription() {
		return itemDescription;
	}

	@Override
	public int getItemBuyPrice() {
		return itemBuyPrice;
	}

	@Override
	public int getItemSellPrice() {
		return itemSellPrice;
	}

	@Override
	public void useItemOnMonster(Monster monsterForItem) {
		monsterForItem.changeCurrHealth(healthIncrease);
		monsterForItem.changeCurrAttack(attackIncrease);
		monsterForItem.changeCurrDefence(defenceIncrease);
		monsterForItem.changeEnergy(energyIncrease);
	}
	
	public String toString() {
		return itemName + ": " + itemDescription;
	}

}
