package src.main.java.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GameBoardPanel extends JPanel {

	private static GameBoardPanel gameBoardPanel;
	private boolean gridPainted = false;
	private ArrayList<Rectangle2D> snake = new ArrayList<Rectangle2D>();
	private Rectangle item = new Rectangle();
	private Rectangle specialItem = new Rectangle();
	private boolean end = false;

	private GameBoardPanel() {
		setSize(500, 500);
	}

	public static GameBoardPanel getInstance() {
		if (GameBoardPanel.gameBoardPanel == null)
			gameBoardPanel = new GameBoardPanel();
		return gameBoardPanel;
	}

	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(225, 220, 219));
		g2d.fillRect(getVisibleRect().x, getVisibleRect().y, getVisibleRect().width, getVisibleRect().height);
		g2d.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 25; j++) {
				g2d.drawRect(i * 20, j * 20, 20, 20);
			}
		}

		if (snake.size() != 0) {
			g2d.setColor(new Color(134, 174, 13));
			g2d.fill(snake.get(0));
			g2d.setColor(new Color(196, 117, 39));
			for (int i = 1; i < snake.size(); i++) {
				g2d.fill(snake.get(i));
			}

				g2d.setColor(Color.ORANGE);
				g2d.fill(item);


		}
	}

	public void setSnake(ArrayList<Rectangle2D> snake) {
		this.snake = snake;
		this.repaint();
	}

	public void setItem(Rectangle2D item) {
		this.item = (Rectangle) item;
		this.repaint();
	}

	public void signalizeEnd(){
		this.item = getVisibleRect();
		end = true;
		this.repaint();
		SnakeFrame.getInstance().requestFocus();
	}

	public void initializeStart() {
		end = true;
		this.repaint();
	}

	public void setheadColor(){

	}

	public void setTailColor(){

	}

	public void setSpecialItem(Rectangle specialtem) {
		this.specialItem = (Rectangle) specialtem;
		this.repaint();
	}
}
