package com.shentu.tank.factory;

import com.shentu.tank.Bullet;
import com.shentu.tank.Dir;
import com.shentu.tank.Group;
import com.shentu.tank.Tank;
import com.shentu.tank.TankFrame;

public class EnemyFactory extends GeneralFactory {
	
	private static final EnemyFactory ef = new EnemyFactory();
	
	private EnemyFactory(){}
	
	public static EnemyFactory getInstance() {
		return ef;
	}

	@Override
	public TankModel creatTank(TankFrame tf) {
		
		return new Tank(100,50, Dir.DOWN,true,Group.bad, tf);
	}

	@Override
	public BulletModel creatBullet(TankFrame tf,Tank t) {
		// TODO Auto-generated method stub
		int bx = t.getX() + Tank.width/2 - Bullet.width/2;
		int by = t.getY() + Tank.height/2 - Bullet.height/2;
		return new Bullet(bx, by, t.getDir(),t.getGroup(),tf);
	}

	@Override
	public ExplodeModel creatExplode() {
		// TODO Auto-generated method stub
		return null;
	}

}
