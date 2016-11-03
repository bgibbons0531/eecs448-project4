
package bgibbons.game.entities;
import bgibbons.game.level.Level;
import bgibbons.game.graphics.Screen;
/**
 * File Name:Item.java
 * Description: Class for the items for the game 
 * @author Rony Singh
 * @version 1.0 24 October 2016.
 * 
 */
public class Item extends Entity
{
	/**
	*Variables for Item.
	*/
	public String name;
	public String description;
	public int dexterity;
	public int intelligence;
	public int vitality;
	/**
	 * Constructor for the Item object.
	 * @param level 	Level to add the Item to.
	 * @param name 		The name coordinate of the item.
	 * @param description 		The description of the Item.
	 * @param dexterity 	Dexterity Stat of item.
	 * @param intelligence 		Intelligence Stat of item.
	 * @param vitality 		Vitality Stat of item.
	 */	
	public Item(Level level, String name, String description, int dexterity, int intelligence, int vitality)
	{
		super(level);
		this.name="";
		this.description="";
		this.dexterity=dexterity;
		this.intelligence=intelligence;
		this.vitality=vitality;
	}
	/**
	 * Ticks the item to allow for animation.
	 */
	public void tick() 
	{
		
	}
	/**
	 * Renders the item to the screen
	 * @param screen 	The screen the item is to be rendered to.
	 */
	public void render(Screen screen) 
	{
		
	}

	public boolean isTouching(Entity entity) {
		return false;
	}
}