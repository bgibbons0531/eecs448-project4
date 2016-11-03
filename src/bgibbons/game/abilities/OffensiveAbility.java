package bgibbons.game.abilities;

public class OffensiveAbility extends Ability {
	
	protected int damage;
	protected boolean stun;

	/**
	 * Sole constructor for the OffensiveAbility class.
	 * @param id 		Unique id for the ability.
	 * @param name 		The name of the ability.
	 * @param cooldown 	Cooldown for the ability.
	 * @param damage 	Amount of damage the ability does.
	 * @param stun 		If the ability stuns or not.
	 */
	public OffensiveAbility(int id, String name, int cooldown, int damage, boolean stun) {
		super(id, name, cooldown);
		this.damage = damage;
		this.stun = stun;
	}

	/**
	 * Returns the damage of the ability.
	 * @return The damage of the ability. 
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Returns whether or the ability stuns or not.
	 * @return A boolean telling if the ability stuns or not.
	 */
	public boolean getStun() {
		return stun;
	}

	/**
	 * Returns the heal the ability gives.
	 * @return The heal the ability gives.
	 */
	public int getHeal() {
		return 0;
	}

	/**
	 * Returns the shield the ability gives.
	 * @return the shield the ability gives.
	 */
	public int getShield() {
		return 0;
	}
}