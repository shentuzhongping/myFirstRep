package com.shentu.netty;

import com.shentu.tank.Dir;
import com.shentu.tank.Group;
import com.shentu.tank.Tank;
import com.shentu.tank.TankFrame;
import com.shentu.tankChangeMsg.TankJoinMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class ClientChannelHandler extends SimpleChannelInboundHandler<TankJoinMsg> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Tank t = TankFrame.TANK_FRAME.mainTank;
        TankFrame.TANK_FRAME.add(t);
        ctx.writeAndFlush(new TankJoinMsg(t));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TankJoinMsg msg) throws Exception {

        msg.handle();
        if (msg.id == TankFrame.TANK_FRAME.mainTank.id ||
                TankFrame.TANK_FRAME.findByUUID(msg.id)) return;
        System.out.println(msg);
        TankFrame.TANK_FRAME.add(new Tank(msg));
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.TANK_FRAME.mainTank));
    }
}
