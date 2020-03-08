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
	
	List<Bullet> bullets = new ArrayList<>();
	static List<Tank> enemyTanks = new ArrayList<>();
	static List<Explode> explodes = new ArrayList<>();
	
	Tank tank = new Tank(200,200,Dir.UP,3,Group.good,this);
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
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量:" + bullets.size(), 10, 60);
		g.drawString("敌人的数量:" + enemyTanks.size(), 10, 80);
		g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
		g.setColor(c);
		tank.paint(g);
		
		//画出剩余的敌人坦克
		for (Iterator i = enemyTanks.iterator(); i.hasNext();) {
			Tank tank = (Tank) i.next();
			if (!tank.isLiving()) {
				i.remove();
				continue;
			}
			tank.paint(g);
		}
		//画出剩余的子弹
		for (Iterator i = bullets.iterator(); i.hasNext();) {
			Bullet bullet = (Bullet) i.next();
			if (!bullet.isLiving()) {
				i.remove();
				continue;
			}
			
			bullet.paint(g);
		}
		
		//碰撞检测
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < enemyTanks.size(); j++) {
				bullets.get(i).collideWith(enemyTanks.get(j));
			}
		}
		//画出爆炸的动画
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
		
	}
	
	
	
	class TankKeyMonitor extends KeyAdapter {
		
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
