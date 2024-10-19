package ru.isaevisa05.rpt;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("byteArrayEncoder", new ByteArrayEncoder());
        pipeline.addLast("byteArrayDecoder", new ByteArrayDecoder());

        pipeline.addLast("MyHandler", new MyHandler());
    }
}
