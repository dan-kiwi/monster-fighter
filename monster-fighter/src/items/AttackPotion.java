package items;

/**
 * The Class AttackPotion, a type of Potions
 */
public class AttackPotion extends Potions {
	
	/**
	 * Instantiates a new Attack Potion
	 * Buy Price - 130
	 * Sell Price - 40
	 * Health Increase - 0
	 * Defence Increase - 0
	 * Attack Increase - 10
	 */
	public AttackPotion() {
		super("Attack Potion", "Increase Monster Attack by 10 For the day", 130, 40, 0, 0, 10);
	}
}
