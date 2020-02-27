package com.shentu.tank.factory;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.shentu.tank.ResourceMagr;
import com.shentu.tank.Tank;

public abstract class BulletModel {
	
	Rectangle rect = new Rectangle(0,0,1,1);
	public static int width = ResourceMagr.bulletD.getWidth();
	public static int height = ResourceMagr.bulletD.getHeight();
	
	protected BulletModel(int x,int y) {
		this.rect.x = x + width/2;
		this.rect.y = y + height/2;
	}
	
	public abstract void paint(Graphics g);
	
	//TODU这里本来应该传的一个父类应用，但为了方便就先不改了
	public abstract void collideWith (TankModel t);
	
	public abstract void die();
	
	

}
