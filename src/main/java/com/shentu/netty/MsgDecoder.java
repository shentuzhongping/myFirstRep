package com.shentu.netty;

import java.util.List;
import java.util.UUID;

import com.shentu.tank.Dir;
import com.shentu.tank.Group;
import com.shentu.tankChangeMsg.Msg;
import com.shentu.tankChangeMsg.MsgType;
import com.shentu.tankChangeMsg.TankJoinMsg;
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
		in.markReaderIndex();
		MsgType msgType = MsgType.values()[in.readInt()];
		int length = in.readInt();
		if (length > in.readableBytes()) {
			in.resetReaderIndex();
			return;
		}
		byte[] bytes = new byte[length];
		in.readBytes(bytes);
		Msg msg =
				(Msg) Class.forName("com.shentu.tankChangeMsg." + msgType.toString() + "Msg").getDeclaredConstructor().newInstance();
//		TankJoinMsg msg = new TankJoinMsg();
//		msg.x = in.readInt();
//		msg.y = in.readInt();
//		msg.dir = Dir.values()[in.readInt()];
//		msg.moving = in.readBoolean();
//		msg.group = Group.values()[in.readInt()];
//		msg.id = new UUID(in.readLong(),in.readLong());
		msg.parse(bytes);
		out.add(msg);
	}

}
