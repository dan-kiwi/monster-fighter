package Monster;
public class Monster {
	
	private double maxHealth;
	private double currHealth;
	private double energy = 1;
	private int baseDefense;
	private int baseAttack;
	
	Monster(double health, int attack, int defense) {
		maxHealth = health;
		currHealth = health;
		this.setBaseAttack(attack);
		this.setBaseDefense(defense);
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}
	
	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	public double getCurrHealth() {
		return currHealth;
	}

	public void setCurrHealth(double currHealth) {
		this.currHealth = currHealth;
	}

	public int getBaseAttack() {
		return baseAttack;
	}

	public void setBaseAttack(int baseAttack) {
		this.baseAttack = baseAttack;
	}
	
	public int getBaseDefense() {
		return baseDefense;
	}

	public void setBaseDefense(int baseDefense) {
		this.baseDefense = baseDefense;
	}
}
