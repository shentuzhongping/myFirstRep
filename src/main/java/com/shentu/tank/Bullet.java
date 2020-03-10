package com.shentu.tank;

import com.shentu.netty.Client;
import com.shentu.tankChangeMsg.BulletNewMsg;
import com.shentu.tankChangeMsg.TankDieMsg;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.UUID;

public class Bullet {
	private int x;
	private int y;
	private Dir dir;
	private static final int speed = 10;
	private boolean living = true;
	private Group group;
	public UUID id;
	public UUID playerId;
	public boolean isLiving() {
		return living;
	}
	public boolean getLiving() {
		return living;
	}

	public static int width = ResourceMagr.bulletD.getWidth();
	public static int height = ResourceMagr.bulletD.getHeight();
	
	Rectangle rect = new Rectangle(0,0,1,1);
	
	public Bullet (int x, int y, Dir dir,Group group) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.id = UUID.randomUUID();
		this.playerId = TankFrame.TANK_FRAME.mainTank.id;
		this.rect.x = x + width/2;
		this.rect.y = y + height/2;

	}

	public Bullet(BulletNewMsg msg) {
		this.x = msg.x;
		this.y = msg.y;
		this.dir = msg.dir;
		this.group = msg.group;
		this.id = msg.id;
		this.playerId = msg.playerID;
		this.rect.x = msg.x + width/2;
		this.rect.y = msg.y + height/2;
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
		}
		this.rect.x = x + width/2;
		this.rect.y = y + height/2;
	}
	
	public void die() {
//		System.out.print("消失的子弹：");
//		System.out.println(this.playerId);
		this.living = false;
	}
	
	
	public void collideWith (Tank tank) {
//		System.out.print("用于碰撞检测的子弹：");
//		System.out.println(this.playerId);
		if(this.playerId.equals(tank.id)) {
			return;
		}

		if(this.living && tank.isLiving() && this.rect.intersects(tank.rect)) {
			tank.die();
			this.die();
			Client.INSTANCE.sendMsg(new TankDieMsg(this.id, tank.id));
		}
	}

	@Override
	public String toString() {
		return "Bullet{" +
				"x=" + x +
				", y=" + y +
				", dir=" + dir +
				", living=" + living +
				", group=" + group +
				", id=" + id +
				", playerId=" + playerId +
				", rect=" + rect +
				'}';
	}
}
