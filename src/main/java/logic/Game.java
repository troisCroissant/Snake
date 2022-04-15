package src.main.java.logic;

import src.main.java.frame.GameBoardPanel;
import src.main.java.frame.MenuPanel;
import src.main.java.frame.OverViewPanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {

    private static Game game;
    private Rectangle item = new Rectangle();
    private MenuPanel menue = MenuPanel.getInstance();
    private OverViewPanel overview = OverViewPanel.getInstance();
    private GameBoardPanel gameboard = GameBoardPanel.getInstance();
    private Snake snake = Snake.getInstance();

    private ArrayList<Rectangle> temporarysafer = new ArrayList<Rectangle>();
    Map<Character, Integer> newHeadposition = new HashMap<Character, Integer>();

    private boolean gameRunning = true;
    private char direction = ' ';
    private int speed = 50;
    private int score = 1;
    private boolean add = false;
    private boolean directionChangeBlocked = false;
    private boolean isAllowed = false;
    private boolean aiControlled = false;
    private boolean stopAi = false;
    private boolean moveX = false;
    private ArrayList<Character> directions = new ArrayList<>();
    private ArrayList<Rectangle2D> temporarySnakeSaver = new ArrayList<>();

    private long start = 0;
    private long end = 0;

    public static Game getInstance() throws InterruptedException {
        if (Game.game == null)
            game = new Game();
        return game;
    }

    private Game() throws InterruptedException {

        // if game started by pressing key, a random initial direction doesn't have to be created
        if (!menue.checkStartButtonStatus()) {
            setStartDirection();
        } else {
            menue.disableStartButton();
            menue.disableAiButton();
        }

        resetScore();
        resetSnake();
        generateSnakeHead();
        generateItem();

        Thread gameThread = new Thread(() -> {
            try {
                startGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        gameThread.start();
    }

    public void startGame() throws InterruptedException {
        start = System.currentTimeMillis();
        if (aiControlled) {
            directions.add('o');
            while (gameRunning) {
                directionChangeBlocked = false;
                addLogic();

                if (snakeHeadReachedItem()) {
                    temporarysafer.add(new Rectangle(item.x - 1, item.y - 1, 20, 20));
                    generateItem();
                    overview.setScore(++score);
                    generateDirections();
                }

                setDirection(directions.get(0));
                directions.remove(0);

                if (checkCollision() || !aiControlled) {
                    reset();
                    aiControlled = false;
                }
                gameboard.setSnake(snake.getSnake());
                Thread.sleep(speed);
            }
        } else {
            while (gameRunning) {
                directionChangeBlocked = false;
                if (snakeHeadReachedItem()) {
                    temporarysafer.add(new Rectangle(item.x - 1, item.y - 1, 20, 20));
                    generateItem();
                    overview.setScore(++score);
                }
                addLogic();
                GameBoardPanel.getInstance().setSnake(snake.getSnake());
                if (checkCollision())
                    reset();
                Thread.sleep(speed);
            }
        }
    }

    private void addLogic() {
        if(add) {
            snake.add((int) temporarysafer.get(0).getX(), (int) temporarysafer.get(0).getY(), 20, 20);
            temporarysafer.remove(0);
            add = false;
        }

        if (temporarysafer.size() != 0) {
            if (snake.getTail().getX() == temporarysafer.get(0).getX()
                    && snake.getTail().getY() == temporarysafer.get(0).getY()) {
                add = true;
            }
        }

        moveSnake(snake.getSnake());

    }


    public void reset() {
        gameRunning = false;
        menue.enableStartButton();
        menue.enableAiButton();
        Game.game = null;
        end = System.currentTimeMillis();
        overview.setTime(end - start);
    }

    private boolean checkCollision() {
        if (snake.getHead().getX() < 0 || snake.getHead().getX() > 480 || snake.getHead().getY() < 0
                || snake.getHead().getY() > 480 || stopAi) return true;
        for (int i = 1; i < snake.getSnake().size(); i++) {
            if (snake.getHead().getX() == snake.getSnake().get(i).getX() &&
                    snake.getHead().getY() == snake.getSnake().get(i).getY()) {
                return true;
            }
        }

        return false;
    }

    private void generateDirections() {
        // aktuelle Snake wird zwischengespeichert, anhand der neuen Liste die Richtungen zu berechnen
        if (temporarySnakeSaver.size() != 0 ) temporarySnakeSaver.clear();
        temporarySnakeSaver = snake.getSnake();
        int deltaX = -1 * ((item.x - 1) - (int) snake.getHead().getX()) / 20;
        int deltaY = (item.y - (int) snake.getHead().getY()) / 20;



        if (deltaX != 0 && direction != 'l' && direction != 'r') {
            if (deltaX < 0) {
                direction = 'r';
                moveX = true;
            } else {
                direction = 'l';
                moveX = true;
            }
        }
        // wenn snake per Zufall schon in die richtige Richtung geht
        else if (deltaX < 0 && direction == 'r') {
            moveX = true;
        } else if (deltaX > 0 && direction == 'l') {
            moveX = true;
        }

        // Richtung geht nach rechts oder links, dann kann richtung oben oder unten sein
        if (!moveX) {
            if ((deltaY != 0 && direction != 'u' && direction != 'd')) {
                if (deltaY < 0) {
                    direction = 'u';
                } else {
                    direction = 'd';
                }
            }
        }
    }

    private void moveSnake(ArrayList<Rectangle2D> snakeList) {
        for (int i = snakeList.size() - 1; i > 0; i--) {
            snakeList.get(i).setRect(snakeList.get(i - 1).getX(), snakeList.get(i - 1).getY(), 20,
                    20);
        }
        switch (direction) {
            case 'u':
                snakeList.get(0).setRect(snakeList.get(0).getX(), snakeList.get(0).getY() - 20, 20, 20);
                break;
            case 'd':
                snakeList.get(0).setRect(snakeList.get(0).getX(), snakeList.get(0).getY() + 20, 20, 20);
                break;
            case 'l':
                snakeList.get(0).setRect(snakeList.get(0).getX() - 20, snakeList.get(0).getY(), 20, 20);
                break;
            case 'r':
                snakeList.get(0).setRect(snakeList.get(0).getX() + 20, snakeList.get(0).getY(), 20, 20);
                break;
        }

    }

    void generateItem() {
        double x = 0, y = 0;
        while (!isAllowed) {
            x = ((int) (Math.random() * 100)) % 25 * 20;
            y = ((int) (Math.random() * 100)) % 25 * 20;
            isAllowed = checkItemNotOnSnake(x, y);
        }
        item.setRect(x + 1, y + 1, 18, 18);
        gameboard.setItem(item);
        isAllowed = false;
    }

    private boolean checkItemNotOnSnake(double x, double y) {
        for (int i = 0; i < snake.getSnake().size(); i++) {
            if (snake.getSnake().get(i).getX() == x && snake.getSnake().get(i).getY() == y)
                return false;
        }
        return true;
    }

    private void generateSnakeHead() {
        snake.add(240, 240, 20, 20);
        GameBoardPanel.getInstance().setSnake(snake.getSnake());
    }

    private void setStartDirection() {
        double random = Math.random();
        if (random < .25)
            direction = 'u';
        else if (random < .5)
            direction = 'd';
        else if (random < .75)
            direction = 'l';
        else direction = 'r';
    }

    public void setDirection(char direction) {
        if (directionChangeBlocked || aiControlled) return;
        if ((this.direction == 'l') && (direction == 'r') || (this.direction == 'r') && (direction == 'l')
                || (this.direction == 'u') && (direction == 'd') || (this.direction == 'd') && (direction == 'u'))
            return;
        this.direction = direction;
        directionChangeBlocked = true;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private void resetScore() {
        this.score = 1;
    }

    private void resetSnake() {
        snake.clearSnake();
        GameBoardPanel.getInstance().setSnake(snake.getSnake());
        OverViewPanel.getInstance().setScore(score);
    }

    private boolean snakeHeadReachedItem() {
        return snake.getHead().getX() == item.getX() - 1 && snake.getHead().getY() == item.getY() - 1;
    }

    public void switchAiControlled() {
        if (aiControlled) {
            aiControlled = false;
        } else {
            aiControlled = true;
        }
    }


}
