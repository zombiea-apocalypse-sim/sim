package zombies;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Zombie extends Tile {
	private boolean spawnZombie = false;
	
	public Zombie(int x, int y) {
		super(x, y);
		this.color = Color.green;
		this.type = ZOMBIE;

	}
	
	@Override
	public void update(World world, Tile[][] tempgrid) {
		int startX = this.x - 1;
		int startY = this.y - 1;
		int endX = this.x + 1;
		int endY = this.y + 1;
		
		int zombies = 0;
		int humans = 0;
		
		if(startX < 0) {
			startX = 0;
		}
		if(startY < 0) {
			startY = 0;
		}
		if(endX > world.width) {
			endX = world.width;
		}
		if(endY > world.height) {
			endY = world.height;
		}
		
		for(int y = startY; y < endY; y++) {
			for(int x = startX; x < endX; x++) {
				if(tempgrid[x][y].type == HUMAN) {
					humans++;
				}
				else if (tempgrid[x][y].type == ZOMBIE) {
					zombies++;
				}
			}
		}
		
		if(humans >= zombies) {
			world.grid[this.x][this.y] = new Tile(this.x, this.y);
		}
	}

	@Override
	public void update1(World world, Tile[][] tempgrid) {
		Random rand = new Random();
		int temp = rand.nextInt(4);
		boolean success = false;
		int dir;
		
		for(int i = 0; i < 4; i++) {
			dir = (temp + i) % 4;
			switch(dir) {
				case NORTH:
					if(validMove(world, tempgrid, x, y - 1)) {
						if(spawnZombie) {
							world.infectHuman(x, y - 1);
							spawnZombie = false;
						}
						else {
							move(world, tempgrid, NORTH);
						}
						success = true;
					}
					break;
				
				case EAST:
					if(validMove(world, tempgrid, x + 1, y)) {
						if(spawnZombie) {
							world.infectHuman(x + 1, y);
							spawnZombie = false;
						}
						else {
							move(world, tempgrid, EAST);
						}
						success = true;
					}
					break;
				
				case SOUTH:
					if(validMove(world, tempgrid, x, y + 1)) {
						if(spawnZombie) {
							world.infectHuman(x, y + 1);
							spawnZombie = false;
						}
						else {
							move(world, tempgrid, SOUTH);
						}
						success = true;
					}
					break;
				
				case WEST:
					if(validMove(world, tempgrid, x - 1, y)) {
						if(spawnZombie) {
							world.infectHuman(x - 1, y);
							spawnZombie = false;
						}
						else {
							move(world, tempgrid, WEST);
						}
						success = true;
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
		if(world.grid[x][y].type == ZOMBIE) {
			return false;
		}
		if(tempgrid[x][y].type == ZOMBIE) {
			return false;
		}
		
		//Spawn new Zombie
		if(world.grid[x][y].type == HUMAN) {
			spawnZombie = true;
		}
		
		return true;
	}
}
