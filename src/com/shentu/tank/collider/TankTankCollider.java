package com.shentu.tank.collider;

import com.shentu.tank.GameObject;
import com.shentu.tank.Tank;

public class TankTankCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Tank && o2 instanceof Tank) {
			if (o1.rect.intersects(o2.rect)) {
				Tank tank1 = (Tank)o1;
				Tank tank2 = (Tank)o2;
				tank1.back();
				tank2.back();
//				o1.x = o1.lastSeatX;o1.y = o1.lastSeatY;
//				o2.x = o2.lastSeatX;o2.y = o2.lastSeatY;
			}
			
		}
		return true;
	}

}
