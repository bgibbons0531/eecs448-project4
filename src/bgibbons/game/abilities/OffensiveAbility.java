package bgibbons.game.abilities;

public class OffensiveAbility extends Ability {
	
	protected int damage;
	protected boolean stun;
	protected String type;
	protected int scale;
	protected int duration;

	/**
	 * Sole constructor for the OffensiveAbility class.
	 * @param id 		Unique id for the ability.
	 * @param name 		The name of the ability.
	 * @param cooldown 	Cooldown for the ability.
	 * @param damage 	Amount of damage the ability does.
	 * @param stun 		If the ability stuns or not.
	 */
	public OffensiveAbility(int id, String name, int cooldown, int damage, String type, int scale, boolean stun, int duration) {
		super(id, name, cooldown);
		this.damage = damage;
		this.stun = stun;
		this.type = type;
		this.scale = scale;
		this.duration = duration;
	}

	/**
	 * Returns the damage of the ability.
	 * @return The damage of the ability. 
	 */
	public int getDamage() {
		return damage;
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
	 * Returns whether or the ability stuns or not.
	 * @return A boolean telling if the ability stuns or not.
	 */
	public boolean getStun() {
		return stun;
	}

	/**
	 * Returns the duration of the ability.
	 * @return the duration of the ability.
	 */
	public int getDuration() {
		return duration;
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