package ru.isaevisa05.rpt.tunel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ru.isaevisa05.rpt.Settings;

public class TunnelHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(Tunnel.client == null) {
            if(!(msg instanceof String s)) return;
            if(s == null) return;
            if(!s.equals(Settings.key)) return;
            Tunnel.client = ctx.channel();
        return;}
    }
}
