package com.shentu.tank;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends GameObject {
	
	public int width;
	public int height;
	public GameModel gm = GameModel.getInstance();
	Wall(int x,int y,int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect.x = x;
		rect.y = y;
		rect.width = width;
		rect.height = height;
		gm.add(this);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		g.setColor(c);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	
}
