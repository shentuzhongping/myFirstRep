package com.shentu.netty;

import com.shentu.tankChangeMsg.TankJoinMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    public static final Client INSTANCE = new Client();

    private Client(){}

    Channel channel;
    public void connect() {
        EventLoopGroup group = new NioEventLoopGroup(1);

        Bootstrap b = new Bootstrap();
        ChannelFuture f = b.group(group).channel(NioSocketChannel.class)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                         @Override
                                         protected void initChannel(SocketChannel ch) throws Exception {
                                             System.out.println("channel初始化成功");
                                             ch.pipeline()
                                                     .addLast(new MsgDecoder())
                                                     .addLast(new MsgEncoder())
                                                     .addLast(new ClientChannelHandler());
                                         }
                                     }
                            )
                            .connect("localhost",8888);

        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    channel = future.channel();
                } else {
                    System.out.println("channel 连接失败");
                }
            }
        });
        try {
            f.sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void sendMsg(TankJoinMsg msg) {
        channel.writeAndFlush(msg);
    }

//    public static void main(String[] args) {
//        new Client().connect();
//    }
}
