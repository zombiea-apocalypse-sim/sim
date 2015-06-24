package zombies;

import javax.swing.*;
import java.awt.*;


class Tile {
	final static int NORTH = 0;
	final static int EAST = 1;
	final static int SOUTH = 2;
	final static int WEST = 3;
	
	final static String LAND = "land";
	final static String ZOMBIE = "zombie";
	final static String HUMAN = "human";
	
	int x;
	int y;
	Color color;
	String type;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.color = Color.yellow;
		this.type = LAND;
	}
	
	public void update(World world, Tile[][] tempgrid) {}
	
	public void move(World world, int dir) {
		int new_x = this.x;
		int new_y = this.y;
		
		switch(dir) {
			case NORTH:
				new_y--;
				break;
			
			case EAST:
				new_x++;
				break;
			
			case SOUTH:
				new_y++;
				break;
			
			case WEST:
				new_x--;
				break;
		}
		
		world.grid[new_x][new_y] = this;
		world.grid[this.x][this.y] = new Tile(this.x, this.y);
		this.x = new_x;
		this.y = new_y;
	}
	
	/*
	 * Calculate manhattan distance from current to end position
	 */
	public int manhattanDistance(int endX, int endY) {
		return Math.abs(this.x - endX) + Math.abs(this.y - endY);
	}	
}