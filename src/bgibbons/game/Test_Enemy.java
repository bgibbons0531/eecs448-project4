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
    System.out.println("---------Starting Enemy Testing Suite---------");
    System.out.println("testOrcStartingPos - " + testOrcStartingPos());
    System.out.println("testBanditStartingPos - " + testBanditStartingPos());
    System.out.println("testVampireStartingPos - " + testVampireStartingPos());
    System.out.println("testRespawnOrcs - " + testRespawnOrcs());
    System.out.println("testSpawnBandits - " + testSpawnBandits());
    System.out.println("testSpawnVampires - " + testSpawnVampires());
    System.out.println("testEnemyMovement - " + testEnemyMovement());
    }

  /**
   * Tests Orcs starting position
   * @return boolean, true if orc starts in correct position, false otherwise
   */
  public boolean testOrcStartingPos(){
    Level testLevel = new Level("/res/levels/test_level.png", null, false);
    Entity testOrc = new Orc(testLevel, 24*8, 48*8);
    int test = ((Orc)testOrc).getCurrentHealth();
    System.out.println(test);
    testLevel.addEntity(testOrc);
    if(testLevel.entities.get(0).x == 24*8 && testLevel.entities.get(0).y == 48*8){
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Tests bandits starting position
   * @return boolean, true if bandits starts in correct position, false otherwise
   */
  public boolean testBanditStartingPos(){
    Level testLevel = new Level("/res/levels/test_level.png", null, false);
    Entity testBandit = new Bandit(testLevel, 24*8, 48*8);
    testLevel.addEntity(testBandit);
    if(testLevel.entities.get(0).x == 24*8 && testLevel.entities.get(0).y == 48*8){
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Tests vampires starting position
   * @return boolean, true if vampires starts in correct position, false otherwise
   */
  public boolean testVampireStartingPos(){
    Level testLevel = new Level("/res/levels/test_level.png", null, false);
    Entity testVampire = new Vampire(testLevel, 24*8, 48*8);
    testLevel.addEntity(testVampire);
    if(testLevel.entities.get(0).x == 24*8 && testLevel.entities.get(0).y == 48*8){
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Tests that respawn enemies respawns correct amount of enemies
   * @return boolean, true if respawing works, false otherwise
   */
  public boolean testRespawnOrcs(){
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
   * Tests that spawn function spawns the correct number of bandits
   * @return boolean, true if spawning bandits works, false if incorrect number of bandits or level has an instance of something other than a bandit
   */
  public boolean testSpawnBandits(){
    Level testLevel = new Level("/res/levels/test_level.png", null, false);
    testLevel.spawn(1, 32);
    if(testLevel.entities.size()==32){
      for(int i = 0; i<32; i++){
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

  /**
   * Tests that spawn function spawns the correct number of vampires
   * @return boolean, true if spawning vampires works, false if incorrect number of vampires or level has an instance of something other than a vampires
   */
  public boolean testSpawnVampires(){
    Level testLevel = new Level("/res/levels/test_level.png", null, false);
    testLevel.spawn(2, 21);
    if(testLevel.entities.size()==21){
      for(int i = 0; i<21; i++){
        if(!(testLevel.entities.get(i) instanceof Vampire)){
          return false;
        }
      }
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * Tests that enemy movement works correctly
   * @return boolean, true if movement works correctly on orcs
   */
  public boolean testEnemyMovement(){
    Level testLevel = new Level("/res/levels/test_level.png", null, false);
    Entity e = new Orc(testLevel, 32*8, 32*8);
    testLevel.addEntity(e);
    while(((Orc)e).getDirection() == 4){
      ((Orc)e).move();
    }
    int direction = ((Orc)e).getDirection();
    if(direction == 0){
      if(e.y == 32*8+1){
        return true;
      }
    }
    else if(direction == 1){
      if(e.y == 32*8-1){
        return true;
      }
    }
    else if(direction == 2){
      if(e.x == 32*8+1){
        return true;
      }
    }
    else if(direction == 3){
      if(e.x == 32*8-1){
        return true;
      }
    }
    return false;
  }
}