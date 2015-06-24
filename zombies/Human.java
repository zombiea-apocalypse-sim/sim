package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Human extends Tile {
	int senseRange = 2;
  int clusterSize = 3;
	public Human(int x, int y) {
	  super(x, y);
	  Random rand = new Random();
	  this.color = Color.pink;
	  this.type = HUMAN;
	}

	public void update(World world, Tile[][] tempgrid) {
    if(!humanNear(world, tempgrid, x, y)) {
      randomMove(world, tempgrid, x, y);
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

	  for(int i = 1; i <= senseRange; i ++) {
      if(objectSpotted(world, tempgrid, x, y - i, ZOMBIE)) {
		    return false;
		  }
	  }

	  for(int i = 1; i <= senseRange; i ++) {
		  if(objectSpotted(world, tempgrid, x + i, y, ZOMBIE)) {
        return false;
		  }		
	  }

	  for(int i = 1; i <= senseRange; i ++) {
		  if(objectSpotted(world, tempgrid, x - i, y, ZOMBIE)) {
		    return false;
		  }
	  }
 
    for(int i = 1; i <= senseRange; i ++) {
      if(objectSpotted(world, tempgrid, x, y + i, ZOMBIE)) {
		    return false;
      }		
	  }
    return true;
  }
	public boolean objectSpotted(World world, 
                              Tile[][] tempgrid, 
                              int xCo, 
                              int yCo,
                              String object) {
    if(xCo <= 0 || xCo >= world.width || yCo <= 0 || yCo >= world.height) {
      return false;
    }

		Tile xyTile = tempgrid[xCo][yCo];
		String tileType = xyTile.type;
		if(tileType == object){
			return true;
		}

		return false;
	}

  public void randomMove(World world, Tile[][] tempgrid, int x, int y) {
	  Random rand = new Random();
    int dir = rand.nextInt(4);
		boolean success = false;
		for(int i = 0; i < 4; i++) {
			switch(dir) {
				case NORTH:
					if(validMove(world, tempgrid, x, y - 1)) {
						move(world, NORTH);
						success = true;
					}
					else {
						dir = EAST;
					}
					break;
	
				case EAST:
					if(validMove(world, tempgrid, x + 1, y)) {
						move(world, EAST);
						success = true;
					}
					else {
					  dir = SOUTH;
					}
					break;
	
				case SOUTH:
					if(validMove(world, tempgrid, x, y + 1)) {
						move(world, SOUTH);
						success = true;
					}
					else {
						dir = WEST;
					}
					break;
			
				case WEST:
					if(validMove(world, tempgrid, x - 1, y)) {
						move(world, WEST);
						success = true;
					}
					else {
						dir = NORTH;
					}
					break;
			}
			if(success) {
				break;
			}
		}
	}

  public boolean humanNear(World world, Tile[][] tempgrid, int x, int y) {
      int counter = 0;
      boolean returnBool = false;
      if(objectSpotted(world, tempgrid, x, y - 1, HUMAN)) {
        counter += 1;
		  }
		  if(objectSpotted(world, tempgrid, x + 1, y, HUMAN)) {
        counter += 1;
		  }
		  if(objectSpotted(world, tempgrid, x - 1, y, HUMAN)) {
        counter += 1;
		  }
      if(objectSpotted(world, tempgrid, x, y + 1, HUMAN)) {
        counter += 1;
      }

      if(objectSpotted(world, tempgrid, x + 1, y + 1, HUMAN)) {
        counter += 1;
      }
      if(objectSpotted(world, tempgrid, x + 1, y - 1, HUMAN)) {
        counter += 1;
      }
      if(objectSpotted(world, tempgrid, x - 1 , y + 1, HUMAN)) {
        counter += 1;
      }
      if(objectSpotted(world, tempgrid, x - 1, y - 1, HUMAN)) {
        counter += 1;
      }

      if(counter >= clusterSize) {
        returnBool = true;
        this.color = Color.red;
      } else {
        this.color = Color.pink;
      }
      return returnBool;
  }
}
