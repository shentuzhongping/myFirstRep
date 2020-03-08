package com.shentu.tank;

import com.shentu.netty.Client;
import com.shentu.tankChangeMsg.TankStartMovingMsg;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;

public class TankFrame extends Frame {
	public static final TankFrame TANK_FRAME = new TankFrame();
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	static final int GAME_WIDTH = 1080,GAME_HEIGHT = 720;
	
	static List<Bullet> bullets = new ArrayList<>();
	static Map<UUID, Tank> tanks = new HashMap<>();
	static List<Explode> explodes = new ArrayList<>();

	Random r = new Random();
	
	public Tank mainTank = new Tank(r.nextInt(GAME_WIDTH),r.nextInt(GAME_HEIGHT),Dir.UP,false,Group.good);
	private TankFrame () {
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

	public void add(Tank t) {
		tanks.put(t.id,t);
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
		g.drawString("的数量:" + tanks.size(), 10, 80);
		g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
		g.setColor(c);
		
		//画出剩余的敌人坦克
		tanks.values().stream().forEach((e) -> e.paint(g));


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
			for (int j = 0; j < tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}
		//画出爆炸的动画
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
		
	}

	public Tank findByUUID(UUID id) {
		if (tanks.containsKey(id)) {
			Tank t = tanks.get(id);
			return t;
		}
		return null;
	}


	class TankKeyMonitor extends KeyAdapter {
		
		private void setMainTankDir() {
			
			if(!up && !down && !left && !right) {
				mainTank.setMoving(false);
			} else {
				if(up) mainTank.setDir(Dir.UP);
				if(down)  mainTank.setDir(Dir.DOWN);
				if(left)  mainTank.setDir(Dir.LEFT);
				if(right)  mainTank.setDir(Dir.RIGHT);
				if ( !mainTank.isMoving()) {
					mainTank.setMoving(true);
					Client.INSTANCE.sendMsg(new TankStartMovingMsg(mainTank));
				}
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
				mainTank.fire();
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
