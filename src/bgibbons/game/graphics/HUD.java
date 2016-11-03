package bgibbons.game.graphics;

import bgibbons.game.Game;

/**
 * Class to display a HUD to the screen showing the player's stats.
 * @author Brad Gibbons
 * @version 1.0 14 October 2016
 */
public class HUD {

	/**
	 * Render the HUD to the screen.
	 * @param screen 	Screen to display the HUD to.
	 * @param game		Game to render from.
	 */
	public static void render(Screen screen, Game game) {

		// Render top left of screen
		// Example: screen.render(screen.xOffset, screen.yOffset, tile, color, 0x00, 1);
		// Render top right of screen
		// Example: screen.render(screen.xOffset+screen.width-8, screen.yOffset, tile, color, 0x00, 1);
		// Render bottom left of screen
		// Example: screen.render(screen.xOffset, screen.yOffset+screen.height-8, tile, color, 0x00, 1);
		// Render bottom right of screen
		// Example: screen.render(screen.xOffset+screen.width-8, screen.yOffset+screen.height-8, tile, color, 0x00, 1);

		// EXP bar
		screen.render(screen.xOffset+screen.width/2-21, screen.yOffset, 28+21*32, Colors.get(-1,000,000,000), 0x00, 1);
		for (int i=0; i<10; i++) {
			if (game.player.getCurrentExp() % game.player.getMaxExp() >= (i+1)*10 || game.player.getCurrentExp() == game.player.getMaxExp()) {
				screen.render(screen.xOffset+i*4+screen.width/2-21, screen.yOffset, 28+20*32, Colors.get(-1,000,121,343), 0x00,1);
			}
		}
		screen.render(screen.xOffset+36+screen.width/2-21, screen.yOffset, 28+21*32, Colors.get(-1,000,000,000), 0x01, 1);

		// Health bar
		for (int i=0; i<game.player.getMaxHealth()/2; i++) {
			if (game.player.getCurrentHealth()/2 > i) { // Full health orb
				screen.render(screen.xOffset+i*8, screen.yOffset+8, 29+21*32, Colors.get(-1,000,500,555), 0x00, 1);
			} else if ((game.player.getCurrentHealth()+1)/2 > i) { // Half health orb
				screen.render(screen.xOffset+i*8, screen.yOffset+8, 30+21*32, Colors.get(-1,000,500,555), 0x00, 1);
			} else { // Empty health orb
				screen.render(screen.xOffset+i*8, screen.yOffset+8, 31+21*32, Colors.get(-1,000,500,555), 0x00, 1);
			}
		}

		// Player rank
		Font.render("Rank:" + game.player.getRank(), screen, screen.xOffset, screen.yOffset, Colors.get(-1,-1,-1,000), 1);
	}

}