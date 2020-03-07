package com.shentu.tank.decorater;

import java.awt.Color;
import java.awt.Graphics;

import com.shentu.tank.GameObject;

public class TailDecorater extends GoDecorater {

	public TailDecorater(GameObject go) {
		super(go);
		// TODO Auto-generated constructor stub
	}
	
	public void paint(Graphics g) {
		x = go.x;
		y = go.y;
		go.paint(g);
		Color c = g.getColor();
		g.setColor(Color.yellow);
		g.drawLine(x, y, x+10, y+10);
		g.setColor(c);
	}
	
	public void die() {
		super.living = false;
	}

}
