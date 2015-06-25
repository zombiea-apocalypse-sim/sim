package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/*
 * World class.
 * This is where the world update every time tick.
 * We also create the world here.
 */
public class World extends JPanel {
	Random rand = new Random();
	int width;
	int height;
	int tileSize;
	int amountHumans;
	int amountZombies;
	boolean flip = false;
	Tile[][] grid;

	public World(int width, int height, int amountHumans, int amountZombies) {
		this.width = width;
		this.height = height;
		this.tileSize = 10;
		this.amountHumans = amountHumans;
		this.amountZombies = amountZombies;
		this.grid = new Tile[width][height];

		createGrid();
	}

	/*
	 * Update method
	 * We create an extra grid that's the same as our current. This way we
	 * can make changes on the grid more fair.
	 * Upper-left corner still has an advantage over the bottom-right corner, but
	 * we cancel this out by flipping our iteration.
	 */
	public void update() {
		Tile[][] temp = new Tile[width][height];
		
		// Update humans->zombies and zombies->death
		for (int i = 0; i < grid.length; i++) {
			System.arraycopy(grid[i], 0, temp[i], 0, grid[i].length);
		}
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				temp[x][y].update(this, temp);
			}
		}

		// Move zombies and humans
		for (int i = 0; i < grid.length; i++) {
			System.arraycopy(grid[i], 0, temp[i], 0, grid[i].length);
		}
		if (flip) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					temp[x][y].move(this, temp);
				}
			}
		} else {
			for (int y = height - 1; y > 0; y--) {
				for (int x = width - 1; x > 0; x--) {
					temp[x][y].move(this, temp);
				}
			}
		}
		flip = !flip;
	}

	/*
	 * Create Grid
	 * Creates a new grid, we start without any humans or zombies
	 */
	public void createGrid() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				grid[x][y] = new Tile(x, y);
			}
		}

		spawnHumans();
		spawnZombies();
	}

	/*
	 * Spawn Humans
	 * Spawn humans in the world, this humans can later become zombies
	 */
	private void spawnHumans() {
		int x;
		int y;

		for (int i = 0; i < amountHumans; i++) {
			x = rand.nextInt(width);
			y = rand.nextInt(height);

			if (grid[x][y].type == Tile.LAND) {
				grid[x][y] = new Human(x, y);
			} else {
				i--; // Try again
			}
		}
	}

	/*
	 * Spawn zombies in the world
	 */
	private void spawnZombies() {
		int x;
		int y;
		for (int i = 0; i < amountZombies; i++) {
			x = rand.nextInt(width);
			y = rand.nextInt(height);

			if (grid[x][y].type == Tile.LAND) {
				grid[x][y] = new Zombie(x, y);
			} else {
				i--; // Try again
			}
		}
	}

	/*
	 * Spawn zombie by clicking on a tile
	 */
	public void spawnZombieOnClick(int pixelX, int pixelY) {
		int x = pixelX / tileSize;
		int y = pixelY / tileSize;
		grid[x][y] = new Zombie(x, y);
	}
	
	public void spawnHumanOnClick(int pixelX, int pixelY) {
		int x = pixelX / tileSize;
		int y = pixelY / tileSize;
		grid[x][y] = new Human(x, y);
	}

	/*
	 * Paint Component
	 * This function renders the grid
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.yellow);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(grid[x][y].type != Tile.LAND) {
					g.setColor(grid[x][y].color);
					g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
				}
			}
		}
	}

	/*
	 * Getters and Setters
	 */
	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public void setAmountHumans(int amountHumans) {
		this.amountHumans = amountHumans;
	}

	public void setAmountZombies(int amountZombies) {
		this.amountZombies = amountZombies;
	}
}