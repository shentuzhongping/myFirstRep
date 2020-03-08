package com.shentu.netty;

import com.shentu.tank.Dir;
import com.shentu.tank.Group;
import com.shentu.tank.Tank;
import com.shentu.tank.TankFrame;
import com.shentu.tankChangeMsg.Msg;
import com.shentu.tankChangeMsg.TankJoinMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class ClientChannelHandler extends SimpleChannelInboundHandler<Msg> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Tank t = TankFrame.TANK_FRAME.mainTank;
        TankFrame.TANK_FRAME.add(t);
        ctx.writeAndFlush(new TankJoinMsg(t));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
        msg.handle();
    }
}
