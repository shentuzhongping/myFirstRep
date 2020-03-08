package com.shentu.netty;

import com.shentu.tankChangeMsg.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<TankJoinMsg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, TankJoinMsg tankJoinMsg, ByteBuf buf) throws Exception {
		buf.writeBytes(tankJoinMsg.toBytes());
	}

}
