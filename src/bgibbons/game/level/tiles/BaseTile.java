package bgibbons.game.level.tiles;

import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

/**
 * An extension of the Tile class.
 * @author Brad Gibbons
 * @version 1.0 12 October 2016
 */
public class BaseTile extends Tile {

	protected int tileId;
	protected int tileColor;

	/**
	 * Constructor for the BaseTile object.
	 * @param id 			The id of the tile to be rendered.
	 * @param x 			The x coordinate of the tile in the level.
	 * @param y 			The y coordinate of the tile in the level.
	 * @param tileColor 	Color obtained from the Color.get(int, int, int, int) function.
	 * @param levelColor	Integer value of the color from the level resource to render the specific tile.
	 */
	public BaseTile(int id, int x, int y, int tileColor, int levelColor) {
		super(id, false, false, levelColor);
		this.tileId = x+y*32;
		this.tileColor = tileColor;
	}

	/**
	 * Ticks the tile.
	 */
	public void tick() {
	}

	/**
	 * Renders the tile.
	 * @param screen 	Screen to render to.
	 * @param level 	Level to render from.
	 * @param x 		The x coordinate to begin rendering.
	 * @param y 		The y coordiante to begin rendering.
	 */
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, tileId, tileColor, 0x00, 1);
	}
}