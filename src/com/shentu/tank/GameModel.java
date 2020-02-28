package com.shentu.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 添加门面设计模式，TankFrame只负责展现画面和接受指令
 * GameModel是TankFrame的门面，所有数据处理都交给这个门面（实体对象的数据改变）
 * 这是一种MVC设计模式：TankFrame是V,GameModel是M
 * @author Administrator
 *
 */
public class GameModel {
	
	List<Bullet> bullets = new ArrayList<>();
	static List<Tank> enemyTanks = new ArrayList<>();
	static List<Explode> explodes = new ArrayList<>();
	
	Tank tank = new Tank(200,200,Dir.UP,3,Group.good,this);
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("子弹的数量:" + bullets.size(), 10, 60);
		g.drawString("敌人的数量:" + enemyTanks.size(), 10, 80);
		g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
		g.setColor(c);
		tank.paint(g);
		
		//画出剩余的敌人坦克
		for (Iterator i = enemyTanks.iterator(); i.hasNext();) {
			Tank tank = (Tank) i.next();
			if (!tank.isLiving()) {
				i.remove();
				continue;
			}
			tank.paint(g);
		}
		//画出剩余的子弹
		for (Iterator i = bullets.iterator(); i.hasNext();) {
			Bullet bullet = (Bullet) i.next();
			if (!bullet.isLiving()) {
				i.remove();
				continue;
			}
			
			bullet.paint(g);
		}
		
		//碰撞检测
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < enemyTanks.size(); j++) {
				bullets.get(i).collideWith(enemyTanks.get(j));
			}
		}
		//画出爆炸的动画
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
	}
}
