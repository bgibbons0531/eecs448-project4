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
	public int start;
	public int end;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Orc> area1Orcs = new ArrayList<Orc>();
	public ArrayList<Orc> area2Orcs = new ArrayList<Orc>();
	public ArrayList<Orc> area3Orcs = new ArrayList<Orc>();
	public Entity boss;
	private String tileImagePath;
	private String entityImagePath;
	private BufferedImage tileImage;
	private BufferedImage entityImage;
	private boolean mainLevel;

	/**
	 * Constructor for the Level object.
	 * @param tileImagePath 	Path to the location of the level tile image.
	 * @param entityImagePath 	Path to the location of the level entity image.
	 * @param mainLevel		True if mainLevel, false otherwise
	 */
	public Level(String tileImagePath, String entityImagePath, boolean mainLevel) {
		this.tileImagePath = tileImagePath;
		this.entityImagePath = entityImagePath;
		this.mainLevel = mainLevel;
		if (tileImagePath != null) {
			this.loadLevelFromFile();
		} else {
			Random rand = new Random();
			this.width = 64;
			this.height = 64;
			this.start = rand.nextInt(height-3) + 1;
			System.out.println(start);
			this.end = rand.nextInt(height-3) + 1;
			System.out.println(end);
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
				} if (entityColors[x+y*width] == 0xFFFF0000){
					Entity e = new Boss(this, x*8, y*8);
					this.addEntity(e);
					this.boss = e;
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
		//set all tiles to stone
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				this.tiles[x+y*width] = 1;
			}
		}
		//begin procedural generation, algorithm based on The Game of Life by John Conway
		//loop through all the tiles, minus borders
		Random rand = new Random();
		int temp = 0;
		int test = 0;
		Boolean[][] map = new Boolean[width][height];

		//initialize map to all false
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				map[x][y] = false;
			}
		}

		//initialize map with a 45% of being live
		for (int y=2; y<(height-2); y++) {
			for (int x=2; x<(width-2); x++) {
				if(rand.nextInt(100) <= 40)
					map[x][y] = true;
				else
					map[x][y] = false;
			}
		}

		//generate map
		for(int i=0; i<2; i++){
			map = proceduralStep(map);
		}

		//set forced values for map
		map[0][start] = true;
		map[0][start+1] = true;
		map[1][start] = true;
		map[1][start+1] = true;
		map[2][start] = true;
		map[2][start+1] = true;
		map[3][start] = true;
		map[3][start+1] = true;
		map[4][start] = true;
		map[4][start+1] = true;
		map[width-1][end] = true;
		map[width-1][end+1] = true;
		map[width-2][end] = true;
		map[width-2][end+1] = true;
		map[width-3][end] = true;
		map[width-3][end+1] = true;
		map[width-4][end] = true;
		map[width-4][end+1] = true;
		map[width-5][end] = true;
		map[width-5][end+1] = true;

		while(!checkValidDungeon(map, 0, start)){
			for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					map[x][y] = false;
				}
			}

			for (int y=2; y<(height-2); y++) {
				for (int x=2; x<(width-2); x++) {
					if(rand.nextInt(100) <= 40)
						map[x][y] = true;
					else
						map[x][y] = false;
				}
			}

			for(int i=0; i<2; i++){
				map = proceduralStep(map);
			}

			map[0][start] = true;
			map[0][start+1] = true;
			map[1][start] = true;
			map[1][start+1] = true;
			map[2][start] = true;
			map[2][start+1] = true;
			map[3][start] = true;
			map[3][start+1] = true;
			map[4][start] = true;
			map[4][start+1] = true;
			map[width-1][end] = true;
			map[width-1][end+1] = true;
			map[width-2][end] = true;
			map[width-2][end+1] = true;
			map[width-3][end] = true;
			map[width-3][end+1] = true;
			map[width-4][end] = true;
			map[width-4][end+1] = true;
			map[width-5][end] = true;
			map[width-5][end+1] = true;
			System.out.println("moomoomoomoo");
		}

		//apply tiles to map
		for (int y=2; y<(height-2); y++){
			for (int x=2; x<(width-2); x++){
				if(map[x][y])
					this.tiles[x+y*width] = 2;
				else
					this.tiles[x+y*width] = 1;
			}
		}

		//set forced start tiles
		this.tiles[0+start*width] = 2;
		this.tiles[0+(start+1)*width] = 2;
		this.tiles[1+start*width] = 2;
		this.tiles[1+(start+1)*width] = 2;
		this.tiles[2+start*width] = 2;
		this.tiles[2+(start+1)*width] = 2;
		this.tiles[3+start*width] = 2;
		this.tiles[3+(start+1)*width] = 2;
		this.tiles[4+start*width] = 2;
		this.tiles[4+(start+1)*width] = 2;
		//set forced end tiles
		this.tiles[(width - 1)+end*width] = 2;
		this.tiles[(width - 1)+(end+1)*width] = 2;
		this.tiles[(width - 2)+end*width] = 2;
		this.tiles[(width - 2)+(end+1)*width] = 2;
		this.tiles[(width - 3)+end*width] = 2;
		this.tiles[(width - 3)+(end+1)*width] = 2;
		this.tiles[(width - 4)+end*width] = 2;
		this.tiles[(width - 4)+(end+1)*width] = 2;
		this.tiles[(width - 5)+end*width] = 2;
		this.tiles[(width - 5)+(end+1)*width] = 2;
	}

	public boolean checkValidDungeon(Boolean[][] map, int x, int y){
		boolean check = true;
		Boolean[][] tempMap = map;
		int dir = 1;
		x++;
		x++;
		while((!(x == 1 && y == start) && !(x == 62 && y == end)) && dir != 4){
			switch(dir){
				case 0:	
					if(x != 0 && x != tempMap.length && y != 0 && y != tempMap[0].length){
						if(!tempMap[x-1][y]){
							//System.out.println("left wall found");
							if(tempMap[x][y-1]){
								//System.out.println("can go forward");
								y--;
							}
							else if(tempMap[x+1][y]){
								//System.out.println("can go right");
								x++;
								dir = 1;
							}
							else if(tempMap[x][y+1]){
								//System.out.println("can go back");
								y++;
								dir = 2;
							}
							else{
								//System.out.println("rip, no way out");
								dir = 4;
								check = false;
							}
						}
						else{
							//System.out.println("left wall not found, go left");
							x--;
							dir = 3;
						}
					}
					else{
						//System.out.println("rip, out of bounds");
						dir = 4;
						check = false;
					}
					break;
				case 1:
					if(x != 0 && x != tempMap.length && y != 0 && y != tempMap[0].length){
						if(!tempMap[x][y-1]){
							//System.out.println("left wall found");
							if(tempMap[x+1][y]){
								//System.out.println("can go forward");
								x++;
							}
							else if(tempMap[x][y+1]){
								//System.out.println("can go right");
								y++;
								dir = 2;
							}
							else if(tempMap[x-1][y]){
								//System.out.println("can go back");
								x--;
								dir = 3;
							}
							else{
								//System.out.println("rip, no way out");
								dir = 4;
								check = false;
							}
						}
						else{
							//System.out.println("left wall not found, go left");
							y--;
							dir = 0;
						}
					}
					else{
						//System.out.println("rip, out of bounds");
						dir = 4;
						check = false;
					}
					break;
				case 2:
					if(x != 0 && x != tempMap.length && y != 0 && y != tempMap[0].length){
						if(!tempMap[x+1][y]){
							//System.out.println("left wall found");
							if(tempMap[x][y+1]){
								//System.out.println("can go forward");
								y++;
							}
							else if(tempMap[x-1][y]){
								//System.out.println("can go right");
								x--;
								dir = 3;
							}
							else if(tempMap[x][y-1]){
								//System.out.println("can go back");
								y--;
								dir = 0;
							}
							else{
								//System.out.println("rip, no way out");
								dir = 4;
								check = false;
							}
						}
						else{
							//System.out.println("left wall not found, go left");
							x++;
							dir = 1;
						}
					}
					else{
						//System.out.println("rip, out of bounds");
						dir = 4;
						check = false;
					}
					break;
				case 3:
					if(x != 0 && x != tempMap.length && y != 0 && y != tempMap[0].length){
						if(!tempMap[x][y+1]){
							//System.out.println("left wall found");
							if(tempMap[x-1][y]){
								//System.out.println("can go forward");
								x--;
								dir = 3;
							}
							else if(tempMap[x][y-1]){
								//System.out.println("can go right");
								y--;
								dir = 0;
							}
							else if(tempMap[x+1][y]){
								//System.out.println("can go back");
								x++;
								dir = 1;
							}
							else{
								//System.out.println("rip, no way out");
								dir = 4;
								check = false;
							}
						}
						else{
							//System.out.println("left wall not found, go left");
							y++;
							dir = 2;
						}
					}
					else{
						//System.out.println("rip, out of bounds");
						dir = 4;
						check = false;
					}
					break;
				case 4:
					//System.out.println("rip, dir 4");
					check = false;
					break;
				default:
					check = false;
					break;
			}
		}
		if(x == 62 && y == end){
			System.out.println("end found");
		}
		else if(x == 1 && y == start){
			System.out.println("start found");
		}
		else{
			System.out.println(check);
		}
		return check;
	}

	/**
	 * Uses 4 rules of "The Game of Life" to generate a new map.
	 * @param oldMap	Old boolean map representing the dungeon.
	 * @return A new boolean map representing the dungeon.
	 */
	public Boolean[][] proceduralStep(Boolean[][] oldMap){
		Boolean[][] newMap = new Boolean[width][height];
		for(int y=0; y<oldMap[0].length; y++){
			for(int x=0; x<oldMap.length; x++){
				int neighbours = checkTile(oldMap, x, y);
				if(oldMap[x][y]){
					if(neighbours < 2){
						newMap[x][y] = false;
					}
					else{
						newMap[x][y] = true;
					}
				}
				else{
					if(neighbours > 3){
						newMap[x][y] = true;
					}
					else{
						newMap[x][y] = false;
					}
				}
			}
		}
		return newMap;
	}

	/**
	 * Counts the number of live tiles surrounding the passed tile.
	 * @param map 	The boolean map representing the dungeon.
	 * @param x 	The x coordinate of the current tile.
	 * @param y 	The y coordinate of the current tile.
	 * @return The number of live tiles surrounding the passed tile.
	 */
	public int checkTile(Boolean[][] map, int x, int y){
		int count = 0;
		for(int j=-1; j<=1; j++){
			for(int i=-1; i<=1; i++){
				if(i==0 && j==0){
					//Looking at current tile
				}
				else if((x+i) < 0 || (y+j) < 0 || (x+i) >= map.length || (y+j) >= map[0].length){
					count++;
				}
				else if(map[x+i][y+j]){
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Returns the start tile
	 * @return The int corresponding to the start tile.
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Returns the end tile
	 * @return The int corresponding to the end tile.
	 */
	public int getEnd() {
		return end;
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
			if(((Mob)eWinner).getRank()>((Mob)this.boss).getRank()){
				((Mob)this.boss).rankUp();
				removeEntity(eWinner);
			}
		}
		for (Tile t : Tile.tiles) {
			if (t == null) {
				break;
			} else {
				t.tick();
			}
		}
		if(mainLevel){
			if(this.area1Orcs.size()<20){
				this.respawnOrcs(area1Orcs, 33, 1);
			}
			if(this.area2Orcs.size()<20){
				this.respawnOrcs(area2Orcs, 117, 5);
			}
			if(this.area3Orcs.size()<20){
				this.respawnOrcs(area3Orcs, 200, 10);
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
		if(entity instanceof Orc){								//adds orcs to their proper arraylist based on starting location
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
		if(entity instanceof Orc){								//removes orcs from their proper arraylist based on death location
			if((entity.x)<65*8 && (entity.x)>8){
				this.area1Orcs.remove((Orc)entity);
			}
			else if((entity.x)>84*8 && (entity.x)<148*8){
				this.area2Orcs.remove((Orc)entity);
			}
			else if((entity.x)>167*8 && (entity.x)<232*8){
				this.area3Orcs.remove((Orc)entity);
			}
		}
		if(entities.remove(entity))
			return entity;
		return null;
	}

	/**
	 * Respawns orcs on the level randomly
	 * @param orcsInArea, ArrayList of Orcs containing the orcs in the area that respawning it occuring
	 * @param xGridBoundary, int to mark the boundary of the grid separating the different grids in the area for respawning
	 * @param rank, int telling which rank to set the new orc at
	 */ 
	public void respawnOrcs(ArrayList<Orc> orcsInArea, int xGridBoundary, int rank) {
		int[] gridPopulation = new int[4];
		Orc tempOrc;
		int leastPopGrid;
		int orcX;
		int orcY;
		boolean orcPlaced;
		Random rand = new Random();

		leastPopGrid = 0;
		orcPlaced = false;
		for(int k = 0; k<orcsInArea.size(); k++){
			tempOrc = orcsInArea.get(k);
			if((tempOrc.x)<xGridBoundary*8 && (tempOrc.y)<32*8){
				gridPopulation[0] ++;
			}
			else if((tempOrc.x)>xGridBoundary*8 && (tempOrc.y)<32*8){
				gridPopulation[1] ++;
			}
			else if((tempOrc.x)<xGridBoundary*8 && (tempOrc.y)>32*8){
				gridPopulation[2] ++;
			}
			else if((tempOrc.x)>xGridBoundary*8 && (tempOrc.y)>32*8){
				gridPopulation[3] ++;
			}
		}
		for(int k = 1; k<4; k++){
			if(gridPopulation[k]<gridPopulation[leastPopGrid]){
				leastPopGrid = k;
			}
		}
		if(leastPopGrid==0){
			while(!orcPlaced){
				orcX = rand.nextInt(32)+(xGridBoundary-32);
				orcY = rand.nextInt(32)+1;
				if(getTile(orcX, orcY).getId()==2){
					Entity e = new Orc(this, orcX*8, orcY*8);
					this.addEntity(e);
					((Mob)e).setRank(rank);
					orcPlaced = true;
				}
			}
		}
		else if(leastPopGrid==1){
			while(!orcPlaced){
				orcX = rand.nextInt(32)+xGridBoundary;
				orcY = rand.nextInt(32)+1;
				if(getTile(orcX, orcY).getId()==2){
					Entity e = new Orc(this, orcX*8, orcY*8);
					this.addEntity(e);
					((Mob)e).setRank(rank);
					orcPlaced = true;
				}
			}
		}
		else if(leastPopGrid==2){
			while(!orcPlaced){
				orcX = rand.nextInt(32)+(xGridBoundary-32);
				orcY = rand.nextInt(32)+32;
				if(getTile(orcX, orcY).getId()==2){
					Entity e = new Orc(this, orcX*8, orcY*8);
					this.addEntity(e);
					((Mob)e).setRank(rank);
					orcPlaced = true;
				}
			}
		}
		else if(leastPopGrid==3){
			while(!orcPlaced){
				orcX = rand.nextInt(32)+xGridBoundary;
				orcY = rand.nextInt(32)+32;
				if(getTile(orcX, orcY).getId()==2){
					Entity e = new Orc(this, orcX*8, orcY*8);
					this.addEntity(e);
					((Mob)e).setRank(rank);
					orcPlaced = true;
				}
			}
		}
	}

	/**
	 * Spawns enemies for dungeons
	 * @param dungeonNum 	The number indicating which dungeon is spawning the entities. 1 spawns bandits, 2 spawns vampires
	 */
	public void spawn(int dungeonNum, int numEnemies){
		Random rand = new Random();
		boolean enemyPlaced = false;
		int enemyX;
		int enemyY;

		for(int i = 0; i<numEnemies; i++){
			enemyPlaced=false;
			while(!enemyPlaced){
				enemyX = rand.nextInt(63)+1;
				enemyY = rand.nextInt(63)+1;
				if(getTile(enemyX,enemyY).getId() == 2){
					if(dungeonNum == 1){
						Entity e = new Bandit(this, enemyX*8, enemyY*8);
						this.addEntity(e);
						enemyPlaced=true;
					}
					else if(dungeonNum == 2){
						Entity e = new Vampire(this, enemyX*8, enemyY*8);
						this.addEntity(e);
						enemyPlaced=true;
					}

				}
			}
		}
	}
}