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
	private static final GameModel gm = new GameModel();
	
	private GameModel(){}
	
	public static GameModel getInstance() {
		return gm;
	}
	
	public List<GameObject> objects = new LinkedList<>();
	
	private ColliderChan chan = new ColliderChan();
	
	Tank tank = new Tank(200,200,Dir.UP,3,Group.good);
	
	
	public void paint(Graphics g) {

		tank.paint(g);
		
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			if (!o.living) {
				objects.remove(i);	
				i--;
			} else {
				o.paint(g);
			}
		}
		
		//碰撞检测
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i+1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
//				if ( !chan.collide(o1, o2)) {
//					System.out.println(objects.size());
//					i--;
//				}
				chan.collide(o1, o2);
			}
		}
		
	}
}
