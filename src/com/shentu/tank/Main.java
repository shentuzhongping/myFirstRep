package com.shentu.tank;


public class Main {
	public static void main(String[] args) {
		TankFrame tf = new TankFrame();
		for (int i = 0; i < 5; i++) {
			tf.enemyTanks.add(new Tank(50 + i*70,50, Dir.DOWN,true,Group.bad, tf));
		}
		
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
