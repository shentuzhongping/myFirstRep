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
	Tank tank = new Tank(200,200,Dir.DOWN);
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
		tank.paint(g);
	}
	
	
	class MyKeyMonitor extends KeyAdapter {
		
		private void setMainTankDir() {
			
			if(!up && !down && !left && !right) {
				tank.setMoving(false);
			} else {
				tank.setMoving(true);
				if(up) tank.setDir(Dir.UP);
				if(down)  tank.setDir(Dir.DOWN);
				if(left)  tank.setDir(Dir.LEFT);
				if(right)  tank.setDir(Dir.RIGHT);
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
