package bgibbons.game.level.tiles;

/**
 * An extension of the BaseTile class for solid tiles.
 * @author Brad Gibbons
 * @version 1.0 12 October 2016
 */
public class BaseSolidTile extends BaseTile {
	
	/**
	 * Constructor for the BaseSolidTile object.
	 * @param id 			The id of the tile to be rendered.
	 * @param x 			The x coordinate of the tile in the level.
	 * @param y 			The y coordinate of the tile in the level.
	 * @param tileColor 	Color obtained from the Color.get(int, int, int, int) function.
	 * @param levelColor	Integer value of the color from the level resource to render the specific tile.
	 */
	public BaseSolidTile(int id, int x, int y, int tileColor, int levelColor) {
		super(id, x, y, tileColor, levelColor);
		this.solid = true;
	}
}