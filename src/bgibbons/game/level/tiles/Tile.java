package bgibbons.game.level.tiles;

import java.lang.RuntimeException;

import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

/**
 * Tile class used to set the tiles for the level.
 * @author Brad Gibbons
 * @version 1.0 12 October 2016
 */
public abstract class Tile {
	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BaseSolidTile(0, 0, 0, Colors.get(000,-1,-1,-1), 0xFF000000);
	public static final Tile STONE = new BaseSolidTile(1,1,0, Colors.get(222,333,-1,-1), 0xFF555555);
	public static final Tile GRASS = new BaseTile(2,2,0, Colors.get(-1, 131, 141, -1), 0xFF00FF00);
	public static final Tile WATER = new AnimatedTile(3, new int[][] {{0,5},{1,5},{2,5}, {1,5}}, Colors.get(-1,004,115,-1), 0xFF0000FF, 500);
	public static final Tile WOOD = new BaseTile(4,3,0, Colors.get(210,320,430,-1), 0xFFAA5500);

	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	private int levelColor;

	/**
	 * Constructor for a tile object.
	 * @param id 			Integer id for the tile.
	 * @param isSolid 		Boolean on if the tile is solid.
	 * @param isEmitter		Boolean on if the tile is an emitter
	 * @param levelColor 	Integer value of the color from the level resource to render the specific tile
	 */
	public Tile(int id, boolean isSolid, boolean isEmitter, int levelColor) {
		this.id = (byte) id;
		if (tiles[id] != null) throw new RuntimeException("Duplicate tile id on " + id);
		this.solid = isSolid;
		this.emitter = isEmitter;
		this.levelColor = levelColor;
		tiles[id] = this;
	}

	/**
	 * Returns the Id of the tile.
	 * @return The Id of the tile.
	 */
	public byte getId() {
		return id;
	}

	/**
	 * Returns whether the tile is solid or not.
	 * @return A boolean, true if solid, else otherwise.
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * Returns whether the tile is an emitter or not.
	 * @return A boolean, true if an emitter, false otherwise.
	 */
	public boolean isEmitter() {
		return emitter;
	}

	/** 
	 * Returns the integer value of the color from the level resource to render the specific tile.
	 * @return The integer value of the color from the level resource to render the specific tile.
	 */
	public int getLevelColor() {
		return levelColor;
	}

	/**
	 * Ticks the tile.
	 */
	public abstract void tick();

	/**
	 * Renders the tile.
	 * @param screen 	Screen to render to.
	 * @param level 	Level to render from.
	 * @param x 		The x coordinate to begin rendering.
	 * @param y 		The y coordiante to begin rendering.
	 */
	public abstract void render(Screen screen, Level level, int x, int y);
}