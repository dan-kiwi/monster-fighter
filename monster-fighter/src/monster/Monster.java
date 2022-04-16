
package monster;

import items.*;

public class Monster {
	
	private int maxHealth;
	private int currHealth;
	private int baseDefence;
	private int currDefence;
	private int baseAttack;
	private int currAttack;
	private int energy = 1;
	private String monsterName;
	private int monsterBuyPrice;
	private int monsterSellPrice;
	
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
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int increase) {
		this.maxHealth = getMaxHealth() + increase;
	}

	public int getCurrHealth() {
		return currHealth;
	}

	public void setCurrHealth(int currHealth) {
		this.currHealth = currHealth;
	}
	
	public void changeCurrHealth(int health) {	
		// If the increase goes over max health, just make it max health
		
		if ((this.getCurrHealth() + health) > this.getMaxHealth()) {
			setCurrHealth(this.getMaxHealth());
		} else {
			this.currHealth += health;
		}
	}

	//------------------------Defence Getters/Setters/Changers----------------------------------
	
	public int getBaseDefence() {
		return baseDefence;
	}
	
	public void setBaseDefence(int increase) {
		baseDefence = getBaseDefence() + increase;
	}

	public int getCurrDefence() {
		return currDefence;
	}

	public void setCurrDefense(int currDefence) {
		this.currDefence = currDefence;
	}
	
	public void changeCurrDefence(int defence) {
		this.currDefence += defence;
	}
	
	//------------------------Attack Getters/Setters/Changers----------------------------------
	
	public int getBaseAttack() {
		return baseAttack;
	}
	
	public void setBaseAttack(int increase) {
		this.baseAttack = getBaseAttack() + increase;
	}

	
	public int getCurrAttack() {
		return currAttack;
	}

	public void setCurrAttack(int currAttack) {
		this.currAttack = currAttack;
	}
	
	public void changeCurrAttack(int attack) {
		this.currAttack += attack;
	}
	
	//------------------------Energy Getters/Setters/Changers----------------------------------
	
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public void changeEnergy(int energy) {
		this.energy += energy;
	}
	
	//------------------------Name/Price Getters/Setters/Changers----------------------------------

	public String getMonsterName() {
		return monsterName;
	}

	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}
	
	public int getMonsterBuyPrice() {
		return monsterBuyPrice;
	}
	
	public int getMonsterSellPrice() {
		return monsterSellPrice;
	}
	

	public String toString() {
		String firstLine = "Name: " + monsterName + "\n";
		String secondLine = "Current Health: " + currHealth + "\n";
		String thirdLine = "Current Attack: " + currAttack + "\n";
		String forthLine = "Current Defense: " + currDefence + "\n";
		return firstLine + secondLine + thirdLine + forthLine;
	}
	
	public static void main(String[] args) {
		Dragon test = new Dragon();
		Potions health = new HealthPotion();
		System.out.println(test.getCurrHealth());
		test.setCurrHealth(10);
		System.out.println(test.getCurrHealth());
		health.useItemOnMonster(test);
		System.out.println(test.getCurrHealth());
		health.useItemOnMonster(test);
		System.out.println(test.getCurrHealth());	
	}

}
