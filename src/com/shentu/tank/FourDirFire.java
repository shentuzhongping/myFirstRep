package com.shentu.tank;

public class FourDirFire implements FireStrategy {
	
	private static final FourDirFire sf = new FourDirFire();
	
	private FourDirFire(){
		
	}
	
	public static FourDirFire getInstance() {
		return sf;
	}
	@Override
	public void fire(Tank t) {
		TankFrame tf = t.getTf();
		int bx = t.getX() + t.width/2 - Bullet.width/2;
		int by = t.getY() + t.height/2 - Bullet.height/2;
		Bullet bU = new Bullet(bx,by,Dir.UP,Group.good,tf);
		Bullet bD = new Bullet(bx,by,Dir.DOWN,Group.good,tf);
		Bullet bL = new Bullet(bx,by,Dir.LEFT,Group.good,tf);
		Bullet bR = new Bullet(bx,by,Dir.RIGHT,Group.good,tf);
	}

}
