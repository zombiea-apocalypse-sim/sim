package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


class Tile {
    final static int NORTH = 0;
    final static int EAST = 1;
    final static int SOUTH = 2;
    final static int WEST = 3;

	int x;
	int y;
	Color color;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		color = Color.black;
	}
    public boolean frontIsClear(Tile[][] grid, int xCo,	int yCo,int dir) {
	int destinationX;
	int destinationY;
	if(dir == NORTH) {
	    destinationY = yCo - 1;
	} else if(dir == EAST) {
	    destinationX = xCo + 1;
	} else if(dir == SOUTH) {
	    destinationY = yCo + 1;
	} else if(dir == WEST) {
	    destinationX = xCo - 1;
	}
	Tile destTile = grid[destinationX][destinationY];
	if(destTile.color == black) {
    }
}