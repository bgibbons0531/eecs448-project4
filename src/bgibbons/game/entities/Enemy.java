package bgibbons.game.entities;

import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

/**
 * An extension of the Mob class for an orc.
 * @author Chris Porras based on Player.java
 * @version 1.0 20 October 2016.
 */

public abstract class Enemy extends Mob {

	protected int xTile;
	protected int yTile;
	protected int scale;
	protected int color;


	/**
	 * Constructor the Enemy object.
	 * @param level 	Level for the Enemy to be added to.
	 * @param x 		The x coordinate the Enemy will start at.
	 * @param y 		The y coordinate the Enemy will start at.
	 * @param xTile 	The x coordinate for the sprite to start at.
	 * @param yTile 	The y coordinate for the sprite to start at.
	 * @param color 	The color to apply to the enemy sprite.
	 * @param maxHealth The maximum health for the enemy.
	 * @param speed 	The speed of the enemy.
	 * @param rank 		The starting rank of the enemy.
	 * @param maxExp	The maximum experience of the enemy.
	 * @param scale 	The scale of the enemy
	 */
	public Enemy(Level level, int x, int y, int xTile, int yTile, int color, int maxHealth, int speed, int rank, int maxExp, int scale) {
		super(level, "Enemy", x, y, maxHealth, speed, rank, maxExp);
		this.xTile = xTile;
		this.yTile = yTile;
		this.color = color;
		this.scale = scale;
	}

	/**
	 * Ticks the Enemy
	 */
	public void tick() {
		// Level orc
		if (currentExp >= maxExp) {
			rankUp();
		}

		if(this instanceof Orc){
			move();
		}


		tickCount++;
	}

	/**
	 * Renders the Enemy to the screen
	 * @param screen 	The screen to render the Enemy to.
	 */
	public void render(Screen screen) {
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;
		int render_xTile = xTile;
		int render_yTile = yTile;

		if (movingDir == 0) {
			render_xTile += 2;
		} else if (movingDir == 1) {
			render_xTile += 6;
		} else if (movingDir > 1) {
			render_xTile += 8 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}

		if (!isMoving) {
			if (lastDir == 0) {
				render_xTile = xTile;
			} else if (lastDir == 1) {
				render_xTile = xTile + 4;
			} else {
				render_xTile = xTile + 8;
			}
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier/2 - 4;
		int yOffset = y - modifier/2 - 4;

		if (isSwimming) {
			int waterColor = 0;
			yOffset += 4;
			if (tickCount % 60 < 15) {
				yOffset -= 1;
				waterColor = Colors.get(-1,-1,225,-1);
			} else if (15 <= tickCount%60 && tickCount%60 < 30) {
				waterColor = Colors.get(-1, 225, 115, -1);
			} else if (30 <= tickCount%60 && tickCount%60 < 45) {
				waterColor = Colors.get(-1, 115, -1, 225);
			} else {
				waterColor = Colors.get(-1, 225, 115, -1);
			}
			screen.render(xOffset, yOffset + 3, 31+29*32, waterColor, 0x00, 1);
			screen.render(xOffset+8, yOffset + 3, 31+29*32, waterColor, 0x01, 1);
		}

		screen.render(xOffset + (modifier * flipTop), yOffset, render_xTile + render_yTile * 32, color, flipTop, scale); //Top left
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, render_xTile + 1 + render_yTile * 32, color, flipTop, scale); //Top right

		if (!isSwimming) {
			screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, render_xTile + (render_yTile + 1) * 32, color, flipBottom, scale); //Bottom left
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, render_xTile + 1 + (render_yTile + 1) * 32, color, flipBottom, scale); //Bottom right
		}
	}

	/**
	 * Checks if the Enemy will collide when moving.
	 * @param xa 	The x direction the Enemy wants to move.
	 * @param ya 	The y direction the Enemy wants to move.
	 */
	public boolean hasCollided(int xa, int ya) {
		int xMin = -4;
		int xMax = 3;
		int yMin = 3;
		int yMax = 7;

		for (int x=xMin; x<xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}

		for (int x=xMin; x<xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}

		for (int y=yMin; y<yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}

		for (int y=yMin; y<yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}


		return false;
	}
	/**
	 * Level up the mob once max exp is reached.
	 */
	public abstract void rankUp();
	
	/*
	* Method to determine the movement of enemies
	*/
	public abstract void move();
}