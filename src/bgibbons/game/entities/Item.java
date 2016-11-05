package bgibbons.game.entities;

import bgibbons.game.level.Level;
import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Screen;

/**
 * Class for the items for the game 
 * @author Rony Singh
 * @author Brad Gibbons
 * @version 1.1 4 November 2016.
 * 
 */
public class Item extends Entity
{
	/**
	 * Variables for Item.
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
		this.name=name;
		this.description=description;
		this.dexterity=dexterity;
		this.intelligence=intelligence;
		this.vitality=vitality;
		this.x = 17*8;
		this.y = 8*8;
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
		screen.render(x, y, 0+19*32, Colors.get(-1,110,320,430), 0x00, 1);
	}

	public boolean isTouching(Entity entity) {
		return false;
	}

	/**
	*Returns Dexterity variable
	*@return A integer of the dexterity value.
	*/
	public int getDexterity() 
	{
		return dexterity;
	}
	/**
	*Returns Intelligence variable
	*@return A integer of intelligence value.
	*/
	public int getIntelligence() 
	{
		return intelligence;
	}

	/**
	*Returns Vitality variable.
	*@return A integer of the vitality value.
	*/
	public int getVitality() 
	{
		return vitality;
	}
}