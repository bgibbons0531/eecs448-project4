package bgibbons.game.entities;

import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

/**
 * An extension of the Entity class for a Torch.
 * @author Brad Gibbons
 * @version 1.0 24 September 2016.
 */
public class Torch extends Entity {
	
	private int x;
	private int y;
	private int tileId;
	private int[][] animationTileCoords;
	private int currentAnimationIndex;
	private long lastIterationTime;
	private int animationSwitchDelay;

	/**
	 * Constructor for the Torch object.
	 * @param level 	Level to add the Torch to.
	 * @param x 		The x coordinate of the Torch.
	 * @param y 		The y coordinate of the Torch.
	 */
	public Torch(Level level, int x, int y) {
		super(level);
		this.x = x;
		this.y = y;
		this.tileId = 0 + 7*32;
		this.animationTileCoords = new int[][] {{0,7},{1,7},{2,7}};
		this.currentAnimationIndex = 0;
		this.lastIterationTime = System.currentTimeMillis();
		this.animationSwitchDelay = 350;
	}

	/**
	 * Ticks the torch to allow for animation.
	 */
	public void tick() {
		if ((System.currentTimeMillis() - lastIterationTime) >= animationSwitchDelay) {
			lastIterationTime = System.currentTimeMillis();
			currentAnimationIndex = (currentAnimationIndex + 1) % animationTileCoords.length;
			this.tileId = animationTileCoords[currentAnimationIndex][0] + (animationTileCoords[currentAnimationIndex][1] * 32);
		}
		tickCount++;
	}

	/**
	 * Renders the torch to the screen
	 * @param screen 	The screen the torch is to be rendered to.
	 */
	public void render(Screen screen) {
		screen.render(x, y, tileId, Colors.get(-1, 211, 330, 530), 0x00, 1);
	}

	/**
	 * Checks if torch is touching another torch
	 * @param entity 	The entity to compare caller torch to.
	 * @return A boolean, true if the mobs are touching, false otherwise.
	 */
	public boolean isTouching(Entity entity) {
		double hitbox = 2.0;
		double xdif = (this.x - entity.x);
		double ydif = (this.y - entity.y);
		double distance = Math.sqrt(Math.abs(xdif)*Math.abs(xdif) + Math.abs(ydif)*Math.abs(ydif));
		if(distance < hitbox)
			return true;
		else
			return false;
	}
}