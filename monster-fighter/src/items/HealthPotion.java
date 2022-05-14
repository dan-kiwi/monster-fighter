package items;

/**
 * The Class HealthPotion, a type of Potions
 */
public class HealthPotion extends Potions{
	
	/**
	 * Instantiates a new Health Potion
	 * Buy Price - 100
	 * Sell Price - 30
	 * Health Increase - 60
	 * Defence Increase - 0
	 * Attack Increase - 0
	 */
	public HealthPotion() {
		super("Health Potion","Increase Monster Current Health by 60", 100, 30, 60, 0, 0);
	}
}
