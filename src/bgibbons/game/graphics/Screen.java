package bgibbons.game.graphics;

/**
 * The screen object to be displayed to the user.
 * @author Brad Gibbons
 * @version 1.0 12 October 2016
 */
public class Screen {
 
    public static final int MAP_WIDTH = 64;
    public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;

    public static final byte BIT_MIRROR_X = 0x01;
    public static final byte BIT_MIRROR_Y = 0x02;

    public int[] pixels;

    public int xOffset = 0;
    public int yOffset = 0;

    public int width;
    public int height;

    public int dim;

    public SpriteSheet sheet;

    /**
     * Constructor object for the Screen object.
     * @param width     Width of the screen.
     * @param height    Height of the screen.
     * @param sheet     Sprite sheet to be used.
     */
    public Screen(int width, int height, SpriteSheet sheet) {
        this.width = width;
        this.height = height;
        this.sheet = sheet;
        this.dim = 0;
        pixels = new int[width * height];

    }

    /**
     * Sets the offset of the screen based on the player and level.
     * @param xOffset   Offset in the x direction to be used.
     * @param yOffset   Offset in the y direction to be used.
     */
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * Renders to the screen.
     * @param xPos      Starting x position to render to.
     * @param yPos      Starting y position to render to
     * @param tile      Tile from the sprite sheet to render to the screen.
     * @param color     Color obtained from the Color.get(int, int, int, int) function.
     * @param mirrorDir An integer value for how the sprite tile is to mirrored (0x00 where first digit is x mirror, second is y mirror).
     * @param scale     Scale of the sprite to be rendered.
     */
    public void render(int xPos, int yPos, int tile, int color, int mirrorDir, int scale) {

        xPos -= xOffset;
        yPos -= yOffset;

        boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0;
        boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0;

        int scaleMap = scale-1;
        int xTile = tile % 32;
        int yTile = tile / 32;
        int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width;

        for (int y = 0; y<8; y++) {
            int ySheet = y;
            if (mirrorY) ySheet = 7-y;

            int yPixel = y + yPos + (y * scaleMap) - ((scaleMap << 3)/2);

            for (int x=0; x<8; x++) {
            	int xSheet = x;
            	if (mirrorX) xSheet = 7-x;

            	int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 3)/2);

            	int col = (color >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 8)) & 255;

            	if (col < 255) {
            		for (int yScale=0; yScale < scale; yScale++) {
            			if (yPixel + yScale < 0 || yPixel + yScale >= height) continue;

            			for (int xScale=0; xScale < scale; xScale++) {
            				if (xPixel + xScale < 0 || xPixel + xScale >= width) continue;

            				pixels[(xPixel + xScale) + (yPixel + yScale) * width] = col;
            			}
            		}
            	}
            }
        }
    }
}