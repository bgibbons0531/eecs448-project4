package bgibbons.game;

import bgibbons.game.entities.*;
import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.HUD;
import bgibbons.game.graphics.Screen;
import bgibbons.game.graphics.SpriteSheet;
import bgibbons.game.level.Level;
import bgibbons.game.Test_Enemy;
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
	}
}