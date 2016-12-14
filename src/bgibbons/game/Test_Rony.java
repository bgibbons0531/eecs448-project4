/*

package bgibbons.game;
import bgibbons.game.entities.*;
public class Test_Rony
{
  public void run()
  {
    //Sound sound1 = new Sound("")
    //Sound sound2 = new Sound("")
    Item item = new Item(1,"Test Item","Test Item",1,1,1);
    Helmet helmet = new Helmet(1,"Test Helmet","Test Helmet",1,1,1);
    Chest chest = new Chest(1,"Test Chest","Test Chest",1,1,1);
    Legs legs = new Legs(1,"Test Legs","Test Legs",1,1,1);
    Weapon weapon = new Weapon(1,"Test Weapon","Test Weapon",1,1,1);
    Shield shield = new Shield(1,"Test Shield","Test Shield",1,1,1);
    //Test Constructor
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

  /*
  public boolean testItemGetters(Item e)
  {
    if(e.getDexterity() instanceof Integer && e.getVitality() instanceof Integer && e.getIntelligence() instanceof Integer)
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
    if(e.getDexterity() instanceof Integer && e.getVitality() instanceof Integer && e.getIntelligence() instanceof Integer)
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
    if(e.getDexterity() instanceof Integer && e.getVitality() instanceof Integer && e.getIntelligence() instanceof Integer)
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
    if(e.getDexterity() instanceof Integer && e.getVitality() instanceof Integer && e.getIntelligence() instanceof Integer)
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
    if(e.getDexterity() instanceof Integer && e.getVitality() instanceof Integer && e.getIntelligence() instanceof Integer)
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
    if(e.getDexterity() instanceof Integer && e.getVitality() instanceof Integer && e.getIntelligence() instanceof Integer)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
}

*/