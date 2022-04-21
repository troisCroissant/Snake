package src.main.java.frame;

import src.main.java.configuration.Configs;
import src.main.java.logic.Game;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private static MenuPanel menuPanel;
    private JButton startButton = new JButton("Start");
    private JButton aiButton;
    private Configs configs = Configs.getInstance();
    private OverViewPanel overview = OverViewPanel.getInstance();

    /* Singleton-Pattern ensures only one instance is up and running*/
    public static MenuPanel getInstance() {
        if (MenuPanel.menuPanel == null) menuPanel = new MenuPanel();
        return menuPanel;
    }

    private MenuPanel() {
        setSize(500, 50);
        setLayout(new GridLayout(2, 2, 0, 0));
        generateDesignAndLogicForStartButton();
        generateDesignAndLogicForAIButton();
        generateDesignAndLogicForFasterButton();
        generateDesignAndLogicForSlowerButton();
    }

    private void generateDesignAndLogicForStartButton() {
        JPanel startPanel = new JPanel();
        startPanel.setSize(250, 50);
        add(startPanel);
        startPanel.setLayout(new GridLayout(0, 1, 0, 0));
        startPanel.add(startButton);
        startButton.addActionListener(l -> {
            try {
                startButton.setEnabled(false);
                aiButton.setEnabled(false);
                Game.getInstance();
                SnakeFrame.getInstance().requestFocus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void generateDesignAndLogicForAIButton() {
        JPanel aiPanel = new JPanel();
        aiPanel.setSize(250, 50);
        add(aiPanel);
        aiPanel.setLayout(new GridLayout(0, 1, 0, 0));
        aiButton = new JButton("Start AI");
        aiPanel.add(aiButton);
        aiButton.addActionListener(t -> {
            if (startButton.isEnabled()) {
                startButton.setEnabled(false);
            } else {
                startButton.setEnabled(true);
            }
            try {
                changeAiTextField();
                Game.getInstance().switchAiControlled();
                SnakeFrame.getInstance().requestFocus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /* If the AI Button has been pressed, the text of the button has to be changed*/
    public void changeAiTextField() throws InterruptedException {
        if (aiButton.getText().equalsIgnoreCase("Start AI")) {
            aiButton.setText("Stop AI");
        } else {
            aiButton.setText("Start AI");
        }
    }

    private void generateDesignAndLogicForFasterButton() {
        JPanel fasterButtonPanel = new JPanel();
        fasterButtonPanel.setSize(250, 50);
        add(fasterButtonPanel);
        fasterButtonPanel.setLayout(new GridLayout(0, 1, 0, 0));
        JButton fasterButton = new JButton("FASTER");
        fasterButton.addActionListener(l -> {
            if (configs.getSpeed() >20)
            configs.setSpeed(configs.getSpeed()-20);
            overview.setSpeedLabel(1);
        });
        fasterButtonPanel.add(fasterButton);
    }

    private void generateDesignAndLogicForSlowerButton() {
        JPanel slowerPanel = new JPanel();
        slowerPanel.setSize(250, 50);
        add(slowerPanel);
        slowerPanel.setLayout(new GridLayout(0, 1, 0, 0));
        JButton slowerButton = new JButton("Slow Down");
        slowerButton.addActionListener(l -> {
                configs.setSpeed(configs.getSpeed()+20);
            overview.setSpeedLabel(-1);
        });
        slowerPanel.add(slowerButton);
    }

    /* Method is called by the game class to disable the startbutton when a game has started*/
    public void disableStartButton() {
        startButton.setEnabled(false);
        SnakeFrame.getInstance().requestFocus();
    }

    /* Method is called by the game class to enable the startbutton when a game has ended*/
    public void enableStartButton() {
        startButton.setEnabled(true);
        SnakeFrame.getInstance().requestFocus();
    }

    /* Before starting a new game, the program has to check wether the game is started by pressing the start button or
     *  by just pressing a direction. First case -> the start button is alread disabled; Second case -> the start button is enabled
     *  and has to be diabled manually. */
    public boolean isStartButtonEnabled() {
        return startButton.isEnabled();
    }

    /* if a AI controlled game has started, the option to start another game while the current on
     *  is still running is disabled */
    public void enableAiButton() {
        aiButton.setEnabled(true);
        SnakeFrame.getInstance().requestFocus();
    }

    public void disableAiButton() {
        aiButton.setEnabled(false);
        SnakeFrame.getInstance().requestFocus();
    }
}
