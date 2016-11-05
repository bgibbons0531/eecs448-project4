package bgibbons.game.entities;

import bgibbons.game.InputHandler;
import bgibbons.game.entities.*;
import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

/**
 * An extension of the Mob class for a player.
 * @author Brad Gibbons
 * @author Chris Porras
 * @version 1.0 12 October 2016.
 */
public class Player extends Mob {

	public int mainX;
	public int mainY;
	private InputHandler input;
	private int color = Colors.get(-1, 111, 145, 543);
	private int scale = 1;
	private Item helmet = null;
	private Item chest = null;
	private Item legs = null;
	private Item weapon = null;
	private Item shield = null;
	private Item[] inventory = new Item[6];
	private int attributePoints = 0;

	/**
	 * Constructor the Player object.
	 * @param level 	Level for the player to be added to.
	 * @param x 		The x coordinate the player will start at.
	 * @param y 		The y coordinate the player will start at.
	 * @param input 	The InputHandler used to control the player.
	 */
	public Player(Level level, int x, int y, InputHandler input) {
		super(level, "Player", x, y, 10, 1, 1, 100);
		this.mainX = x;
		this.mainY = y;
		this.input = input;
		inventory[0] = new Helmet(level,"Helmet","Standard",0,0,0);
		inventory[1] = new Chest(level,"Chest","Standard",0,0,0);
		inventory[2] = new Legs(level,"Legs","",0,0,0);
		inventory[3] = new Weapon(level,"Weapon","Standard",0,0,0);
		inventory[4] = new Shield(level,"Shield","Standard",0,0,0);
	}

	/**
	 * Ticks the player.
	 */
	public void tick() {
		// Level player
		if (currentExp >= maxExp) {
			rankUp();
		}

		// Move player
		int xa = 0;
		int ya = 0;

		if (input.up.isPressed()) {
			ya--;
		}
		if (input.down.isPressed()) {
			ya++;
		}
		if (input.left.isPressed()) {
			xa--;
		}
		if (input.right.isPressed()) {
			xa++;
		}

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
	 * Renders the player to the screen
	 * @param screen 	The screen to render the player to.
	 */
	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 28;
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
	 * Checks if the player will collide when moving.
	 * @param xa 	The x direction the player wants to move.
	 * @param ya 	The y direction the player wants to move.
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
		rank++;
		attributePoints += 3;
		if (maxHealth < 40 && rank % 2 == 1) {
			maxHealth+=2;
		}
		if (currentHealth < maxHealth) {
			currentHealth++;
		}
	}

	/**
	 * Get the item in the player's helmet slot.
	 * @return The Item object in the player's helmet slot.
	 */
	public Item getHead() {
		return helmet;
	}

	/**
	 * Get the item in the player's chest slot.
	 * @return The Item object in the player's chest slot.
	 */
	public Item getChest() {
		return chest;
	}

	/**
	 * Get the item in the player's legs slot.
	 * @return The Item object in the player's legs slot.
	 */
	public Item getLegs() {
		return legs;
	}

	/**
	 * Get the item in the player's weapon slot.
	 * @return The Item object in the player's weapon slot.
	 */
	public Item getWeapon() {
		return weapon;
	}

	/**
	 * Get the item in the player's shield slot.
	 * @return The Item object in the player's shield slot.
	 */
	public Item getShield() {
		return shield;
	}

	/**
	 * Get the item from the given inventory slot.
	 * @param index 	The slot to remove the item from.
	 * @return The Item from the given index.
	 */
	public Item getInventory(int index) {
		return inventory[index];
	}

	/**
	 * Equips the item in the given inventory slot.
	 * @param index 	Index of the item to equip.
	 */
	public void equip(int index) {
		Item e = inventory[index];
		if (e instanceof Helmet) {
			Item temp = helmet;
			helmet = e;
			inventory[index] = temp;
		} else if (e instanceof Chest) {
			Item temp = chest;
			chest = e;
			inventory[index] = temp;
		} else if (e instanceof Legs) {
			Item temp = legs;
			legs = e;
			inventory[index] = temp;
		} else if (e instanceof Weapon) {
			Item temp = weapon;
			weapon = e;
			inventory[index] = temp;
		} else if (e instanceof Shield) {
			Item temp = shield;
			shield = e;
			inventory[index] = temp;
		} 
	}

	/**
	 * Unequips the item in the given inventory slot.
	 * @param e 	The item to unequip
	 */
	public void unEquip(Item e) {
		for (int i=0; i<6; i++) {
			if (inventory[i] == null) {
				if (e instanceof Helmet) {
					inventory[i] = e;
					helmet = null;
				} else if (e instanceof Chest) {
					inventory[i] = e;
					chest = null;
				} else if (e instanceof Legs) {
					inventory[i] = e;
					legs = null;
				} else if (e instanceof Weapon) {
					inventory[i] = e;
					weapon = null;
				} else if (e instanceof Shield) {
					inventory[i] = e;
					shield = null;
				}
				return;
			}
		}
	}

	/**
	 * Picks up the item the player ran over.
	 * @param item 	The item to pick up.
	 * @return True if the item was picked up, false otherwise.
	 */
	public boolean pickUp(Item item) {
		for (int i=0; i<6; i++) {
			if (inventory[i] == null) {
				inventory[i] = item;
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the remaining attribute points of the player.
	 * @return The remaining attribute points of the player.
	 */
	public int getAttributePoints() {
		return attributePoints;
	}

	/**
	 * Increase Dexterity.
	 */
	public void increaseDexterity() {
		dexterity++;
		attributePoints--;
	}

	/**
	 * Increase Intelligence.
	 */
	public void increaseIntelligence() {
		intelligence++;
		attributePoints--;
	}

	/**
	 * Increase Vitality.
	 */
	public void increaseVitality() {
		vitality++;
		attributePoints--;
	}
}