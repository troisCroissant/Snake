package src.main.java.frame;

import src.main.java.logic.Keys;

import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame {

	private static SnakeFrame snakeFrame;

	public static SnakeFrame getInstance() {
		if (SnakeFrame.snakeFrame == null)
			snakeFrame = new SnakeFrame();
		return snakeFrame;
	}

	private SnakeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 517, 640);
		setResizable(false);
		setVisible(true);

		JPanel contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
		contentPane.setBackground(new Color(225, 220, 219));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(OverViewPanel.getInstance(), BorderLayout.NORTH);
		contentPane.add(GameBoardPanel.getInstance(), BorderLayout.CENTER);
		contentPane.add(MenuPanel.getInstance(), BorderLayout.SOUTH);

		requestFocus();
		addKeyListener(new Keys());
	}

}
