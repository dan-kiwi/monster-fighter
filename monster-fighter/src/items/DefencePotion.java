package items;

/**
 * The Class DefencePotion, a type of Potions
 */
public class DefencePotion extends Potions {
	
	/**
	 * Instantiates a new Defence Potion
	 * Buy Price - 130
	 * Sell Price - 40
	 * Health Increase - 0
	 * Defence Increase - 10
	 * Attack Increase - 0
	 */
	public DefencePotion() {
		super("Defence Potion", "Increase Monster Defence by 10 For the day", 130, 40, 0, 10, 0);
	}

}
