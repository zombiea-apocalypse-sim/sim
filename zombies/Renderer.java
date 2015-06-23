package zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Renderer {
	final static int windowBarHeight = 22;
	
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
		
		JLayeredPane total = new JLayeredPane();
		frame.add(total, BorderLayout.CENTER);
		
		/* Set location and size of the window */
		int width = 120;
		int height = 80;
		World world = new World(width, height);
		
		world.setBounds(0, 0, width*world.tile_size, height*world.tile_size);
		world.setOpaque(true);
		total.add(world, 0, 0);
		
		Menu menu = new Menu();
		menu.setBounds(200, 200, 800, 400);
		menu.setOpaque(true);
		menu.setVisible(false);
		total.add(menu, 1, 0);
		
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == 'm') {
					menu.setVisible(!menu.isVisible());
				}
			}
		});
		
		frame.setSize(1200, 800 + windowBarHeight);
		frame.setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		
		frame.setVisible(true);
		
		new javax.swing.Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				world.update();
				frame.getContentPane().repaint();
        	}
		}).start();
	}
}
