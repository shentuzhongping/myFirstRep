package com.shentu.tankChangeMsg;

import com.shentu.tank.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.UUID;

public class BulletNewMsg extends Msg {
    public int x;
    public int y;
    public Dir dir;
    public Group group;
    public UUID id;
    public UUID playerID;
    public BulletNewMsg(){}
    public BulletNewMsg(int x,int y,Dir dir,Group group,UUID id,UUID playerID) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.id = id;
        this.playerID = playerID;
    }
    public BulletNewMsg (Bullet bullet) {
        this.playerID = bullet.playerId;
        this.id = bullet.id;
        this.x = bullet.getX();
        this.y = bullet.getY();
        this.dir = bullet.getDir();
        this.group = bullet.getGroup();
    }
    @Override
    public void handle() {
//        System.out.print("接受到的子弹");
//        System.out.println(this);
        if (this.playerID.equals(TankFrame.TANK_FRAME.mainTank.id))
            return;

        Bullet bullet = new Bullet(this);

        TankFrame.TANK_FRAME.addBullet(bullet);
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        byte[] bytes = null;
        try {
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeInt(group.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeLong(playerID.getMostSignificantBits());
            dos.writeLong(playerID.getLeastSignificantBits());
            dos.flush();
            bytes = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null){
                    bos.close();
                }
                if (dos != null) {
                    dos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.group = Group.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(),dis.readLong());
            this.playerID = new UUID(dis.readLong(),dis.readLong());
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
        return MsgType.BulletNew;
    }

    @Override
    public String toString() {
        return "BulletNewMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", id=" + id +
                ", playerID=" + playerID +
                '}';
    }
}
