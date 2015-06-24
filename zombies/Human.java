package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


class Human extends Tile {
    int direction;
    int senseRange;

    public Human(int x, int y) {
	super(x, y);
	Random rand = new Random();
	this.color = Color.pink;
	this.type = HUMAN;
	direction = rand.nextInt(4);
    }

    public void update(World world, Tile[][] tempgrid) {
	int dir;
	boolean success = false;
	for(int i = 0; i < 4; i++) {
	    dir = this.direction;
	    switch(dir) {
	    case NORTH:
		if(validMove(world, tempgrid, x, y - 1)) {
		    success = move(world, tempgrid, NORTH);
		} else {
			this.direction = EAST;
		    }
		    break;
	
		case EAST:
		    if(validMove(world, tempgrid, x + 1, y)) {
			success = move(world, tempgrid, EAST);
		    } else {
			this.direction = SOUTH;
		    }
		    break;
	
		case SOUTH:
		    if(validMove(world, tempgrid, x, y + 1)) {
			success = move(world, tempgrid, SOUTH);
		    } else {
			this.direction = WEST;
		    }
		    break;
			
		case WEST:
		    if(validMove(world, tempgrid, x - 1, y)) {
			success = move(world, tempgrid, WEST);
		    } else {
			this.direction = NORTH;
		    }
		    break;
		}
		
		if(success) {
		    break;
		}
	    
	}
    }
   
    public boolean validMove(World world, Tile[][] tempgrid, int x, int y) {
	if(x < 0 || x >= world.width) {
	    return false;
	}
	if(y < 0 || y >= world.height) {
	    return false;
	}
	if(world.grid[x][y].type != LAND) {
	    return false;
	}
	if(tempgrid[x][y].type != LAND) {
	    return false;
	}
	// rules for seeing zombie
	if(zombieSpotted(world, tempgrid, x, y)) {
	    return false;
	}
	return true;
    }

    public boolean zombieSpotted(World world, Tile[][] tempgrid, int x, int y) {
	return false;
    }
}
