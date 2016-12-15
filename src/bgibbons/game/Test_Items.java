package bgibbons.game;
import bgibbons.game.entities.*;
import bgibbons.game.level.Level;
public class Test_Items
{
  public void run()
  {
    Level testLevel = new Level("/res/levels/test_level.png", null, false);
    Item item = new Item(testLevel,"Test Item","Test Item",1,1,1);
    Helmet helmet = new Helmet(testLevel,"Test Helmet","Test Helmet",1,1,1);
    Chest chest = new Chest(testLevel,"Test Chest","Test Chest",1,1,1);
    Legs legs = new Legs(testLevel,"Test Legs","Test Legs",1,1,1);
    Weapon weapon = new Weapon(testLevel,"Test Weapon","Test Weapon",1,1,1);
    Shield shield = new Shield(testLevel,"Test Shield","Test Shield",1,1,1);
    //Test Constructor
    System.out.println("---------Starting Item Testing Suite---------");
    System.out.println("testItemConstruction - " + testItemConstruction(item));
    System.out.println("testHelmetConstruction - " + testHelmetConstruction(helmet));
    System.out.println("testChestConstruction - " + testChestConstruction(chest));
    System.out.println("testLegsConstruction - " + testLegsConstruction(legs));
    System.out.println("testWeaponConstruction - " + testWeaponConstruction(weapon));
    System.out.println("testShieldConstruction - " + testShieldConstruction(shield));
    //Test getters
    System.out.println("testItemGetters - " + testItemGetters(item));
    System.out.println("testHelmetGetters - " + testHelmetGetters(helmet));
    System.out.println("testChestGetters - " + testChestGetters(chest));
    System.out.println("testLegsGetters - " + testLegsGetters(legs));
    System.out.println("testWeaponGetters - " + testWeaponGetters(weapon));
    System.out.println("testShieldGetters - " + testShieldGetters(shield));
    System.out.println("---------Ending Item Testing Suite---------");
  }
  public boolean testItemConstruction(Item e)
  {
    if(e instanceof Entity)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  public boolean testHelmetConstruction(Helmet e)
  {
    if(e instanceof Item)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  public boolean testChestConstruction(Chest e)
  {
    if(e instanceof Item)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  public boolean testLegsConstruction(Legs e)
  {
    if(e instanceof Item)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  public boolean testShieldConstruction(Shield e)
  {
    if(e instanceof Item)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  public boolean testWeaponConstruction(Weapon e)
  {
    if(e instanceof Item)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /************************************************************************************************/


  public boolean testItemGetters(Item e)
  {
    if(e.getDexterity() == 1 && e.getVitality() == 1 && e.getIntelligence() == 1)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  public boolean testHelmetGetters(Helmet e)
  {
    if(e.getDexterity() == 1 && e.getVitality() == 1 && e.getIntelligence() == 1)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  public boolean testChestGetters(Chest e)
  {
    if(e.getDexterity() == 1 && e.getVitality() == 1 && e.getIntelligence() == 1)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  public boolean testLegsGetters(Legs e)
  {
    if(e.getDexterity() == 1 && e.getVitality() == 1 && e.getIntelligence() == 1)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  public boolean testShieldGetters(Shield e)
  {
    if(e.getDexterity() == 1 && e.getVitality() == 1 && e.getIntelligence() == 1)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  public boolean testWeaponGetters(Weapon e)
  {
    if(e.getDexterity() == 1 && e.getVitality() == 1 && e.getIntelligence() == 1)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
}
