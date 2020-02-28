package com.shentu.tank.strategy;

import com.shentu.tank.Bullet;
import com.shentu.tank.Dir;
import com.shentu.tank.Group;
import com.shentu.tank.Tank;
import com.shentu.tank.TankFrame;

public class FourDirFire implements FireStrategy {
	
	private static final FourDirFire sf = new FourDirFire();
	
	private FourDirFire(){
		
	}
	
	public static FourDirFire getInstance() {
		return sf;
	}
	@Override
	public void fire(Tank t) {
		int bx = t.getX() + t.width/2 - Bullet.width/2;
		int by = t.getY() + t.height/2 - Bullet.height/2;
		Bullet bU = new Bullet(bx,by,Dir.UP,Group.good);
		Bullet bD = new Bullet(bx,by,Dir.DOWN,Group.good);
		Bullet bL = new Bullet(bx,by,Dir.LEFT,Group.good);
		Bullet bR = new Bullet(bx,by,Dir.RIGHT,Group.good);
	}

}
