package com.shentu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<TankMsg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, TankMsg tankMsg, ByteBuf buf) throws Exception {
		buf.writeInt(tankMsg.x);
		buf.writeInt(tankMsg.y);
	}

}
