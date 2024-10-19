package ru.isaevisa05.rpt.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientSession {
    private Channel MainChannel;
    public Channel channel;
    public ClientSession(Channel MainChannel) {
        this.MainChannel = MainChannel;

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientSessionInitializer(this));
            ChannelFuture future = bootstrap.connect("0.0.0.0", 25565).sync();
            channel = future.channel();

            channel.closeFuture().sync();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            group.shutdownGracefully();
        }
    }
}
