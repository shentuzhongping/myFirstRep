package com.shentu.tank;

import java.awt.Graphics;

public class Explode {
	private int x;
	private int y;
	private TankFrame tf;
//	private boolean living = true;
	public static int width = ResourceMagr.explodes[0].getWidth();
	public static int height = ResourceMagr.explodes[0].getHeight();
	
	private int step = 0;
	
	public Explode(int x,int y,TankFrame tf) { 
		this.x = x;
		this.y = y;
		this.tf = tf;
//		new Audio("audio/explode.wav").loop();
	}
	
	public void paint(Graphics g) {
		if (step == 0) {
			new Thread(() -> new Audio("audio/explode.wav").loop()).start();
		}
		g.drawImage(ResourceMagr.explodes[step++], x, y, null);
		if (step >= ResourceMagr.explodes.length ) {
			tf.explodes.remove(this);
		}
		
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

//	public boolean isLiving() {
//		return living;
//	}
//
//	public void setLiving(boolean living) {
//		this.living = living;
//	}
	
	
}
