package com.shentu.tankChangeMsg;

import com.shentu.tank.Dir;
import com.shentu.tank.Tank;
import com.shentu.tank.TankFrame;

import java.io.*;
import java.util.UUID;

public class TankDirChangedMsg extends Msg{
    public int x;
    public int y;
    public Dir dir;
    public UUID id;


    public TankDirChangedMsg() {
    }


    public TankDirChangedMsg(int x, int y, Dir dir, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.id = id;
    }
    public TankDirChangedMsg(Tank t) {
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
        this.id = t.id;
    }
    @Override
    public byte[] toBytes () {
        ByteArrayOutputStream bis = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bis);
        byte[] bytes = null;
        try {
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
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

    @Override
    public void parse (byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(),dis.readLong());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankDirChanged;
    }

    @Override
    public String toString() {
        return "TankDirChangedMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", id=" + id +
                '}';
    }

    @Override
    public void handle() {
        if (this.id.equals(TankFrame.TANK_FRAME.mainTank.id)) return;
        System.out.println(this);
        Tank t = TankFrame.TANK_FRAME.findByUUID(this.id);
        if (t != null) {
            t.setDir(this.dir);
            t.setX(this.x);
            t.setY(this.y);
        }
    }
}
