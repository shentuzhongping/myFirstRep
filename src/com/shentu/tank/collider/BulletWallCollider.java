package com.shentu.tank.collider;

import com.shentu.tank.Bullet;
import com.shentu.tank.GameObject;
import com.shentu.tank.Wall;

public class BulletWallCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Bullet && o2 instanceof Wall) {
			if (o1.rect.intersects(o2.rect)) {
				o1.die();
				return true;
			}
		} else if (o1 instanceof Wall && o2 instanceof Bullet) {
			collide(o2,o1);
		}
		return true;
	}

}
