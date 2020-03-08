package com.shentu.tank;


import com.shentu.netty.Client;

public class Main {
    public static void main(String[] args) {
        TankFrame tf = TankFrame.TANK_FRAME;
//        int tankCount = Integer.parseInt(PropertyMagr.get("initTankCount"));
//        for (int i = 0; i < tankCount; i++) {
//            tf.enemyTanks.add(new Tank(50 + i * 80, 50, Dir.DOWN, true, Group.bad, tf));
//        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tf.repaint();
            }
        }).start();

        new Client().connect();
    }

}
