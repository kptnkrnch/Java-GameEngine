package gameplay;

import java.util.Random;

import engine.Entity;

public class CombatCalculator {
	
	public static int CalculateDamage(Entity attacker, Entity defender) {
		int damage = 0;
		
		if (!DodgeCalculator(defender)) {
			damage = attacker.c_attack;
			
			if (CriticalHitCalculator(attacker)) {
				damage += Math.round((float)attacker.c_attack * attacker.c_critical_damage);
				System.out.println("CRITICAL HIT");
			}
			
			damage -= defender.c_defence;
			
			if (damage <= 0) {
				damage = 1;
			}
		} else {
			System.out.println("DODGED");
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
	
	public static boolean CriticalHitCalculator(Entity attacker) {
		Random random = new Random();
		int roll = random.nextInt(100) + 1;
		
		if (roll < attacker.c_critical_chance) {
			return true;
		}
		return false;
	}
	
	public static boolean DodgeCalculator(Entity defender) {
		Random random = new Random();
		int roll = random.nextInt(100) + 1;
		
		if (roll < defender.c_dodge_chance) {
			return true;
		}
		return false;
	}
	
}
