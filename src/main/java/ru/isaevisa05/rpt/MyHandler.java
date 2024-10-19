package ru.isaevisa05.rpt;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.HashMap;

public class MyHandler extends ChannelInboundHandlerAdapter {
    private String rpcKey = "privet123";
    //ipv true = ipv4 false = ipv6
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {
        if(msg instanceof Data data) {
            if(Main.RPCChannel == null) {
                if(data.getValue("rpc") == null) return;
                String otvet = Arrays.toString(data.getMessage());
                if(otvet != null && otvet.equals(rpcKey)) {
                    Main.RPCChannel = ctx.channel();
                return;}
                return;}
            SocketAddress address = data.getValue("ip");
            Channel channel = Main.Sessions.get(address);
            channel.writeAndFlush(data.getMessage());
        return;}
        HashMap<String, Object> putData = new HashMap<>();
        putData.put("ip", ctx.channel().remoteAddress());

        ByteArrayOutputStream bos = new ByteArrayOutputStream(); //Convert to byte[]
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();

        Data sendData = new Data(putData, bytes);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Main.Sessions.put(ctx.channel().remoteAddress(), ctx.channel());
        if(Main.RPCChannel == null) return;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Main.Sessions.remove(ctx.channel().remoteAddress());
        if(Main.RPCChannel == null) return;
    }

    private void read(String s) {
        System.out.println(s);
    }
}
