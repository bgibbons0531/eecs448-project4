package bgibbons.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Handles the user input for the program.
 * @author Brad Gibbons
 * @version 1.0 12 October 2016
 */
public class InputHandler implements KeyListener, MouseListener {

	public boolean isMouseClicked;
	public MouseEvent me;

	/**
	 * Constructor the the InputHandler object.
	 * @param game 	Game object to be monitored by the KeyListener.
	 */
	public InputHandler(Game game) {
		game.addKeyListener(this);
		game.addMouseListener(this);
		this.isMouseClicked = false;
	}

	/**
	 * Key object to keep track of keys currently pressed
	 * @author Brad Gibbons
	 * @version 1.0 12 October 2016
	 */
	public class Key {
		private int numTimesPressed = 0;
		private boolean pressed = false;

		/**
		 * Returns the number of times the key has been pressed.
		 * @return 	Number of times the key has been pressed.
		 */
		public int getNumTimesPressed() {
			return numTimesPressed;
		}

		/**
		 * Gets whether or not the key is pressed.
		 * @return 	A boolean with True if the key is pressed, false otherwise.
		 */
		public boolean isPressed() {
			return pressed;
		}

		/**
		 * Toggles the key based on the input
		 * @param isPressed 	The current state of the key.
		 */
		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if (pressed) {
				numTimesPressed++;
			}
		}
	}

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();

	/**
	 * Method to handle if a key is pressed.
	 * @param e 	A KeyEvent that determined a keystroke occurred in a component.
	 */
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}

	/**
	 * Method to handle if a key is released.
	 * @param e 	A KeyEvent that determined a keystroke occurred in a component.
	 */
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}

	/**
	 * Method to handle if a key is typed.
	 * @param e 	A KeyEvent that determined a keystroke occurred in a component.
	 */
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Toggles the key based on the key code and the key's current state.
	 * @param keyCode 	The code of the current keystroke.
	 * @param isPressed The status of the current keystroke.
	 */
	public void toggleKey(int keyCode, boolean isPressed) {
		if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) { 
			up.toggle(isPressed); 
		}
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) { 
			down.toggle(isPressed); 
		}
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) { 
			left.toggle(isPressed); 
		}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) { 
			right.toggle(isPressed); 
		}
	}

	/**
	 * Invoked when the mouse enters a component.
	 * @param e 	Event that determined a mouse entered a component.
	 */
	public void mouseEntered(MouseEvent e) {
		this.me = e;
	}

	/**
	 * Invoked when the mouse exits a component.
	 * @param e 	Event that determined a mouse exited a component.
	 */
	public void mouseExited(MouseEvent e) {
		this.me = e;
	}

	/**
	 * Invoked when the mouse button has been clicked (pressed and released) on a component.
	 * @param e 	Event that determined a mouse button has been clicked on a component.
	 */
	public void mouseClicked(MouseEvent e) {
		this.me = e;
		isMouseClicked = true;
	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 * @param e 	Event that determined a mouse button has been clicked on a component.
	 */
	public void mousePressed(MouseEvent e) {
		this.me = e;
	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 * @param e 	Event that determined a mouse button has been released on a component.
	 */
	public void mouseReleased(MouseEvent e) {
		this.me = e;
	}
}