package zombies;

import java.awt.*;
import java.util.Random;

class Human extends Tile {
	int senseRange = 2;
	int clusterSize = 4;

	public Human(int x, int y) {
		super(x, y);
		this.color = Color.pink;
		this.type = HUMAN;
	}

	/*
	 * Update tile
	 */
	@Override
	public void update(World world, Tile[][] oldgrid) {
		int startX = this.x - 1;
		int startY = this.y - 1;
		int endX = this.x + 2;
		int endY = this.y + 2;

		int zombies = 0;
		int humans = 0;

		if (startX < 0) {
			startX = 0;
		}
		if (startY < 0) {
			startY = 0;
		}
		if (endX > world.width) {
			endX = world.width;
		}
		if (endY > world.height) {
			endY = world.height;
		}

		for (int iy = startY; iy < endY; iy++) {
			for (int ix = startX; ix < endX; ix++) {
				if (oldgrid[ix][iy].type == HUMAN) {
					humans++;
				}
				if (oldgrid[ix][iy].type == ZOMBIE) {
					zombies++;
				}
			}
		}

		if (zombies >= humans) {
			world.grid[this.x][this.y] = new Zombie(this.x, this.y);
		}
	}

	/*
	 * Randomly move when no other humans are near
	 */
	public void move(World world, Tile[][] tempgrid) {
		if (!humanNear(world, tempgrid)) {
			randomMove(world, tempgrid);
		}
	}

	/*
	 * Returns true if a position is on the grid
	 */
	public boolean validMove(World world, Tile[][] tempgrid, int x, int y) {
		if (x < 0 || x >= world.width) {
			return false;
		}

		if (y < 0 || y >= world.height) {
			return false;
		}

		if (world.grid[x][y].type != LAND) {
			return false;
		}

		if (tempgrid[x][y].type != LAND) {
			return false;
		}

		for (int i = 1; i <= senseRange; i++) {
			if (objectSpotted(world, tempgrid, x, y - i, ZOMBIE)) {
				return false;
			}
		}

		for (int i = 1; i <= senseRange; i++) {
			if (objectSpotted(world, tempgrid, x + i, y, ZOMBIE)) {
				return false;
			}
		}

		for (int i = 1; i <= senseRange; i++) {
			if (objectSpotted(world, tempgrid, x - i, y, ZOMBIE)) {
				return false;
			}
		}

		for (int i = 1; i <= senseRange; i++) {
			if (objectSpotted(world, tempgrid, x, y + i, ZOMBIE)) {
				return false;
			}
		}
		return true;
	}

	public boolean objectSpotted(World world, Tile[][] tempgrid, int xCo, int yCo, String object) {
		if (xCo <= 0 || xCo >= world.width || yCo <= 0 || yCo >= world.height) {
			return false;
		}

		Tile xyTile = tempgrid[xCo][yCo];
		String tileType = xyTile.type;
		if (tileType == object) {
			return true;
		}

		return false;
	}

	/*
	 * Does a random move
	 */
	private void randomMove(World world, Tile[][] tempgrid) {
		Random rand = new Random();
		int temp = rand.nextInt(4);
		boolean success = false;
		int dir;

		for (int i = 0; i < 4; i++) {
			dir = (temp + i) % 4;

			switch (dir) {
				case NORTH:
					if (validMove(world, tempgrid, x, y - 1)) {
						moveInDir(world, NORTH);
						success = true;
					}
					break;

				case EAST:
					if (validMove(world, tempgrid, x + 1, y)) {
						moveInDir(world, EAST);
						success = true;
					}
					break;

				case SOUTH:
					if (validMove(world, tempgrid, x, y + 1)) {
						moveInDir(world, SOUTH);
						success = true;
					}
					break;

				case WEST:
					if (validMove(world, tempgrid, x - 1, y)) {
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
	 * Returns true when human is near a cluster
	 */
	public boolean humanNear(World world, Tile[][] tempgrid) {
		int counter = 0;
		boolean returnBool = false;
		if (objectSpotted(world, tempgrid, x, y - 1, HUMAN)) {
			counter += 1;
		}
		if (objectSpotted(world, tempgrid, x + 1, y, HUMAN)) {
			counter += 1;
		}
		if (objectSpotted(world, tempgrid, x - 1, y, HUMAN)) {
			counter += 1;
		}
		if (objectSpotted(world, tempgrid, x, y + 1, HUMAN)) {
			counter += 1;
		}
		if (objectSpotted(world, tempgrid, x + 1, y + 1, HUMAN)) {
			counter += 1;
		}
		if (objectSpotted(world, tempgrid, x + 1, y - 1, HUMAN)) {
			counter += 1;
		}
		if (objectSpotted(world, tempgrid, x - 1, y + 1, HUMAN)) {
			counter += 1;
		}
		if (objectSpotted(world, tempgrid, x - 1, y - 1, HUMAN)) {
			counter += 1;
		}

		if (counter >= clusterSize) {
			returnBool = true;
			this.color = Color.red;
		} else {
			this.color = Color.pink;
		}
		return returnBool;
	}
}
