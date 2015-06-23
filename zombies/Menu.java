package zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel {
	public JSlider slider;
	public JCheckBox behavior1;
	
	public Menu() {
		slider = new JSlider();
		behavior1 = new JCheckBox();
		
		slider.setLayout(null);
		slider.setMajorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setSize(200, 200);
		slider.setVisible(true);
		
		slider.setFocusable(false);
		behavior1.setFocusable(false);
		
		this.add(behavior1);
		this.add(slider);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, 400, 200);
	}
}