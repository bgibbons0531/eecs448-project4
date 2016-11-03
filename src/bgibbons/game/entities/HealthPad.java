package bgibbons.game.entities;

import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

/**
 * An extension of the Entity class for a HealthPad.
 * @author Brad Gibbons
 * @version 1.0 24 October 2016.
 */
public class HealthPad extends Entity {
	
	private int tileId;
	private int color;
	private int health;
	private boolean active;
	private int respawnRate; // Number of ticks between health pad respawning
	private int nextRespawn; // Tickcount at next pick up

	/**
	 * Constructor for the HealthPad object.
	 * @param level 	Level to add the HealthPad to.
	 * @param x 		The x coordinate of the HealthPad.
	 * @param y 		The y coordinate of the HealthPad.
	 */
	public HealthPad(Level level, int x, int y) {
		super(level);
		this.x = x;
		this.y = y;
		this.tileId = 0 + 6*32;
		this.health = 10;
		this.active = true;
		this.respawnRate = 60*60;
		this.nextRespawn = 0;
		this.color = Colors.get(-1, 000, 500, 555);
	}

	/**
	 * Ticks the health pad to check for pad respawn.
	 */
	public void tick() {
		if (!active && tickCount >= nextRespawn) {
			active = true;
			color = Colors.get(-1, 000, 500, 555);
		}
		tickCount++;
	}

	/**
	 * Renders the HealthPad to the screen
	 * @param screen 	The screen the HealthPad is to be rendered to.
	 */
	public void render(Screen screen) {
		screen.render(x, y, tileId, color, 0x00, 1);
	}

	/**
	 * Returns the health value to heal the player by and deactivates the health.
	 * @return The health value to heal the player by.
	 */
	public int activate() {
		if (active) {
			active = false;
			color = Colors.get(-1, 000, 000, 555);
			nextRespawn = tickCount + respawnRate;
			return health;
		} else {
			return 0;
		}
	}

	/**
	 * Checks to see if the health pad is touching a given entity
	 * @param entity 	Entity to compare health pad to
	 * @return A boolean, true if health pad is touching entity, false otherwise.
     */
	public boolean isTouching(Entity entity){
		return false;
	}
}