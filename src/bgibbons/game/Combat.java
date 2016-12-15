package bgibbons.game;

import bgibbons.game.entities.*;
import bgibbons.game.abilities.*;
import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.Screen;

import java.util.Random;

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
	private int burnDamage;
	private int burnDuration;
	private int wallDamage;
	private int wallDuration;
	private int blindDuration;
	private int markDuration;
	private int combatTicks;
	private Boolean thickSkin;
	private int thickSkinDur;
	private int thickSkinStart;
	private Boolean savageRoar;
	private int savageRoarDur;
	private int savageRoarStart;
	private Boolean attackMissed;

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
	private String currentAbility;
	public Sound sound = new Sound("/res/sounds/Combat/playerHit.wav");;			//Declare the Sound object.
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
		this.thickSkin = false;
		this.thickSkinDur = 3*60;
		this.thickSkinStart = 0;
		this.savageRoar = false;
		this.savageRoarDur = 3*60;
		this.savageRoarStart = 0;
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
			//special ability processing
			if(ability.getDuration() != 0){
				if(ability.getName() == "Scorch        "){
					this.burnDamage = damage;
					this.burnDuration = ability.getDuration() * 60;
					combatant2.burn();
					combatant2.burnStart = this.combatTicks;
				}
				else if(ability.getName() == "Fire  Wall  "){
					this.wallDamage = damage;
					this.wallDuration = ability.getDuration() * 60;
					combatant2.isWalled = true;
					combatant2.wallStart = this.combatTicks;
				}
				else if(ability.getName() == "Smoke Bomb  "){
					this.blindDuration = ability.getDuration() * 60;
					combatant2.blind();
					combatant2.blindStart = this.combatTicks;
				}
				else if(ability.getName() == "Mark        "){
					this.markDuration = ability.getDuration() * 60;
					combatant2.mark();
					combatant2.markStart = this.combatTicks;
				}
			}

			//check for damage amplification
			if(combatant2.isMarked){
				damage = damage * 2;
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
			//alter damage for target damage reduction
			if(thickSkin){
				damage = damage/2;
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
				Ability.playAbility1Sound();					//Calls Ability 1 Sound
				currentAbility=combatant1.mob.getAbility(1).getName();
			}
			else if (ability == combatant1.mob.getAbility(2))
			{
				combatant1.ability2CD = cooldown;
				Ability.playAbility2Sound();					//Calls Ability 2 Sound
				currentAbility=combatant1.mob.getAbility(2).getName();
			}
			else if (ability == combatant1.mob.getAbility(3))
			{
				combatant1.ability3CD = cooldown;
				Ability.playAbility3Sound();					//Calls Ability 3 Sound
				currentAbility=combatant1.mob.getAbility(3).getName();
			}
			else if (ability == combatant1.mob.getAbility(4))
			{
				combatant1.ability4CD = cooldown;
				Ability.playAbility4Sound();					//Calls Ability 4 Sound
				currentAbility=combatant1.mob.getAbility(4).getName();
			}
		}
	}

	/**
	 * Ticks the combat object.
	 */
	public void tick() {
		//boss combat stuff
		if(combatant2.mob instanceof Boss){
			//use Big Bonk every 2 seconds
			if(combatTicks % 120 == 0 && combatTicks > 0 && !combatant2.isStunned){
				int bigBonk = combatant2.mob.getVitality() / 4;
				if(savageRoar){
					bigBonk = bigBonk*2;
				}
				if(!combatant2.isBlinded){
					if(!combatant2.isWalled){
						inCombat = combatant1.takeDamage(bigBonk);
						sound.playFX();
						playerDamageRender = bigBonk;
						this.attackMissed = false;
					}
					else{
						inCombat = combatant2.takeDamage(this.wallDamage);
						playerDamageRender = 0;
						this.attackMissed = false;
					}
				}
				else{
					Random rand = new Random();
					int temp = rand.nextInt(3);
					if(temp == 0){
						if(!combatant2.isWalled){
							inCombat = combatant1.takeDamage(bigBonk);
							sound.playFX();
							playerDamageRender = bigBonk;
							this.attackMissed = false;
						}
						else{
							inCombat = combatant2.takeDamage(this.wallDamage);
							playerDamageRender = 0;
							this.attackMissed = false;
						}
					}
					else{
						playerDamageRender = 0;
						this.attackMissed = true;
					}
				}
			}
			//use Head Bash every 6.5 seconds
			if(combatTicks % 390 == 0 && combatTicks > 0 && !combatant2.isStunned){
				int headBash = combatant2.mob.getVitality() / 6;
				if(savageRoar){
					headBash = headBash*2;
				}
				if(!combatant2.isBlinded){
					if(!combatant2.isWalled){
						combatant1.stunPlayer();
						inCombat = combatant1.takeDamage(headBash);
						sound.playFX();
						playerDamageRender = headBash;
						this.attackMissed = false;
					}
					else{
						inCombat = combatant2.takeDamage(this.wallDamage);
						playerDamageRender = 0;
						this.attackMissed = false;
					}
				}
				else{
					Random rand = new Random();
					int temp = rand.nextInt(3);
					if(temp == 0){
						if(!combatant2.isWalled){
							inCombat = combatant1.takeDamage(headBash);
							sound.playFX();
							playerDamageRender = headBash;
							this.attackMissed = false;
						}
						else{
							inCombat = combatant2.takeDamage(this.wallDamage);
							playerDamageRender = 0;
							this.attackMissed = false;
						}
					}
					else{
						playerDamageRender = 0;
						this.attackMissed = true;
					}
				}
			}
			//use Thick Skin every 5 seconds
			if(combatTicks % 300 == 0 && combatTicks > 0){
				thickSkin = true;
				thickSkinStart = combatTicks;
			}
			//use Savage roar every 7 seconds
			if(combatTicks % 420 == 0 && combatTicks > 0){
				savageRoar = true;
				savageRoarStart = combatTicks;
			}
			//check if thicc skin expires
			if(thickSkin && (this.combatTicks - thickSkinStart) > thickSkinDur){
				thickSkin = false;
			}
			//check if savage roar expires
			if(savageRoar && (this.combatTicks - savageRoarStart) > thickSkinDur){
				savageRoar = false;
			}
		}
		else{
			//enemy attack
			if (combatTicks % 120 == 0 && combatTicks > 0 && !combatant2.isStunned) {
				int enemyDamage = combatant2.mob.getVitality() / 5;
				if(!combatant2.isBlinded){
					if(!combatant2.isWalled){
						inCombat = combatant1.takeDamage(enemyDamage);
						sound.playFX();
						playerDamageRender = enemyDamage;
						this.attackMissed = false;
					}
					else{
						inCombat = combatant2.takeDamage(this.wallDamage);
						playerDamageRender = 0;
						this.attackMissed = false;
					}
				}
				else{
					Random rand = new Random();
					int temp = rand.nextInt(3);
					if(temp == 0){
						if(!combatant2.isWalled){
							inCombat = combatant1.takeDamage(enemyDamage);
							sound.playFX();
							playerDamageRender = enemyDamage;
							this.attackMissed = false;
						}
						else{
							inCombat = combatant2.takeDamage(this.wallDamage);
							playerDamageRender = 0;
							this.attackMissed = false;
						}
					}
					else{
						playerDamageRender = 0;
						this.attackMissed = true;
					}
				}
				playerOffRenderStart = combatTicks;

				playerDamageRenderStart = combatTicks;
			}
		}
		//check if enemy should no longer be stunned
		if(combatant2.isStunned && (this.combatTicks - combatant2.stunStart) > this.stunDuration){
			combatant2.isStunned = false;
		}
		//check if burn expires
		if(combatant2.isBurned && (this.combatTicks - combatant2.burnStart) > this.burnDuration){
			combatant2.isBurned = false;
		}
		//check if wall expires
		if(combatant2.isWalled && (this.combatTicks - combatant2.wallStart) > this.wallDuration){
			combatant2.isWalled = false;
		}
		//check if blind expires
		if(combatant2.isBlinded && (this.combatTicks - combatant2.blindStart) > this.blindDuration){
			combatant2.isBlinded = false;
		}
		//check if mark expires
		if(combatant2.isMarked && (this.combatTicks - combatant2.markStart) > this.markDuration){
			combatant2.isMarked = false;
		}
		//burn enemy
		if(combatTicks % 60 == 0 && combatTicks > 0  && combatant2.isBurned){
			inCombat = combatant2.takeDamage(burnDamage);
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
		/*
		*	Rendering Enemy Combat Effects
		*/
		//Enemy Health
		String health = combatant2.mob.getCurrentHealth() + "/" + combatant2.mob.getMaxHealth();
		Font.render(health, screen, (20-health.length()-1)*8, 5*8, Colors.get(-1,-1,-1,100), 1);
		if (enemyDamageRenderStart != 0 && combatTicks <= enemyDamageRenderStart + 45) {
			Font.render("" + enemyDamageRender, screen, 16*8, 6*8, Colors.get(-1,-1,-1,100), 1);
		}
		//If player ability is Strike/Stab/KnifeThrow.
		if(currentAbility=="Strike      " || currentAbility=="Stab        " ||currentAbility=="Knife Throw ")
		{
			if (enemyOffRenderStart != 0 && combatTicks <= enemyOffRenderStart + 30) {
				screen.render(16*8, 7*8, 30+27*32, Colors.get(-1,100,200,300), 0x00, 1); // Top left
				screen.render(17*8, 7*8, 31+27*32, Colors.get(-1,100,200,300), 0x00, 1); // Top right
				screen.render(16*8, 8*8, 30+28*32, Colors.get(-1,100,200,300), 0x00, 1); // Bottom left
				screen.render(17*8, 8*8, 31+28*32, Colors.get(-1,100,200,300), 0x00, 1); // Bottom right
			}
		}
		//If player ability is Bonk/ShieldBash.
		if(currentAbility=="BONK        " || currentAbility=="ShieldBash  ")
		{
			if (enemyOffRenderStart != 0 && combatTicks <= enemyOffRenderStart + 30) {
				screen.render(16*8, 7*8, 26+27*32, Colors.get(-1,100,510,300), 0x00, 1); // Top left
				screen.render(17*8, 7*8, 27+27*32, Colors.get(-1,100,510,300), 0x00, 1); // Top right
				screen.render(16*8, 8*8, 26+28*32, Colors.get(-1,100,510,300), 0x00, 1); // Bottom left
				screen.render(17*8, 8*8, 27+28*32, Colors.get(-1,100,510,300), 0x00, 1); // Bottom right
			}
		}
		//If player ability is Scorch.
		if(currentAbility=="Scorch        ")
		{
			if (enemyOffRenderStart != 0 && combatTicks <= enemyOffRenderStart + 30) {
				screen.render(16*8, 7*8, 28+27*32, Colors.get(-1,500,510,550), 0x00, 1);// Top left
				screen.render(17*8, 7*8, 29+27*32, Colors.get(-1,500,510,550), 0x00, 1); // Top right
				screen.render(16*8, 8*8, 28+28*32, Colors.get(-1,500,510,550), 0x00, 1); // Bottom left
				screen.render(17*8, 8*8, 29+28*32, Colors.get(-1,500,510,550), 0x00, 1); // Bottom right
			}
		}
		//If player ability is KABOOM.
		if(currentAbility=="KABOOM      ")
		{
			if (enemyOffRenderStart != 0 && combatTicks <= enemyOffRenderStart + 30) {
				screen.render(16*8, 7*8, 28+25*32, Colors.get(-1,500,510,550), 0x00, 1); // Top left
				screen.render(17*8, 7*8, 29+25*32, Colors.get(-1,500,510,550), 0x00, 1); // Top right
				screen.render(16*8, 8*8, 28+26*32, Colors.get(-1,500,510,550), 0x00, 1); // Bottom left
				screen.render(17*8, 8*8, 29+26*32, Colors.get(-1,500,510,550), 0x00, 1); // Bottom right
			}
		}
		//If player ability is Holy Smite.
		if(currentAbility=="Holy  Smite ")
		{
			if (enemyOffRenderStart != 0 && combatTicks <= enemyOffRenderStart + 30) {
				screen.render(16*8, 7*8, 24+25*32, Colors.get(-1,555,005,550), 0x00, 1); // Top left
				screen.render(17*8, 7*8, 25+25*32, Colors.get(-1,555,005,550), 0x00, 1); // Top right
				screen.render(16*8, 8*8, 24+26*32, Colors.get(-1,555,005,550), 0x00, 1); // Bottom left
				screen.render(17*8, 8*8, 25+26*32, Colors.get(-1,555,005,550), 0x00, 1); // Bottom right
			}
		}
		//If player ability is Mark.
		if(currentAbility=="Mark        ")
		{
			if (enemyOffRenderStart != 0 && combatTicks <= enemyOffRenderStart + 30) {
				screen.render(16*8, 7*8, 24+27*32, Colors.get(-1,000,550,500), 0x00, 1); // Top left
				screen.render(17*8, 7*8, 25+27*32, Colors.get(-1,000,550,500), 0x00, 1); // Top right
				screen.render(16*8, 8*8, 24+28*32, Colors.get(-1,000,550,500), 0x00, 1); // Bottom left
				screen.render(17*8, 8*8, 25+28*32, Colors.get(-1,000,550,500), 0x00, 1); // Bottom right
			}
		}
		//If the ability is Smoke Bomb.
		if(currentAbility=="Smoke Bomb  ")
		{
			if (enemyOffRenderStart != 0 && combatTicks <= enemyOffRenderStart + 30) {
				screen.render(2*8, 7*8, 26+25*32, Colors.get(-1,111,222,000), 0x00, 1); // Top left
				screen.render(3*8, 7*8, 27+25*32, Colors.get(-1,111,222,000), 0x00, 1); // Top right
				screen.render(2*8, 8*8, 26+26*32, Colors.get(-1,111,222,000), 0x00, 1); // Bottom left
				screen.render(3*8, 8*8, 27+26*32, Colors.get(-1,111,222,000), 0x00, 1); // Bottom right
			}
		}
		//If the ability is Fire Wall.
		if(currentAbility=="Fire  Wall  ")
		{
			if (enemyOffRenderStart != 0 && combatTicks <= enemyOffRenderStart + 30) {
				//Upper Flame
				screen.render(5*8, 6*8, 28+27*32, Colors.get(-1,500,510,550), 0x00, 1); // Top left
				screen.render(6*8, 6*8, 29+27*32, Colors.get(-1,500,510,550), 0x00, 1); // Top right
				screen.render(5*8, 7*8, 28+28*32, Colors.get(-1,500,510,550), 0x00, 1); // Bottom left
				screen.render(6*8, 7*8, 29+28*32, Colors.get(-1,500,510,550), 0x00, 1); // Bottom right
				//Middle Flame
				screen.render(5*8, 7*8, 28+27*32, Colors.get(-1,500,510,550), 0x00, 1); // Top left
				screen.render(6*8, 7*8, 29+27*32, Colors.get(-1,500,510,550), 0x00, 1); // Top right
				screen.render(5*8, 8*8, 28+28*32, Colors.get(-1,500,510,550), 0x00, 1); // Bottom left
				screen.render(6*8, 8*8, 29+28*32, Colors.get(-1,500,510,550), 0x00, 1); // Bottom right
				//Bottom Flame
				screen.render(5*8, 8*8, 28+27*32, Colors.get(-1,500,510,550), 0x00, 1); // Top left
				screen.render(6*8, 8*8, 29+27*32, Colors.get(-1,500,510,550), 0x00, 1); // Top right
				screen.render(5*8, 9*8, 28+28*32, Colors.get(-1,500,510,550), 0x00, 1); // Bottom left
				screen.render(6*8, 9*8, 29+28*32, Colors.get(-1,500,510,550), 0x00, 1); // Bottom right

			}
		}
		/*
		* Render Player effects that are done onto the player
		*/
		if (playerDamageRenderStart != 0 && combatTicks <= playerDamageRenderStart + 45) {
			Font.render("" + playerDamageRender, screen, 2*8, 6*8, Colors.get(-1,-1,-1,100), 1);
		}
		//If the player takes damage
		if (playerOffRenderStart != 0 && combatTicks <= playerOffRenderStart + 30) {
			screen.render(2*8, 7*8, 30+27*32, Colors.get(-1,100,200,300), 0x00, 1); // Top left
			screen.render(3*8, 7*8, 31+27*32, Colors.get(-1,100,200,300), 0x00, 1); // Top right
			screen.render(2*8, 8*8, 30+28*32, Colors.get(-1,100,200,300), 0x00, 1); // Bottom left
			screen.render(3*8, 8*8, 31+28*32, Colors.get(-1,100,200,300), 0x00, 1); // Bottom right
		}
		//If the ability is Divine Call.
		if(currentAbility=="DivineCall  ")
		{
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
	}

	/**
	 * Object that will house combat attributes.
	 * @author Jackson Schilmoeller
	 * @version 1.0, 29 October 2016
	 */
	public class Combatant {
		public Mob mob;
		public Boolean isStunned;
		public Boolean isBurned;
		public Boolean isWalled;
		public Boolean isBlinded;
		public Boolean isMarked;
		public int stunStart;
		public int burnStart;
		public int wallStart;
		public int blindStart;
		public int markStart;
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
			this.isBurned = false;
			this.isWalled = false;
			this.isBlinded = false;
			this.isMarked = false;
			this.stunStart = 0;
			this.burnStart = 0;
			this.wallStart = 0;
			this.blindStart = 0;
			this.markStart = 0;
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
		 * Sets the burn status effect for a combatant.
		 */
		public void burn() {
			this.isBurned = true;
		}

		/**
		 * Sets the blind status effect for a combatant.
		 */
		public void blind() {
			this.isBlinded = true;
		}

		/**
		 * Sets the mark status effect for a combatant.
		 */
		public void mark() {
			this.isMarked = true;
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

		public void stunPlayer() {
			this.ability1CD += 2;
			this.ability2CD += 2;
			this.ability3CD += 2;
			this.ability4CD += 2;
		}
	}

}
