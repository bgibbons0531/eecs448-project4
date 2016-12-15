package bgibbons.game;
import bgibbons.game.entities.*;
import bgibbons.game.level.Level;
/**
 * Testing Suite Class for Items
 * @author Rony Singh
 * @version 1.0 00 December 2016.
 */
public class Test_Items
{
  /**
   * Method that runs the Suite. Calls other methods.
   */
  public void run()
  {
    Level testLevel = new Level("/res/levels/test_level.png", null, false);   //Declare Level

    Item item = new Item(testLevel,"Test Item","Test Item",1,1,1);            //Declare Item
    Helmet helmet = new Helmet(testLevel,"Test Helmet","Test Helmet",1,1,1);  //Declare Helmet
    Chest chest = new Chest(testLevel,"Test Chest","Test Chest",1,1,1);       //Declare Chest
    Legs legs = new Legs(testLevel,"Test Legs","Test Legs",1,1,1);            //Declare Legs
    Weapon weapon = new Weapon(testLevel,"Test Weapon","Test Weapon",1,1,1);  //Declare Weapon
    Shield shield = new Shield(testLevel,"Test Shield","Test Shield",1,1,1);  //Declare Shield

    System.out.println("---------Starting Item Testing Suite---------");
    //Test Constructors
    System.out.println("testItemConstruction - " + testItemConstruction(item));
    System.out.println("");
    System.out.println("testHelmetConstruction - " + testHelmetConstruction(helmet));
    System.out.println("testChestConstruction - " + testChestConstruction(chest));
    System.out.println("testLegsConstruction - " + testLegsConstruction(legs));
    System.out.println("testWeaponConstruction - " + testWeaponConstruction(weapon));
    System.out.println("testShieldConstruction - " + testShieldConstruction(shield));
    System.out.println("");
    //Test getters
    System.out.println("testItemGetters - " + testItemGetters(item));
    System.out.println("testHelmetGetters - " + testHelmetGetters(helmet));
    System.out.println("testChestGetters - " + testChestGetters(chest));
    System.out.println("testLegsGetters - " + testLegsGetters(legs));
    System.out.println("testWeaponGetters - " + testWeaponGetters(weapon));
    System.out.println("testShieldGetters - " + testShieldGetters(shield));

  }
  /**
   * Testing the item Construction.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is constructed, else false.
   */
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
  /**
   * Testing the helmet item Construction.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is constructed, else false.
   */
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
  /**
   * Testing the chest item Construction.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is constructed, else false.
   */
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
  /**
   * Testing the legs item Construction.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is constructed, else false.
   */
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
  /**
   * Testing the shield item Construction.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is constructed, else false.
   */
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
  /**
   * Testing the weapon item Construction.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is constructed, else false.
   */
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
  /**
   * Testing the item stats getter.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is getting stats correctly, else false.
   */
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
  /**
   * Testing the helmet item stats getter.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is getting stats correctly, else false.
   */
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
  /**
   * Testing the chest item stats getter.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is getting stats correctly, else false.
   */
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
  /**
   * Testing the legs item stats getter.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is getting stats correctly, else false.
   */
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
  /**
   * Testing the shield item stats getter.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is getting stats correctly, else false.
   */
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
  /**
   * Testing the weapon item stats getter.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item is getting stats correctly, else false.
   */
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
