package src.main.java.initializer;

import src.main.java.frame.SnakeFrame;

import java.awt.*;

public class SnakeInitializer {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SnakeFrame.getInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
