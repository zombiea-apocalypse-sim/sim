package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Human extends Tile {
	int direction;
	int senseRange = 2;

	public Human(int x, int y) {
	super(x, y);
	Random rand = new Random();
	this.color = Color.pink;
	this.type = HUMAN;
	direction = rand.nextInt(4);
	}

	public void update(World world, Tile[][] tempgrid) {
	  Random rand = new Random();
    this.direction = rand.nextInt(4);
		int dir = this.direction;
		boolean success = false;
		
		for(int i = 0; i < 4; i++) {
			dir = this.direction;
			switch(dir) {
				case NORTH:
					if(validMove(world, tempgrid, x, y - 1)) {
						move(world, tempgrid, NORTH);
						success = true;
					}
					else {
						this.direction = EAST;
					}
					break;
	
				case EAST:
					if(validMove(world, tempgrid, x + 1, y)) {
						move(world, tempgrid, EAST);
						success = true;
					}
					else {
						this.direction = SOUTH;
					}
					break;
	
				case SOUTH:
					if(validMove(world, tempgrid, x, y + 1)) {
						move(world, tempgrid, SOUTH);
						success = true;
					}
					else {
						this.direction = WEST;
					}
					break;
			
				case WEST:
					if(validMove(world, tempgrid, x - 1, y)) {
						move(world, tempgrid, WEST);
						success = true;
					}
					else {
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

	  if(this.direction == NORTH && y - senseRange >= 0) {
	    for(int i = 1; i <= senseRange; i ++) {
		    if(zombieSpotted(world, tempgrid, x, y - i)) {
		      return false;
		    }
		
	    }	    
	  }

 	  if(this.direction == EAST && x + senseRange < world.width) {
	    for(int i = 1; i <= senseRange; i ++) {
		    if(zombieSpotted(world, tempgrid, x + i, y)) {
		        return false;
		    }		
	    }
	  }

	  if(this.direction == WEST && x - senseRange >= 0) {
	    for(int i = 1; i <= senseRange; i ++) {
		    if(zombieSpotted(world, tempgrid, x - i, y)) {
		      return false;
		    }
	    }
	  }
 
	  if(this.direction == SOUTH && y + senseRange < world.height) {
      for(int i = 1; i <= senseRange; i ++) {
      		if(zombieSpotted(world, tempgrid, x, y + i)) {
		      return false;
      		}		
	    }
    }
    return true;
}
	public boolean zombieSpotted(World world, Tile[][] tempgrid, int x, int y) {
		Tile xyTile = tempgrid[x][y];
		String tileType = xyTile.type;
		if(tileType == ZOMBIE){
			return true;
		}
		return false;
	}
}
