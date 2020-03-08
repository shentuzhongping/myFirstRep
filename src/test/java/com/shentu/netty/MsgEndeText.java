package com.shentu.netty;

import com.shentu.tank.Dir;
import com.shentu.tank.Group;
import com.shentu.tankChangeMsg.TankJoinMsg;
import org.junit.Assert;
import org.junit.Test;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

import java.util.UUID;

public class MsgEndeText {

	@Test
	public void test() {
		TankMsg tankMsg = new TankMsg(8,9);
		EmbeddedChannel ch = new EmbeddedChannel(new MsgEncoder(),new MsgDecoder());
		ch.writeOutbound(tankMsg);
		ByteBuf buf = ch.readOutbound();
		int x = buf.readInt();
		int y = buf.readInt();
		Assert.assertTrue(x == 8 && y == 9);
		buf.release();
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

}
