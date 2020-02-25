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
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 50, 50);
		g.setColor(c);
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
		tf.bullets.add(new Bullet(x, y, dir));
	}
	
	
}
