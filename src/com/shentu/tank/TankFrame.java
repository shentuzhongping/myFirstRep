package com.shentu.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	int x = 200;
	int y = 200;
	//刚开始必须要给一个值，如果不给用switch的时候会报空指针
	Dir dir = Dir.DOWN; 
	private final int speed = 10;
	
	TankFrame () {
		setSize(800,600);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		
		addKeyListener(new MyKeyMonitor());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
	}
	
	public void paint(Graphics g) {
		g.fillRect(x, y, 50, 50);
		
		switch (dir) {
		case UP:
			y -= speed;
			break;
		case DOWN:
			y += speed;
			break;
		case LEFT:
			x -= speed;
			break;
		case RIGHT:
			x += speed;
			break;
		default:
			break;
		}
		
		
	}
	
	
	class MyKeyMonitor extends KeyAdapter {
		
		private void setMainTankDir() {
			if(up) dir = Dir.UP;
			if(down) dir = Dir.DOWN;
			if(left) dir = Dir.LEFT;
			if(right) dir = Dir.RIGHT;
			if(up == false && down == false && left == false && right == false) {
				dir = Dir.STOP;
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				up = false;
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				break;
			case KeyEvent.VK_LEFT:
				left = false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			default:
				break;
			}
			
			setMainTankDir();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				up = true;
				break;
			case KeyEvent.VK_DOWN:
				down = true;
				break;
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_RIGHT:
				right = true;
				break;

			default:
				break;
			}
			
			setMainTankDir();
		}
		
	}

}
