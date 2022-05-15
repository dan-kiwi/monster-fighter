
package monster;

import items.*;


/**
 * The Class Monster. This class is the super class to all other monsters.
 * Each monster object contains the attributes of each monster which determines gameplay
 */
public class Monster {
	
	private int maxHealth;
	private int currHealth;
	private int resetHealAmount = 60;
	private int baseDefence;
	private int currDefence;
	private int baseAttack;
	private int currAttack;
	private int baseEnergy = 2;
	private int energy = 2;
	private String monsterName;
	private int monsterBuyPrice;
	private int monsterSellPrice;
	private int dailyBattlesWon = 0;
	private int totalBattlesWon = 0;

	
	/**
	 * Instantiates a new monster.
	 *
	 * @param monName the monster name
	 * @param buyPrice the buy price
	 * @param sellPrice the sell price
	 * @param monMaxHealth the monster max health
	 * @param monBaseAttack the monster base attack
	 * @param monBaseDefence the monster base defence
	 */
	Monster(String monName, int buyPrice, int sellPrice, int monMaxHealth, 
			int monBaseAttack, int monBaseDefence) {
		this.monsterName = monName;
		this.monsterBuyPrice = buyPrice;
		this.monsterSellPrice = sellPrice;
		this.maxHealth = this.currHealth = monMaxHealth;
		this.baseDefence = this.currDefence = monBaseDefence;
		this.baseAttack = this.currAttack = monBaseAttack;
	}
	
	//------------------------Health Getters/Setters/Changers----------------------------------
	
	/**
	 * Gets the max health.
	 *
	 * @return the max health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Sets the max health.
	 *
	 * @param increase the new max health
	 */
	public void setMaxHealth(int increase) {
		this.maxHealth = getMaxHealth() + increase;
	}

	/**
	 * Gets the curr health.
	 *
	 * @return the curr health
	 */
	public int getCurrHealth() {
		return currHealth;
	}

	/**
	 * Sets the curr health.
	 *
	 * @param currHealth the new curr health
	 */
	public void setCurrHealth(int currHealth) {
		if(currHealth > this.maxHealth) {
			this.currHealth = this.maxHealth;
		} else if (currHealth < 0) {
			this.currHealth = 0;
		} else {
			this.currHealth = currHealth;
		}
	}
	
	/**
	 * Changes current health. Method ensures that monster health does not go higher than max health
	 * or lower than 0
	 *
	 * @param health the health
	 */
	public void changeCurrHealth(int health) {			
		if ((this.getCurrHealth() + health) > this.getMaxHealth()) {
			setCurrHealth(this.getMaxHealth());
		} else if ((this.getCurrHealth() + health) < 0) {
			this.currHealth = 0;
		} else {
			this.currHealth += health;
		}
	}
	
	/**
	 * Change max health.
	 *
	 * @param health the health
	 */
	public void changeMaxHealth(int health) {
		this.maxHealth += health;
	}

	//------------------------Defence Getters/Setters/Changers----------------------------------
	
	/**
	 * Gets the base defence.
	 *
	 * @return the base defence
	 */
	public int getBaseDefence() {
		return baseDefence;
	}
	
	/**
	 * Sets the base defence.
	 *
	 * @param increase the new base defence
	 */
	public void setBaseDefence(int increase) {
		baseDefence = getBaseDefence() + increase;
	}

	/**
	 * Gets the current defence.
	 *
	 * @return the current defence
	 */
	public int getCurrDefence() {
		return currDefence;
	}

	/**
	 * Sets the current defense.
	 *
	 * @param currDefence, the new current defense
	 */
	public void setCurrDefense(int currDefence) {
		this.currDefence = currDefence;
	}
	
	/**
	 * Change current defence.
	 *
	 * @param defence, the amount of defence to be changed
	 */
	public void changeCurrDefence(int defence) {
		this.currDefence += defence;
	}
	
	//------------------------Attack Getters/Setters/Changers----------------------------------
	
	/**
	 * Gets the base attack.
	 *
	 * @return the base attack
	 */
	public int getBaseAttack() {
		return baseAttack;
	}
	
	/**
	 * Sets the base attack.
	 *
	 * @param increase the new base attack
	 */
	public void setBaseAttack(int increase) {
		this.baseAttack = getBaseAttack() + increase;
	}

	
	/**
	 * Gets the current attack.
	 *
	 * @return the current attack
	 */
	public int getCurrAttack() {
		return currAttack;
	}

	/**
	 * Sets the current attack.
	 *
	 * @param currAttack the new current attack
	 */
	public void setCurrAttack(int currAttack) {
		this.currAttack = currAttack;
	}
	
	/**
	 * Change current attack.
	 *
	 * @param attack, the attack amount to change current attack by
	 */
	public void changeCurrAttack(int attack) {
		this.currAttack += attack;
	}
	
	//------------------------Energy Getters/Setters/Changers----------------------------------
	
	/**
	 * Gets the energy.
	 *
	 * @return the energy
	 */
	public int getEnergy() {
		return energy;
	}
	
	/**
	 * Gets the base energy.
	 *
	 * @return the base energy
	 */
	public int getBaseEnergy() {
		return baseEnergy;
	}
	
	/**
	 * Sets the base Energy.
	 *
	 * @param increase the new base energy
	 */
	public void setBaseEnergy(int increase) {
		this.baseEnergy += increase;
	}

	/**
	 * Sets the energy.
	 *
	 * @param energy the new energy
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	/**
	 * Change energy.
	 *
	 * @param energy, the amount to change energy by
	 */
	public void changeEnergy(int energy) {
		this.energy += energy;
	}
	
	//------------------------Name/Price Getters/Setters/Changers----------------------------------

	/**
	 * Gets the monster name.
	 *
	 * @return the monster name
	 */
	public String getMonsterName() {
		return monsterName;
	}

	/**
	 * Sets the monster name.
	 *
	 * @param monsterName the new monster name
	 */
	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}
	
	/**
	 * Gets the monster buy price.
	 *
	 * @return the monster buy price
	 */
	public int getMonsterBuyPrice() {
		return monsterBuyPrice;
	}
	
	/**
	 * Gets the monster sell price.
	 *
	 * @return the monster sell price
	 */
	public int getMonsterSellPrice() {
		return monsterSellPrice;
	}
	

	//------------------------Battles Getters/Setters/Changers----------------------------------
	
	/**
	 * Gets the daily battles won.
	 *
	 * @return the daily battles won
	 */
	public int getDailyBattlesWon() {
		return dailyBattlesWon;
	}

	/**
	 * Sets the daily battles won.
	 *
	 * @param dailyBattlesWon the new daily battles won
	 */
	public void setDailyBattlesWon(int dailyBattlesWon) {
		this.dailyBattlesWon = dailyBattlesWon;
	}
	
	/**
	 * Adds the daily battles won.
	 *
	 * @param battleWon the battle won
	 */
	public void addDailyBattlesWon(int battleWon) {
		this.dailyBattlesWon += battleWon;
		this.totalBattlesWon += battleWon;
	}

	/**
	 * Gets the total battles won.
	 *
	 * @return the total battles won
	 */
	public int getTotalBattlesWon() {
		return totalBattlesWon;
	}

	/**
	 * Sets the total battles won.
	 *
	 * @param totalBattlesWon the new total battles won
	 */
	public void setTotalBattlesWon(int totalBattlesWon) {
		this.totalBattlesWon = totalBattlesWon;
	}
	
	//------------------------Other Methods----------------------------------------

	/**
	 * Return's the monster's name and statistics
	 *
	 * @return the string
	 */
	public String toString() {
		String firstLine = "Name: " + monsterName + ",\n";
		String secondLine = " Current Health: " + currHealth + ",\n";
		String thirdLine = " Current Attack: " + currAttack + ",\n";
		String forthLine = " Current Defense: " + currDefence + ",\n";
		String fifthLine = " Current Energy: " + energy + "\n";
		return firstLine + secondLine + thirdLine + forthLine + fifthLine;
	}
	
	/**
	 * Method to level up the monster
	 */
	public void levelUp() {
		this.changeMaxHealth(25);
		this.setBaseDefence(5);
		this.setBaseAttack(5);
		this.setBaseEnergy(1);
		this.setEnergy(baseEnergy);
	}
	
	/**
	 * Resets monster the statistics.
	 * This gets run on all of the users monster every time they sleep
	 */
	public void resetMonsterStats() {
		this.changeCurrHealth(resetHealAmount);
		this.setCurrDefense(this.getBaseDefence());
		this.setCurrAttack(this.getBaseAttack());
		this.setEnergy(this.getBaseEnergy());
		this.setDailyBattlesWon(0);
	}
}
