package com.shentu.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Server.clients.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
//        cause.printStackTrace();
        Server.clients.remove(ctx.channel());
        System.out.println("现在已经连接的客户端数量" + Server.clients.size());
        ctx.close();
    }
}
