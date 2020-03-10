package com.shentu.netty;

import com.shentu.tank.Bullet;
import com.shentu.tank.Dir;
import com.shentu.tank.Group;
import com.shentu.tankChangeMsg.*;
import org.junit.Assert;
import org.junit.Test;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

import java.util.UUID;

public class MsgEndeText {

	@Test
	public void test() {
	}
	
	@Test
	public void decoderText () {
		ByteBuf buf = Unpooled.buffer();
		EmbeddedChannel ch = new EmbeddedChannel(new MsgEncoder(),new MsgDecoder());
		TankJoinMsg tankMsg = new TankJoinMsg(5,10, Dir.UP,false, Group.good, UUID.randomUUID());
		buf.writeBytes(tankMsg.toBytes());
		ch.writeInbound(buf.duplicate());
		TankJoinMsg msg = ch.readInbound();
		System.out.println(msg);
		Assert.assertTrue(msg.x == 5 && msg.y == 10);
		
	}
	@Test
	public void tankMsgDecoderText1 () {
		EmbeddedChannel ch = new EmbeddedChannel(new MsgEncoder(),new MsgDecoder());
		UUID id = UUID.randomUUID();
//		TankStartMovingMsg msg = new TankStartMovingMsg(5,10,Dir.RIGHT,id);
//		TankDirChangedMsg msg = new TankDirChangedMsg(5,10,Dir.RIGHT,id);
		BulletNewMsg msg = new BulletNewMsg(5,10,Dir.RIGHT,Group.good,UUID.randomUUID(),UUID.randomUUID());
		ch.writeOutbound(msg);
		ByteBuf buf = ch.readOutbound();
//		Assert.assertTrue(MsgType.values()[buf.readInt()].equals(MsgType.TankStartMoving));
//		Assert.assertTrue(MsgType.values()[buf.readInt()].equals(MsgType.TankDirChanged));
		Assert.assertTrue(MsgType.values()[buf.readInt()].equals(MsgType.BulletNew));
		buf.readInt();
		Assert.assertTrue(buf.readInt() == 5);
		Assert.assertTrue(buf.readInt() == 10);
		Assert.assertTrue(Dir.values()[buf.readInt()].equals(Dir.RIGHT));
//		Assert.assertTrue(new UUID(buf.readLong(),buf.readLong()).equals(id));

		ByteBuf buf1 = Unpooled.buffer();
//		buf1.writeInt(MsgType.TankStartMoving.ordinal());
//		buf1.writeInt(MsgType.TankDirChanged.ordinal());
		buf1.writeInt(MsgType.BulletNew.ordinal());
		byte[] bytes = msg.toBytes();
		buf1.writeInt(bytes.length);
		buf1.writeBytes(bytes);
		ch.writeInbound(buf1.duplicate());
		Msg msg1 = ch.readInbound();
		System.out.println(msg1);
	}

}
