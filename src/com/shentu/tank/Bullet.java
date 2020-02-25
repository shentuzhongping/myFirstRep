package com.shentu.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private int x;
	private int y;
	private Dir dir;
	private static final int speed = 10;
	
	Bullet (int x, int y, Dir dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillRect(x, y, 30, 30);
		move();
		g.setColor(c);
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
	}

}
