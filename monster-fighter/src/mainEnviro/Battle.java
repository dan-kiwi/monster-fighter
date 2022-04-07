package mainEnviro;
import monster.*;
import java.lang.Math;

public class Battle {
	
	private Monster enemy;
	private Monster user;
	
	public void fight() {
		while (enemy.getCurrHealth() > 0 && user.getCurrHealth() > 0) {
			int enemyChoice = enemyChoice();
		}
	}
	
	public static int enemyChoice() {
		double rand = Math.random();
		return (int)(rand * 3);
	}
}
