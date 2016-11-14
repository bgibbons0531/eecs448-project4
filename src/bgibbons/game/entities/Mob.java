package bgibbons.game.entities;

import bgibbons.game.abilities.*;
import bgibbons.game.level.Level;
import bgibbons.game.level.tiles.Tile;

import java.lang.Math;

/**
 * A class to extend entities to moving objects.
 * @author Brad Gibbons
 * @author Chris Porras
 * @author Jackson Schilmoeller
 * @version 1.0 12 October 2016
 */
public abstract class Mob extends Entity {

	protected String name;
	protected int rank;
	protected int killCount;
	protected int maxHealth;
	protected int currentHealth;
	protected int maxExp;
	protected int currentExp;
	protected int dexterity = 5;
	protected int intelligence = 5;
	protected int vitality = 5;
	protected int baseDex = 5;
	protected int baseInt = 5;
	protected int baseVit = 5;
	protected Ability ability1;
	protected Ability ability2;
	protected Ability ability3;
	protected Ability ability4;

	protected int speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected boolean isSwimming;
	protected int movingDir = 1; //0 is up, 1 is down, 2 is left, 3 and right
	protected int lastDir = 1;
	protected int scale = 1;

	/**
	 * Constructor for the Mob object.
	 * @param level 		Level to for the mob to be added to.
	 * @param name 			Name of the mob.
	 * @param x 			The x coordinate of the mob.
	 * @param y 			The y coordinate of the mob.
	 * @param maxHealth 	The max health the mob is to have, whole numbers preferred.
	 * @param speed 		The speed of the mob.
	 * @param rank 			The starting rank of the mob.
	 * @param maxExp 		The exp needed for the mob to rank up again.
	 */
	public Mob(Level level, String name, int x, int y, int maxHealth, int speed, int rank, int maxExp) {
		super(level);
		this.name = name;
		this.rank = rank;
		this.killCount = 0;
		this.x = x;
		this.y = y;
		this.maxExp = maxExp;
		this.currentExp = 0;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		this.speed = speed;
		this.isMoving = false;
		this.isSwimming = false;
		this.dexterity = 5;
		this.intelligence = 5;
		this.vitality = 5;
		this.baseDex = 5;
		this.baseInt = 5;
		this.baseVit = 5;
		this.ability1 = Ability.STRIKE;
		this.ability2 = Ability.SHIELD_BASH;
		this.ability3 = Ability.DIVINE_CALL;
		this.ability4 = Ability.HOLY_SMITE;
	}

	/**
	 * Moves the mob to a target direction
	 * @param xa 	The x direction to move the mob.
	 * @param ya 	The y direction to move the mob.
	 */
	public void move(int xa, int ya) {
		this.lastDir = movingDir;
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			numSteps--;
			return;
		}
		numSteps++;
		if (!hasCollided(xa, ya)) {
			if (ya < 0) movingDir = 0;
			if (ya > 0) movingDir = 1;
			if (xa < 0) movingDir = 2;
			if (xa > 0) movingDir = 3;

			x += xa*speed;
			y += ya*speed;
		}
	}

	/**
	 * Method to check if the mob has collided if it were to move to a direction
	 * @param xa 	The x direction the mob would move to.
	 * @param ya 	The y direction the mob would move to.
	 * @return A boolean, true if will collide, false otherwise.
	 */
	public abstract boolean hasCollided(int xa, int ya);

	/**
	 * Checks if the mob is moving into a solid tile.
	 * @param xa 	The x direction the mob would move to.
	 * @param ya 	The y direction the mob would move to.
	 * @param x 	The mob's current x location.
	 * @param y 	The mob's current y location.
	 * @return A boolean, true if the tile the the mob would be moving onto is solid, false otherwise.
	 */
	protected boolean isSolidTile(int xa, int ya, int x, int y) {
		if (level == null) {
			return false;
		}

		Tile lastTile = level.getTile((this.x + x)>>3, (this.y + y)>>3);
		Tile newTile = level.getTile((this.x + x + xa)>>3, (this.y + y + ya)>>3);

		if (!lastTile.equals(newTile) && newTile.isSolid()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if mob is touching another mob
	 * @param entity 	The entity to compare caller mob to.
	 * @return A boolean, true if the mobs are touching, false otherwise.
	 */
	public boolean isTouching(Entity entity) {
		double hitbox = 8.0;
		double xdif = (this.x - entity.x);
		double ydif = (this.y - entity.y);
		double distance = Math.sqrt(Math.abs(xdif)*Math.abs(xdif) + Math.abs(ydif)*Math.abs(ydif));
		if(distance < hitbox)
			return true;
		else
			return false;
	}

	/**
	 * Returns the name of the mob.
	 * @return The name of the mob.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the max health of the mob.
	 * @return The max health of the mob.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Set's the max health of the mob.
	 * @param maxHealth 	The new value the health it to be.
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * Returns the current health of the mob.
	 * @return The current health of the mob.
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}

	/**
	 * Heals the mob for the set amount of health.
	 * @param health 	The amount of health to heal the mob
	 */
	public void heal(int health) {
		if (currentHealth + health >= maxHealth) {
			currentHealth = maxHealth;
		} else {
			currentHealth += health;
		}
	}

	/**
	 * Deals damage to the mob's current health.
	 * @param damage 	The amount of damage to do to the mob.
	 */
	public void takeDamage(int damage) {
		currentHealth -= damage;
	}

	/**
	 * Returns the Mob's rank.
	 * @return the Mob's rank.
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Level up the mob once max exp is reached.
	 */
	public abstract void rankUp();

	/**
	 * Returns the max exp of the mob.
	 * @return The max exp of the mob.
	 */
	public int getMaxExp() {
		return maxExp;
	}

	/**
	 * Set the max exp of the mob.
	 * @param maxExp 	The new max exp of the mob.
	 */
	public void setMaxExp(int maxExp) {
		maxExp = maxExp;
	}

	/**
	 * Returns the current exp of the mob.
	 * @return The current exp of the mob.
	 */
	public int getCurrentExp() {
		return currentExp;
	}

	/**
	 * Returns the current dexterity of the mob.
	 * @return The current dexterity of the mob.
	 */
	public int getDexterity(){
		return dexterity;
	}

	/**
	 * Returns the current intellgence of the mob.
	 * @return The current intelligence of the mob.
	 */
	public int getIntelligence(){
		return intelligence;
	}


	/**
	 * Returns the current vitality of the mob.
	 * @return The current vitality of the mob.
	 */
	public int getVitality(){
		return vitality;
	}

	/**
	 * Add exp to the mob.
	 * @param exp 	Amount of exp to add to the mob.
	 */
	public void addExp(int exp) {
		if (currentExp + exp < maxExp) {
			currentExp += exp;
		} else {
			currentExp = maxExp;
		}
	}

	/**
	 * Returns the ability of the mob based on the index.
	 * @param index 	Index for the ability to return.
	 * @return The ability of the mob based on the index.
	 */
	public Ability getAbility(int index) {
		switch(index) {
			case(1): 
				return ability1;
			case(2):
				return ability2;
			case(3):
				return ability3;
			case(4):
				return ability4;
			default:
				return null;
		}
	}

	/**
	 * Increases the kill count of the mob.
	 */
	public void addKill() {
		killCount++;
	}

	/**
	 * Returns the kill count of the mob.
	 * @return The kill count of the mob.
	 */
	public int getKillCount() {
		return killCount;
	}

}