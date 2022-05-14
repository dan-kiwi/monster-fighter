package monster;

/**
 * Dragon, a rarer type of monster, Stronger but more expensive
 * Not a starter Monster but can be brought at the shop
 * High Health, Medium Attack, Medium Defence
 */
public class Dragon extends Monster{

	/**
	 * Instantiates a new dragon.
	 */
	public Dragon() {
		super("Dragon",250, 70, 100, 20, 20);
	}
}
