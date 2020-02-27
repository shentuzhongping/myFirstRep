package com.shentu.tank.factory;

import java.awt.Graphics;

//定义抽象类还是接口，看类名是名词还是形容词（这是一个语义的问题）
public abstract class TankModel {
	public abstract void paint(Graphics g);
	
	public abstract void move();
	
	public abstract void fire();
	
	public abstract void die();
	
	
}
