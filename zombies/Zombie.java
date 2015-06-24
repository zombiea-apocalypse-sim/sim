package zombies;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

class Zombie extends Tile {
	private boolean spawnZombie = false;
	
	public Zombie(int x, int y) {
		super(x, y);
		this.color = Color.green;
		this.type = ZOMBIE;

	}

	@Override
	public void update(World world, Tile[][] tempgrid) {
		
		Tile clossestHuman = findClosestHuman(world, tempgrid);
		if (clossestHuman == null) {
			randomMove(world, tempgrid);
		} else {
			searchHumanMove(world, clossestHuman);
		}
	}

	private void searchHumanMove(World world, Tile clossestHumanTile) {

		if (this.y < clossestHumanTile.y) {
			move(world, NORTH);
		} else if (this.y > clossestHumanTile.y) {
			move(world, SOUTH);
		} else if (this.x < clossestHumanTile.x) {
			move(world, WEST);
		} else {
			move(world, EAST);
		}
	}

	private void randomMove(World world, Tile[][] tempgrid) {
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
							move(world, NORTH);
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
							move(world, EAST);
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
							move(world, SOUTH);
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
							move(world, WEST);
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

	public ArrayList<Tile> getNeighbourHumans(World world, Tile[][] tempgrid, int distance) {

		ArrayList<Tile> humanTiles = new ArrayList<>();

		int endx = this.x + distance;
		int endy = this.y + distance;

		int startx = this.x - distance;
		int starty = this.y - distance;

		if (endx > world.width) {
			endx = world.width;
		}

		if (endy > world.height) {
			endy = world.height;
		}

		if (startx < 0) {
			startx = 0;
		}	

		if (starty < 0) {
			endy = 0;
		}		

		for (int i=startx; i<endx; i++) {
			for (int j=starty; j<endy; j++) {

				if (tempgrid[i][j].type == HUMAN)
					humanTiles.add(tempgrid[i][j]);
			}
		}

		return humanTiles;
	}

	public Tile findClosestHuman(World world, Tile[][] tempgrid) {
		ArrayList<Tile> humanTiles = getNeighbourHumans(world, tempgrid, 3);

		Tile clossestTile = null;
		int smallestDistance = 9999;

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

		if(clossestList == null) {
			return clossestList.get(rand.nextInt(clossestList.size()));
		}
		return null;
	}


}
