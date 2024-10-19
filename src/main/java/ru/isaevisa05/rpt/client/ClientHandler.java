package ru.isaevisa05.rpt.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import ru.isaevisa05.rpt.IPForwarding;

import java.util.concurrent.Callable;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        IPForwarding forwarding = (IPForwarding) msg;
        forwarding.getAddress();
        forwarding.getMsg();
        switch (forwarding.getTo()) {
            case "join":
                Client.Sessions.put(forwarding.getAddress(), new ClientSession(ctx.channel()));
                return;
            case "quit":
                return;
            case null: //Общение

                return;
            default:
                return;
        }
    }
}
