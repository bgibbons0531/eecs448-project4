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

public class Orc extends Mob {

	private int color = Colors.get(-1, 000, 320, 120);
	private int scale = 1;

	/**
	 * Constructor the Orc object.
	 * @param level 	Level for the orc to be added to.
	 * @param x 		The x coordinate the orc will start at.
	 * @param y 		The y coordinate the orc will start at.
	 */
	public Orc(Level level, int x, int y) {
		super(level, "Orc", x, y, 10, 1, 1, 100);
	}

	/**
	 * Ticks the orc.
	 */
	public void tick() {
		// Level orc
		if (currentExp >= maxExp) {
			rankUp();
		}

		// Move orc
		int xa = 0;
		int ya = 0;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;
		} else {
			isMoving = false;
		}

		if (level.getTile(this.x >> 3, this.y >> 3).getId() == 3) {
			isSwimming = true;
		}
		if  (isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 3) {
			isSwimming = false;
		}
		tickCount++;
	}

	/**
	 * Renders the orc to the screen
	 * @param screen 	The screen to render the orc to.
	 */
	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 26;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		if (movingDir == 0) {
			xTile += 2;
		} else if (movingDir == 1) {
			xTile += 6;
		} else if (movingDir > 1) {
			xTile += 8 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}

		if (!isMoving) {
			if (lastDir == 0) {
				xTile = 0;
			} else if (lastDir == 1) {
				xTile = 4;
			} else {
				xTile = 8;
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

		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, color, flipTop, scale); //Top left
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, xTile + 1 + yTile * 32, color, flipTop, scale); //Top right

		if (!isSwimming) {
			screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, color, flipBottom, scale); //Bottom left
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, xTile + 1 + (yTile + 1) * 32, color, flipBottom, scale); //Bottom right
		}
	}

	/**
	 * Checks if the orc will collide when moving.
	 * @param xa 	The x direction the orc wants to move.
	 * @param ya 	The y direction the orc wants to move.
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
	public void rankUp() {
		currentExp = 0;
		rank ++;
		dexterity ++;
		intelligence ++;
		vitality ++;
	}
}