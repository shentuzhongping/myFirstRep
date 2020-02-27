package com.shentu.tank;
import com.shentu.tank.Bullet;
import com.shentu.tank.Dir;
import com.shentu.tank.FireStrategy;
import com.shentu.tank.Group;
import com.shentu.tank.Tank;
import com.shentu.tank.TankFrame;

public class DefaultFire implements FireStrategy {

	private static final DefaultFire df = new DefaultFire();
	private DefaultFire(){
		
	}
	public static DefaultFire getInstance() {
		return df;
	}
	@Override
	public void fire(Tank t) {
		TankFrame tf = t.getTf();
		int bx = t.getX() + t.width/2 - Bullet.width/2;
		int by = t.getY() + t.height/2 - Bullet.height/2;
		Bullet bU = new Bullet(bx,by,t.getDir(),t.getGroup(),tf);

	}

}
