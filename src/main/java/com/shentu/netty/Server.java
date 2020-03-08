package com.shentu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup workers = new NioEventLoopGroup(2);
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        ChannelFuture f = null;
        try {
            f = serverBootstrap.group(boss,workers)
                                .channel(NioServerSocketChannel.class)
                                .childHandler(new ChannelInitializer<SocketChannel>() {
                                    @Override
                                    protected void initChannel(SocketChannel ch) throws Exception {
//                                        System.out.println("A Client has connected");
//                                        clients.add(ch);
//                                        System.out.println("已经连接的客户端数量" + clients.size());
                                        System.out.println("初始化成功");
                                        ChannelPipeline pl = ch.pipeline();
//                                        pl.addLast(new MsgDecoder());
//                                        pl.addLast(new MsgEncoder());
                                        pl.addLast(new ServerChannelHandler());
                                    }
                                })
                                .bind(8888)
                                .sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            workers.shutdownGracefully();
        }
    }
}
