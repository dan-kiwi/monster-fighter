package Monster;
public class Monster {
	
	private double maxHealth;
	private double currHealth;
	private double energy = 1;
	private double baseDefense;
	private double currDefense;
	private double baseAttack;
	private double currAttack;
	
	Monster(double health, double attack, double defense) {
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

	public void setBaseAttack(double baseAttack) {
		this.baseAttack = baseAttack;
	}
	
	public int getBaseDefense() {
		return baseDefense;
	}

	public void setBaseDefense(double baseDefense) {
		this.baseDefense = baseDefense;
	}

	public double getCurrDefense() {
		return currDefense;
	}

	public void setCurrDefense(double currDefense) {
		this.currDefense = currDefense;
	}

	public double getCurrAttack() {
		return currAttack;
	}

	public void setCurrAttack(double currAttack) {
		this.currAttack = currAttack;
	}
}
