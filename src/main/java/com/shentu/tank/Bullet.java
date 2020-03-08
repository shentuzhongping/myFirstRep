package com.shentu.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	private int x;
	private int y;
	private Dir dir;
	private static final int speed = 10;
	private boolean living = true;
	private Group group;
	public boolean isLiving() {
		return living;
	}
	public boolean getLiving() {
		return living;
	}

	public static int width = ResourceMagr.bulletD.getWidth();
	public static int height = ResourceMagr.bulletD.getHeight();
	
	Rectangle rect = new Rectangle(0,0,1,1);
	private TankFrame tf;
	
	Bullet (int x, int y, Dir dir,Group group,TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
		this.rect.x = x + width/2;
		this.rect.y = y + height/2;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	
	
	public void paint(Graphics g) {
		switch (dir) {
		case UP:
			g.drawImage(ResourceMagr.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMagr.bulletD, x, y, null);
			break;
		case LEFT:
			g.drawImage(ResourceMagr.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMagr.bulletR, x, y, null);
			break;
		default:
			break;
		}
		move();
	}
	
	public void move() {
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
		if (x < 0 || y < 0 || x > tf.GAME_WIDTH || y > tf.GAME_HEIGHT) {
			this.living = false;
		}
		this.rect.x = x + width/2;
		this.rect.y = y + height/2;
	}
	
	public void die() {
		this.living = false;
	}
	
	
	public void collideWith (Tank t) {
		if (this.group == t.getGroup()) return;
//		Rectangle rect1 = new Rectangle(t.getX(),t.getY(),t.width,t.height);
//		Rectangle rect2 = new Rectangle(x+width/2,y+height/2,1,1);
		if (t.rect.intersects(rect)) {
			t.die();
			this.die();
		}
	}

}
