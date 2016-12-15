package bgibbons.game;
import bgibbons.game.entities.*;
import bgibbons.game.entities.Player;
import bgibbons.game.level.Level;
import bgibbons.game.abilities.*;
import bgibbons.game.InputHandler;
/**
 * Testing Suite Class for Player
 * @author Rony Singh
 * @version 1.0 00 December 2016.
 */
public class Test_Player
{
  public InputHandler input;                                                //Declare Handler
  Level testLevel = new Level("/res/levels/test_level.png", null, false);   //Declare Level
  Player player = new Player(testLevel, 5*8, 5*8, input);                   //Declare Player

  Item item = new Item(testLevel,"Test Item","Test Item",1,1,1);            //Declare Item
  Helmet helmet = new Helmet(testLevel,"Test Helmet","Test Helmet",1,1,1);  //Declare Helmet
  Chest chest = new Chest(testLevel,"Test Chest","Test Chest",1,1,1);       //Declare Chest
  Legs legs = new Legs(testLevel,"Test Legs","Test Legs",1,1,1);            //Declare Legs
  Weapon weapon = new Weapon(testLevel,"Test Weapon","Test Weapon",1,1,1);  //Declare Weapon
  Shield shield = new Shield(testLevel,"Test Shield","Test Shield",1,1,1);  //Declare Shield
  /**
   * Method that runs the Suite. Calls other methods.
   */
  public void run()
  {
    System.out.println("---------Starting Player Testing Suite---------");
    //Testing player constructor.
    System.out.println("testConstructorPosition - " + testConstructorPosition());
    System.out.println("");
    //Testing rank.
    System.out.println("test testRank - " + testRank());
    System.out.println("");
    //Testing getter for inventory.
    System.out.println("testGetInventory - " + testGetInventory(0));
    System.out.println("");
    //Testing pickUp for items.
    System.out.println("testPickUpHead - " + testPickUpHead(helmet));
    System.out.println("testPickUpChest - " + testPickUpChest(chest));
    System.out.println("testPickUpLegs - " + testPickUpLegs(legs));
    System.out.println("testPickUpShield - " + testPickUpShield(shield));
    System.out.println("testPickUpWeapon - " + testPickUpWeapon(weapon));
    System.out.println("");
    //Testing equpping for items.
    System.out.println("testEquipHead - " + testEquipHead(0));
    System.out.println("testEquipChest - " + testEquipChest(1));
    System.out.println("testEquipLegs - " + testEquipLegs(2));
    System.out.println("testEquipShield - " + testEquipShield(3));
    System.out.println("testEquipWeapon - " + testEquipWeapon(4));
    System.out.println("");
    //Testing getter for items objects.
    System.out.println("testGetHead - " + testGetHead());
    System.out.println("testGetChest - " + testGetChest());
    System.out.println("testGetLegs - " + testGetLegs());
    System.out.println("testGetShield - " + testGetShield());
    System.out.println("testGetWeapon - " + testGetWeapon());
    System.out.println("");
    //Testing unequipping for items.
    System.out.println("testUnequipHead - " + testUnequipHead(helmet));
    System.out.println("testUnequipChest - " + testUnequipChest(chest));
    System.out.println("testUnequipLegs - " + testUnequipLegs(legs));
    System.out.println("testUnequipShield - " + testUnequipShield(shield));
    System.out.println("testUnequipWeapon - " + testUnequipWeapon(weapon));
    System.out.println("");
    //Testing dropping for items.
    System.out.println("testDropHead - " + testDropHead(5));
    System.out.println("testDropChest - " + testDropChest(6));
    System.out.println("testDropLegs - " + testDropLegs(7));
    System.out.println("testDropShield - " + testDropShield(8));
    System.out.println("testDropWeapon - " + testDropWeapon(9));
    System.out.println("");
    //Testing setting player class.
    System.out.println("testSetKnight - " + testSetKnight());
    System.out.println("testSetWizard - " + testSetWizard());
    System.out.println("testSetHunter - " + testSetHunter());
    System.out.println("");
    //Testing setting player class abilities.
    System.out.println("testSetKnightAbilities - " + testSetKnightAbilities());
    System.out.println("testSetWizardAbilities - " + testSetWizardAbilities());
    System.out.println("testSetHunterAbilities - " + testSetHunterAbilities());
  }
  /**
   * Testing the item Construction.
   * @return    Boolean, true if the item is constructed, else false.
   */
  public boolean testConstructorPosition()
  {
      if(player.mainX == 40 && player.mainY == 40)
      {
        return(true);
      }
      else{
        return(false);
      }
  }
  /**
   * Testing the ranking.
   * @return    Boolean, true if the player ranks up, else false.
   */
  public boolean testRank()
  {
    player.setRank(2);
    if(player.getRank() == 2)
    {
      return(true);
    }
    else{
      return(false);
    }
  }
  /**
   * Testing the inventory getter.
   * @param n 	Item object to be tested.
   * @return    Boolean, true if the getting an empty item from inventory, else false.
   */
  public boolean testGetInventory(int n)
  {
    if(player.getInventory(n)==null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the helmet pickup.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the picking up into the inventory, else false.
   */
  public boolean testPickUpHead(Item e)
  {
    player.pickUp(e);
    if(player.getInventory(0) instanceof Helmet)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the chest pickup.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the picking up into the inventory, else false.
   */
  public boolean testPickUpChest(Item e)
  {
    player.pickUp(e);
    if(player.getInventory(1) instanceof Chest)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the legs pickup.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the picking up into the inventory, else false.
   */
  public boolean testPickUpLegs(Item e)
  {
    player.pickUp(e);
    if(player.getInventory(2) instanceof Legs)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the shield pickup.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the picking up into the inventory, else false.
   */
  public boolean testPickUpShield(Item e)
  {
    player.pickUp(e);
    if(player.getInventory(3) instanceof Shield)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the weapon pickup.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the picking up into the inventory, else false.
   */
  public boolean testPickUpWeapon(Item e)
  {
    player.pickUp(e);
    if(player.getInventory(4) instanceof Weapon)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the helmet equpping to player.
   * @param n 	slot of inventory to equip.
   * @return    Boolean, true if the equipping from the inventory, else false.
   */
  public boolean testEquipHead(int n)
  {
    player.equip(n);
    if(player.getInventory(0) == null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the chest equpping to player.
   * @param n 	slot of inventory to equip.
   * @return    Boolean, true if the equipping from the inventory, else false.
   */
  public boolean testEquipChest(int n)
  {
    player.equip(n);
    if(player.getInventory(1) == null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the legs equpping to player.
   * @param n 	slot of inventory to equip.
   * @return    Boolean, true if the equipping from the inventory, else false.
   */
  public boolean testEquipLegs(int n)
  {
    player.equip(n);
    if(player.getInventory(2) == null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the shield equpping to player.
   * @param n 	slot of inventory to equip.
   * @return    Boolean, true if the equipping from the inventory, else false.
   */
  public boolean testEquipShield(int n)
  {
    player.equip(n);
    if(player.getInventory(3) == null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the weapon equpping to player.
   * @param n 	slot of inventory to equip.
   * @return    Boolean, true if the equipping from the inventory, else false.
   */
  public boolean testEquipWeapon(int n)
  {
    player.equip(n);
    if(player.getInventory(4) == null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the getting for helmet equpping to player.
   * @return    Boolean, true if the equipped on player, else false.
   */
  public boolean testGetHead()
  {
    if(player.getHead() == helmet)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the getting for chest equpping to player.
   * @return    Boolean, true if the equipped on player, else false.
   */
  public boolean testGetChest()
  {
    if(player.getChest() == chest)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the getting for legs equpping to player.
   * @return    Boolean, true if the equipped on player, else false.
   */
  public boolean testGetLegs()
  {
    if(player.getLegs() == legs)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the getting for shield equpping to player.
   * @return    Boolean, true if the equipped on player, else false.
   */
  public boolean testGetShield()
  {
    if(player.getShield() == shield)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the getting for weapon equpping to player.
   * @return    Boolean, true if the equipped on player, else false.
   */
  public boolean testGetWeapon()
  {
    if(player.getWeapon() == weapon)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the helmet unequipping pickup.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item in unequpped into the inventory, else false.
   */
  public boolean testUnequipHead(Item e)
  {
    player.unEquip(e);
    if(player.getInventory(0) instanceof Helmet)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the chest unequipping pickup.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item in unequpped into the inventory, else false.
   */
  public boolean testUnequipChest(Item e)
  {
    player.unEquip(e);
    if(player.getInventory(1) instanceof Chest)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the legs unequipping pickup.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item in unequpped into the inventory, else false.
   */
  public boolean testUnequipLegs(Item e)
  {
    player.unEquip(e);
    if(player.getInventory(2) instanceof Legs)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the shield unequipping pickup.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item in unequpped into the inventory, else false.
   */
  public boolean testUnequipShield(Item e)
  {
    player.unEquip(e);
    if(player.getInventory(3) instanceof Shield)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the weapon unequipping pickup.
   * @param e 	Item object to be tested.
   * @return    Boolean, true if the item in unequpped into the inventory, else false.
   */
  public boolean testUnequipWeapon(Item e)
  {
    player.unEquip(e);
    if(player.getInventory(4) instanceof Weapon)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the helmet dropping from inventory.
   * @param n 	Slot of the item/whether it's equipped or not.
   * @return    Boolean, true if the helmet is dropped, else false.
   */
  public boolean testDropHead(int n)
  {
    player.drop(n);
    if(player.getInventory(0) == null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the chest dropping from inventory.
   * @param n 	Slot of the item/whether it's equipped or not.
   * @return    Boolean, true if the chest is dropped, else false.
   */
  public boolean testDropChest(int n)
  {
    player.drop(n);
    if(player.getInventory(1) == null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the legs dropping from inventory.
   * @param n 	Slot of the item/whether it's equipped or not.
   * @return    Boolean, true if the legs is dropped, else false.
   */
  public boolean testDropLegs(int n)
  {
    player.drop(n);
    if(player.getInventory(2) == null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the shield dropping from inventory.
   * @param n 	Slot of the item/whether it's equipped or not.
   * @return    Boolean, true if the shield is dropped, else false.
   */
  public boolean testDropShield(int n)
  {
    player.drop(n);
    if(player.getInventory(3) == null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the weapon dropping from inventory.
   * @param n 	Slot of the item/whether it's equipped or not.
   * @return    Boolean, true if the weapon is dropped, else false.
   */
  public boolean testDropWeapon(int n)
  {
    player.drop(n);
    if(player.getInventory(4) == null)
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the set class to knight.
   * @return    Boolean, true if the player class is set to knight, else false.
   */
  public boolean testSetKnight()
  {
    player.setKnight();
    if(player.getPlayerClass()=="Knight")
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the set class to wizard.
   * @return    Boolean, true if the player class is set to wizard, else false.
   */
  public boolean testSetWizard()
  {
    player.setWizard();
    if(player.getPlayerClass()=="Wizard")
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the set class to hunter.
   * @return    Boolean, true if the player class is set to hunter, else false.
   */
  public boolean testSetHunter()
  {
    player.setHunter();
    if(player.getPlayerClass()=="Hunter")
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the set class abilities to knight.
   * @return    Boolean, true if the player class abilities are all set correctly, else false.
   */
  public boolean testSetKnightAbilities()
  {
    player.setKnight();
    if(player.getAbility(1).getName() == "Strike      "
    && player.getAbility(2).getName() == "ShieldBash  "
    && player.getAbility(3).getName() == "DivineCall  "
    && player.getAbility(4).getName() == "Holy  Smite ")
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the set class abilities to wizard.
   * @return    Boolean, true if the player class abilities are all set correctly, else false.
   */
  public boolean testSetWizardAbilities()
  {
    player.setWizard();
    if(player.getAbility(1).getName() == "BONK        "
    && player.getAbility(2).getName() == "Scorch        "
    && player.getAbility(3).getName() == "Fire  Wall  "
    && player.getAbility(4).getName() == "KABOOM      ")
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
  /**
   * Testing the set class abilities to hunter.
   * @return    Boolean, true if the player class abilities are all set correctly, else false.
   */
  public boolean testSetHunterAbilities()
  {
    player.setHunter();
    if(player.getAbility(1).getName() == "Stab        "
    && player.getAbility(2).getName() == "Knife Throw "
    && player.getAbility(3).getName() == "Smoke Bomb  "
    && player.getAbility(4).getName() == "Mark        ")
    {
      return(true);
    }
    else
    {
      return(false);
    }
  }
}
