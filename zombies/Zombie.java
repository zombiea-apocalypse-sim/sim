package zombies;

import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

/*
 * Zombie class.
 */
class Zombie extends Tile {
	public Zombie(int x, int y) {
		super(x, y);
		this.color = Color.green;
		this.type = ZOMBIE;
	}

	/*
	 * Update tile
	 * When humans are outnumbered new tile is
	 * zombie tile and human tile disappears.
	 * When zombies are outnumbered new tile is
	 * human tile and zombie disappears.
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

		if (humans >= zombies) {
			world.grid[this.x][this.y] = new Tile(this.x, this.y);
		}
	}

	/*
	 * Moves zombie
	 * When a human is close move closer to the human otherwise
	 * move randomly.
	 */
	@Override
	public void move(World world, Tile[][] tempgrid) {
		Tile clossestHuman = findClosestHuman(world, tempgrid);
		if (clossestHuman == null) {
			randomMove(world, tempgrid);
		} else {
			searchHumanMove(world, clossestHuman);
		}
	}

	/*
	 * Move to human
	 * Move in an direction closer to the closest human
	 */
	private void searchHumanMove(World world, Tile clossestHumanTile) {

		if (this.y < clossestHumanTile.y) {
			moveInDir(world, NORTH);
		} else if (this.y > clossestHumanTile.y) {
			moveInDir(world, SOUTH);
		} else if (this.x < clossestHumanTile.x) {
			moveInDir(world, WEST);
		} else {
			moveInDir(world, EAST);
		}
	}

	/*
	 * Randomly move
	 * Do a move in a random direction
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
	 * Randomly move
	 * Do a move in a random direction
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

		return true;
	}

	/*
	 * Return close human tiles
	 * Returns an ArrayList of human tiles in
	 * a specific range from the current zombie tile
	 */
	public ArrayList<Tile> getNeighbourHumans(World world, Tile[][] tempgrid, int range) {
		ArrayList<Tile> humanTiles = new ArrayList<>();

		int startX = this.x - range;
		int startY = this.y - range;
		int endX = this.x + range;
		int endY = this.y + range;

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

		for (int y = startY; y < endY; y++) {
			for (int x = startX; x < endX; x++) {
				if (tempgrid[x][y].type == HUMAN) {
					humanTiles.add(tempgrid[x][y]);
				}
			}
		}

		return humanTiles;
	}

	/*
	 * Find closest human
	 * Returns the tile of the closest human. If
	 * multiple tiles are closest pick a random tile
	 * from the closest tiles.
	 */
	public Tile findClosestHuman(World world, Tile[][] tempgrid) {
		ArrayList<Tile> humanTiles = getNeighbourHumans(world, tempgrid, 3);

		int smallestDistance = 99999;

		for (Tile tile : humanTiles) {
			int distance = manhattanDistance(tile.x, tile.y);
			if (distance < smallestDistance)
				smallestDistance = distance;

		}

		ArrayList<Tile> clossestList = new ArrayList();

		for (Tile tile : humanTiles) {
			int distance = manhattanDistance(tile.x, tile.y);
			if (smallestDistance == distance)
				clossestList.add(tile);
		}

		Random rand = new Random();

		if (clossestList == null) {
			return clossestList.get(rand.nextInt(clossestList.size()));
		}
		return null;
	}
}
