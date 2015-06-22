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
	
	public void update() {		
	}
}