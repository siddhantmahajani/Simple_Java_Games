package com.game;

import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SnakeGame extends JFrame {
	
	public SnakeGame() {
		initScreen();
	}
	
	private void initScreen() {
		add(new ScreenPanel());
		
		setResizable(false);
		pack();
		
		setTitle("Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new SnakeGame();
			frame.setVisible(true);
		});
	}

}
