package src.main.java.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameBoardPanel extends JPanel {


	private ArrayList<Rectangle2D> snake = new ArrayList<Rectangle2D>();
	private Rectangle item = new Rectangle();
	static Map<String, Color> colorMap = new HashMap<>();

	private GameBoardPanel() {
		setSize(500, 500);
	}

	/* Singleton-Pattern ensures only one instance is up and running*/
	private static GameBoardPanel gameBoardPanel;

	public static GameBoardPanel getInstance() {
		if (GameBoardPanel.gameBoardPanel == null){
			gameBoardPanel = new GameBoardPanel();
			gameBoardPanel.initilalizeColorMap(colorMap);
		}
		return gameBoardPanel;
	}

	/*paint the game field*/
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(colorMap.get("background"));
		g2d.fillRect(getVisibleRect().x, getVisibleRect().y, getVisibleRect().width, getVisibleRect().height);
		g2d.setColor(colorMap.get("game_lines"));
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				g2d.drawRect(i * 20, j * 20, 20, 20);
			}
		}

		if (snake.size() != 0) {
			g2d.setColor(colorMap.get("snake_head"));
			g2d.fill(snake.get(0));
			g2d.setColor(colorMap.get("snake_tail"));
			for (int i = 1; i < snake.size(); i++) {
				g2d.fill(snake.get(i));
			}
				g2d.setColor(colorMap.get("item_normal"));
				g2d.fill(item);
		}
	}

	/* Method is called by Game class to repaint when the snake position changes.*/
	public void setSnake(ArrayList<Rectangle2D> snake) {
		this.snake = snake;
		this.repaint();
	}

	/* Method is called by Game class to repaint the gameboard when an item is consumed
	*  and a new one at a new position has to be painted. */
	public void setItem(Rectangle2D item) {
		this.item = (Rectangle) item;
		this.repaint();
	}

	private void initilalizeColorMap(Map<String, Color> m) {
		m.put("snake_tail", new Color(196, 117, 39));
		m.put("snake_head", new Color(46,0,235));
		m.put("item_normal", new Color(255,0,0));
		m.put("background", new Color(225, 220, 219));
		m.put("game_lines", new Color(176,178,184));
	}
}
