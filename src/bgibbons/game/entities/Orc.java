package bgibbons.game.entities;

import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

/**
 * An extension of the Enemy class for an orc.
 * @author Chris Porras based on Player.java
 * @version 1.0 20 October 2016.
 */

public class Orc extends Enemy {

	/**
	 * Constructor the Orc object.
	 * @param level 	Level for the orc to be added to.
	 * @param x 		The x coordinate the orc will start at.
	 * @param y 		The y coordinate the orc will start at.
	 */
	public Orc(Level level, int x, int y) {
		super(level, x, y, 0, 26, Colors.get(-1, 000, 320, 120), 10, 1, 1, 100);
	}

	/**
	 * Level up the orc once max exp is reached.
	 */
	public void rankUp() {
		currentExp = 0;
		rank ++;
		dexterity ++;
		intelligence ++;
		vitality ++;
	}
}