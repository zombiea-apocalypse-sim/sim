package zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class ZombieSimulator {
	/* Simulator constants */
	static int windowBarHeight = 0;
	final static int width = 120;
	final static int height = 80;
	final static int tileSize = 10;
	private static boolean pause = false;
	
	/*
	 * Main Function
	 */
	public static void main(String[] args) {
		/* Schedule a job for the event-dispatching thread:
		 * creating and showing this application's GUI.
		 */
		
		if (System.getProperty("os.name").startsWith("Mac")) {
			windowBarHeight = 22;
		}
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initWindow();
			}
		});
	}

	/*
	 * Init the window with the GUI.
	 */
	private static void initWindow() {
		/* Create and set up the window (JFrame). */
		int windowWidth = width * tileSize;
		int windowHeight = height * tileSize;

		JFrame frame = new JFrame("Zombie Simulator 2015");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL iconURL = ZombieSimulator.class.getResource("resources/zombie-icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());

		frame.setSize(windowWidth, windowHeight + windowBarHeight);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dim.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dim.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setResizable(false);
		frame.setVisible(true);
		
		/* We create a JLayeredPane to draw our Menu on top of our World */
		JLayeredPane total = new JLayeredPane();
		frame.add(total, BorderLayout.CENTER);
		
		/* Create ans setup World (extends JPanel) */
		World world = new World(width, height, width * height / 6, 10);

		world.setTileSize(tileSize);
		world.setBounds(0, 0, windowWidth, windowHeight);
		world.setOpaque(true);
		total.add(world, 0, 0);
		
		/* Create ans setup Menu (extends JPanel) */
		Menu menu = new Menu(world);
		menu.setBounds(200, 200, windowWidth - 2 * 200, windowHeight - 2 * 200);
		menu.setOpaque(true);
		menu.setVisible(false);
		total.add(menu, 1, 0);
		
		/* Add keylistener for opening and closing menu */
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'm') {
					menu.setVisible(!menu.isVisible());
				}
				if (e.getKeyChar() == 'p') {
					pause = !pause;
				}
				if (e.getKeyChar() == 'r') {
					pause = false;
					menu.setVisible(false);
				}
				if (e.getKeyChar() == 'n') {
					world.createGrid();
				}
			}
		});
		
		/* Add mouse events for adding zombies/humans to the grid */
		frame.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!menu.isVisible()) {
					if(SwingUtilities.isRightMouseButton(e)) {
						world.spawnZombieOnClick(e.getX(), e.getY() - windowBarHeight);
					}
					else if(SwingUtilities.isLeftMouseButton(e)) {
						world.spawnHumanOnClick(e.getX(), e.getY() - windowBarHeight);
					}
				}
			}
		});
		
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!menu.isVisible()) {	
					if(SwingUtilities.isRightMouseButton(e)) {
						world.spawnZombieOnClick(e.getX(), e.getY() - windowBarHeight);
					}
					else if(SwingUtilities.isLeftMouseButton(e)) {
						world.spawnHumanOnClick(e.getX(), e.getY() - windowBarHeight);
					}
				}
			}
		});

		/* Add timer for updating world state
		 * Pause the simulation once menu is open
		 * Heart of the program!
		 */
		new javax.swing.Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!menu.isVisible() && !pause) {
					world.update();
				}
				frame.getContentPane().repaint();
			}
		}).start();
	}
}
