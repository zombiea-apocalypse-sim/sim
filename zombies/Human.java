package zombies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


class Human extends Tile {
	int direction;
	
	public Human(int x, int y) {
		super(x, y);
	    	Random rand = new Random();
		this.color = Color.pink;
		this.direction = rand.nextInt(4);
		System.out.println(this.direction);
	}
	
	public void update(World world) {
	    if(zombieSpotted()) {
		run();
	    } else {
		move(world);
	    }
	}


    public void run() {

    }

    public boolean zombieSpotted() {
	boolean zombieSpotted = false;
	return zombieSpotted;
    }



    public void move(World world) {
	Point destination = moveTo(world);
	
	this.x = destination.x;
	this.y = destination.y;
    }

    public Point moveTo(World world) {
	int fromX = this.x;
	int fromY = this.y;
	int oldDirection = this.direction;
	int toX;
	int toY;
	int newDirection;
	Point newDest = new Point();

	if(frontIsClear()) {
	    world.grid[][]
	} else {

	}
	newDest.x = toX;
	newDest.y = toY;
	newDest.z = newDirection;
	return newDest;

}