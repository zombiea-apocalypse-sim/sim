package zombies;

import java.awt.*;
import java.util.Random;

class Human extends Tile {
	int senseRange = 1;
	int clusterSize = 4;

	public Human(int x, int y) {
		super(x, y);
		this.color = Color.pink;
		this.type = HUMAN;
	}

	/*
	 * Update Human (Game of life style, with our own rules)
	 */
	@Override
	public void update(World world, Tile[][] oldgrid) {
		Tuple count = countMooreNeighborhood(world, oldgrid);
		
		/* Becomes a zombie when there are more or equal amount of zombies
		 * as there are humans
		 */
		if (count.zombies >= count.humans) {
			world.grid[this.x][this.y] = new Zombie(this.x, this.y);
		}
	}

	/*
	 * Randomly move when no other humans are near
	 */
	@Override
	public void move(World world, Tile[][] oldgrid) {
		if(!humanNear(world, oldgrid)) {
			randomMove(world, oldgrid);
		}
	}

	/*
	 * Returns true if a moving would not result in being on a position 
	 * outside the grid, or if it would result in being closer to a zombie
	 */
	private boolean validMove(World world, Tile[][] oldgrid, int x, int y) {
		if (x < 0 || x >= world.width) {
			return false;
		}

		if (y < 0 || y >= world.height) {
			return false;
		}

		if (world.grid[x][y].type != LAND) {
			return false;
		}

		if (oldgrid[x][y].type != LAND) {
			return false;
		}
		
		/* Return false when there is a zombie in range */
		return !zombieInRange(world, oldgrid);
	}
	
	private boolean zombieInRange(World world, Tile[][] oldgrid) {
		int startX = this.x - senseRange;
		int startY = this.y - senseRange;
		int endX = this.x + senseRange;
		int endY = this.y + senseRange;

		if (startX < 0) {
			startX = 0;
		}
		if (startY < 0) {
			startY = 0;
		}
		if (endX >= world.width) {
			endX = world.width - 1;
		}
		if (endY >= world.height) {
			endY = world.height - 1;
		}

		for (int iy = startY; iy <= endY; iy++) {
			for (int ix = startX; ix <= endX; ix++) {
				if (oldgrid[ix][iy].type == ZOMBIE) {
					return true;
				}
			}
		}
		
		return false;
	}

	/*
	 * Does a random move
	 */
	private void randomMove(World world, Tile[][] oldgrid) {
		Random rand = new Random();
		int temp = rand.nextInt(4);
		boolean success = false;
		int dir;

		for (int i = 0; i < 4; i++) {
			dir = (temp + i) % 4;

			switch (dir) {
				case NORTH:
					if (validMove(world, oldgrid, x, y - 1)) {
						moveInDir(world, NORTH);
						success = true;
					}
					break;

				case EAST:
					if (validMove(world, oldgrid, x + 1, y)) {
						moveInDir(world, EAST);
						success = true;
					}
					break;

				case SOUTH:
					if (validMove(world, oldgrid, x, y + 1)) {
						moveInDir(world, SOUTH);
						success = true;
					}
					break;

				case WEST:
					if (validMove(world, oldgrid, x - 1, y)) {
						moveInDir(world, WEST);
						success = true;
					}
					break;
			}

			if (success) {
				break;
			}
		}
	}

	/*
	 * Returns true when human is near another human
	 */
	private boolean humanNear(World world, Tile[][] oldgrid) {
		Tuple count = countMooreNeighborhood(world, oldgrid);
		
		if(count.humans >= clusterSize) {
			this.color = Color.red;
			return true;
		}
		this.color = Color.pink;
		return false;
	}
}
