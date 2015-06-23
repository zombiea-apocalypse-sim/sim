package zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel {
	public JSlider zombieCount;
	public JSlider humanCount;
	public JCheckBox runBehavior;
	public JCheckBox killBehavior;
	
	public Menu() {
		zombieCount = new JSlider();
		humanCount = new JSlider();
		runBehavior = new JCheckBox("Run");
		killBehavior = new JCheckBox("Kill");
		
		zombieCount.setLayout(null);
		zombieCount.setMajorTickSpacing(5);
		zombieCount.setPaintTicks(true);
		zombieCount.setSize(200, 200);
		zombieCount.setVisible(true);
		
		humanCount.setLayout(null);
		humanCount.setMajorTickSpacing(5);
		humanCount.setPaintTicks(true);
		humanCount.setSize(200, 200);
		humanCount.setVisible(true);
		
		zombieCount.setFocusable(false);
		humanCount.setFocusable(false);
		runBehavior.setFocusable(false);
		killBehavior.setFocusable(false);
		
		this.add(zombieCount);
		this.add(humanCount);
		this.add(runBehavior);
		this.add(killBehavior);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, 800, 400);
	}
}