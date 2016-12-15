package bgibbons.game;

import bgibbons.game.entities.*;
import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.HUD;
import bgibbons.game.graphics.Screen;
import bgibbons.game.graphics.SpriteSheet;
import bgibbons.game.level.Level;
import bgibbons.game.Test_Enemy;
import bgibbons.game.Test_Items;
/**
 * Testing class for testing various parts of the codebase.
 * @author Brad Gibbons
 * @author Jackson Schilmoeller
 * @author Rony Singh
 * @author Chris Porras
 * @version 1.0 14 December 2016
 */
public class Test  {

	public static void main(String[] args) {
		System.out.println("Welcome to the Testing Suite!");
	    Test_Enemy enemyTester = new Test_Enemy();
	    enemyTester.run();
	    System.out.println("---------Ending Enemy Testing Suite---------");
	    System.out.println("");
		Test_Items itemTester = new Test_Items();
		itemTester.run();
		System.out.println("---------Ending Item Testing Suite---------");
		Test_Player playerTester = new Test_Player();
		playerTester.run();
		System.out.println("---------Ending Item Testing Suite---------");
		Test_Combat combatTester = new Test_Combat();
		combatTester.run();
		System.out.println("---------Ending Combat Testing Suite---------");
	}
}
