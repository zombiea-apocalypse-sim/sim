package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Human extends Tile {
	
	public Human(int x, int y) {
		super(x, y);
		Random rand = new Random();
		this.color = Color.pink;
		this.type = HUMAN;
	}
	
	public void update(World world, Tile[][] tempgrid) {
		
	}
	
// 	public void scanZombies(World world, Tile[][] tempgrid) {
// 		int range = 3;
// 		int startX = this.x - range;
// 		int startY = this.y - range;
// 		int endX = this.x + range; 
// 		int endY = this.y + range;
// 		
// 		if(startX < 0) {
// 			startX = 0;
// 		}
// 		if(startY < 0) {
// 			startY = 0;
// 		}
// 		if(endX > world.width) {
// 			endX = world.width;
// 		}
// 		if(endY > world.height) {
// 			endY = world.height;
// 		}
// 		
// 		for(int y = startY; y < endY; y++) {
// 			for(int x = startX; x < endX; x++) {
// 				if(tempgrid[x][y].type == ZOMBIE && this.manhattanDistance(x, y) < range) {
// 					
// 				}
// 			}
// 		}
// 	}
}