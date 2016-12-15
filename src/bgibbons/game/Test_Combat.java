package bgibbons.game;

import bgibbons.game.entities.*;
import bgibbons.game.abilities.*;
import bgibbons.game.level.Level;
/**
 * Testing class for testing combat.
 * @author Jackson Schilmoeller
 * @version 1.0 14 December 2016
 */
public class Test_Combat {

	public InputHandler input;

	/**
	 * Runs all the tests.
	 */
	public void run(){
		System.out.println("---------Starting Combat Testing Suite---------");
		System.out.println("Testing player using ability Strike:" + testStrike());
		System.out.println("Testing player using ability Shield Bash:" + testShieldBash());
		System.out.println("Testing player using ability Divine Call:" + testDivineCall());
		System.out.println("Testing player using ability Holy Smite:" + testHolySmite());
		System.out.println("Testing player using ability Bonk:" + testBonk());
		System.out.println("Testing player using ability Scorch:" + testScorch());
		System.out.println("Testing player using ability Fire Wall:" + testFireWall());
		System.out.println("Testing player using ability KABOOM:" + testKABOOM());
		System.out.println("Testing player using ability Stab:" + testStab());
		System.out.println("Testing player using ability Knife Throw:" + testKnifeThrow());
		System.out.println("Testing player using ability Mark:" + testMark());
	}

	/**
	 * Test code for the Knight's Strike ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testStrike(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setKnight();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		int initHealth = testPlayer2.getCurrentHealth();
		testCombat.useAbility(testPlayer1.ability1);
		if(testPlayer2.getCurrentHealth() == (initHealth - ((testPlayer1.getVitality() / testPlayer1.ability1.getScale()) + testPlayer1.ability1.getDamage())))
			return true;
		else
			return false;
	}

	/**
	 * Test code for the Knight's Shield Bash ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testShieldBash(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setKnight();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		int initHealth = testPlayer2.getCurrentHealth();
		testCombat.useAbility(testPlayer1.ability2);
		if((testPlayer2.getCurrentHealth() == (initHealth - ((testPlayer1.getVitality() / testPlayer1.ability2.getScale()) + testPlayer1.ability2.getDamage()))) 
			&& testCombat.combatant2.isStunned)
			return true;
		else
			return false;
	}

	/**
	 * Test code for the Knight's Divine Call ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testDivineCall(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setKnight();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		//first deal damage to player 1 so there is health to be healed
		testPlayer1.takeDamage(5);
		int initHealth = testPlayer1.getCurrentHealth();
		testCombat.useAbility(testPlayer1.ability3);
		if((testPlayer1.getCurrentHealth() == (initHealth + ((testPlayer1.getIntelligence() / testPlayer1.ability3.getScale()) + testPlayer1.ability3.getHeal()))) 
			&& (testCombat.combatant1.shield == (testPlayer1.getIntelligence() / testPlayer1.ability3.getScale()) + testPlayer1.ability3.getShield()))
			return true;
		else
			return false;
	}

	/**
	 * Test code for the Knight's Holy Smite ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testHolySmite(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setKnight();
		testPlayer1.rankUp();
		testPlayer1.rankUp();
		testPlayer1.rankUp();
		testPlayer1.rankUp();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		testPlayer2.rankUp();
		testPlayer2.rankUp();
		testPlayer2.rankUp();
		testPlayer2.rankUp();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		int initHealth = testPlayer2.getCurrentHealth();
		testCombat.combatant1.ability4CD = 0;
		testCombat.useAbility(testPlayer1.ability4);
		if(testPlayer2.getCurrentHealth() == (initHealth - ((testPlayer1.getIntelligence() / testPlayer1.ability4.getScale()) + testPlayer1.ability4.getDamage())))
			return true;
		else
			return false;
	}

	/**
	 * Test code for the Wizard's Bonk ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testBonk(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setWizard();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		int initHealth = testPlayer2.getCurrentHealth();
		testCombat.useAbility(testPlayer1.ability1);
		if(testPlayer2.getCurrentHealth() == (initHealth - ((testPlayer1.getVitality() / testPlayer1.ability1.getScale()) + testPlayer1.ability1.getDamage())))
			return true;
		else
			return false;
	}

	/**
	 * Test code for the Wizard's Scorch ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testScorch(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setWizard();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		int initHealth = testPlayer2.getCurrentHealth();
		testCombat.useAbility(testPlayer1.ability2);
		if(testPlayer2.getCurrentHealth() == (initHealth - ((testPlayer1.getIntelligence() / testPlayer1.ability2.getScale()) + testPlayer1.ability2.getDamage())))
			return true;
		else
			return false;
	}

	/**
	 * Test code for the Wizard's Fire Wall ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testFireWall(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setWizard();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		int initHealth = testPlayer2.getCurrentHealth();
		testCombat.useAbility(testPlayer1.ability3);
		if(testPlayer2.getCurrentHealth() == (initHealth - ((testPlayer1.getIntelligence() / testPlayer1.ability3.getScale()) + testPlayer1.ability3.getDamage())))
			return true;
		else
			return false;
	}

	/**
	 * Test code for the Wizard's KABOOM ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testKABOOM(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setWizard();
		testPlayer1.rankUp();
		testPlayer1.rankUp();
		testPlayer1.rankUp();
		testPlayer1.rankUp();
		testPlayer1.rankUp();
		testPlayer1.rankUp();
		testPlayer1.rankUp();
		testPlayer1.rankUp();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		testPlayer2.rankUp();
		testPlayer2.rankUp();
		testPlayer2.rankUp();
		testPlayer2.rankUp();
		testPlayer2.rankUp();
		testPlayer2.rankUp();
		testPlayer2.rankUp();
		testPlayer2.rankUp();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		int initHealth = testPlayer2.getCurrentHealth();
		testCombat.combatant1.ability4CD = 0;
		testCombat.useAbility(testPlayer1.ability4);
		if(testPlayer2.getCurrentHealth() == (initHealth - ((testPlayer1.getIntelligence() / testPlayer1.ability4.getScale()) + testPlayer1.ability4.getDamage())))
			return true;
		else
			return false;
	}

	/**
	 * Test code for the Hunter's Stab ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testStab(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setHunter();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		int initHealth = testPlayer2.getCurrentHealth();
		testCombat.useAbility(testPlayer1.ability1);
		if(testPlayer2.getCurrentHealth() == (initHealth - ((testPlayer1.getDexterity() / testPlayer1.ability1.getScale()) + testPlayer1.ability1.getDamage())))
			return true;
		else
			return false;
	}

	/**
	 * Test code for the Hunter's Knife Throw ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testKnifeThrow(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setHunter();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		int initHealth = testPlayer2.getCurrentHealth();
		testCombat.useAbility(testPlayer1.ability2);
		if(testPlayer2.getCurrentHealth() == (initHealth - ((testPlayer1.getDexterity() / testPlayer1.ability2.getScale()) + testPlayer1.ability2.getDamage())))
			return true;
		else
			return false;
	}

	/**
	 * Test code for the Hunter's Strike ability.
	 * @return A boolean, true if test passed, false otherwise.
	 */
	public Boolean testMark(){
		Level testLevel = new Level(null, null, false);
		Player testPlayer1 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer1.setHunter();
		Player testPlayer2 = new Player(testLevel, 16, testLevel.height*8/2, input);
		testPlayer2.setKnight();
		Combat testCombat = new Combat(testPlayer1, testPlayer2);
		int initHealth = testPlayer2.getCurrentHealth();
		testCombat.combatant1.ability4CD = 0;
		testCombat.useAbility(testPlayer1.ability4);
		if(testPlayer2.getCurrentHealth() == (initHealth - ((testPlayer1.getDexterity() / testPlayer1.ability4.getScale()) + testPlayer1.ability4.getDamage())))
			return true;
		else
			return false;
	}
}