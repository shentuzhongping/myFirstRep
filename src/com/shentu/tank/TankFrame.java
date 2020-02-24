package com.shentu.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
	int x = 200;
	int y = 200;
	
	TankFrame () {
		setSize(800,600);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		
		addKeyListener(new MyKeyMonitor());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
	}
	
	public void paint(Graphics g) {
		g.fillRect(x, y, 50, 50);
		y += 20;
	}
	
	class MyKeyMonitor extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			x += 20;
			repaint();
		}
	}

}
