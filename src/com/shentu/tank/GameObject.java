package com.shentu.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	public int x;
	public int y;
	public int lastSeatX;
	public int lastSeatY;
	public Group group;
	public boolean living = true;
	
	public Rectangle rect = new Rectangle();
	
	public abstract void paint(Graphics g);

	public abstract void die();
	
}
