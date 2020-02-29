package com.shentu.tank;


public class Main {
	public static void main(String[] args) {
		//将单例类加载进类存
		ResourceMagr resourceMagr = ResourceMagr.getInstance();
		
		TankFrame tf = new TankFrame();
		
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tf.repaint();
		}
	}

}
