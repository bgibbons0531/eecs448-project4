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
    System.out.println("testRespawnOrcs - " + testRespawnOrcs());
    System.out.println("testSpawnBandits - " + testSpawnBandits());
    System.out.println("testSpawnVampires - " + testSpawnVampires());
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
}