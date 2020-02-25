package com.shentu.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Tank {
	private int x;
	private int y;
	private Dir dir;
	private boolean moving = false;
	
	private static final int speed = 5;
	private TankFrame tf;
	
	public static int width = ResourceMagr.tankD.getWidth();
	public static int height = ResourceMagr.tankD.getHeight();
	
	private boolean living = true;
	public boolean isLiving() {
		return living;
	}
	public boolean getLiving() {
		return living;
	}
	Tank (int x,int y, Dir dir,TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
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
	public boolean isMoving() {
		return moving;
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void paint(Graphics g) {	
		switch (dir) {
		case UP:
			g.drawImage(ResourceMagr.tankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMagr.tankD, x, y, null);
			break;
		case LEFT:
			g.drawImage(ResourceMagr.tankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMagr.tankR, x, y, null);
			break;
		default:
			break;
		}
		move();
	}
	
	public void move() {
		if (!moving) return;
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
	public void fire() {
		int bx = x + width/2 - Bullet.width/2;
		int by = y + height/2 - Bullet.height/2;
		tf.bullets.add(new Bullet(bx, by, dir,tf));
	}
	public void die() {
		this.living = false;
	}
	
	
}
