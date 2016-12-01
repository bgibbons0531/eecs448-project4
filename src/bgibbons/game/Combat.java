package bgibbons.game;

import bgibbons.game.entities.*;
import bgibbons.game.abilities.*;
import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.Screen;

/**
 * Object to handle combat interactions.
 * @author Brad Gibbons
 * @author Jackson Schilmoeller
 * @version 1.0, 28 October 2016
 */
public class Combat {

	private Screen screen;
	public Combatant combatant1;
	public Combatant combatant2;
	public Boolean inCombat;
	private int stunDuration;
	private int combatTicks;

	private int enemyOffRenderStart;
	private int enemyDamageRenderStart;
	private int enemyDamageRender;

	private int playerOffRenderStart;
	private int playerDamageRenderStart;
	private int playerDamageRender;
	private int playerDefRenderStart;
	private int playerHealRenderStart;
	private int playerHealRender;
	private int playerShieldRenderStart;
	private int playerShieldRender;
	public Sound sound;			//Declare the Sound object.
	/**
	 * Constructor for the Comabt object.
	 * @param mob1 		First Combatant.
	 * @param mob2 		Second Combatant.
	 */
	public Combat(Mob mob1, Mob mob2) {
		this.combatant1 = new Combatant(mob1);
		this.combatant2 = new Combatant(mob2);
		this.inCombat = true;
		this.stunDuration = 2*60;
		this.combatTicks = 0;

		this.enemyOffRenderStart = 0;
		this.enemyDamageRenderStart = 0;
		this.enemyDamageRender = 0;

		this.playerOffRenderStart = 0;
		this.playerDamageRenderStart = 0;
		this.playerDamageRender = 0;
		this.playerDefRenderStart = 0;
		this.playerHealRenderStart = 0;
		this.playerHealRender = 0;
		this.playerShieldRenderStart = 0;
		this.playerShieldRender = 0;
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
			//damage stat calculation
			int damage;
			if(ability.getDamage() == 0){
				damage = 0;
			}
			else{
				if(ability.getType() == ""){
					damage = ability.getDamage();
				}
				else if(ability.getType() == "rank"){
					damage = (combatant1.mob.getRank() / ability.getScale()) + ability.getDamage();
				}
				else if(ability.getType() == "int"){
					damage = (combatant1.mob.getIntelligence() / ability.getScale()) + ability.getDamage();
				}
				else if(ability.getType() == "dex"){
					damage = (combatant1.mob.getDexterity() / ability.getScale()) + ability.getDamage();
				}
				else if(ability.getType() == "vit"){
					damage = (combatant1.mob.getVitality() / ability.getScale()) + ability.getDamage();
				}
				else{
					damage = ability.getDamage();
				}
			}
			//heal stat calculation
			int heal;
			if(ability.getHeal() == 0)
				heal = 0;
			else{
				if(ability.getType() == ""){
					heal = ability.getHeal();
				}
				else if(ability.getType() == "rank"){
					heal = (combatant1.mob.getRank() / ability.getScale()) + ability.getHeal();
				}
				else if(ability.getType() == "int"){
					heal = (combatant1.mob.getIntelligence() / ability.getScale()) + ability.getHeal();
				}
				else if(ability.getType() == "dex"){
					heal = (combatant1.mob.getDexterity() / ability.getScale()) + ability.getHeal();
				}
				else if(ability.getType() == "vit"){
					heal = (combatant1.mob.getVitality() / ability.getScale()) + ability.getHeal();
				}
				else{
					heal = ability.getHeal();
				}
			}
			int shield;
			if(ability.getShield() == 0){
				shield = 0;
			}
			else{
				if(ability.getType() == ""){
					shield = ability.getShield();
				}
				else if(ability.getType() == "rank"){
					shield = (combatant1.mob.getRank() / ability.getScale()) + ability.getShield();
				}
				else if(ability.getType() == "int"){
					shield = (combatant1.mob.getIntelligence() / ability.getScale()) + ability.getShield();
				}
				else if(ability.getType() == "dex"){
					shield = (combatant1.mob.getDexterity() / ability.getScale()) + ability.getShield();
				}
				else if(ability.getType() == "vit"){
					shield = (combatant1.mob.getVitality() / ability.getScale()) + ability.getShield();
				}
				else{
					shield = ability.getShield();
				}
			}
			int cooldown = ability.getCooldown();

			//combat effect render variables
			if (ability instanceof OffensiveAbility) {
				enemyOffRenderStart = combatTicks;
				enemyDamageRender = damage;
				enemyDamageRenderStart = combatTicks;
			} else if (ability instanceof DefensiveAbility) {
				playerDefRenderStart = combatTicks;
				playerHealRender = heal;
				playerHealRenderStart = combatTicks;
				playerShieldRender = shield;
				playerShieldRenderStart = combatTicks;
			}

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
			if (ability == combatant1.mob.getAbility(1)){
				combatant1.ability1CD = cooldown;
				Ability.playAbility1Sound();
			}
			else if (ability == combatant1.mob.getAbility(2))
			{
				combatant1.ability2CD = cooldown;
				Ability.playAbility2Sound();
			}
			else if (ability == combatant1.mob.getAbility(3))
			{
				combatant1.ability3CD = cooldown;
				Ability.playAbility3Sound();
			}
			else if (ability == combatant1.mob.getAbility(4))
			{
				combatant1.ability4CD = cooldown;
				Ability.playAbility4Sound();
			}
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
			playerOffRenderStart = combatTicks;
			playerDamageRender = 1;
			playerDamageRenderStart = combatTicks;
		}
		//update cooldowns for abilities
		if (combatTicks % 60 == 0) {
			combatant1.tickCD();
		}
		this.combatTicks+=1;
	}

	/**
	 * Render combat effects to the screen as needed.
	 * @param screen 	Screen to render to.
	 */
	public void render(Screen screen) {
		// Render Enemy effects
		String health = combatant2.mob.getCurrentHealth() + "/" + combatant2.mob.getMaxHealth();
		Font.render(health, screen, (20-health.length()-1)*8, 5*8, Colors.get(-1,-1,-1,100), 1);
		if (enemyDamageRenderStart != 0 && combatTicks <= enemyDamageRenderStart + 45) {
			Font.render("" + enemyDamageRender, screen, 16*8, 6*8, Colors.get(-1,-1,-1,100), 1);
		}
		if (enemyOffRenderStart != 0 && combatTicks <= enemyOffRenderStart + 30) {
			screen.render(16*8, 7*8, 30+27*32, Colors.get(-1,100,200,300), 0x00, 1); // Top left
			screen.render(17*8, 7*8, 31+27*32, Colors.get(-1,100,200,300), 0x00, 1); // Top right
			screen.render(16*8, 8*8, 30+28*32, Colors.get(-1,100,200,300), 0x00, 1); // Bottom left
			screen.render(17*8, 8*8, 31+28*32, Colors.get(-1,100,200,300), 0x00, 1); // Bottom right
		}

		// Render Player effects
		if (playerDamageRenderStart != 0 && combatTicks <= playerDamageRenderStart + 45) {
			Font.render("" + playerDamageRender, screen, 2*8, 6*8, Colors.get(-1,-1,-1,100), 1);
		}
		if (playerOffRenderStart != 0 && combatTicks <= playerOffRenderStart + 30) {
			screen.render(2*8, 7*8, 30+27*32, Colors.get(-1,100,200,300), 0x00, 1); // Top left
			screen.render(3*8, 7*8, 31+27*32, Colors.get(-1,100,200,300), 0x00, 1); // Top right
			screen.render(2*8, 8*8, 30+28*32, Colors.get(-1,100,200,300), 0x00, 1); // Bottom left
			screen.render(3*8, 8*8, 31+28*32, Colors.get(-1,100,200,300), 0x00, 1); // Bottom right
		}

		if (playerDefRenderStart != 0 && combatTicks <= playerDefRenderStart + 30) {
			screen.render(2*8, 7*8, 30+25*32, Colors.get(-1,111,330,004), 0x00, 1); // Top left
			screen.render(3*8, 7*8, 31+25*32, Colors.get(-1,111,330,004), 0x00, 1); // Top right
			screen.render(2*8, 8*8, 30+26*32, Colors.get(-1,111,330,004), 0x00, 1); // Bottom left
			screen.render(3*8, 8*8, 31+26*32, Colors.get(-1,111,330,004), 0x00, 1); // Bottom right
		}
		if (playerHealRenderStart != 0 && combatTicks <= playerHealRenderStart + 45) {
			Font.render("" + playerHealRender, screen, 1*8, 6*8, Colors.get(-1,-1,-1,020), 1);
		}
		if (playerShieldRenderStart != 0 && combatTicks <= playerShieldRenderStart + 45) {
			Font.render("" + playerShieldRender, screen, 3*8, 6*8, Colors.get(-1,-1,-1,330), 1);
		}
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

		public void useAbility(Combatant target) {

		}
	}

}
