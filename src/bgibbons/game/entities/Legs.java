package bgibbons.game.entities;

import bgibbons.game.level.Level;

/**
 * Class for the an item that can be equiped in the legs slot.
 * @author Rony Singh
 * @author Brad Gibbons
 * @version 1.1 4 November 2016.
 */
public class Legs extends Item
{
	/**
	 * Constructor for the Legs object.
	 * @param level 	Level to add the leg item to.
	 * @param name 		The name coordinate of the leg item.
	 * @param description 		The description of the leg item.
	 * @param dexterity 	Dexterity Stat of item.
	 * @param intelligence 		Intelligence Stat of item.
	 * @param vitality 		Vitality Stat of item.
	 */	
	public Legs(Level level,String name,String description, int dexterity, int intelligence, int vitality)
	{	
		super(level,name, description, dexterity, intelligence, vitality);
	}
	
}