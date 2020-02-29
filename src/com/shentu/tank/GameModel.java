package com.shentu.tank;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import com.shentu.tank.collider.ColliderChan;
/**
 * 添加门面设计模式，TankFrame只负责展现画面和接受指令
 * GameModel是TankFrame的门面，所有数据处理都交给这个门面（实体对象的数据改变）
 * 这是一种MVC设计模式：TankFrame是V,GameModel是M
 * @author Administrator
 *
 */
public class GameModel {
	private static final GameModel INSTANCE = new GameModel();
	
	static {	
		INSTANCE.init();
	}
	
	public List<GameObject> objects = new LinkedList<>();;
	
	private ColliderChan chan = new ColliderChan();
	
	Tank tank;
	
	private GameModel(){
	}
	
	public static GameModel getInstance() {
		return INSTANCE;
	}
	
	private void init() {
		tank = new Tank(200,200,Dir.UP,3,Group.good);
		
		int tankCount = Integer.parseInt(PropertyMagr.get("initTankCount"));
		for (int i = 0; i < tankCount; i++) {
			objects.add(new Tank(50 + i*80,50, Dir.DOWN,true,Group.bad));
		}
		
		new Wall(200,100,50,200);
		new Wall(500,100,50,200);
		new Wall(200,400,200,50);
		new Wall(500,400,200,50);
	}
	
	public void add(GameObject go) {
		objects.add(go);
	}
	
	public void remove(GameObject go) {
		objects.remove(go);
	}
	
	
	public void paint(Graphics g) {

		tank.paint(g);
		
		//碰撞检测
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i+1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				chan.collide(o1, o2);
			}
		}
		
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			if (!o.living) {
				objects.remove(i);	
				i--;
			} else {
				o.paint(g);
			}
		}
		
		
		
	}
}
