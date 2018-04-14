package Interface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AboutFrame extends JFrame {
	private static final long serialVersionUID = 6888434488245880774L;
	public Box box;
	public JButton returnButton;
	public JPanel downLine;

	private class AboutContainer extends Container {
		private static final long serialVersionUID = 1223760054255611988L;
		private final int spaceExcess = 12;
		private Image rules;
		private Dimension size;

		public AboutContainer() {
			rules = new ImageIcon("img/rules.jpg").getImage();
			
			size = new Dimension(rules.getWidth(null) - spaceExcess,
					rules.getHeight(null) - spaceExcess);

			this.setPreferredSize(size);
			this.setBackground(Color.WHITE);
			this.setFocusable(true);
		}

		public void paint(Graphics g) {
			g.drawImage(rules, 0, 0, null);
		}
	}

	public AboutFrame() {	
		box = Box.createVerticalBox();
		box.add(new AboutContainer());

		returnButton = new JButton("Return to main menu");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StartFrame();
			}
		});

		downLine = new JPanel(new FlowLayout());
		downLine.setBackground(Color.WHITE);
		downLine.add(returnButton);
		box.add(downLine);

		this.setContentPane(box);
		this.setTitle("P E N T A G O - About this game");
		this.setVisible(true);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}