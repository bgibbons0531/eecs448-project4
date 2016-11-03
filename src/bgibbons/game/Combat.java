package bgibbons.game;

import bgibbons.game.entities.*;
import bgibbons.game.abilities.*;

/**
 * Object to handle combat interactions.
 * @author Brad Gibbons
 * @author Jackson Schilmoeller
 * @version 1.0, 28 October 2016
 */
public class Combat {
	
	public Combatant combatant1;
	public Combatant combatant2;
	public Boolean inCombat;
	private int stunDuration;
	private int combatTicks;

	/**
	 * Constructor for the Comabt object.
	 * @param mob1 	First Combatant.
	 * @param mob2 	Second Combatant.
	 */
	public Combat(Mob mob1, Mob mob2) {
		this.combatant1 = new Combatant(mob1);
		this.combatant2 = new Combatant(mob2);
		this.inCombat = true;
		this.stunDuration = 2*60;
		this.combatTicks = 0;
	}

	/**
	 * Uses passed ability. Offensive targets mob2, defensive targets mob1 for now.
	 * @param ability 	Ability to be used.
	 */
	public void useAbility(Ability ability) {
		Boolean canUse = false;
		//check if ability can be used
		if(ability == combatant1.mob.getAbility(1) && combatant1.ability1CD == 0)
			canUse = true;
		else if(ability == combatant1.mob.getAbility(2) && combatant1.ability2CD == 0)
			canUse = true;
		else if(ability == combatant1.mob.getAbility(3) && combatant1.ability3CD == 0)
			canUse = true;
		else if(ability == combatant1.mob.getAbility(4) && combatant1.ability4CD == 0)
			canUse = true;

		//if the ability is usable
		if(canUse){
			int damage = ability.getDamage();
			int heal = ability.getHeal();
			int shield = ability.getShield();
			int cooldown = ability.getCooldown();

			//check if ability stuns
			if(ability.getStun()){
				combatant2.stun();
				combatant2.stunStart = this.combatTicks;
			}
			//deal damage, currently always targets mob2 and assumes mob1 is player
			//check if combat continues
			inCombat = combatant2.takeDamage(damage);

			//heal then shield target mob, for now always mob1
			combatant1.mob.heal(heal);
			combatant1.changeShield(shield);

			//set cooldown timer for ability, currently assumes player/mob1 used ability
			if (ability == combatant1.mob.getAbility(1))
				combatant1.ability1CD = cooldown;
			else if (ability == combatant1.mob.getAbility(2))
				combatant1.ability2CD = cooldown;
			else if (ability == combatant1.mob.getAbility(3))
				combatant1.ability3CD = cooldown;
			else if (ability == combatant1.mob.getAbility(4))
				combatant1.ability4CD = cooldown;
		}
	}

	/**
	 * Ticks the combat object.
	 */
	public void tick() {
		//check if enemy should no longer be stunned
		if(combatant2.isStunned && (this.combatTicks - combatant2.stunStart) > this.stunDuration){
			combatant2.isStunned = false;
		}
		//enemy attack
		if (combatTicks % 120 == 0 && combatTicks > 0 && !combatant2.isStunned) {
			inCombat = combatant1.takeDamage(1);
		}
		//update cooldowns for abilities
		if (combatTicks % 60 == 0) {
			combatant1.tickCD();
		}
		this.combatTicks+=1;
	}

	/**
	 * Object that will house combat attributes.
	 * @author Jackson Schilmoeller
	 * @version 1.0, 29 October 2016
	 */
	public class Combatant {
		public Mob mob;
		public Boolean isStunned;
		public int stunStart;
		public int shield;
		public int ability1CD;
		public int ability2CD;
		public int ability3CD;
		public int ability4CD;

		/**
		 * Constructor for the Combatant object.
		 * @param mob 	Mob that will become a combatant.
		 */
		public Combatant(Mob mob) {
			this.mob = mob;
			this.isStunned = false;
			this.shield = 0;
			this.ability1CD = 0;
			this.ability2CD = 0;
			this.ability3CD = 0;
			this.ability4CD = mob.getAbility(4).getCooldown();
		}

		/**
		 * Manipulates the shield amount of a combatant.
		 * @param val 	Integer value to add to shield, can be negative.
		 * @return Integer value of excess damage done.
		 */
		public int changeShield(int val) {
			int excess = 0;
			this.shield += val;
			if(this.shield <= 0) {
				excess = -(this.shield);
				this.shield = 0;
			}
			return excess;
		}

		/**
		 * Sets the stun status effect for a combatant.
		 */
		public void stun() {
			this.isStunned = true;
		}

		/**
		 * Deals damage to this combatant.
		 * @param damage 	Integer value of damage to be taken.
		 * @return A boolean, true if combatant is still alive, false if combatant died.
		 */
		public boolean takeDamage(int damage) {
			int excess = this.changeShield(-damage);
			this.mob.takeDamage(excess);
			if(this.mob.getCurrentHealth()<=0)
				return false;
			else
				return true;
		}

		/**
		 * Decrement cooldown timers.
		 */
		public void tickCD() {
			if(this.ability1CD > 0) {
				this.ability1CD -= 1;
			}
			if(this.ability2CD > 0) {
				this.ability2CD -= 1;
			}
			if(this.ability3CD > 0) {
				this.ability3CD -= 1;
			}
			if(this.ability4CD > 0) {
				this.ability4CD -= 1;
			}
		}
	}

}