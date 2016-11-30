package bgibbons.game.entities;

import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

/**
 * An extension of the Enemy class for the boss Orc.
 * @author Chris Porras
 * @version 1.0 29 November 2016.
 */

public class Boss extends Enemy{
  /**
   * Constructor the Boss object.
   * @param level   Level for the Boss to be added to.
   * @param x     The x coordinate the boss will start at.
   * @param y     The y coordinate the boss will start at.
   */
  public Boss(Level level, int x, int y) {
    super(level, x, y, 0, 26, Colors.get(-1, 000, 320, 120), 10, 1, 1, 100, 2);
  }

  public void rankUp(){
    currentExp = 0;
    rank ++;
    dexterity ++;
    intelligence ++;
    vitality ++;
  }

  public void moveOrc(){
    
  }
}