package zombies;

import javax.swing.*;
import java.awt.*;

public class Renderer {
	/*
	 * Main Function
	 */
	public static void main(String[] args) {
		/* Schedule a job for the event-dispatching thread:
		 * creating and showing this application's GUI.
		 */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initWindow();
			}
		});
	}
	
	/* 
	 * Init the window with the GUI
	 */
	private static void initWindow() {
		/* Create and set up the window. */
		JFrame frame = new JFrame("Zombie Simulator 2015");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* Set location and size of the window */
		frame.add(new World());
		frame.setSize(600, 400);
		frame.setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		
		frame.setVisible(true);
	}
}
