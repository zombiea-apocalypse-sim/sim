package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Zombie extends Tile {
	
	public Zombie(int x, int y) {
		super(x, y);
		this.color = Color.green;
		this.type = ZOMBIE;
	}
	
	@Override
	public void update(World world, Tile[][] tempgrid) {
		Random rand = new Random();
		int temp = rand.nextInt(4);
		boolean success = false;
		int dir;
		
		for(int i = 0; i < 4; i++) {
			dir = (temp + i) % 4;
			switch(dir) {
				case NORTH:
					if(validMove(world, tempgrid, x, y - 1)) {
						success = move(world, tempgrid, NORTH);
					}
					break;
				
				case EAST:
					if(validMove(world, tempgrid, x + 1, y)) {
						success = move(world, tempgrid, EAST);
					}
					break;
				
				case SOUTH:
					if(validMove(world, tempgrid, x, y - 1)) {
						success = move(world, tempgrid, SOUTH);
					}
					break;
				
				case WEST:
					if(validMove(world, tempgrid, x - 1, y)) {
						success = move(world, tempgrid, WEST);
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
		return true;
	}
}