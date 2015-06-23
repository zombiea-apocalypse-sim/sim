package zombies;

import javax.swing.*;
import java.awt.*;

class Human extends Tile {
	
	public Human(int x, int y) {
		super(x, y);
		this.color = Color.pink;
		this.type = HUMAN;
	}
	
	public void update() {		
	}
}