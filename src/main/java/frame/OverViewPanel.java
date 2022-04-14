package src.main.java.frame;

import javax.swing.*;
import java.awt.*;

public class OverViewPanel extends JPanel {

	private static OverViewPanel overViewPanel;
	private JLabel currentScoreLabel;
	private JLabel highScoreLabel;
	private JLabel timePlayedLabel;

	public static OverViewPanel getInstance() {
		if (OverViewPanel.overViewPanel == null)
			overViewPanel = new OverViewPanel();
		return overViewPanel;
	}

	private OverViewPanel() {
		setSize(500, 50);
		setLayout(new GridLayout(3, 2));

		currentScoreLabel = new JLabel("Current Score: 1");
		add(currentScoreLabel);

		highScoreLabel = new JLabel("HighScore:     0");
		add(highScoreLabel);

		JLabel currentPlaceLabel = new JLabel("Current Place:  1");
		add(currentPlaceLabel);

		JLabel speedLabel = new JLabel("Speed (0-10):   1");
		add(speedLabel);

		timePlayedLabel = new JLabel("Time played:    0");
		add(timePlayedLabel);

		JLabel skillLabel = new JLabel("Skill Level: Focking Noob");
		add(skillLabel);
	}

	public void setScore(int score) {
		currentScoreLabel.setText("Current Score: " + score);
		setHighScore(score);
	}

	public void setHighScore(int highScore) {
		String[] label = highScoreLabel.getText().split(" ");
		int lastHighscore = Integer.parseInt(label[label.length - 1]);
		if (highScore > lastHighscore) {
			highScoreLabel.setText("HighScore:     " + highScore);
		}
	}

	public void setTime(long millis) {
	long s = millis/1000;
	if(s == 0) {
		timePlayedLabel.setText("Time played: " + millis + " ms");
	} else if (s<60) {
		millis = millis - (millis/1000)*1000;
		timePlayedLabel.setText("Time played: " + s + " s, " + millis + " ms");
	} else {
		long min = s/60;
		s = 60 - (s%60);
		millis = millis - (millis/1000)*1000;
		timePlayedLabel.setText("Time played: " + min + " min, "+ s + " s, " + millis + " ms");
	}
		
	}

}
