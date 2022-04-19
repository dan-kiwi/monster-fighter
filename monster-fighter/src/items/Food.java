package items;

import monster.Monster;

public class Food implements Items {
	
	private String itemName;
	private String itemDescription;
	private int itemBuyPrice;
	private int itemSellPrice;
	private int maxHealthIncrease = 10; // Make each food give a monster a permanent +10 to its maxHealth
	private int defenceIncrease;
	private int attackIncrease;
	
	Food(String itemName, String itemDescription, int buyPrice, int sellPrice,
			int defenceIncrease, int attackIncrease) {
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemBuyPrice = buyPrice;
		this.itemSellPrice = sellPrice;
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
		monsterForItem.setBaseAttack(attackIncrease);
		monsterForItem.setBaseDefence(defenceIncrease);
		monsterForItem.setMaxHealth(maxHealthIncrease);
	}
	
	public String toString() {
		return itemName + ": " + itemDescription;
	}

}
