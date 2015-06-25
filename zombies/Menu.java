package zombies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Menu extends JPanel {
	public JSlider zombieCount;
	public JSlider humanCount;
	public JCheckBox runBehavior;
	public JCheckBox killBehavior;

	public JButton newBoard;

	public Menu(World world) {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		this.add(Box.createRigidArea(new Dimension(0, 15)));

		JLabel zombieCountLabel = new JLabel("Amount of zombies: " + 10);
		this.add(zombieCountLabel);

		zombieCount = new JSlider(5, 100);
		zombieCount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent changeEvent) {
				zombieCountLabel.setText("Amount of zombies: "
						+ zombieCount.getValue());
			}
		});
		zombieCount.setLayout(null);
		zombieCount.setMajorTickSpacing(5);
		zombieCount.setPaintTicks(true);
		zombieCount.setSize(200, 200);
		zombieCount.setVisible(true);
		zombieCount.setFocusable(false);
		zombieCount.setMaximumSize(new Dimension(300, 30));
		zombieCount.setAlignmentX(-100);
		this.add(zombieCount);

		this.add(Box.createRigidArea(new Dimension(0, 15)));

		JLabel humanCountLabel = new JLabel("Amount of humans: ");
		this.add(humanCountLabel);

		humanCount = new JSlider(50, 1000);
		humanCount.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent changeEvent) {
				humanCountLabel.setText("Amount of humans: "
						+ humanCount.getValue());
			}
		});
		zombieCount.setName("HUMANSLIDER");
		humanCount.setLayout(null);
		humanCount.setMajorTickSpacing(50);
		humanCount.setPaintTicks(true);
		humanCount.setSize(200, 200);
		humanCount.setVisible(true);
		humanCount.setFocusable(false);
		humanCount.setMaximumSize(new Dimension(300, 30));
		humanCount.setAlignmentX(-100);
		this.add(humanCount);

		this.add(Box.createRigidArea(new Dimension(0, 15)));

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
		g.setColor(new Color(238, 238, 238));
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}