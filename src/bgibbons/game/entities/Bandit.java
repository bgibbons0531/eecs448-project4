package bgibbons.game.entities;

import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

import java.util.Random;

/**
 * An extension of the Enemy class for a bandit.
 * @author Chris Porras based on Player.java
 * @version 1.0 04 November 2016.
 */

public class Bandit extends Enemy{

  private int stepsTaken = 0;
  private int direction;
  private int stop;
  /**
   * Constructor the Bandit object.
   * @param level   Level for the bandit to be added to.
   * @param x     The x coordinate the bandit will start at.
   * @param y     The y coordinate the bandit will start at.
   */
  public Bandit(Level level, int x, int y) {
    super(level, x, y, 0, 24, Colors.get(-1, 000, 320, 231), 10, 1, 1, 100, 1);
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
      stop = rand.nextInt(5)+1;
      direction = rand.nextInt(10);
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