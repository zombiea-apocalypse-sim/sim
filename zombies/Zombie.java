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
					if(validMove(world, tempgrid, x, y + 1)) {
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

// Oud misschien?

// 	private void walkZombie() {
// 
// 		Tile possibleTile = findNeighbouringTile(this);
// 		this.x = possibleTile.x;
// 		this.y = possibleTile.y;
// 
// 	}
// 
// 	private Tile findNeighbouringTile(Tile currentTile) {
// 
// 		Tile tileNorth = grid[x][y + 1];
// 		Tile tileSouth = grid[x][y - 1];
// 		Tile tileEast = grid[x - 1][y];
// 		Tile tileWest = grid[x + 1][y];
// 
// 		int temp = rand.nextInt(4);
// 		for(int i=0; i<4; i++) {
// 			int dir = (temp + i) % 4;
// 			switch (dir) {
// 				case NORTH:
// 					if ( tileNorth.color == Color.black || tileNorth.color == Color.pink
// 							&& inBounderies() )
// 						return tileNorth;
// 					break;
// 
// 				case SOUTH:
// 					if ( tileSouth.color == Color.black || tileSouth.color == Color.pink
// 							&& inBounderies())
// 						return tileSouth;
// 					break;
// 				case EAST:
// 					if ( tileEast.color == Color.black || tileEast.color == Color.pink
// 							&& inBounderies())
// 						return tileEast;
// 					break;
// 				case WEST:
// 					if ( tileWest.color == Color.black || tileWest.color == Color.pink
// 							&& inBounderies() )
// 						return tileWest;
// 					break;
// 			}
// 
// 		}
// 
// 		return currentTile;
// 	}
// 
// 	private boolean inBounderies() {
// 		if ()
// 	}

}
