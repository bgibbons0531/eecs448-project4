package bgibbons.game.abilities;

public class DefensiveAbility extends Ability {

	protected int heal;
	protected int shield;
	protected String type;
	protected int scale;

	/**
	 * Sole constructor for the DefensiveAbility class.
	 * @param id 		Unique id for the ability.
	 * @param name 		The name of the ability.
	 * @param cooldown 	Cooldown for the ability.
	 * @param heal 		Amount of health the ability heals.
	 * @param shield	Amount of shield the ability gives.
	 */
	public DefensiveAbility(int id, String name, int cooldown, int heal, String type, int scale, int shield) {
		super(id, name, cooldown);
		this.heal = heal;
		this.shield = shield;
		this.type = type;
		this.scale = scale;
	}

	/**
	 * Returns the heal the ability gives.
	 * @return The heal the ability gives.
	 */
	public int getHeal() {
		return heal;
	}

	/**
	 * Returns the type of stat the ability scales with.
	 * @return A string of the stat to scale with, empty string for no scaling, rank for scale on rank.
	 */
	public String getType(){
		return type;
	}

	/**
	 * Returns the scaling of the ability. Specifically, the number of x stats required to increase the damage/heal by 1 point.
	 * @return The scaling of the ability. 
	 */
	public int getScale(){
		return scale;
	}

	/**
	 * Returns the shield the ability gives.
	 * @return the shield the ability gives.
	 */
	public int getShield() {
		return shield;
	}

	/**
	 * Returns the damage of the ability.
	 * @return The damage of the ability. 
	 */
	public int getDamage() {
		return 0;
	}

	/**
	 * Returns whether or the ability stuns or not.
	 * @return A boolean telling if the ability stuns or not.
	 */
	public boolean getStun() {
		return false;
	}
}