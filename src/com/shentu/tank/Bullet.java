package com.shentu.tank;

import java.awt.Graphics;

public class Bullet extends GameObject{
	private Dir dir;
	private static final int speed = 10;

	public static int width = ResourceMagr.bulletD.getWidth();
	public static int height = ResourceMagr.bulletD.getHeight();
	
	public Bullet (int x, int y, Dir dir,Group group) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.rect.x = x + width/2;
		this.rect.y = y + height/2;
		this.rect.height = 1;
		this.rect.width = 1;
		GameModel.getInstance().add(this);
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
		if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			this.living = false;
			GameModel.getInstance().remove(this);
		}
		this.rect.x = x + width/2;
		this.rect.y = y + height/2;
	}
	
	public void die() {
		this.living = false;
	}
	

}
