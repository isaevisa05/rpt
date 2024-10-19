package ru.isaevisa05.rpt.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ClientSessionInitializer extends ChannelInitializer<Channel> {
    public ClientSession session;
    public ClientSessionInitializer(ClientSession session) {
        this.session = session;
    }
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new ClientSessionHandler(session));
    }
}
