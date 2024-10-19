package ru.isaevisa05.rpt.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.unix.Socket;
import ru.isaevisa05.rpt.client.ClientSession;
import ru.isaevisa05.rpt.tunel.Tunnel;
import ru.isaevisa05.rpt.tunel.TunnelInitializer;

import java.net.SocketAddress;
import java.util.HashMap;

public class Server {
    public static HashMap<SocketAddress, Channel> Sessions = new HashMap<>();
    private final String ip = "0.0.0.0";
    private final int port = 61000;
    public void Run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer());

            ChannelFuture future = server.bind(ip, port).sync();
            Channel channel = future.channel();

            channel.closeFuture().sync();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.Run();
    }
}
