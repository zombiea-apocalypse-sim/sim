package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Tile {
	int x;
	int y;
	Color color;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		color = Color.black;
	}
}