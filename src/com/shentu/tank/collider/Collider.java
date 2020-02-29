package com.shentu.tank.collider;

import com.shentu.tank.GameObject;

public interface Collider {
	
	boolean collide (GameObject o1,GameObject o2);
}
