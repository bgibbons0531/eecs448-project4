package bgibbons.game.entities;

import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

import java.util.Random;

/**
 * An extension of the Enemy class for an orc.
 * @author Chris Porras based on Player.java
 * @version 1.0 20 October 2016.
 */

public class Orc extends Enemy {

	private int stepsTaken = 0;
	private int direction;
	private int stop;
	/**
	 * Constructor the Orc object.
	 * @param level 	Level for the orc to be added to.
	 * @param x 		The x coordinate the orc will start at.
	 * @param y 		The y coordinate the orc will start at.
	 */
	public Orc(Level level, int x, int y) {
		super(level, x, y, 0, 26, Colors.get(-1, 000, 320, 120), 10, 1, 1, 100, 1);
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

	/*
	* Method to determine the movement of orcs
	*/
	public void move() {

		Random rand = new Random();

		if(stepsTaken >= stop){
			stepsTaken = 0;
			stop = rand.nextInt(20)+11;
			direction = rand.nextInt(5);
		}


		// Move orc
		int xa = 0;
		int ya = 0;


		if(direction == 0){
			ya++;
			stepsTaken++;
		}
		else if(direction == 1){
			ya--;
			stepsTaken++;
		}
		else if(direction == 2){
			xa--;
			stepsTaken++;
		}
		else if(direction == 3){
			xa++;
			stepsTaken++;
		}
		else if(direction == 4){
			stepsTaken++;
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
	}
}