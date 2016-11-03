package bgibbons.game.level.tiles;

/**
 * An extension of the BaseTile class for animated tiles.
 * @author Brad Gibbons
 * @version 1.0 12 October 2016
 */
public class AnimatedTile extends BaseTile {

	private int[][] animationTileCoords;
	private int currentAnimationIndex;
	private long lastIterationTime;
	private int animationSwitchDelay;
	
	/**
	 * Constructor for the AnimatedTile object.
	 * @param id 					The Id of the tile to be rendered.
	 * @param animationCoords 		The coordinates of the different animation states.
	 * @param tileColor				Color obtained from the Color.get(int, int, int, int) function.
	 * @param levelColor 			Integer value of the color from the level resource to render the specific tile.
	 * @param animationSwitchDelay 	The delay in milisecond to switch the animation of the tile.
	 */
	public AnimatedTile(int id, int[][] animationCoords, int tileColor, int levelColor, int animationSwitchDelay) {
		super(id, animationCoords[0][0], animationCoords[0][1], tileColor, levelColor);
		this.animationTileCoords = animationCoords;
		this.currentAnimationIndex = 0;
		this.lastIterationTime = System.currentTimeMillis();
		this.animationSwitchDelay = animationSwitchDelay;
	}

	/**
	 * Ticks the tile to switch current animation state.
	 */
	public void tick() {
		if ((System.currentTimeMillis() - lastIterationTime) >= animationSwitchDelay) {
			lastIterationTime = System.currentTimeMillis();
			currentAnimationIndex = (currentAnimationIndex + 1) % animationTileCoords.length;
			this.tileId = animationTileCoords[currentAnimationIndex][0] + (animationTileCoords[currentAnimationIndex][1] * 32);
		}
	}
}