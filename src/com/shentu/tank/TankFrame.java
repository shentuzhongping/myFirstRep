package com.shentu.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	static final int GAME_WIDTH = 1080,GAME_HEIGHT = 720;
	
	GameModel gm = new GameModel();
	
	
	TankFrame () {
		setSize(GAME_WIDTH,GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		
		addKeyListener(new TankKeyMonitor());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
	}
	//解决双缓冲问题
	Image offScreenImage = null;
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage,0,0,null);
	}
	
	//解决容器可能内存溢出问题
	@Override
	public void paint(Graphics g) {
//		explode.paint(g);
		gm.paint(g);
	}
	
	class TankKeyMonitor extends KeyAdapter {
		Tank tank = gm.tank;
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
			case KeyEvent.VK_CONTROL:
				tank.fire();
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
