package bgibbons.game.entities;

import bgibbons.game.level.Level;

/**
 * Class for the an item that can be equiped in the shield slot.
 * @author Rony Singh
 * @author Brad Gibbons
 * @version 1.1 4 November 2016.
 */
public class Shield extends Item
{
	/**
	 * Constructor for the Shield object.
	 * @param level 	Level to add the shield item to.
	 * @param name 		The name coordinate of the shield item.
	 * @param description 		The description of the shield item.
	 * @param dexterity 	Dexterity Stat of item.
	 * @param intelligence 		Intelligence Stat of item.
	 * @param vitality 		Vitality Stat of item.
	 */	
	public Shield(Level level,String name,String description, int dexterity, int intelligence, int vitality)
	{
		super(level,name, description, dexterity, intelligence, vitality);
	}

}