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
	
	public boolean move(World world, Tile[][] tempgrid, int dir) {
		int new_x = this.x;
		int new_y = this.y;
		
		switch(dir) {
			case NORTH:
				new_y--;
				if(new_y < 0) {
					return false;
				}
				break;
			
			case EAST:
				new_x++;
				if(new_x >= world.width) {
					return false;
				}
				break;
			
			case SOUTH:
				new_y++;
				if(new_y >= world.height) {
					return false;
				}
				break;
			
			case WEST:
				new_x--;
				if(new_x < 0) {
					return false;
				}
				break;
		}
		
		world.grid[new_x][new_y] = this;
		world.grid[this.x][this.y] = new Tile(this.x, this.y);
		this.x = new_x;
		this.y = new_y;
		
		return true;
	}

// Overbodig?
//     public boolean frontIsClear(Tile[][] grid, int xCo,	int yCo,int dir) {
// 		int destinationX;
// 		int destinationY;
// 		if(dir == NORTH) {
// 		    destinationY = yCo - 1;
// 		} else if(dir == EAST) {
// 		    destinationX = xCo + 1;
// 		} else if(dir == SOUTH) {
// 		    destinationY = yCo + 1;
// 		} else if(dir == WEST) {
// 		    destinationX = xCo - 1;
// 		}
// 		Tile destTile = grid[destinationX][destinationY];
// 		if(destTile.color == black) {
//     	}
// 	}	
}