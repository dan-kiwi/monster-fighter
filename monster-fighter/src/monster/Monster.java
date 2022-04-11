package monster;
import items.*;
public class Monster {
	
	private static double maxHealth;
	private double currHealth;
	private static double baseDefense;
	private double currDefense;
	private static double baseAttack;
	private double currAttack;
	private double energy = 1;
	private String monsterName;
	
	Monster(String monName, double health, double attack, double defense) {
		setMonsterName(monName);
		maxHealth = currHealth = health;
		baseDefense = currDefense = defense;
		baseAttack = currAttack = attack;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}
	
	public void changeEnergy(double energy) {
		this.energy += energy;
	}
	
	public double getMaxHealth() {
		return maxHealth;
	}

	public double getCurrHealth() {
		return currHealth;
	}

	public void setCurrHealth(double currHealth) {
		this.currHealth = currHealth;
	}
	
	public void changeCurrHealth(double health) {
		this.currHealth += health;
	}

	public double getBaseAttack() {
		return baseAttack;
	}
	
	public double getBaseDefense() {
		return baseDefense;
	}

	public double getCurrDefense() {
		return currDefense;
	}

	public void setCurrDefense(double currDefense) {
		this.currDefense = currDefense;
	}
	
	public void changeCurrDefense(double defense) {
		this.currDefense += defense;
	}

	public double getCurrAttack() {
		return currAttack;
	}

	public void setCurrAttack(double currAttack) {
		this.currAttack = currAttack;
	}
	
	public void changeCurrAttack(double attack) {
		this.currAttack += attack;
	}
	
	public String getMonsterName() {
		return monsterName;
	}

	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}
	
	public void useItem(Items item) {
		this.changeCurrAttack(item.changeAttack());
		this.changeCurrDefense(item.changeDefense());
		this.changeCurrHealth(item.changeHealth());
		this.changeEnergy(item.changeEnergy());
	}
	
	public static void main(String[] args) {
		Dragon test = new Dragon();
		Potions health = new HealthPotion();
		System.out.println(test.getCurrHealth());
		test.useItem(health);
		System.out.println(test.getMonsterName());
		System.out.println(test.getCurrHealth());
	}

	
}
