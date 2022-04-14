package src.main.java.logic;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Snake {

	private static Snake snakeObj;
	private ArrayList<Rectangle2D> snake;

	public static Snake getInstance() {
		if (snakeObj == null)
			snakeObj = new Snake();
		return snakeObj;
	}

	private Snake() {
		snake = new ArrayList<Rectangle2D>();
	}

	public Rectangle2D getHead() {
		return snake.get(0);
	}

	public Rectangle2D getTail() {
		return snake.get(snake.size() - 1);
	}

	public void add(int x, int y, int width, int height) {
		snake.add(new Rectangle(x, y, width, height));
	}

	public ArrayList<Rectangle2D> getSnake() {
		return snake;
	}
	
	public void clearSnake() {
		snake.clear();
	}
}
