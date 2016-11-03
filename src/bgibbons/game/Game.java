package bgibbons.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;

import javax.swing.JFrame;

import bgibbons.game.entities.*;
import bgibbons.game.graphics.Colors;
import bgibbons.game.graphics.Font;
import bgibbons.game.graphics.HUD;
import bgibbons.game.graphics.Screen;
import bgibbons.game.graphics.SpriteSheet;
import bgibbons.game.level.Level;

/**
 * Main class for the game engine.
 * @author Brad Gibbons
 * @author Jackson Schilmoeller
 * @author Rony Singh
 * @version 1.0 20 October 2016
 */
public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 160;				// Width of the image to be displayed
	public static final int HEIGHT = WIDTH*4/5;			// Height of the image to be displayed
	public static final int SCALE = 3;					// Scale of the image to be displayed
	public static final String NAME = "Game";			// Name to displayed for the JFrame

	private JFrame frame;	// Declare JFrame object

	public boolean running = false;	// Variable to track if the game is running
	public int tickCount = 0;		// Variable to track the tick count

	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);	// Initialize the Buffered image with a set width, height, and type
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();		// An array to hold the pixels of the image
	private int[] colors = new int[6*6*6];														// An array of the colors available to use for the image

	private Screen screen;		// Declare the Screen object.
	public InputHandler input;	// Decleare the InputHandler object.
	public Level main_level;	// Declare the Level object.
	public Level combatLevel;	// Declare the combat level object.
	public Combat combat; 		// Declare the combat object.
	public Player player;		// Declare the Player object.
	public Menu menu;			// Declare the Menu object.
	public Sound sound;		//Declare the Sound object.

	public enum States {START, RUNNING, PAUSED, COMBAT, OVER}
	public States state;

	/**
	 * Constructor for the Game object to initialize the JFrame.
	 */
	public Game()
	{

		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));

		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/*
	 * Method to initialize the Game object's properties.
	 */
	public void init()
	{
		int index = 0;
		for (int r=0; r<6; r++) {
			for (int g=0; g<6; g++) {
				for (int b=0; b<6; b++) {
					int rr = (r * 255/5);
					int gg = (g * 255/5);
					int bb = (b * 255/5);

					colors[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}

		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/res/sprite_sheet.png"));	// Initialize the Screen with the width and height specified above and use the sprite sheet in the res/ folder.
		input = new InputHandler(this);													// Initialize the InputHandler to interact with the Game.
		main_level = new Level("/res/levels/main_level.png", "/res/entities/main_level.png");						// Initialize the Level object with the map and entities to be added on startup.
		combatLevel = new Level("/res/levels/combat_level.png", null);			// Initialize the combat level object with the map, but no entities.
		player = new Player(main_level, 16, main_level.height*8/2, input);				// Initialize the Player object with the level at the set coordinates interacting with the input handler.
		main_level.addEntity(player);													// Add the player to the level.
		menu = new Menu(input);															// Initialize the Menu object with the input handler.
		state = States.START;
		sound = new Sound("/res/sounds/BGM.wav"); 		//Intialize BGM sound object with path.
		sound.play();				//Play the sound.
	}

	/**
	 * Method to close the JFrame and stop the game.
	 */
	public void close() {
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	}

	/**
	 * Method to start a thread to run the game.
	 */
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	/**
	 * Method to stop the the game.
	 */
	public synchronized void stop() {
		running = false;
	}

	/**
	 * Handles the running of the Game by rendering the level and performing game ticks.
	 */
	public void run() {
		long lastTime = System.nanoTime();	// Gets the current system time in nano seconds
		double nsPerTick = 1000000000D/60D;	//Sets the number of nano seconds per tick

		int ticks = 0;	// Initialize the number of ticks ran
		int frames = 0;	// Initialize the number of frames rendered

		double delta = 0;	// Time until next system tick

		init();

		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			while(delta >= 1) { // Limit the ticks per second
				ticks++;
				tick();
				delta --;
				shouldRender = true;
			}

			if (shouldRender) { // Can be used to limit FPS
				frames++;
				render();
			}
		}
	}

	/**
	 * Calls the tick method to update Game.
	 * Checks if player is in combat with another entity
	 */
	public void tick() {
		tickCount++;

		switch(state) {
			case START:
			menu.tick(this);
				break;
			case RUNNING:
				player.getLevel().tick();
				Entity e = player.getLevel().getTouching(player);
				if (e != null) {
					System.out.println("test");
				}
				if(e instanceof Mob) {
					player.mainX = player.x;
					player.mainY = player.y;
					player.x = 24;
					player.y = combatLevel.height*8/2;
					e.x = (combatLevel.width*8) - 24;
					e.y = combatLevel.height*8/2;
					combatLevel.addEntity(player.getLevel().removeEntity(e));
					combatLevel.addEntity(player.getLevel().removeEntity(player));
					player.move(1,0);
					state = States.COMBAT;
					menu.state = Menu.MenuStates.COMBAT;
					combat = new Combat(player, (Mob)e);
				} else if (e instanceof HealthPad) {
					player.heal(((HealthPad)e).activate());
				}
				menu.tick(this);
				break;
			case PAUSED:
				menu.tick(this);
				break;
			case COMBAT:
				menu.tick(this);
				combat.tick();
				if (!combat.inCombat && player.getCurrentHealth() > 0) {
					state = States.RUNNING;
					menu.state = Menu.MenuStates.CLOSED;
					combatLevel.removeEntity(combat.combatant2.mob);
					combatLevel.removeEntity(player);
					player.x = player.mainX;
					player.y = player.mainY;
					main_level.addEntity(player);
					player.addExp(20);
				} else if (!combat.inCombat) {
					state = States.OVER;
				}
				break;
			case OVER:
				break;
			default:
				break;
		}
	}

	/**
	 * Render the level tiles and entities.
	 */
	public void render() {

		BufferStrategy bs = getBufferStrategy(); // Get the BufferStrategy
		if (bs == null) { // If no currently set BufferStrategy, use triple buffering
			createBufferStrategy(3);
			return;
		}
		// Set the offset of the screen based on the player location
		int xOffset = player.x - screen.width/2;
		int yOffset = player.y - screen.height/2;
		switch (state) {
			case START:
				for (int i=0; i<screen.width; i++) {
					for (int j=0; j<screen.height; j++) {
						screen.render(i, j, 0, Colors.get(0,0,0,0), 0x00, 1);
					}
				}
				menu.render(this, screen);
				break;
			case RUNNING:
				player.getLevel().renderTiles(screen, xOffset, yOffset);
				player.getLevel().renderEntities(screen);

				HUD.render(screen, this);

				menu.render(this, screen);
				break;
			case PAUSED:
				player.getLevel().renderTiles(screen, xOffset, yOffset);
				player.getLevel().renderEntities(screen);

				HUD.render(screen, this);

				menu.render(this, screen);
				break;
			case COMBAT:
				player.getLevel().renderTiles(screen, xOffset, yOffset);
				player.getLevel().renderEntities(screen);

				HUD.render(screen, this);

				menu.render(this, screen);
				break;
			case OVER:
				for (int i=0; i<screen.width; i++) {
					for (int j=0; j<screen.height; j++) {
						screen.render(i, j, 0, Colors.get(0,0,0,0), 0x00, 1);
					}
				}
				Font.render("GAME  OVER", screen, (screen.width)/2-5*8, (screen.height)/2-1*8, Colors.get(-1,-1,-1,555), 1);
				break;
			default:
				break;
		}


		// Set the values of the pixels
		for (int y=0; y<screen.height; y++) {
			for (int x=0; x<screen.width; x++) {
				int colorCode = screen.pixels[x+y*screen.width];
				if (colorCode < 255) pixels[x+y*WIDTH] = colors[colorCode];
			}
		}

		Graphics g = bs.getDrawGraphics();							// Creates a graphics context for the buffer
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);	// Draws the image from (0,0) to the (width,height) with no ImageObserver
		g.dispose();												// Diposes of the graphics context and releases any system resources that it is using
		bs.show();													// Make the next buffer visible
	}
	/**
	 * Main method to be ran for the program.
	 * @param args	Arguments to be passed into the program.
	 */
		public static void main(String[] args)
		{
			new Game().start();
		}
}
