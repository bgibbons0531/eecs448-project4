package bgibbons.game.abilities;

public class DefensiveAbility extends Ability {

	protected int heal;
	protected int shield;

	/**
	 * Sole constructor for the DefensiveAbility class.
	 * @param id 		Unique id for the ability.
	 * @param name 		The name of the ability.
	 * @param cooldown 	Cooldown for the ability.
	 * @param heal 		Amount of health the ability heals.
	 * @param shield	Amount of shield the ability gives.
	 */
	public DefensiveAbility(int id, String name, int cooldown, int heal, int shield) {
		super(id, name, cooldown);
		this.heal = heal;
		this.shield = shield;
	}

	/**
	 * Returns the heal the ability gives.
	 * @return The heal the ability gives.
	 */
	public int getHeal() {
		return heal;
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