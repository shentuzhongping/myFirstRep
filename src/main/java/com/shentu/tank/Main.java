package com.shentu.tank;


public class Main {
    public static void main(String[] args) {
        TankFrame tf = new TankFrame();

//        int tankCount = Integer.parseInt(PropertyMagr.get("initTankCount"));
//        for (int i = 0; i < tankCount; i++) {
//            tf.enemyTanks.add(new Tank(50 + i * 80, 50, Dir.DOWN, true, Group.bad, tf));
//        }

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            tf.repaint();
        }
    }

}
