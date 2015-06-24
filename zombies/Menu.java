package zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel {
	public JSlider zombieCount;
	public JSlider humanCount;
	public JCheckBox runBehavior;
	public JCheckBox killBehavior;
	
	public JButton newBoard;
	
	public Menu(World world) {
		zombieCount = new JSlider(5, 100);
		zombieCount.setLayout(null);
		zombieCount.setMajorTickSpacing(5);
		zombieCount.setPaintTicks(true);
		zombieCount.setSize(200, 200);
		zombieCount.setVisible(true);
		zombieCount.setFocusable(false);
		this.add(zombieCount);
		
		humanCount = new JSlider(50, 1000);
		humanCount.setLayout(null);
		humanCount.setMajorTickSpacing(50);
		humanCount.setPaintTicks(true);
		humanCount.setSize(200, 200);
		humanCount.setVisible(true);
		humanCount.setFocusable(false);
		this.add(humanCount);
		
		runBehavior = new JCheckBox("Run");
		killBehavior = new JCheckBox("Kill");
		
		runBehavior.setFocusable(false);
		killBehavior.setFocusable(false);
		
		this.add(runBehavior);
		this.add(killBehavior);
		
		newBoard = new JButton("Recreate Board");
		newBoard.setLayout(null);
		newBoard.setVisible(true);
		newBoard.setFocusable(false);
		newBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				world.setAmountZombies(zombieCount.getValue());
				world.setAmountHumans(humanCount.getValue());
				world.createGrid();
			}
		});
		this.add(newBoard);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}