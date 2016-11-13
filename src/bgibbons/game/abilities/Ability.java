package bgibbons.game.abilities;
import java.lang.RuntimeException;

/**
 * Base ability class to be extended by other abilities.
 * @author Brad Gibbons
 * @author Rony Singh
 * @version 1.0. 40 November 2016.
 */
public abstract class Ability 
{
	public static final Ability[] abilities = new Ability[256];
	public static final Ability STRIKE = new OffensiveAbility(0, "Strike      ", 1, 2, false);
	public static final Ability SHIELD_BASH = new OffensiveAbility(1, "ShieldBash  ", 6, 1, true);
	public static final Ability DIVINE_CALL = new DefensiveAbility(2, "DivineCall  ", 10, 2, 1);
	public static final Ability HOLY_SMITE = new OffensiveAbility(3, "Holy  Smite ", 12, 10, false);

	public static final Ability BONK = new OffensiveAbility(4, "BONK        ", 1, 2, false);
	public static final Ability SCORCH = new OffensiveAbility(5, "Scorch        ", 6, 1, true);
	public static final Ability FIRE_WALL = new DefensiveAbility(6, "Fire  Wall  ", 10, 2, 1);
	public static final Ability KABOOM = new OffensiveAbility(7, "KABOOM      ", 12, 10, false);

	public static final Ability STAB = new OffensiveAbility(8, "Stab        ", 1, 2, false);
	public static final Ability KNIFE_THROW = new OffensiveAbility(9, "Knife Throw ", 6, 1, true);
	public static final Ability SMOKE_BOMB = new DefensiveAbility(10, "Smoke Bomb  ", 10, 2, 1);
	public static final Ability MARK = new OffensiveAbility(11, "Mark        ", 12, 10, false);
	
	protected byte id;
	protected String name;
	protected int cooldown;

	/**
	 * Constructor for the Ability clas.
	 * @param id 		Unique id for the ability.
	 * @param name 		Name of the ability.
	 * @param cooldown 	Cooldown for the ability.
	 */
	public Ability(int id, String name, int cooldown) {
		this.id = (byte) id;
		this.name = name;
		this.cooldown = cooldown;

		if (abilities[id] != null) throw new RuntimeException("Duplicate ability id on " + id);

		abilities[id] = this;
	}

	/**
	 * Returns the Id of the Ability.
	 * @return The Id of the Ability.
	 */
	public byte getId() {
		return id;
	}

	/**
	 * Returns the name of the Ability.
	 * @return The name of the Ability.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the cooldown of the Ability.
	 * @return the cooldown of the Ability.
	 */
	public int getCooldown() {
		return cooldown;
	}

	/**
	 * Returns the damage of the ability.
	 * @return The damage of the ability. 
	 */
	public abstract int getDamage();

	/**
	 * Returns whether or the ability stuns or not.
	 * @return A boolean telling if the ability stuns or not.
	 */
	public abstract boolean getStun();

	/**
	 * Returns the heal the ability gives.
	 * @return The heal the ability gives.
	 */
	public abstract int getHeal();

	/**
	 * Returns the shield the ability gives.
	 * @return the shield the ability gives.
	 */
	public abstract int getShield();

}