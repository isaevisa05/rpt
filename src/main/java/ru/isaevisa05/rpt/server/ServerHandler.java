package ru.isaevisa05.rpt.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ru.isaevisa05.rpt.IPForwarding;
import ru.isaevisa05.rpt.tunel.Tunnel;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.Sessions.put(ctx.channel().remoteAddress(), ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Server.Sessions.remove(ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Tunnel.client.writeAndFlush(new IPForwarding(ctx.channel().remoteAddress(), msg));
    }
}
