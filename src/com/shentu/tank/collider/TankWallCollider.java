package com.shentu.tank.collider;

import com.shentu.tank.GameObject;
import com.shentu.tank.Tank;
import com.shentu.tank.Wall;

public class TankWallCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Tank && o2 instanceof Wall) {
			if (o1.rect.intersects(o2.rect)) {
				Tank tank = (Tank)o1;
				tank.back();
				return true;
			}
		} else if (o1 instanceof Wall && o2 instanceof Tank) {
			collide(o2,o1);
		}
		return true;
	}

}
