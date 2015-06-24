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
}