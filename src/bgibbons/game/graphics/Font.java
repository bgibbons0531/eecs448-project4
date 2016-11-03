package bgibbons.game.graphics;

/**
 * Class to handle font rendering.
 * @author Brad Gibbons
 * @version 1.0 12 October 2016
 */
public class Font {

	private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + "0123456789.,:;'\"!?$%()-=+/      ";

	/**
	 * Renders the message onto the screen.
	 * @param msg 		Message to be displayed on the screen.
	 * @param screen 	Screen the message is to be displayed on.
	 * @param x 		The starting x position for the message to begin rendering.
	 * @param y 		The starting y position for the message to begin rendering.
	 * @param color		The color to render the font (-1, -1, -1, "RGB").
	 * @param scale 	The scale of the message.
	 */
	public static void render(String msg, Screen screen, int x, int y, int color, int scale) {
		msg = msg.toUpperCase();

		for (int i=0; i<msg.length(); i++) {
			int charIndex = chars.indexOf(msg.charAt(i));
			if (charIndex >= 0) {
				screen.render(x+(i*8), y, charIndex+30*32, color, 0x00, scale);
			}
		}
	}
}