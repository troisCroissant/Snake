package src.main.java.frame;

import src.main.java.configuration.ConfigFrame;
import src.main.java.logic.Game;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private static MenuPanel menuPanel;
    private JButton startButton = new JButton("Start");
    private JButton aiButton;

    /* Singleton-Pattern ensures only one instance is up and running*/
    public static MenuPanel getInstance() {
        if (MenuPanel.menuPanel == null) menuPanel = new MenuPanel();
        return menuPanel;
    }

    /* Manupanle Constructor: Menu-Field gets*/
    private MenuPanel() {
        setSize(500, 50);
        setLayout(new GridLayout(2, 2, 0, 0));

        /* Area  and logic for the start Button*/
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

        /* Area  and logic for the AI Button*/
        JPanel aiPanel = new JPanel();
        aiPanel.setSize(250, 50);
        add(aiPanel);
        aiPanel.setLayout(new GridLayout(0, 1, 0, 0));

        aiButton = new JButton("Start AI");
        aiPanel.add(aiButton);
        aiButton.addActionListener(t -> {
            try {
                changeAiTextField();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (startButton.isEnabled()) {
                startButton.setEnabled(false);
            } else {
                startButton.setEnabled(true);
            }
            try {
                Game.getInstance().switchAiControlled();
                Game.getInstance();
                SnakeFrame.getInstance().requestFocus();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /* Area  and logic for the Settings Button*/
        JPanel settingsPanel = new JPanel();
        settingsPanel.setSize(250, 50);
        add(settingsPanel);
        settingsPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JButton settingsButton = new JButton("Settings");
        settingsPanel.add(settingsButton);
        settingsButton.addActionListener(l -> {
            try {
                ConfigFrame.getInstance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SnakeFrame.getInstance().setEnabled(false);

        });

        /* Area  and logic for the Stats Button*/
        JPanel statsPanel = new JPanel();
        statsPanel.setSize(250, 50);
        add(statsPanel);
        statsPanel.setLayout(new GridLayout(0, 1, 0, 0));

        JButton statsButton = new JButton("Stats");
        statsPanel.add(statsButton);
    }

    /* If a game has startet, the start Button is disabled*/
    public void disableStartButton() {
        startButton.setEnabled(false);
        SnakeFrame.getInstance().requestFocus();
    }

    /* If a game has ended, the start Button is enabled*/
    public void enableStartButton() {
        startButton.setEnabled(true);
        SnakeFrame.getInstance().requestFocus();
    }

    /* Before starting a new game, the program has to check wether the game is started by pressing the start button or
    *  by just pressing a direction. First case -> the start button is alread disabled; Second case -> the start button is enabled
    *  and has to be diabled manually. */
    public boolean checkStartButtonStatus() {
        return startButton.isEnabled();
    }

    /* If the AI Button has been pressed, the text of the button has to be changed*/
    public void changeAiTextField() throws InterruptedException {
        if (aiButton.getText().equalsIgnoreCase("Start AI")) {
            aiButton.setText("Stop AI");
        } else {
            aiButton.setText("Start AI");
        }
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
