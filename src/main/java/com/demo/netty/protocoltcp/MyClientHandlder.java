package com.demo.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.stream.IntStream;

public class MyClientHandlder extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    private static final String MESSAGE_CONTENT = "今天天气冷，吃火锅";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        IntStream.range(0,5).forEach(i -> {
            byte[] content = MESSAGE_CONTENT.getBytes(Charset.forName("utf-8"));
            int length = content.length;
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setContent(content);
            messageProtocol.setLen(length);
            ctx.writeAndFlush(messageProtocol);
        });
    }


    /**
     * 服务端向客户端返回的数据，客户端在该方法中读取
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();

        System.out.println("客户端接收到消息如下");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, Charset.forName("utf-8")));

        System.out.println("客户端接收消息数量=" + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常消息=" + cause.getMessage());
        ctx.close();
    }
}
