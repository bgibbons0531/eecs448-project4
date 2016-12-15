package bgibbons.game;

import bgibbons.game.*;
import bgibbons.game.Game;
import bgibbons.game.entities.*;
import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.Screen;

/**
 * Class to handle menu interaction and display.
 * @author Brad Gibbons
 * @author Rony Singh
 * @version 1.0 20 November 2016
 */
public class Menu {

	public enum MenuStates { START, CLASSES, CLOSED, OPEN, STATS, GEAR, HELP, COMBAT }

	public enum Slots { NONE, HEAD, CHEST, LEGS, WEAPON, SHIELD, INVENTORY1, INVENTORY2, INVENTORY3, INVENTORY4, INVENTORY5, INVENTORY6 }

	public MenuStates state;

	public Slots slot;

	private boolean itemSelected;

	private InputHandler input;

	public Sound sound;
	/**
	 * Constructor for the menu object to take in and InputHandler.
	 * @param input 	InputHandler to interact with menu.
	 */
	public Menu(InputHandler input) {
		this.input = input;
		this.state = MenuStates.START;
		this.itemSelected = false;
		this.slot = Slots.NONE;
	}

	/**
	 * Ticks the menu checking for input and changing state.
	 * @param game 		Game to base changes on.
	 */
	public void tick(Game game) {
		if (input.isMouseClicked) {
			int x = input.me.getX();
			int y = input.me.getY();
			switch (state) {
				case START:
					if (151 <= x && x <= 338 && 176 <= y && y <= 240) {
						game.state = Game.States.CLASSES;
						sound = new Sound("/res/sounds/Menu/click.wav"); 		//Intialize SFX sound object with path.
						sound.playFX();				//Play the sound.
					}
					break;
				case CLASSES:
					if (28 <= x && x <= 215 && 103 <= y && y <= 166) {
						game.player.setKnight();
						game.state = Game.States.RUNNING;
						state = MenuStates.CLOSED;
						sound.playFX();				//Play the sound.
					} else if (28 <= x && x <= 215 && 214 <= y && y <= 266) {
						game.player.setWizard();
						game.state = Game.States.RUNNING;
						state = MenuStates.CLOSED;
						sound.playFX();				//Play the sound.
					} else if (28 <= x && x <= 215 && 299 <= y && y <= 366) {
						game.player.setHunter();
						game.state = Game.States.RUNNING;
						state = MenuStates.CLOSED;
						sound.playFX();				//Play the sound.
					}
					break;
				case CLOSED:
					if (393 <= x && y <= 24) { // Menu button
						state = MenuStates.OPEN;
						sound.playFX();				//Play the sound.
					}
					break;
				case OPEN:
					if (393 <= x && y <= 24) { // Close button
						state = MenuStates.CLOSED;
						sound.playFX();				//Play the sound.
					} else if (393 <= x && 25 <= y && y <= 48) { // Stats button
						state = MenuStates.STATS;
						sound.playFX();				//Play the sound.
					} else if (393 <= x && 49 <= y && y <= 72) { // Gear button
						state = MenuStates.GEAR;
						sound.playFX();				//Play the sound.
					} else if (393 <= x && x <= 440 && 73 <= y && y <= 96 && game.state != Game.States.POSTCOMBAT) { // Pause/ Play button
						sound.playFX();				//Play the sound.
						if (game.state == Game.States.RUNNING) game.state = Game.States.PAUSED;
						else game.state = Game.States.RUNNING;
					} else if (441 <= x && 73 <= y && y <= 96) { // Mute/ Unmute button
						sound.playFX();				//Play the sound.
						if (game.sound.isPlaying) {
							game.sound.pause();
						} else {
							game.sound.resume();
						}
					} else if (393 <= x && 97 <= y && y <= 120) { // Help button
						state = MenuStates.HELP;
						sound.playFX();				//Play the sound.
					} else if (393 <= x && 121 <= y && y <= 144) { // Exit button
						sound.playFX();				//Play the sound.
						game.close();
					} else {
						state = MenuStates.CLOSED;
						sound.playFX();
					}
					break;
				case STATS:
					if (343 <= x && x <= 392 && y <= 24) { // Back button
						state = MenuStates.OPEN;
						sound.playFX();				//Play the sound.
					} else if (393 <= x && y <= 24) { // Close button
						state = MenuStates.CLOSED;
						sound.playFX();				//Play the sound.
					} else if (322 <= x && x <= 338 && 52 <= y && y <= 68) {
						if (game.player.getAttributePoints() > 0) game.player.increaseDexterity();
					} else if (322 <= x && x <= 338 && 78 <= y && y <= 92) {
						if (game.player.getAttributePoints() > 0) game.player.increaseIntelligence();
					} else if (322 <= x && x <= 338 && 102 <= y && y <= 117) {
						if (game.player.getAttributePoints() > 0) game.player.increaseVitality();
					} else {
						state = MenuStates.CLOSED;
						sound.playFX();
					}
					break;
				case GEAR:
					if (343 <= x && x <= 392 && y <= 24) { // Back button
						state = MenuStates.OPEN;
						sound.playFX();				//Play the sound.
					} else if (393 <= x && y <= 24) { // Close button
						state = MenuStates.CLOSED;
						sound.playFX();				//Play the sound.
					} else if (407 <= x && x <= 425 && 51 <= y && y <= 69) { // Head Slot
						game.player.unEquip(game.player.getHead());
					} else if (407 <= x && x <= 425 && 76 <= y && y <= 93) { // Chest Slot
						game.player.unEquip(game.player.getChest());
					} else if (407 <= x && x <= 425 && 102 <= y && y <= 119) { // Legs Slot
						game.player.unEquip(game.player.getLegs());
					} else if (383 <= x && x <= 401 && 76 <= y && y <= 93) { // Weapon Slot
						game.player.unEquip(game.player.getWeapon());
					} else if (432 <= x && x <= 449 && 76 <= y && y <= 93) { // Shield Slot
						game.player.unEquip(game.player.getShield());
					} else if (346 <= x && x <= 363 && 150 <= y && y <= 168) { // Inventory Slot 1
						game.player.equip(0);
					} else if (370 <= x && x <= 387 && 150 <= y && y <= 168) { // Inventory Slot 2
						game.player.equip(1);
					} else if (394 <= x && x <= 412 && 150 <= y && y <= 168) { // Inventory Slot 3
						game.player.equip(2);
					} else if (419 <= x && x <= 437 && 150 <= y && y <= 168) { // Inventory Slot 4
						game.player.equip(3);
					} else if (444 <= x && x <= 462 && 150 <= y && y <= 168) { // Inventory Slot 5
						game.player.equip(4);
					} else if (469 <= x && x <= 486 && 150 <= y && y <= 168) { // Inventory Slot 6
						game.player.equip(5);

					} else if (407 <= x && x <= 425 && 51 <= y && y <= 69 && game.player.getHead() != null) { // Head Slot
						slot = Slots.HEAD;
					} else if (407 <= x && x <= 425 && 76 <= y && y <= 93 && game.player.getChest() != null) { // Chest Slot
						slot = Slots.CHEST;
					} else if (407 <= x && x <= 425 && 102 <= y && y <= 119 && game.player.getLegs() != null) { // Legs Slot
						slot = Slots.LEGS;
					} else if (383 <= x && x <= 401 && 76 <= y && y <= 93 && game.player.getWeapon() != null) { // Weapon Slot
						slot = Slots.WEAPON;
					} else if (432 <= x && x <= 449 && 76 <= y && y <= 93 && game.player.getShield() != null) { // Shield Slot
						slot = Slots.SHIELD;
					} else if (346 <= x && x <= 363 && 202 <= y && y <= 218 && game.player.getInventory(0) != null) { // Inventory Slot 1
						slot = Slots.INVENTORY1;
					} else if (370 <= x && x <= 387 && 202 <= y && y <= 218 && game.player.getInventory(1) != null) { // Inventory Slot 2
						slot = Slots.INVENTORY2;
					} else if (394 <= x && x <= 412 && 202 <= y && y <= 218 && game.player.getInventory(2) != null) { // Inventory Slot 3
						slot = Slots.INVENTORY3;
					} else if (419 <= x && x <= 437 && 202 <= y && y <= 218 && game.player.getInventory(3) != null) { // Inventory Slot 4
						slot = Slots.INVENTORY4;
					} else if (444 <= x && x <= 462 && 202 <= y && y <= 218 && game.player.getInventory(4) != null) { // Inventory Slot 5
						slot = Slots.INVENTORY5;
					} else if (469 <= x && x <= 486 && 202 <= y && y <= 218 && game.player.getInventory(5) != null) { // Inventory Slot 6
						slot = Slots.INVENTORY6;
					}
					if (slot != Slots.NONE) {
						if (347 <= x && x <= 412 && 222 <= y && y <= 242) { // Equip button
							switch (slot) {
								case HEAD:
									game.player.unEquip(game.player.getHead());
									break;
								case CHEST:
									game.player.unEquip(game.player.getChest());
									break;
								case LEGS:
									game.player.unEquip(game.player.getLegs());
									break;
								case WEAPON:
									game.player.unEquip(game.player.getWeapon());
									break;
								case SHIELD:
									game.player.unEquip(game.player.getShield());
									break;
								case INVENTORY1:
									game.player.equip(0);
									break;
								case INVENTORY2:
									game.player.equip(1);
									break;
								case INVENTORY3:
									game.player.equip(2);
									break;
								case INVENTORY4:
									game.player.equip(3);
									break;
								case INVENTORY5:
									game.player.equip(4);
									break;
								case INVENTORY6:
									game.player.equip(5);
									break;
							}
							slot = Slots.NONE;
						} else if (422 <= x && x <= 484 && 222 <= y && y <= 242) { // Drop button
							switch(slot) {
								case HEAD:
									game.player.drop(0);
									break;
								case CHEST:
									game.player.drop(1);
									break;
								case LEGS:
									game.player.drop(2);
									break;
								case WEAPON:
									game.player.drop(3);
									break;
								case SHIELD:
									game.player.drop(4);
									break;
								case INVENTORY1:
									game.player.drop(5);
									break;
								case INVENTORY2:
									game.player.drop(6);
									break;
								case INVENTORY3:
									game.player.drop(7);
									break;
								case INVENTORY4:
									game.player.drop(8);
									break;
								case INVENTORY5:
									game.player.drop(9);
									break;
								case INVENTORY6:
									game.player.drop(10);
									break;
								default:
									break;
							}
							slot = Slots.NONE;
						}
					} else if(x <= 342 || 243 <= y) {
						state = MenuStates.CLOSED;
						sound.playFX();
					}
					break;
				case HELP:
					if (343 <= x && x <= 392 && y <= 24) { // Back button
						state = MenuStates.OPEN;
						sound.playFX();				//Play the sound.
					} else if (393 <= x && y <= 24) { // Close button
						state = MenuStates.CLOSED;
						sound.playFX();				//Play the sound.
					} else {
						state = MenuStates.CLOSED;
						sound.playFX();
					}
					break;
				case COMBAT:
					if (52 <= x && x <= 244 && 248 <= y && y <= 293) { // Ability 1
						game.combat.useAbility(game.player.getAbility(1));

					} else if (248 <= x && x <= 435 && 248 <= y && y <= 293) { // Ability 2
						game.combat.useAbility(game.player.getAbility(2));

					} else if (52 <= x && x <= 244 && 299 <= y && y <= 341) { // Ability 3
						game.combat.useAbility(game.player.getAbility(3));

					} else if (248 <= x && x <= 435 && 299 <= y && y <= 341) { // Ability 4
						game.combat.useAbility(game.player.getAbility(4));

					}
					break;
				default:
					break;
			}
			input.isMouseClicked = false;
		}
	}
	/**
	 * Render the current menu to the screen.
	 * @param game 		The main game object.
	 * @param screen 	Screen to render to.
	 */
	public void render(Game game, Screen screen) {
		int width = 0;
		int height = 0;
		switch (state) {
			case START:
				screen.render(screen.xOffset+6*8, screen.yOffset+7*8, 31+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Top left corner
				screen.render(screen.xOffset+13*8, screen.yOffset+7*8, 31+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Top right corner
				screen.render(screen.xOffset+6*8, screen.yOffset+9*8, 31+10*32, Colors.get(-1,100,310,000), 0x02, 1); // Bottom left corner
				screen.render(screen.xOffset+13*8, screen.yOffset+9*8, 31+10*32, Colors.get(-1,100,310,000), 0x03, 1); // Bottom right corner
				for (int i=0; i<6; i++) { // Top of menu
					screen.render(screen.xOffset+7*8+i*8, screen.yOffset+7*8, 30+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				for (int i=0; i<6; i++) { // Bottom of menu
					screen.render(screen.xOffset+7*8+i*8, screen.yOffset+9*8, 30+10*32, Colors.get(-1,100,310,000), 0x02, 1);
				}
				screen.render(screen.xOffset+6*8, screen.yOffset+8*8, 29+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Left of menu
				screen.render(screen.xOffset+13*8, screen.yOffset+8*8, 29+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Right of menu
				for (int i=0; i<6; i++) {
					screen.render(screen.xOffset+7*8+i*8, screen.yOffset+8*8, 28+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				Font.render("Game Start", screen, (screen.width)/2-5*8, (screen.height)/2-5*8, Colors.get(-1,-1,-1,555), 1);
				Font.render("Begin?", screen, screen.xOffset+7*8, screen.yOffset+8*8, Colors.get(-1,-1,-1,000), 1);
				break;
			case CLASSES:
				// Class Selection
				Font.render("Select a class", screen, screen.xOffset+3*8, screen.yOffset+1*8, Colors.get(-1,-1,-1,555), 1);
				// Knight
				screen.render(screen.xOffset+1*8, screen.yOffset+4*8, 31+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Top left corner
				screen.render(screen.xOffset+8*8, screen.yOffset+4*8, 31+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Top right corner
				screen.render(screen.xOffset+1*8, screen.yOffset+6*8, 31+10*32, Colors.get(-1,100,310,000), 0x02, 1); // Bottom left corner
				screen.render(screen.xOffset+8*8, screen.yOffset+6*8, 31+10*32, Colors.get(-1,100,310,000), 0x03, 1); // Bottom right corner
				for (int i=0; i<6; i++) { // Top of menu
					screen.render(screen.xOffset+2*8+i*8, screen.yOffset+4*8, 30+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				for (int i=0; i<6; i++) { // Bottom of menu
					screen.render(screen.xOffset+2*8+i*8, screen.yOffset+6*8, 30+10*32, Colors.get(-1,100,310,000), 0x02, 1);
				}
				screen.render(screen.xOffset+1*8, screen.yOffset+5*8, 29+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Left of menu
				screen.render(screen.xOffset+8*8, screen.yOffset+5*8, 29+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Right of menu
				for (int i=0; i<6; i++) {
					screen.render(screen.xOffset+2*8+i*8, screen.yOffset+5*8, 28+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				Font.render("Knight", screen, screen.xOffset+2*8, screen.yOffset+5*8, Colors.get(-1,-1,-1,000), 1);
				Font.render("- damage", screen, screen.xOffset+10*8, screen.yOffset+4*8, Colors.get(-1,-1,-1,555), 1);
				Font.render("+ defense", screen, screen.xOffset+10*8, screen.yOffset+5*8, Colors.get(-1,-1,-1,555), 1);
				Font.render("vit based", screen, screen.xOffset+10*8, screen.yOffset+6*8, Colors.get(-1,-1,-1,555), 1);
				// Wizard
				screen.render(screen.xOffset+1*8, screen.yOffset+8*8, 31+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Top left corner
				screen.render(screen.xOffset+8*8, screen.yOffset+8*8, 31+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Top right corner
				screen.render(screen.xOffset+1*8, screen.yOffset+10*8, 31+10*32, Colors.get(-1,100,310,000), 0x02, 1); // Bottom left corner
				screen.render(screen.xOffset+8*8, screen.yOffset+10*8, 31+10*32, Colors.get(-1,100,310,000), 0x03, 1); // Bottom right corner
				for (int i=0; i<6; i++) { // Top of menu
					screen.render(screen.xOffset+2*8+i*8, screen.yOffset+8*8, 30+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				for (int i=0; i<6; i++) { // Bottom of menu
					screen.render(screen.xOffset+2*8+i*8, screen.yOffset+10*8, 30+10*32, Colors.get(-1,100,310,000), 0x02, 1);
				}
				screen.render(screen.xOffset+1*8, screen.yOffset+9*8, 29+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Left of menu
				screen.render(screen.xOffset+8*8, screen.yOffset+9*8, 29+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Right of menu
				for (int i=0; i<6; i++) {
					screen.render(screen.xOffset+2*8+i*8, screen.yOffset+9*8, 28+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				Font.render("Wizard", screen, screen.xOffset+2*8, screen.yOffset+9*8, Colors.get(-1,-1,-1,000), 1);
				Font.render("+ damage", screen, screen.xOffset+10*8, screen.yOffset+8*8, Colors.get(-1,-1,-1,555), 1);
				Font.render("- defense", screen, screen.xOffset+10*8, screen.yOffset+9*8, Colors.get(-1,-1,-1,555), 1);
				Font.render("Int based", screen, screen.xOffset+10*8, screen.yOffset+10*8, Colors.get(-1,-1,-1,555), 1);
				// Hunter
				screen.render(screen.xOffset+1*8, screen.yOffset+12*8, 31+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Top left corner
				screen.render(screen.xOffset+8*8, screen.yOffset+12*8, 31+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Top right corner
				screen.render(screen.xOffset+1*8, screen.yOffset+14*8, 31+10*32, Colors.get(-1,100,310,000), 0x02, 1); // Bottom left corner
				screen.render(screen.xOffset+8*8, screen.yOffset+14*8, 31+10*32, Colors.get(-1,100,310,000), 0x03, 1); // Bottom right corner
				for (int i=0; i<6; i++) { // Top of menu
					screen.render(screen.xOffset+2*8+i*8, screen.yOffset+12*8, 30+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				for (int i=0; i<6; i++) { // Bottom of menu
					screen.render(screen.xOffset+2*8+i*8, screen.yOffset+14*8, 30+10*32, Colors.get(-1,100,310,000), 0x02, 1);
				}
				screen.render(screen.xOffset+1*8, screen.yOffset+13*8, 29+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Left of menu
				screen.render(screen.xOffset+8*8, screen.yOffset+13*8, 29+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Right of menu
				for (int i=0; i<6; i++) {
					screen.render(screen.xOffset+2*8+i*8, screen.yOffset+13*8, 28+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				Font.render("Hunter", screen, screen.xOffset+2*8, screen.yOffset+13*8, Colors.get(-1,-1,-1,000), 1);
				Font.render("= damage", screen, screen.xOffset+10*8, screen.yOffset+12*8, Colors.get(-1,-1,-1,555), 1);
				Font.render("= defense", screen, screen.xOffset+10*8, screen.yOffset+13*8, Colors.get(-1,-1,-1,555), 1);
				Font.render("Dex based", screen, screen.xOffset+10*8, screen.yOffset+14*8, Colors.get(-1,-1,-1,555), 1);
				break;
			case CLOSED:
				// Menu button
				for (int i=0; i<4; i++) {
					screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset, (28+i)+19*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				break;
			case OPEN:
				// Close button
				for (int i=0; i<4; i++) {
					screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset, (28+i)+18*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				// Stats button
				for (int i=0; i<4; i++) {
					screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset+8, (28+i)+16*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				// Gear button
				for (int i=0; i<4; i++) {
					screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset+16, (28+i)+12*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				// Pause/ Play button
				if (game.state == Game.States.RUNNING || game.state == Game.States.POSTCOMBAT) {
					for (int i=0; i<2; i++) {
						screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset+24, (28+i)+14*32, Colors.get(-1,100,310,000), 0x00, 1);
					}
				} else {
					for (int i=0; i<2; i++) {
						screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset+24, (30+i)+14*32, Colors.get(-1,100,310,000), 0x00, 1);
					}
				}
				// Mute/ Untmute button
				if (game.sound.isPlaying) {
					for (int i=0; i<2; i++) {
						screen.render(screen.xOffset+screen.width-16+i*8, screen.yOffset+24, (28+i)+15*32, Colors.get(-1,100,310,000), 0x00, 1);
					}
				} else {
					for (int i=0; i<2; i++) {
						screen.render(screen.xOffset+screen.width-16+i*8, screen.yOffset+24, (30+i)+15*32, Colors.get(-1,100,310,000), 0x00, 1);
					}
				}
				// Help button
				for (int i=0; i<4; i++) {
					screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset+32, (28+i)+13*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				// Exit button
				for (int i=0; i<4; i++) {
					screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset+40, (28+i)+17*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				break;
			case STATS:
				// Back button
				for (int i=0; i<2; i++) {
					screen.render(screen.xOffset+screen.width-48+i*8, screen.yOffset, (30+i)+11*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				// Close button
				for (int i=0; i<4; i++) {
					screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset, (28+i)+18*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				// Stats Menu
				width = 6;
				height = 4;
				screen.render(screen.xOffset+screen.width-width*8, screen.yOffset+8, 31+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Top left corner
				screen.render(screen.xOffset+screen.width-8, screen.yOffset+8, 31+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Top right corner
				screen.render(screen.xOffset+screen.width-width*8, screen.yOffset+height*8, 31+10*32, Colors.get(-1,100,310,000), 0x02, 1); // Bottom left corner
				screen.render(screen.xOffset+screen.width-8, screen.yOffset+height*8, 31+10*32, Colors.get(-1,100,310,000), 0x03, 1); // Bottom right corner
				for (int i=0; i<width-2; i++) { // Top of menu
					screen.render(screen.xOffset+screen.width-(width-1)*8+i*8, screen.yOffset+8, 30+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				for (int i=0; i<width-2; i++) { // Bottom of menu
					screen.render(screen.xOffset+screen.width-(width-1)*8+i*8, screen.yOffset+height*8, 30+10*32, Colors.get(-1,100,310,000), 0x02, 1);
				}
				for (int i=0; i<height-2; i++) { // Left of menu
					screen.render(screen.xOffset+screen.width-width*8, screen.yOffset+16+i*8, 29+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				for (int i=0; i<height-2; i++) { // Right of menu
					screen.render(screen.xOffset+screen.width-8, screen.yOffset+16+i*8, 29+10*32, Colors.get(-1,100,310,000), 0x01, 1);
				}
				for (int i=0; i<width-2; i++) {
					for (int j=0; j<height-2; j++) {
						screen.render(screen.xOffset+screen.width-(width-1)*8+i*8, screen.yOffset+16+j*8, 28+10*32, Colors.get(-1,100,310,000), 0x00, 1);
					}
				}
				Font.render("Stats", screen, screen.xOffset+screen.width-44, screen.yOffset+8, Colors.get(-1,-1,-1,000), 1);
				Font.render("Dex:" + game.player.getDexterity(), screen, screen.xOffset+screen.width-48, screen.yOffset+16, Colors.get(-1,-1,-1,000), 1);
				Font.render("Int:" + game.player.getIntelligence(), screen, screen.xOffset+screen.width-48, screen.yOffset+24, Colors.get(-1,-1,-1,000), 1);
				Font.render("Vit:" + game.player.getVitality(), screen, screen.xOffset+screen.width-48, screen.yOffset+32, Colors.get(-1,-1,-1,000), 1);
				if (game.player.getAttributePoints() > 0) {
					screen.render(screen.xOffset+screen.width-56, screen.yOffset+16, 29+11*32, Colors.get(-1,100,310,000), 0x00, 1);
					screen.render(screen.xOffset+screen.width-56, screen.yOffset+24, 29+11*32, Colors.get(-1,100,310,000), 0x00, 1);
					screen.render(screen.xOffset+screen.width-56, screen.yOffset+32, 29+11*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				break;
			case GEAR:
				// Back button
				for (int i=0; i<2; i++) {
					screen.render(screen.xOffset+screen.width-48+i*8, screen.yOffset, (30+i)+11*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				// Close button
				for (int i=0; i<4; i++) {
					screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset, (28+i)+18*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				// Gear Menu
				width = 6;
				height = 8;
				screen.render(screen.xOffset+screen.width-width*8, screen.yOffset+8, 31+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Top left corner
				screen.render(screen.xOffset+screen.width-8, screen.yOffset+8, 31+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Top right corner
				screen.render(screen.xOffset+screen.width-width*8, screen.yOffset+height*8, 31+10*32, Colors.get(-1,100,310,000), 0x02, 1); // Bottom left corner
				screen.render(screen.xOffset+screen.width-8, screen.yOffset+height*8, 31+10*32, Colors.get(-1,100,310,000), 0x03, 1); // Bottom right corner
				for (int i=0; i<width-2; i++) { // Top of menu
					screen.render(screen.xOffset+screen.width-(width-1)*8+i*8, screen.yOffset+8, 30+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				for (int i=0; i<width-2; i++) { // Bottom of menu
					screen.render(screen.xOffset+screen.width-(width-1)*8+i*8, screen.yOffset+height*8, 30+10*32, Colors.get(-1,100,310,000), 0x02, 1);
				}
				for (int i=0; i<height-2; i++) { // Left of menu
					screen.render(screen.xOffset+screen.width-width*8, screen.yOffset+16+i*8, 29+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				for (int i=0; i<height-2; i++) { // Right of menu
					screen.render(screen.xOffset+screen.width-8, screen.yOffset+16+i*8, 29+10*32, Colors.get(-1,100,310,000), 0x01, 1);
				}
				for (int i=0; i<width-2; i++) {
					for (int j=0; j<height-2; j++) {
						screen.render(screen.xOffset+screen.width-(width-1)*8+i*8, screen.yOffset+16+j*8, 28+10*32, Colors.get(-1,100,310,000), 0x00, 1);
					}
				}
				Font.render("Gear", screen, screen.xOffset+screen.width-40, screen.yOffset+8, Colors.get(-1,-1,-1,000), 1);
				// Gear slots
				screen.render(screen.xOffset+screen.width-28, screen.yOffset+16, 28+11*32, Colors.get(-1,100,420,000), 0x00, 1); // Head
				screen.render(screen.xOffset+screen.width-28, screen.yOffset+24, 28+11*32, Colors.get(-1,100,420,000), 0x00, 1); // Chest
				screen.render(screen.xOffset+screen.width-28, screen.yOffset+32, 28+11*32, Colors.get(-1,100,420,000), 0x00, 1); // Legs
				screen.render(screen.xOffset+screen.width-36, screen.yOffset+24, 28+11*32, Colors.get(-1,100,420,000), 0x00, 1); // Weapon
				screen.render(screen.xOffset+screen.width-20, screen.yOffset+24, 28+11*32, Colors.get(-1,100,420,000), 0x00, 1); // Shield
				// Inventory
				for (int i=0; i<6; i++) {
					screen.render(screen.xOffset+screen.width-48+i*8, screen.yOffset+64, 28+11*32, Colors.get(-1,100,420,000), 0x00, 1);
				}
				// Actual gear
				if (game.player.getHead() != null) {
					screen.render(screen.xOffset+screen.width-28, screen.yOffset+16, 3+19*32, Colors.get(-1,222,444,-1), 0x00, 1); // Head
				}
				if (game.player.getChest() != null) {
					screen.render(screen.xOffset+screen.width-28, screen.yOffset+24, 4+19*32, Colors.get(-1,222,444,-1), 0x00, 1); // Chest
				}
				if (game.player.getLegs() != null) {
					screen.render(screen.xOffset+screen.width-28, screen.yOffset+32, 5+19*32, Colors.get(-1,222,444,-1), 0x00, 1); // Legs
				}
				if (game.player.getWeapon() != null) {
					screen.render(screen.xOffset+screen.width-36, screen.yOffset+24, 1+19*32, Colors.get(-1,222,444,320), 0x00, 1); // Weapon
				}
				if (game.player.getShield() != null) {
					screen.render(screen.xOffset+screen.width-20, screen.yOffset+24, 2+19*32, Colors.get(-1,222,444,-1), 0x00, 1); // Weapon
				}
				for (int i=0; i<6; i++) { // Inventory
					Item e = game.player.getInventory(i);
					if (e != null) {
						int yTile = 19;
						int xTile = 0;
						if (e instanceof Weapon) {
							xTile += 1;
						} else if (e instanceof Shield) {
							xTile += 2;
						} else if (e instanceof Helmet) {
							xTile += 3;
						} else if (e instanceof Chest) {
							xTile += 4;
						} else if (e instanceof Legs) {
							xTile += 5;
						}
						screen.render(screen.xOffset+screen.width-48+i*8, screen.yOffset+64, xTile+yTile*32, Colors.get(-1,222,444,320), 0x00, 1);
					}
				}
				// Item has been selected
				if (slot != Slots.NONE) {
					for (int i=0; i<3; i++) {
						screen.render(screen.xOffset+screen.width-48+i*8, screen.yOffset+72, 28+i+9*32, Colors.get(-1,100,310,000), 0x00, 1);
						screen.render(screen.xOffset+screen.width-24+i*8, screen.yOffset+72, 28+i+8*32, Colors.get(-1,100,310,000), 0x00, 1);
					}
					Item item = null;
					switch (slot) {
						case HEAD:
							item = game.player.getHead();
							break;
						case CHEST:
							item = game.player.getChest();
							break;
						case LEGS:
							item = game.player.getLegs();
							break;
						case WEAPON:
							item = game.player.getWeapon();
							break;
						case SHIELD:
							item = game.player.getShield();
							break;
						case INVENTORY1:
							item = game.player.getInventory(0);
							break;
						case INVENTORY2:
							item = game.player.getInventory(1);
							break;
						case INVENTORY3:
							item = game.player.getInventory(2);
							break;
						case INVENTORY4:
							item = game.player.getInventory(3);
							break;
						case INVENTORY5:
							item = game.player.getInventory(4);
							break;
						case INVENTORY6:
							item = game.player.getInventory(5);
							break;
					}
					// Dex
					Font.render("Dex:" + item.getDexterity(), screen, screen.xOffset+screen.width-48, screen.yOffset+40, Colors.get(-1,-1,-1,000), 1);
					// Int
					Font.render("Int:" + item.getIntelligence(), screen, screen.xOffset+screen.width-48, screen.yOffset+48, Colors.get(-1,-1,-1,000), 1);
					// Vit
					Font.render("Vit:" + item.getVitality(), screen, screen.xOffset+screen.width-48, screen.yOffset+56, Colors.get(-1,-1,-1,000), 1);
				}
				break;
			case HELP:
				// Back button
				for (int i=0; i<2; i++) {
					screen.render(screen.xOffset+screen.width-48+i*8, screen.yOffset, (30+i)+11*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				// Close button
				for (int i=0; i<4; i++) {
					screen.render(screen.xOffset+screen.width-32+i*8, screen.yOffset, (28+i)+18*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				// Stats Menu
				width = 6;
				height = 2;
				screen.render(screen.xOffset+screen.width-width*8, screen.yOffset+8, 31+10*32, Colors.get(-1,100,310,000), 0x00, 1); // Top left corner
				screen.render(screen.xOffset+screen.width-8, screen.yOffset+8, 31+10*32, Colors.get(-1,100,310,000), 0x01, 1); // Top right corner
				screen.render(screen.xOffset+screen.width-width*8, screen.yOffset+height*8, 31+10*32, Colors.get(-1,100,310,000), 0x02, 1); // Bottom left corner
				screen.render(screen.xOffset+screen.width-8, screen.yOffset+height*8, 31+10*32, Colors.get(-1,100,310,000), 0x03, 1); // Bottom right corner
				for (int i=0; i<width-2; i++) { // Top of menu
					screen.render(screen.xOffset+screen.width-(width-1)*8+i*8, screen.yOffset+8, 30+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				for (int i=0; i<width-2; i++) { // Bottom of menu
					screen.render(screen.xOffset+screen.width-(width-1)*8+i*8, screen.yOffset+height*8, 30+10*32, Colors.get(-1,100,310,000), 0x02, 1);
				}
				for (int i=0; i<height-2; i++) { // Left of menu
					screen.render(screen.xOffset+screen.width-width*8, screen.yOffset+16+i*8, 29+10*32, Colors.get(-1,100,310,000), 0x00, 1);
				}
				for (int i=0; i<height-2; i++) { // Right of menu
					screen.render(screen.xOffset+screen.width-8, screen.yOffset+16+i*8, 29+10*32, Colors.get(-1,100,310,000), 0x01, 1);
				}
				for (int i=0; i<width-2; i++) {
					for (int j=0; j<height-2; j++) {
						screen.render(screen.xOffset+screen.width-(width-1)*8+i*8, screen.yOffset+16+j*8, 28+10*32, Colors.get(-1,100,310,000), 0x00, 1);
					}
				}
				Font.render("Good", screen, screen.xOffset+screen.width-40, screen.yOffset+8, Colors.get(-1,-1,-1,000), 1);
				Font.render("Luck", screen, screen.xOffset+screen.width-40, screen.yOffset+16, Colors.get(-1,-1,-1,000), 1);
				break;
			case COMBAT:
				width = 16; // Even numbers preferred
				height = 4; // Even numbers preferred
				screen.render(screen.xOffset+screen.width/2-width/2*8, screen.yOffset+screen.height-24-(height-1)*8, 31+10*32, Colors.get(-1,000,555,000), 0x00, 1); // Top left corner
				screen.render(screen.xOffset+screen.width/2+(width/2-1)*8, screen.yOffset+screen.height-24-(height-1)*8, 31+10*32, Colors.get(-1,000,555,000), 0x01, 1); // Top right corner
				screen.render(screen.xOffset+screen.width/2-width/2*8, screen.yOffset+screen.height-24, 31+10*32, Colors.get(-1,000,555,000), 0x02, 1); // Bottom left corner
				screen.render(screen.xOffset+screen.width/2+(width/2-1)*8, screen.yOffset+screen.height-24, 31+10*32, Colors.get(-1,000,555,000), 0x03, 1); // Bottom right corner
				for (int i=0; i<width-2; i++) { // Top of menu
					screen.render(screen.xOffset+screen.width/2-width/2*8+(i+1)*8, screen.yOffset+screen.height-24-(height-1)*8, 30+10*32, Colors.get(-1,000,555,000), 0x00, 1);
				}
				for (int i=0; i<width-2; i++) { // Bottom of menu
					screen.render(screen.xOffset+screen.width/2-width/2*8+(i+1)*8, screen.yOffset+screen.height-24, 30+10*32, Colors.get(-1,000,555,000), 0x02, 1);
				}
				for (int i=0; i<height-2; i++) { // Left of menu
					screen.render(screen.xOffset+screen.width/2-width/2*8, screen.yOffset+screen.height-24-(height-2)*8+i*8, 29+10*32, Colors.get(-1,000,555,000), 0x00, 1);
				}
				for (int i=0; i<height-2; i++) { // Right of menu
					screen.render(screen.xOffset+screen.width/2+(width/2-1)*8, screen.yOffset+screen.height-24-(height-2)*8+i*8, 29+10*32, Colors.get(-1,000,555,000), 0x01, 1);
				}
				for (int i=0; i<width-2; i++) {
					for (int j=0; j<height-2; j++) {
						screen.render(screen.xOffset+screen.width/2-width/2*8+(i+1)*8, screen.yOffset+screen.height-24-(height-1)*8+(j+1)*8, 28+10*32, Colors.get(-1,000,555,000), 0x00, 1);
					}
				}
				for (int i=0; i<width; i++) { // Middle horizontal of menu
					screen.render(screen.xOffset+screen.width/2-width/2*8+(i)*8, screen.yOffset+screen.height-24-(height/2-1)*8, 30+10*32, Colors.get(-1,000,-1,-1), 0x00, 1);
				}
				for (int i=0; i<height; i++) { // Middle vertical of menu
					screen.render(screen.xOffset+screen.width/2, screen.yOffset+screen.height-24-(height-1)*8+i*8, 29+10*32, Colors.get(-1,000,-1,-1), 0x00, 1);
				}

				// Render ability names
				if (game.player.getAbility(1) != null) { // Ability 1
					String abilityName = game.player.getAbility(1).getName();
					for (int i=0; i<12; i++) {
						Font.render(abilityName.charAt(i) + "", screen, screen.xOffset+screen.width/2-width/2*8+i*8-(i/6*48),  screen.yOffset+screen.height-16-(height-2)*8-(height-2)*8+(i/6)*8, Colors.get(-1,-1,-1,222), 1);
					}
				}
				if (game.player.getAbility(2) != null) { // Ability 2
					String abilityName = game.player.getAbility(2).getName();
					for (int i=0; i<12; i++) {
						Font.render(abilityName.charAt(i) + "", screen, screen.xOffset+screen.width/2+i*8-(i/6*48), screen.yOffset+screen.height-32-(height-2)*8+(i/6)*8, Colors.get(-1,-1,-1,222), 1);
					}
				}
				if (game.player.getAbility(3) != null) { // Ability 3
					String abilityName = game.player.getAbility(3).getName();
					for (int i=0; i<12; i++) {
						Font.render(abilityName.charAt(i) + "", screen, screen.xOffset+screen.width/2-width/2*8+i*8-(i/6*48), screen.yOffset+screen.height-16-(height-2)*8+(i/6)*8, Colors.get(-1,-1,-1,222), 1);
					}
				}
				if (game.player.getAbility(4) != null) { // Ability 4
					String abilityName = game.player.getAbility(4).getName();
					for (int i=0; i<12; i++) {
						Font.render(abilityName.charAt(i) + "", screen, screen.xOffset+screen.width/2+i*8-(i/6*48), screen.yOffset+screen.height-16-(height-2)*8+(i/6)*8, Colors.get(-1,-1,-1,222), 1);
					}
				}
				// Render cooldown timers
				Font.render(game.combat.combatant1.ability1CD + "", screen, screen.xOffset+8*8,  screen.yOffset+screen.height-16-(height)*8, Colors.get(-1,-1,-1,124), 1);
				Font.render(game.combat.combatant1.ability2CD + "", screen, screen.xOffset+16*8,  screen.yOffset+screen.height-16-(height)*8, Colors.get(-1,-1,-1,124), 1);
				Font.render(game.combat.combatant1.ability3CD + "", screen, screen.xOffset+8*8,  screen.yOffset+screen.height-16-(height-2)*8, Colors.get(-1,-1,-1,124), 1);
				Font.render(game.combat.combatant1.ability4CD + "", screen, screen.xOffset+16*8,  screen.yOffset+screen.height-16-(height-2)*8, Colors.get(-1,-1,-1,124), 1);
				break;
			default:
				break;
		}
	}

}
