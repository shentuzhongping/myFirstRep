package com.shentu.tankChangeMsg;

import com.shentu.netty.Client;
import com.shentu.tank.Dir;
import com.shentu.tank.Group;
import com.shentu.tank.Tank;
import com.shentu.tank.TankFrame;

import java.io.*;
import java.util.UUID;

public class TankJoinMsg {
    public int x;
    public int y;
    public Dir dir;
    public boolean moving;
    public Group group;
    public UUID id;


    public TankJoinMsg() {
    }


    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = id;
    }
    public TankJoinMsg(Tank t) {
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
        this.moving = t.isMoving();
        this.group = t.getGroup();
        this.id = t.id;
    }
    public byte[] toBytes () {
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bis);
        byte[] bytes = null;
        try {
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = bis.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;

    }

    public void parse (byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        DataInputStream dis = new DataInputStream(bis);
        try {
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.group = Group.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(),dis.readLong());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", moving=" + moving +
                ", group=" + group +
                ", id=" + id +
                '}';
    }

    public void handle() {
        if (this.id == TankFrame.TANK_FRAME.mainTank.id ||
                TankFrame.TANK_FRAME.findByUUID(this.id)) return;
        System.out.println(this);
        TankFrame.TANK_FRAME.add(new Tank(this));
        Client.INSTANCE.sendMsg(new TankJoinMsg(TankFrame.TANK_FRAME.mainTank));
    }

//    public static void main(String[] args) {
//        System.out.println(new TankJoinMsg(5,10,Dir.UP,false,Group.good,UUID.randomUUID()).toBytes().length);
//
//    }
}
