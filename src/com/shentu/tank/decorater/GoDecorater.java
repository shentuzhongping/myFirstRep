package com.shentu.tank.decorater;

import java.awt.Graphics;

import com.shentu.tank.GameObject;

public class GoDecorater extends GameObject {
	
	GameObject go;
	GoDecorater(GameObject go) {
		this.go = go;
	}
	@Override
	public void paint(Graphics g) {
		go.paint(g);

	}

	@Override
	public void die() {
		go.die();

	}

}
