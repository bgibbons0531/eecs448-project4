package bgibbons.game.entities;

import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

import java.util.Random;

/**
 * An extension of the Enemy class for a vampire.
 * @author Chris Porras based on Player.java
 * @version 1.0 04 November 2016.
 */

public class Vampire extends Enemy{

  private int stepsTaken = 0;
  private int direction;
  private int stop;
  /**
   * Constructor the Vampire object.
   * @param level   Level for the vampire to be added to.
   * @param x     The x coordinate the vampire will start at.
   * @param y     The y coordinate the vampire will start at.
   */
  public Vampire(Level level, int x, int y) {
    super(level, x, y, 0, 22, Colors.get(-1, 000, 500, 543), 30, 1, 10, 100, 1);
  }

  public void rankUp(){
    currentExp = 0;
    rank ++;
    dexterity ++;
    intelligence ++;
    vitality ++;
  }

  public void move() {
    Random rand = new Random();

    if(stepsTaken >= stop){
      stepsTaken = 0;
      direction = rand.nextInt(10);
      if(direction<4){
        stop = rand.nextInt(10)+5;
      }
      else{
        stop = rand.nextInt(30)+11;
      }
    }

    // Move bandit
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
    else{
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