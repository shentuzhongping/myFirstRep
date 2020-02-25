package com.shentu.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private int x;
	private int y;
	private Dir dir;
	private static final int speed = 10;
	private boolean live = true;
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	private TankFrame tf;
	
	Bullet (int x, int y, Dir dir,TankFrame tf) {
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
			this.live = false;
		}
	}

}
