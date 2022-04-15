package src.main.java.frame;

import src.main.java.configuration.ConfigFrame;
import src.main.java.logic.Game;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

	private static MenuPanel menuPanel;
	private JButton startButton = new JButton("Start");
	private JButton aiButton;


	public static MenuPanel getInstance() {
		if(MenuPanel.menuPanel == null) menuPanel = new MenuPanel();
		return menuPanel;
	}

	private MenuPanel() {
		setSize(500,50);
		setLayout(new GridLayout(2, 2, 0, 0));

		JPanel startPanel = new JPanel();
		startPanel.setSize(250,50);
		add(startPanel);
		startPanel.setLayout(new GridLayout(0, 1, 0, 0));

		startPanel.add(startButton);
		startButton.addActionListener(l ->{
			try {
				startButton.setEnabled(false);
				aiButton.setEnabled(false);
				Game.getInstance();
				SnakeFrame.getInstance().requestFocus();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		JPanel restartPanel = new JPanel();
		restartPanel.setSize(250,50);
		add(restartPanel);
		restartPanel.setLayout(new GridLayout(0, 1, 0, 0));

		aiButton = new JButton("Start AI");
		restartPanel.add(aiButton);
		aiButton.addActionListener(t->{
			try {
				changeAiTextField();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(startButton.isEnabled()){
				startButton.setEnabled(false);
			} else{
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


		JPanel settingsPanel = new JPanel();
		settingsPanel.setSize(250,50);
		add(settingsPanel);
		settingsPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton settingsButton = new JButton("Settings");
		settingsPanel.add(settingsButton);
		settingsButton.addActionListener(l -> {
			ConfigFrame.getInstance();
			System.out.println("pressed");
		});


		JPanel statsPanel = new JPanel();
		statsPanel.setSize(250,50);
		add(statsPanel);
		statsPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton statsButton = new JButton("Stats");
		statsPanel.add(statsButton);
	}


	public void disableStartButton() {
		startButton.setEnabled(false);
		SnakeFrame.getInstance().requestFocus();
	}

	public void enableStartButton() {
		startButton.setEnabled(true);
		SnakeFrame.getInstance().requestFocus();
	}

	public boolean checkStartButtonStatus() {
		return startButton.isEnabled();
	}

	public void changeAiTextField() throws InterruptedException {
		if (aiButton.getText().equalsIgnoreCase("Start AI")){
			aiButton.setText("Stop AI");
		} else{
			aiButton.setText("Start AI");
		}
	}

	public void enableAiButton() {
		aiButton.setEnabled(true);
		SnakeFrame.getInstance().requestFocus();
	}

	public void disableAiButton() {
		aiButton.setEnabled(false);
		SnakeFrame.getInstance().requestFocus();
	}
}
