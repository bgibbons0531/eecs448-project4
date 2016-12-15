package bgibbons.game;

import bgibbons.game.entities.*;
import bgibbons.game.level.Level;
import java.util.ArrayList;

/**
 * Testing class for testing enemies.
 * @author Chris Porras
 * @version 1.0 14 December 2016
 */
public class Test_Enemy {

  /**
   * Runs all the tests
   */
  public void run(){
    System.out.println("Starting enemy testing");
    System.out.println("Testing respwaning Orcs: " + testRespawnEnemies());
    System.out.println("Testing spawning Bandits: " + testSpawnBandits());
  }

  /**
   * Tests that respawn enemies respawns correct amount of enemies
   * @return boolean, true if respawing works, false otherwise
   */
  public boolean testRespawnEnemies(){
    Level testLevel = new Level("/res/levels/test_level.png", null, false);
    while(testLevel.area1Orcs.size()<5){
      testLevel.respawnOrcs(testLevel.area1Orcs, 33, 1);
    }
    if(testLevel.area1Orcs.size()==5){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   *
   */
  public boolean testSpawnBandits(){
    Level testLevel = new Level("/res/levels/test_level.png", null, false);
    testLevel.spawn(1);
    if(testLevel.entities.size()==40){
      for(int i = 0; i<40; i++){
        if(!(testLevel.entities.get(i) instanceof Bandit)){
          return false;
        }
      }
      return true;
    }
    else{
      return false;
    }
  }
}