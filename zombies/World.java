package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class World extends JPanel {
	Random rand = new Random();
	int width;
	int height;
	int tileSize;
	int amountHumans;
	int amountZombies;
	Tile[][] grid;
	
	public World(int width, int height, int amountHumans, int amountZombies) {
		this.width = width;
		this.height = height;
		this.tileSize = 10;
		this.amountHumans = amountHumans;
		this.amountZombies = amountZombies;
		this.grid = new Tile[width][height];
		
		createGrid();
		
// 		for(int y = 0; y < height; y++) {
// 			for(int x = 0; x < width; x++) {
// 				switch(rand.nextInt(50) % 3) {
// 					case 0:
// 						grid[x][y] = new Human(x, y);
// 						break;
// 					case 1:
// 						grid[x][y] = new Zombie(x, y);
// 						break;
// 					default:
// 						grid[x][y] = new Tile(x, y);
// 						break;
// 				}
// 			}
// 		}
	}
	
	public void update() {
		Tile[][] temp = new Tile[width][height];
		for (int i = 0; i < grid.length; i++) {
			System.arraycopy(grid[i], 0, temp[i], 0, grid[i].length);
		}
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				temp[x][y].update(this, temp);
			}
		}
	}
	
	private void createGrid() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				grid[x][y] = new Tile(x, y);
			}
		}
		
		spawnHumans();
		infectHumans();
	}
	
	private void spawnHumans() {
		int x;
		int y;
		for(int i = 0; i < amountHumans; i++) {
			x = rand.nextInt(width);
			y = rand.nextInt(height);
			
			if(grid[x][y].type == Tile.LAND) {
				grid[x][y] = new Human(x, y);
			}
			else {
				i--; // Try again
			}
		}	
	}
	
	private void infectHumans() {
		int x;
		int y;
		for(int i = 0; i < amountZombies; i++) {
			x = rand.nextInt(width);
			y = rand.nextInt(height);
			
			if(grid[x][y].type == Tile.HUMAN) {
				grid[x][y] = new Zombie(x, y);
			}
			else {
				i--; // Try again
			}
		}	
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				g.setColor(grid[x][y].color);
				g.fillRect(x*tileSize, y*tileSize, (x*tileSize) + tileSize, (y*tileSize) + tileSize);
			}
		}	
	}
	
	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
}