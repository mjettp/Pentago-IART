package Interface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.GameThread;
import Game.GameBoard;;

public class OptionsFrame extends JFrame {
	private static final long serialVersionUID = -3367328642501208248L;
	private Box box;
	private JButton startButton, menuButton;
	private JPanel downLine;
	private JComboBox<String> levelComboBox, typeComboBox, algorithmComboBox;

	private class OptionsBox extends Box {
		private static final long serialVersionUID = 5427769975055181858L;
		private final Font bigFont = new Font("Arial", Font.BOLD, 23);

		public OptionsBox() {
			super(BoxLayout.Y_AXIS);

			JLabel typeLabel = new JLabel("Game Type:", JLabel.CENTER);
			typeLabel.setFont(bigFont);
			JLabel levelLabel = new JLabel("         Artificial Intelligence:      ",
					JLabel.CENTER);
			levelLabel.setFont(bigFont);
			JLabel algorithmLabel = new JLabel("Minimax depth:",
					JLabel.CENTER);
			algorithmLabel.setFont(bigFont);


			typeLabel.setAlignmentX(CENTER_ALIGNMENT);
			algorithmLabel.setAlignmentX(CENTER_ALIGNMENT);
			levelLabel.setAlignmentX(CENTER_ALIGNMENT);

			String[] typeValues = { "Choose one", "Human vs Human", 
					"Human vs Computer", "Computer vs Computer" };	
			String[] algorithmValues = { "Choose one", "1", 
					"2", "3", "4" };		
			String[] levelValues = { "Choose one", "Valid random play", 
			"Minimax with alpha-beta pruning"};

			typeComboBox = new JComboBox<>(typeValues);		
			algorithmComboBox = new JComboBox<>(algorithmValues);		
			levelComboBox = new JComboBox<>(levelValues);

			algorithmComboBox.setEnabled(false);
			levelComboBox.setEnabled(false);

			typeComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (typeComboBox.getSelectedItem().equals("Human vs Computer") ||
							typeComboBox.getSelectedItem().equals("Computer vs Computer")) {
						levelComboBox.setEnabled(true);
					}
					else {
						algorithmComboBox.setEnabled(false);
						levelComboBox.setEnabled(false);
					}
				}
			});
			
			levelComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (levelComboBox.getSelectedItem().equals("Minimax with alpha-beta pruning"))
						algorithmComboBox.setEnabled(true);
					else
						algorithmComboBox.setEnabled(false);
				}
			});

			Box decisionOneBox = Box.createHorizontalBox();
			Box decisionTwoBox = Box.createHorizontalBox();
			Box decisionThreeBox = Box.createHorizontalBox();

			decisionOneBox.add(new JLabel("      "));
			decisionOneBox.add(typeComboBox);
			decisionOneBox.add(new JLabel("      "));

			decisionTwoBox.add(new JLabel("      "));
			decisionTwoBox.add(levelComboBox);
			decisionTwoBox.add(new JLabel("      "));

			decisionThreeBox.add(new JLabel("      "));
			decisionThreeBox.add(algorithmComboBox);
			decisionThreeBox.add(new JLabel("      "));

			add(typeLabel);
			add(new JLabel(" "));
			add(decisionOneBox);
			add(new JLabel(" "));
			add(levelLabel);
			add(new JLabel(" "));
			add(decisionTwoBox);
			add(new JLabel(" "));
			add(algorithmLabel);
			add(new JLabel(" "));
			add(decisionThreeBox);
			add(new JLabel(" "));

			setFocusable(true);
		}
	}

	public OptionsFrame() {
		box = Box.createVerticalBox();
		box.add(new OptionsBox());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new StartFrame();
			}
		});

		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (typeComboBox.getSelectedItem().equals("Human vs Human") ||
						levelComboBox.getSelectedItem().equals("Valid random play") || 
								!algorithmComboBox.getSelectedItem().equals("Choose one")) {
					String title;

					String gameType = (String) typeComboBox.getSelectedItem();
					title = "Pentago - " + gameType;

					String depth = (String) algorithmComboBox.getSelectedItem();
					int depthInt = 0;
					
					if (depth != "Choose one")
						depthInt = Integer.parseInt(depth);
					
					dispose();
					if (typeComboBox.getSelectedItem().equals("Human vs Human"))
						new GameFrame(new GameThread(new GameBoard(false, false), -1), title);
					else if (typeComboBox.getSelectedItem().equals("Computer vs Computer"))
						new GameFrame(new GameThread(new GameBoard(true, true), depthInt), title);
					else
						new GameFrame(new GameThread(new GameBoard(false, true), depthInt), title);
				}
			}
		});

		menuButton = new JButton("Menu");
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StartFrame();
			}
		});

		downLine = new JPanel(new FlowLayout());
		downLine.setBackground(Color.WHITE);
		downLine.add(startButton);
		downLine.add(menuButton);

		box.add(downLine);

		setContentPane(box);
		setTitle("Options");
		pack();
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}