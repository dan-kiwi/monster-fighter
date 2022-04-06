package items;
public class Potions implements Items{
	
	private double health;
	private double defense;
	private double attack;
	
	Potions(double health, double defense, double attack) {
		this.health = health;
		this.defense = defense;
		this.attack = attack;
	}

	@Override
	public double changeAttack() {
		// TODO Auto-generated method stub
		return attack;
	}

	@Override
	public double changeHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	@Override
	public double changeDefense() {
		// TODO Auto-generated method stub
		return defense;
	}
	
	public double changeEnergy() {
		return 0;
	}
}
