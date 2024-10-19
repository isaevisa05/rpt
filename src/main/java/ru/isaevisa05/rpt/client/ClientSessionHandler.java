package ru.isaevisa05.rpt.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientSessionHandler extends ChannelInboundHandlerAdapter {
    public ClientSession session;
    public ClientSessionHandler(ClientSession session) {
        this.session = session;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
