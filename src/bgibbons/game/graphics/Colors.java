package bgibbons.game.graphics;

/**
 * Class to handle the colors to be used for the game.
 * @author Brad Gibbons
 * @version 1.0 12 October 2016
 */
public class Colors {

	/**
	 * Returns the color to be used for a given sprite for each of the four colors in the sprite.
	 * @param color1 	A three digit number "rgb" where each digit ranges from 0 to 5 and is for the black color of the sprite, or -1 for the color not to be rendered.
	 * @param color2 	A three digit number "rgb" where each digit ranges from 0 to 5 and is for the dark grey color of the sprite, or -1 for the color not to be rendered.
	 * @param color3 	A three digit number "rgb" where each digit ranges from 0 to 5 and is for the light grey color of the sprite, or -1 for the color not to be rendered.
	 * @param color4 	A three digit number "rgb" where each digit ranges from 0 to 5 and is for the white color of the sprite, or -1 for the color not to be rendered.
	 * @return An integer value of the colors to be rendered sprite.
	 */
	public static int get(int color1, int color2, int color3, int color4) {
		return (get(color4)<<24) + (get(color3)<<16) + (get(color2)<<8) + get(color1);
	}

	/**
	 * Returns an int representing the color for the "rgb" color inputted.
	 * @param color 	the color to be used for a given sprite for each of the four colors in the sprite
	 * @return An int between 0 and 255.
	 */
	private static int get(int color) {
		if (color < 0) {
			return 255;
		}
		int r = color/100 % 10;
		int g = color/10 % 10;
		int b = color % 10;
		return r*36 + g*6 + b;
	}

	/**
	 * Method to undo the get(int color) method.
	 * @param color 	An int between 0 and 255.
	 * @return A three digit number "rgb" where each digit ranges from 0 to 5, or -1 if the input was 255.
	 */
	private static int toRGB(int color) { //undo get(int color);
		int r = 0;
		int g = 0;
		int b = 0;
		if (color == 255) {
			return -1;
		} else {
			r = color / 36;
			g = color / 6 - r * 6;
			b = color - r*36 - g*6;
		}
		return (r*100 + g*10 + b);
	}

	/**
	 * Method to undo the get(int color1, int color2, int color3, int color4) method.
	 * @param color 	An int representing the color to be used for a given sprite for each of the four colors in the sprite.
	 * @return An array of three digit number's "rgb" where each digit ranges from 0 to 5, or -1 if the color was not to be rendered for black, dark grey, light grey, and white respectively.
	 */
	public static int[] getRGB(int color) {
		int color1 = toRGB(color & 255);
		int color2 = toRGB((color & (255<<8 | 255)) >> 8);
		int color3 = toRGB((color & (255<<16 | 255<<8 | 255)) >> 16);
		int color4 = toRGB((color & (255<<24 | 255<<16 | 255<<8 | 255)) >> 24);

		if (color4 % 10 == -5) {
			color4 += 660;
		} else if (color < 0) {
			color4+=664;
		}

		return new int[] {color1, color2, color3, color4};
	}
}