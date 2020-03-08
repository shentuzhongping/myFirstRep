package com.shentu.netty;

import org.junit.Assert;
import org.junit.Test;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

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
		TankMsg tankMsg = new TankMsg(7,8);
		buf.writeInt(tankMsg.x);
		buf.writeInt(tankMsg.y);
		ch.writeInbound(buf.duplicate());
		TankMsg msg = ch.readInbound();
		Assert.assertTrue(msg.x == 7 && msg.y == 8);
		
	}

}
