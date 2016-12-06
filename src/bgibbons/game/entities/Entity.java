package bgibbons.game.entities;

import bgibbons.game.graphics.Screen;
import bgibbons.game.level.Level;

/**
 * Basic class to handle entities on the map.
 * @author Brad Gibbons
 * @author Jackson Schilmoeller
 * @version 1.0 12 October 2016
 */
public abstract class Entity {

	public int x, y;
	protected Level level;
	protected Level prevLevel;
	protected int tickCount;

	/**
	 * Basic constructor for an Entity object.
	 * @param level 	The level to be added to.
	 */
	public Entity(Level level) {
		init(level);
	}

	/**
	 * Initializes the entity.
	 * @param level 	The level to be added to.
	 */
	public final void init(Level level) {
		this.level = level;
		this.level = prevLevel;
		this.tickCount = 0;
	}

	/**
	 * Ticks the entity.
	 */
	public abstract void tick();

	/**
	 * Checks if entity is touching other entity.
	 * @param entity 	Entity to compare caller to.
	 * @return A boolean, true if entities are touching, false otherwise.
	 */
	public abstract boolean isTouching(Entity entity);

	/** 
	 * Renders the entity.
	 * @param screen 	Screen to render the entity to.
	 */
	public abstract void render(Screen screen);

	/**
	 * Returns level object of entity
	 * @return level    The level of the entity
	 */
	public Level getLevel() {
		return this.level;
	}

	/**
	 * Sets the new level of an entity
	 * @param level 	The new level of the entity
	 */
	public void setLevel(Level level){
		this.level = level;
	}

	/**
	 * Returns the previous level of the entity
	 * @return level 	The previous level of the entity
	 */
	public Level getPrevLevel(){
		return this.prevLevel;
	}

	/**
	 * Sets the previous level of the entity
	 * @param level 	The previous level of the entity
	 */
	public void setPrevLevel(Level level){
		this.prevLevel = level;
	}
}