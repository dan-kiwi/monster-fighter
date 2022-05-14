package monster;

/**
 * Goblin, a non rare type of monster, Cheaper but weaker
 * A starter Monster but can be brought at the shop aswell
 * high Health, mid Attack, low Defence
 */
public class Goblin extends Monster{

	/**
	 * Instantiates a new goblin.
	 */
	public Goblin() {
		super("Goblin", 175, 40, 100, 20, 10);
	}
}
