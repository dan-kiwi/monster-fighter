package items;

/**
 * The Class Apple, a type of Food.
 */
public class Apple extends Food {
	
	/**
	 * Instantiates a new apple. 
	 * Buy Price - 80
	 * Sell Price - 20
	 * Defence Increase - 0
	 * Attack Increase - 5
	 */
	public Apple() {
		super("Apple","Increase Monster Attack by 5 permanently", 80, 20, 0, 5);
	}

}
