package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class World extends JPanel {
	Random rand = new Random();
	int width = 120;
	int height = 80;
	int tile_size = 10;
	Tile[][] grid = new Tile[width][height];
	
	public World() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				switch(rand.nextInt(50) % 3) {
					case 0:
						grid[x][y] = new Human(x, y);
						break;
					case 1:
						grid[x][y] = new Zombie(x, y);
						break;
					default:
						grid[x][y] = new Tile(x, y);
						break;
				}
			}
		}	
	}
	
	public void update() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				switch(rand.nextInt(50) % 3) {
					case 0:
						grid[x][y] = new Human(x, y);
						break;
					case 1:
						grid[x][y] = new Zombie(x, y);
						break;
					default:
						grid[x][y] = new Tile(x, y);
						break;
				}
			}
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				g.setColor(grid[x][y].color);
				g.fillRect(x*tile_size, y*tile_size, (x*tile_size) + tile_size, (y*tile_size) + tile_size);
			}
		}	
	}
}