package com.shentu.tank.decorater;

import java.awt.Color;
import java.awt.Graphics;

import com.shentu.tank.GameObject;

public class RectDecorater extends GoDecorater {

	public RectDecorater(GameObject go) {
		super(go);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		x = go.x;
		y = go.y;
		
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 3, 3);
		g.setColor(c);
	}
	
	public void die() {
		super.living = false;
	}

}
