package bgibbons.game;
import bgibbons.game.entities.*;
import bgibbons.game.entities.Player;
import bgibbons.game.level.Level;
import bgibbons.game.abilities.*;
import bgibbons.game.InputHandler;

public class Test_Player
{
  public InputHandler input;
  Level testLevel = new Level("/res/levels/test_level.png", null, false);
  Player player = new Player(testLevel, 5*8, 5*8, input);

  Item item = new Item(testLevel,"Test Item","Test Item",1,1,1);
  Helmet helmet = new Helmet(testLevel,"Test Helmet","Test Helmet",1,1,1);
  Chest chest = new Chest(testLevel,"Test Chest","Test Chest",1,1,1);
  Legs legs = new Legs(testLevel,"Test Legs","Test Legs",1,1,1);
  Weapon weapon = new Weapon(testLevel,"Test Weapon","Test Weapon",1,1,1);
  Shield shield = new Shield(testLevel,"Test Shield","Test Shield",1,1,1);
  public void run()
  {
    System.out.println("---------Starting Player Testing Suite---------");
    System.out.println("testConstructorPosition - " + testConstructorPosition());
    System.out.println("");
    System.out.println("test testRank - " + testRank());
    System.out.println("");
    System.out.println("testGetInventory - " + testGetInventory(0));
    System.out.println("");
    System.out.println("testPickUpHead - " + testPickUpHead(helmet));
    System.out.println("testPickUpChest - " + testPickUpChest(chest));
    System.out.println("testPickUpLegs - " + testPickUpLegs(legs));
    System.out.println("testPickUpShield - " + testPickUpShield(shield));
    System.out.println("testPickUpWeapon - " + testPickUpWeapon(weapon));
    System.out.println("");
    System.out.println("testEquipHead - " + testEquipHead(0));
    System.out.println("testEquipChest - " + testEquipChest(1));
    System.out.println("testEquipLegs - " + testEquipLegs(2));
    System.out.println("testEquipShield - " + testEquipShield(3));
    System.out.println("testEquipWeapon - " + testEquipWeapon(4));
    System.out.println("");
    System.out.println("testGetHead - " + testGetHead());
    System.out.println("testGetChest - " + testGetChest());
    System.out.println("testGetLegs - " + testGetLegs());
    System.out.println("testGetShield - " + testGetShield());
    System.out.println("testGetWeapon - " + testGetWeapon());
    System.out.println("");
    System.out.println("testUnequipHead - " + testUnequipHead(helmet));
    System.out.println("testUnequipChest - " + testUnequipChest(chest));
    System.out.println("testUnequipLegs - " + testUnequipLegs(legs));
    System.out.println("testUnequipShield - " + testUnequipShield(shield));
    System.out.println("testUnequipWeapon - " + testUnequipWeapon(weapon));
    System.out.println("");
    System.out.println("testDropHead - " + testDropHead(5));
    System.out.println("testDropChest - " + testDropChest(6));
    System.out.println("testDropLegs - " + testDropLegs(7));
    System.out.println("testDropShield - " + testDropShield(8));
    System.out.println("testDropWeapon - " + testDropWeapon(9));
    System.out.println("");
    System.out.println("testSetKnight - " + testSetKnight());
    System.out.println("testSetWizard - " + testSetWizard());
    System.out.println("testSetHunter - " + testSetHunter());
    System.out.println("");
    System.out.println("testSetKnight - " + testSetKnightAbilities());
    System.out.println("testSetWizard - " + testSetWizardAbilities());
    System.out.println("testSetHunter - " + testSetHunterAbilities());
  }
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
  /************************************************************************************************/
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
  /************************************************************************************************/
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
  //
  /*public boolean testDrop(int n)
  {

  }*/
  /************************************************************************************************/
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
  /************************************************************************************************/
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
  /************************************************************************************************/
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
  /************************************************************************************************/
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
  /************************************************************************************************/
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
  /************************************************************************************************/
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
