package com.shentu.tank;

import java.awt.Graphics;
import java.util.Random;

public class Tank {
	private int x;
	private int y;
	private Dir dir;
	private boolean moving = false;
	
	private Group group;
	
	private int speed = 2;
	
	private Random random = new Random();
	private TankFrame tf;
	
	public static int width = ResourceMagr.badTankD.getWidth();
	public static int height = ResourceMagr.badTankD.getHeight();
	
	private boolean living = true;
	public boolean isLiving() {
		return living;
	}
	public boolean getLiving() {
		return living;
	}
	Tank (int x,int y, Dir dir,boolean moving,Group group,TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.moving = moving;
		this.group = group;
		this.tf = tf;
	}
	
	Tank (int x,int y, Dir dir,int speed,Group group,TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.speed = speed;
		this.group = group;
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
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
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
			g.drawImage(this.group == Group.good ? ResourceMagr.goodTankU :ResourceMagr.badTankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(this.group == Group.good ? ResourceMagr.goodTankD :ResourceMagr.badTankD, x, y, null);
			break;
		case LEFT:
			g.drawImage(this.group == Group.good ? ResourceMagr.goodTankL :ResourceMagr.badTankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(this.group == Group.good ? ResourceMagr.goodTankR :ResourceMagr.badTankR, x, y, null);
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
		if (this.group == Group.good) {
			new Thread(() -> new Audio("audio/tank_move.wav").loop()).start();
		}
		//只有敌人的坦克移动的时候才会自动发射子弹
		if (this.group == Group.bad) {
			if (random.nextInt(100) > 95) {	
				this.fire();
			}
		}
		//只有敌人的坦克才会随机换方向
		if (this.group == Group.bad && random.nextInt(20) > 17) {
			randomDir();
		}
	}
	
	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}
	public void fire() {
		int bx = x + width/2 - Bullet.width/2;
		int by = y + height/2 - Bullet.height/2;
		tf.bullets.add(new Bullet(bx, by, dir,this.group,tf));
		if (this.group == Group.good) {
			new Thread(() -> new Audio("audio/tank_fire.wav").loop()).start();
		}
	}
	public void die() {
		this.living = false;
		tf.explodes.add(new Explode(x, y - height/2, tf));
	}
	
	
}
