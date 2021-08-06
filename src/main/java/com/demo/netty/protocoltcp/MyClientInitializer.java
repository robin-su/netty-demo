package com.demo.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyMessageEncoder()); //加入编码器 MessageProtocol -> byte
        pipeline.addLast(new MyMessageDecoder()); //加入解码器 byte -> MessageProtocol
        pipeline.addLast(new MyClientHandlder()); // 加入自己的业务逻辑
    }
}
