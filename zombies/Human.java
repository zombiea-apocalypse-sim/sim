package zombies;

import javax.swing.*;
import java.awt.*;

class Human extends Tile {
	int direction;
	
	public Human(int x, int y) {
		super(x, y);
		this.color = Color.pink;
		direction = 1;
	}
	
	public void update() {		
	}
}