package zombies;

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

	/*
	 * Inner tuple class used for counting zombies and humans
	 */
	public class Tuple {
		public final int humans;
		public final int zombies;

		private Tuple(int humans, int zombies) {
			this.humans = humans;
			this.zombies = zombies;
		}
	}

	/*
	 * Tile constructor
	 */
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.color = Color.yellow;
		this.type = LAND;
	}

	/* Doesn't do anything for an empty tiles */
	public void update(World world, Tile[][] oldgrid) {
	}

	public void move(World world, Tile[][] oldgrid) {
	}

	/*
	 * Move the tile in a direction
	 */
	public void moveInDir(World world, int dir) {
		int new_x = this.x;
		int new_y = this.y;

		switch (dir) {
			case NORTH:
				new_y--;
				break;

			case EAST:
				new_x++;
				break;

			case SOUTH:
				new_y++;
				break;

			case WEST:
				new_x--;
				break;
		}

		world.grid[new_x][new_y] = this;
		world.grid[this.x][this.y] = new Tile(this.x, this.y);
		this.x = new_x;
		this.y = new_y;
	}

	/*
	 * Count the zombies and humans in the Moore neighborhood
	 */
	public Tuple countMooreNeighborhood(World world, Tile[][] oldgrid) {
		int startX = this.x - 1;
		int startY = this.y - 1;
		int endX = this.x + 1;
		int endY = this.y + 1;

		int zombies = 0;
		int humans = 0;

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
				if (oldgrid[ix][iy].type == HUMAN) {
					humans++;
				}
				if (oldgrid[ix][iy].type == ZOMBIE) {
					zombies++;
				}
			}
		}

		return new Tuple(humans, zombies);
	}

	/*
	 * Calculate manhattan distance from current to end position
	 */
	public int manhattanDistance(int endX, int endY) {
		return Math.abs(this.x - endX) + Math.abs(this.y - endY);
	}
}