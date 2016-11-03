
package bgibbons.game.entities;
import bgibbons.game.level.Level;
import bgibbons.game.graphics.Screen;
/**
 * File Name:Legs.java
 * Description: Class for the Legs inherited from Item 
 * @author Rony Singh
 * @version 1.0 24 October 2016.
 * 
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
		super(level,"Legs","Leggy",2,2,2);
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