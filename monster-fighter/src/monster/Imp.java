package monster;

/**
 * Imp, a non rare type of monster, Cheaper but weaker
 * A starter Monster but can be brought at the shop aswell
 * low Health, high Attack, medium Defence
 */
public class Imp extends Monster {
	
	/**
	 * Instantiates a new imp.
	 */
	public Imp() {
		super("Imp", 175, 40, 50, 30, 20);
	}
}
