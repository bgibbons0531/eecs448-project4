package bgibbons.game.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import bgibbons.game.entities.*;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.tiles.Tile;

import java.util.Random;

/**
 * Class to handle the game level including the tiles and and entities.
 * @author Brad Gibbons
 * @author Jackson Schilmoeller
 * @author Chris Porras
 * @version 1.0 12 October, 2016
 */
public class Level {
	
	private byte[] tiles;
	public int width;
	public int height;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Orc> area1Orcs = new ArrayList<Orc>();
	public ArrayList<Orc> area2Orcs = new ArrayList<Orc>();
	public ArrayList<Orc> area3Orcs = new ArrayList<Orc>();
	private String tileImagePath;
	private String entityImagePath;
	private BufferedImage tileImage;
	private BufferedImage entityImage;

	/**
	 * Constructor for the Level object.
	 * @param tileImagePath 	Path to the location of the level tile image.
	 * @param entityImagePath 	Path to the location of the level entity image.
	 */
	public Level(String tileImagePath, String entityImagePath) {
		this.tileImagePath = tileImagePath;
		this.entityImagePath = entityImagePath;
		if (tileImagePath != null) {
			this.loadLevelFromFile();
		} else {
			this.width = 64;
			this.height = 64;
			tiles = new byte[width*height];
			this.generateLevel();
		}
	}

	/**
	 * Load the level from the files specified in the constructor
	 */
	private void loadLevelFromFile() {
		try {
			this.tileImage = ImageIO.read(Level.class.getResourceAsStream(this.tileImagePath));
			if (this.entityImagePath != null) {
				this.entityImage = ImageIO.read(Level.class.getResourceAsStream(this.entityImagePath));
			}
			this.width = tileImage.getWidth();
			this.height = tileImage.getHeight();
			tiles = new byte[width*height];
			if (this.entityImagePath != null) {
				this.loadEntities();
			}
			this.loadTiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the entities into the level.
	 */
	private void loadEntities() {
		int[] entityColors = this.entityImage.getRGB(0, 0, width, height, null, 0, width);
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				if (entityColors[x+y*width] == 0xFFFFFF00) {
					Entity e = new Torch(this, x*8, y*8);
					this.addEntity(e);
				} if (entityColors[x+y*width] == 0xFFFF00FF) {
					Entity e = new HealthPad(this, x*8, y*8);
					this.addEntity(e);
				} if (entityColors[x+y*width] == 0xFF005500) {
					Entity e = new Orc(this, x*8, y*8);
					this.addEntity(e);
				} 
			}
		}
	}

	/**
	 * Loads the tiles into the level.
	 */
	private void loadTiles() {
		int[] tileColors = this.tileImage.getRGB(0, 0, width, height, null, 0, width);
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				tileCheck: for (Tile t : Tile.tiles) {
					if (t != null && t.getLevelColor() == tileColors[x+y*width]) {
						this.tiles[x+y*width] = t.getId();
						break tileCheck;
					}
				}
			}
		}
	}

	/**
	 * Saves the current level state to a file.
	 */
	private void saveLevelToFile() {
		try {
			ImageIO.write(tileImage, "png", new File(Level.class.getResource(this.tileImagePath).getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Changes the tile at a specific tile location with another tile.
	 * @param x 		The x coordinate of the tile to be altered.
	 * @param y 		The y coordinate of the tile to be altered.
	 * @param newTile 	The new tile to replace the old tile.
	 */
	public void alterTile(int x, int y, Tile newTile) {
		this.tiles[x+y*width] = newTile.getId();
		tileImage.setRGB(x, y, newTile.getLevelColor());
	}

	/**
	 * Generates the level tiles if no file is specified.
	 */
	public void generateLevel() {

	}
	
	/**
	 * Ticks the tiles and entities in the level.
	 */
	public void tick() {
		int winner;
		Entity eLoser = null;
		Entity eWinner = null;
		for (Entity e : entities) { 
			e.tick();
			if (!(e instanceof Player)) {
				Entity e1 = getTouching(e);
				if(e1 instanceof Orc && !(e1 instanceof Player)){
					Random rand = new Random();
					winner = rand.nextInt(1);
					if(winner == 0){
						eWinner = e;
						eLoser = e1;
					}
					else if(winner == 1){
						eWinner = e1;
						eLoser = e;
					}
				}
			}
		}

		if(eLoser instanceof Mob && eWinner instanceof Mob){
			removeEntity(eLoser);
			((Mob)eWinner).addExp(20);
		}
		for (Tile t : Tile.tiles) {
			if (t == null) {
				break;
			} else {
				t.tick();
			}
		}
	}

	/**
	 * Checks if the passed entity is touching any entities in the level
	 * @param entity 	Entity to compare with all level entities
	 * @return The level entity that the passed entity is touching, null if not touching
	 */

	public Entity getTouching(Entity entity) {
		for (Entity e : entities) {
			if(entity.isTouching(e) && entity != e) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Renders the tiles to be shown on the screen.
	 * @param screen 	Screen to be rendered to.
	 * @param xOffset 	The x offset of the tiles to be rendered.
	 * @param yOffset 	The y offset of the tiles to be rendered.
	 */
	public void renderTiles(Screen screen, int xOffset, int yOffset) {
		if (xOffset<0) xOffset = 0;
		if (xOffset>((width<<3)-screen.width)) xOffset = ((width<<3)-screen.width);
		if (yOffset<0) yOffset = 0;
		if (yOffset>((height<<3)-screen.height)) yOffset = ((height<<3)-screen.height);

		screen.setOffset(xOffset, yOffset);

		for (int y=(yOffset >> 3); y<(yOffset + screen.height>>3) + 1; y++) {
			for (int x=(xOffset >>3); x<(xOffset + screen.width>>3) + 1; x++) {
				getTile(x,y).render(screen, this, x<<3,y<<3);
			}
		}
	}

	/**
	 * Renders the entities on the level.
	 * @param screen 	Screen to render the entities onto.
	 */
	public void renderEntities(Screen screen) {
		for (Entity e : entities) {
			e.render(screen);
		}
	}

	/**
	 * Returns the tile at the given coordinate
	 * @param x 	The x coordinate of the tile to be returned.
	 * @param y 	The y coordinate of the tile to be returned.
	 * @return The Tile object at the specified coordinates.
	 */
	public Tile getTile(int x, int y) {
		if (0 > x || x >= width || 0 > y || y >= height) return(Tile.VOID);
		return Tile.tiles[tiles[x+y*width]];
	}

	/**
	 * Adds an entity to the level.
	 * @param entity 	The entity to be added to the level.
	 */
	public void addEntity(Entity entity) {
		this.entities.add(entity);
		if(entity instanceof Orc){
			if((entity.x)<65*8 && (entity.x)>8){
				this.area1Orcs.add((Orc)entity);
			}
			else if((entity.x)>84*8 && (entity.x)<148*8){
				this.area2Orcs.add((Orc)entity);
			}
			else if((entity.x)>167*8 && (entity.x)<232*8){
				this.area3Orcs.add((Orc)entity);
			}
		}
		entity.setLevel(this);
	}

	/**
	 * Removes entity from the level.
	 * @param entity 	Removes given entity from the level, if it is there.
	 * @return An entity, if the entity was in the level and successfully removed, otherwise null.
	 */
	public Entity removeEntity(Entity entity){
		if(entities.remove(entity))
			return entity;
		return null;
	} 	
}