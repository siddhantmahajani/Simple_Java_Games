package com.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Pong extends JFrame {
	
	int gWidth = 500, gHeight = 400;
	Dimension screenSize = new Dimension(gWidth, gHeight);
	
	Image dbImage;
	Graphics dbGraphics;
	
	static Ball ball = new Ball(250, 200);
	
	public Pong() {
		this.setTitle("Ping-Pong!");
		this.setSize(screenSize);
		this.setResizable(false);
		this.setVisible(true);
		this.setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(new AL());
	}
	
	public static void main(String[] args) {
		new Pong();
		Thread ballThread = new Thread(ball);
		ballThread.start();
		Thread player1 = new Thread(ball.player1);
		Thread player2 = new Thread(ball.player2);
		player1.start();
		player2.start();
	}
	
	@Override
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbGraphics = dbImage.getGraphics();
		draw(dbGraphics);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void draw(Graphics g) {
		ball.draw(g);
		ball.player1.draw(g);
		ball.player2.draw(g);
		
		g.setColor(Color.WHITE);
		g.drawString("" + ball.p1Score, 15, 20);
		g.drawString("" + ball.p2Score, 385, 20);
		
		repaint();
	}
	
	public class AL extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent event) {
			ball.player1.keyPressed(event);
			ball.player2.keyPressed(event);
		}
		
		@Override
		public void keyReleased(KeyEvent event) {
			ball.player1.keyReleased(event);
			ball.player2.keyReleased(event);
		}
	}
}
