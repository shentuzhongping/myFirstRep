package com.shentu.tank;

import com.shentu.netty.Client;
import com.shentu.tankChangeMsg.BulletNewMsg;
import com.shentu.tankChangeMsg.TankJoinMsg;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.UUID;

public class Tank {
    private int x;
    private int y;
    private Dir dir;
    private boolean moving = false;

    public UUID id;

    private Group group;

    private int speed = 2;

    private Random random = new Random();

    public static int width = ResourceMagr.badTankD.getWidth();
    public static int height = ResourceMagr.badTankD.getHeight();

    private boolean living = true;

    public boolean isLiving() {
        return living;
    }

    public boolean getLiving() {
        return living;
    }

    Rectangle rect = new Rectangle(0, 0, width, height);

    Tank(int x, int y, Dir dir, boolean moving, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = UUID.randomUUID();
        this.rect.x = x;
        this.rect.y = y;
    }
    public Tank (TankJoinMsg msg) {
        this.x = msg.x;
        this.y = msg.y;
        this.dir = msg.dir;
        this.moving = msg.moving;
        this.group = msg.group;
        this.id = msg.id;
        this.rect.x = msg.x;
        this.rect.y = msg.y;
    }

//    Tank(int x, int y, Dir dir, int speed, Group group, TankFrame tf) {
//        this.x = x;
//        this.y = y;
//        this.dir = dir;
//        this.speed = speed;
//        this.group = group;
//        this.tf = tf;
//        this.rect.x = x;
//        this.rect.y = y;
//    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case UP:
                g.drawImage(this.group == Group.good ? ResourceMagr.goodTankU : ResourceMagr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.good ? ResourceMagr.goodTankD : ResourceMagr.badTankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.good ? ResourceMagr.goodTankL : ResourceMagr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.good ? ResourceMagr.goodTankR : ResourceMagr.badTankR, x, y, null);
                break;
            default:
                break;
        }
        move();
    }


    public void move() {
        if (!moving) return;
        switch (dir) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            default:
                break;
        }
//        if (this.group == Group.good) {
//            new Thread(() -> new Audio("audio/tank_move.wav").loop()).start();
//        }
//        //只有敌人的坦克移动的时候才会自动发射子弹
//        if (this.group == Group.bad && random.nextInt(100) > 95) {
//            this.fire();
//        }
//        //只有敌人的坦克才会随机换方向
//        if (this.group == Group.bad && random.nextInt(100) > 95) {
//            randomDir();
//        }

        boundsCheck();
        this.rect.x = x;
        this.rect.y = y;

    }

    private void boundsCheck() {
        if (x < 2) {
            x = 2;
        }
        if (x > TankFrame.GAME_WIDTH - width) {
            x = TankFrame.GAME_WIDTH - width;
        }
        if (y < height / 2) {
            y = height / 2;
        }
        if (y > TankFrame.GAME_HEIGHT - height) {
            y = TankFrame.GAME_HEIGHT - height;
        }

    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx = x + width / 2 - Bullet.width / 2;
        int by = y + height / 2 - Bullet.height / 2;
        Bullet bullet = new Bullet(bx, by, dir, this.group);
        TankFrame.bullets.add(bullet);
//        System.out.print("刚生成的子弹：");
//        System.out.println(bullet);
        Client.INSTANCE.sendMsg(new BulletNewMsg(bullet));
//        if (this.group == Group.good) {
//            new Thread(() -> new Audio("audio/tank_fire.wav").loop()).start();
//        }
    }

    public void die() {
        this.living = false;
//        System.out.print("死掉的坦克");
//        System.out.println(this.id);

        TankFrame.explodes.add(new Explode(x, y - height / 2, TankFrame.TANK_FRAME));
    }

    @Override
    public String toString() {
        return "Tank{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", moving=" + moving +
                ", id=" + id +
                ", group=" + group +
                ", speed=" + speed +
                ", random=" + random +
                ", living=" + living +
                ", rect=" + rect +
                '}';
    }
}
