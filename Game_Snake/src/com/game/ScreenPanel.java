package com.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class ScreenPanel extends JPanel implements ActionListener {
	
	private final int SCREEN_WIDTH = 300;
    private final int SCREEN_HEIGHT = 300;
    private final int DOT_ICON_SIZE = 10;
    private final int DOT_ICONS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;
 
    private final int x[] = new int[DOT_ICONS];
    private final int y[] = new int[DOT_ICONS];
 
    private int parts;
    private int apple_x;
    private int apple_y;
 
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inSnakeGame = true;
 
    private Timer timer;
    private Image snakepart;
    private Image apple;
    private Image snakehead;
    
    public ScreenPanel() {
    	initScreenPanel();
    }
	
    private  void initScreenPanel() {
    	addKeyListener(new GameKeyAdapter());
    	setBackground(Color.black);
    	setFocusable(true);
    	
    	setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    	loadIcons();
    	initSnakeGame();
    }
    
    private void loadIcons() {
    	ImageIcon snake = new ImageIcon("src/img/snake.png");
    	snakepart = snake.getImage();
    	
    	ImageIcon apple = new ImageIcon("src/img/apple.png");
    	this.apple = apple.getImage();
    	
    	ImageIcon snakeHead = new ImageIcon("src/img/snakehead.png");
    	snakehead = snakeHead.getImage();
    }
    
    private void initSnakeGame() {
    	parts = 5;
    	for (int i = 0; i < parts; i++) {
    		x[i] = 50 - i * 10;
    		y[i] = 50;
    	}
    	
    	locateApple();
    	
    	timer = new Timer(DELAY, this);
    	timer.start();
    }
    
    @Override
    public void paintComponent(final Graphics g) {
    	super.paintComponent(g);
    	doDrawing(g);
    }
    
    private void doDrawing(final Graphics g) {
    	if (inSnakeGame) {
    		g.drawImage(apple, apple_x, apple_y, this);
    		for (int i = 0; i < parts; i++) {
    			if  (i == 0) {
    				g.drawImage(snakehead, x[i], y[i], this);
    			} else {
    				g.drawImage(snakepart, x[i], y[i], this);
    			}
    		}
    		Toolkit.getDefaultToolkit().sync();
    	} else {
    		endGame(g);
    	}
    }
    
    private void endGame(Graphics g) {
    	String msg = "Game over";
    	Font font = new Font("Ubuntu Mono", Font.BOLD, 12);
    	FontMetrics metr = getFontMetrics(font);
    	
    	g.setColor(Color.white);
    	g.setFont(font);
    	g.drawString(msg, (SCREEN_WIDTH - metr.stringWidth(msg)) / 2, SCREEN_HEIGHT / 2);
    }
    
    public void findAppleIcon() {
    	if ((x[0] == apple_x) && (y[0] == apple_y)) {
    		parts++;
    		locateApple();
    	}
    }
    
    private void shift() {
    	for (int i = parts; i > 0; i--) {
    		x[i] = x[i - 1];
    		y[i] = y[i - 1];
    	}
    	
    	if (leftDirection) {
    		x[0] -= DOT_ICON_SIZE;
    	}
    	
    	if (rightDirection) {
    		x[0] += DOT_ICON_SIZE;
    	}
    	
    	if (upDirection) {
    		y[0] -= DOT_ICON_SIZE;
    	}
    	
    	if (downDirection) {
    		y[0] += DOT_ICON_SIZE;
    	}
    }
    
    private void findCollision() {
    	for (int i = parts; i > 0; i--) {
    		if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
    			inSnakeGame = false;
    		}
    	}
    	
    	if (y[0] >= SCREEN_HEIGHT) {
            inSnakeGame = false;
        }

        if (y[0] < 0) {
            inSnakeGame = false;
        }

        if (x[0] >= SCREEN_WIDTH) {
            inSnakeGame = false;
        }

        if (x[0] < 0) {
            inSnakeGame = false;
        }
        
        if (!inSnakeGame) {
            timer.stop();
        }
    }
    
    private void locateApple() {
    	int random = (int) Math.random() * RAND_POS;
    	apple_x = (random * DOT_ICON_SIZE);
    	
    	random = (int) Math.random() * RAND_POS;
    	apple_y = random * DOT_ICON_SIZE;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
    	if (inSnakeGame) {
    		findAppleIcon();
    		findCollision();
    		shift();
    	}
    	repaint();
    }
    
    private class GameKeyAdapter extends KeyAdapter {
    	@Override
    	public void keyPressed(KeyEvent event) {
    		int key = event.getKeyCode();
    		
    		if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
    	}
    }
}
