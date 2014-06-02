package gameplay;

import engine.Entity;

public class CombatCalculator {
	
	public static int CalculateDamage(Entity attacker, Entity defender) {
		int damage = 0;
		
		damage = attacker.c_attack - defender.c_defence;
		
		if (damage <= 0) {
			damage = 1;
		}
		
		return damage;
	}
	
	public static int CalculateCooldown(Entity attacker, int base_cooldown) {
		int cooldown = base_cooldown;
		
		cooldown = base_cooldown - (attacker.c_speed * 10);
		
		if (cooldown < base_cooldown / 2) {
			cooldown = base_cooldown / 2;
		}
		
		return cooldown;
	}
	
}
