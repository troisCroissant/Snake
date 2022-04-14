package src.main.java.logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
				
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case 37 : 
			try {
				Game.getInstance().setDirection('l');

			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			break;
		case 38:
			try {
				Game.getInstance().setDirection('u');
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			break;
		case 39: 
			try {
				Game.getInstance().setDirection('r');
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		break;
		case 40:
			try {
				Game.getInstance().setDirection('d');
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
