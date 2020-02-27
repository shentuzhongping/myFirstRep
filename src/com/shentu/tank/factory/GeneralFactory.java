package com.shentu.tank.factory;

import com.shentu.tank.Tank;
import com.shentu.tank.TankFrame;

public abstract class GeneralFactory {
	
	public abstract TankModel creatTank(TankFrame tf);
	
	public abstract BulletModel creatBullet(TankFrame tf,Tank t);
	
	public abstract ExplodeModel creatExplode();

}
