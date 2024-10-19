package ru.isaevisa05.rpt.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import ru.isaevisa05.rpt.MyHandler;

import java.net.SocketAddress;
import java.util.HashMap;

public class Client {
    public static HashMap<SocketAddress, ClientSession> Sessions = new HashMap<>();
    private final String ip = "0.0.0.0";
    private final int port = 61000;
    public void Run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            Channel channel = future.channel();

            channel.closeFuture().sync();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            group.shutdownGracefully();
        }
    }
}
