package com.shentu.tank.collider;

import com.shentu.tank.Bullet;
import com.shentu.tank.GameObject;
import com.shentu.tank.Tank;

public class TankBulletCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Bullet && o2 instanceof Tank) {
			if (o1.group == o2.group) {
				return true;
			}
//			Rectangle rect1 = new Rectangle(t.getX(),t.getY(),t.width,t.height);
//			Rectangle rect2 = new Rectangle(x+width/2,y+height/2,1,1);
			if (o1.rect.intersects(o2.rect)) {
				o1.die();
				o2.die();
				return false;
			}
		} else if (o1 instanceof Tank && o2 instanceof Bullet) {
			collide(o2,o1);
		}
		
		return true;

	}

}
