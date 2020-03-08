package com.shentu.netty;

import com.shentu.tankChangeMsg.Msg;
import com.shentu.tankChangeMsg.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<Msg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf buf) throws Exception {
		buf.writeInt(msg.getMsgType().ordinal());
		byte[] bytes = msg.toBytes();
		buf.writeInt(bytes.length);
		buf.writeBytes(bytes);
	}

}
