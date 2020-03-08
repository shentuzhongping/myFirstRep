package com.shentu.netty;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MsgDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		//Tcp拆包粘包问题
		if (in.readableBytes() < 8) {
			return;
		}
		int x = in.readInt();
		int y = in.readInt();
		out.add(new TankMsg(x,y));
	}

}
