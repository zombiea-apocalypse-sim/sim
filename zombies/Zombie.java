package zombies;
import javax.swing.*;
import java.awt.*;

class Zombie extends Tile {
	int direction;
	
	public Zombie(int x, int y) {
		super(x, y);
		this.color = Color.green;
		direction = 1;
	}

	
	public void update(currentWorld, tempWorld) {
		walkZombie();
	}

	private void walkZombie() {

		Tile possibleTile = findNeighbouringTile(this);
		this.x = possibleTile.x;
		this.y = possibleTile.y;

	}

	private Tile findNeighbouringTile(Tile currentTile) {

		Tile tileNorth = grid[x][y + 1];
		Tile tileSouth = grid[x][y - 1];
		Tile tileEast = grid[x - 1][y];
		Tile tileWest = grid[x + 1][y];

		int temp = rand.nextInt(4);
		for(int i=0; i<4; i++) {
			int dir = (temp + i) % 4;
			switch (dir) {
				case NORTH:
					if ( tileNorth.color == Color.black || tileNorth.color == Color.pink
							&& inBounderies() )
						return tileNorth;
					break;

				case SOUTH:
					if ( tileSouth.color == Color.black || tileSouth.color == Color.pink
							&& inBounderies())
						return tileSouth;
					break;
				case EAST:
					if ( tileEast.color == Color.black || tileEast.color == Color.pink
							&& inBounderies())
						return tileEast;
					break;
				case WEST:
					if ( tileWest.color == Color.black || tileWest.color == Color.pink
							&& inBounderies() )
						return tileWest;
					break;
			}

		}

		return currentTile;
	}

	private boolean inBounderies() {
		if ()
	}

}
