package com.shentu.tank.collider;

import java.util.LinkedList;
import java.util.List;

import com.shentu.tank.GameObject;

public class ColliderChan{
	
	private List<Collider> colliders = new LinkedList<>();

	public ColliderChan () {
		addCollider(new TankBulletCollider());
		addCollider(new TankTankCollider());
	}
	
	public void addCollider(Collider c) {
		colliders.add(c);
	}
	
	public boolean collide(GameObject o1, GameObject o2) {
		for (int i = 0; i < colliders.size(); i++) {
			if (!colliders.get(i).collide(o1, o2)) {
				return false;
			}
		}
		return true;
	}

}
