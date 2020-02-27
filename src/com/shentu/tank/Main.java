package com.shentu.tank;

import com.shentu.tank.factory.EnemyFactory;

public class Main {
	public static void main(String[] args) {
		//将单例类加载进类存
		ResourceMagr resourceMagr = ResourceMagr.getInstance();
		
		TankFrame tf = new TankFrame();
		
		EnemyFactory ef = EnemyFactory.getInstance();
		
		int tankCount = Integer.parseInt(PropertyMagr.get("initTankCount"));
		for (int i = 0; i < tankCount; i++) {
			tf.enemyTanks.add(ef.creatTank(tf));
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
