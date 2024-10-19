package ru.isaevisa05.rpt;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import ru.isaevisa05.rpt.tunel.Tunnel;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main { //Test class

    public static Channel RPCChannel = null;
    public static HashMap<SocketAddress, Channel> Sessions = new HashMap<>();

    public static ServerBootstrap server;

    static EventLoopGroup l1 = new NioEventLoopGroup(1);
    static EventLoopGroup l2 = new NioEventLoopGroup(1);

    public static void main(String[] args) {
        Tunnel tunnel = new Tunnel();
        tunnel.Run();
        /*server = new ServerBootstrap();
        server.group(l1, l2);
        server.channel(NioServerSocketChannel.class);

        server.childHandler(new MyHandler());

        server.option(ChannelOption.SO_BACKLOG, 128);

        server.bind("0.0.0.0", 61000).sync();

        ChannelFuture f = server.bind().sync();

        f.channel().closeFuture().sync();


        ByteArrayEncoder byteArrayEncoder = new ByteArrayEncoder();
        ByteArrayDecoder byteArrayDecoder = new ByteArrayDecoder();*/
    }
}
